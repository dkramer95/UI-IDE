package client;

import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import lib.UIElement;

public class JUIElement extends UIElement {
	// underlying java component that we're wrapping
	protected JComponent m_component;
	
	protected JUIElement() { 
		
	}
	
	public JUIElement(JComponent comp) {
		m_component = comp;
		m_component.setLayout(null);
		init();
	}
	
	protected void init() {
		// default starting size
		setWidth(100);
		setHeight(25);
		setText(m_component.getClass().getSimpleName());
	}
	
	public void setLocation(Point p) {
		super.setLocation(p);
		m_component.setLocation(p);
	}
	
	public boolean contains(Point p) {
		return m_component.getBounds().contains(p);
	}
	
	public void setX(int x) {
		setLocation(x, getLocation().y);
		m_component.setLocation(getLocation());
	}

	public void setY(int y) {
		setLocation(getLocation().x, y);
		m_component.setLocation(getLocation());
	}
	
	public void setWidth(int width) {
		setSize(width, getHeight());
		m_component.setSize(getSize());
	}
	
	public void setHeight(int height) {
		setSize(getWidth(), height);
		m_component.setSize(getSize());
	}
	
	public void draw(Graphics2D g) {
		g.draw(m_component.getBounds());
		g.translate(getX(), getY());
		m_component.paint(g);
		g.translate(-getX(), -getY());
	}
	
	public void setText(String text) {
		super.setText(text);

		// base JComponent does not have setText property
		// so we have to go the ugly route here, otherwise we would
		// have to create multiple subclasses....
		
		if (m_component instanceof JButton) {
			JButton btn = (JButton)m_component;
			btn.setText(text);
		} else if (m_component instanceof JLabel) {
			JLabel label = (JLabel)m_component;
			label.setText(text);
		} else if (m_component instanceof JTextField) {
			JTextField textField = (JTextField)m_component;
			textField.setText(text);
		} else if (m_component instanceof JCheckBox) {
			JCheckBox checkBox = (JCheckBox)m_component;
			checkBox.setText(text);
		}
	}
	
	// to allow us to create unique varnames
	protected static int elementCounter = 1;

	@Override
	public String generateSourceCode() {
		StringBuilder sb = new StringBuilder();
		
		// object instantiation
		String className = m_component.getClass().getSimpleName();
		String varName = className.toLowerCase() + elementCounter;
		sb.append(String.format("%s %s = new %s();\n", className, varName, className));
		
		// attributes
		sb.append(String.format("%s.setSize(%d, %d);\n", varName, getWidth(), getHeight()));
		sb.append(String.format("%s.setLocation(%d, %d);\n", varName, getX(), getY()));
		sb.append(String.format("%s.setBackground(%s);\n", varName, getColor()));
		sb.append(String.format("%s.setText(\"%s\");\n", varName, getText()));
		sb.append(String.format("%s.setBounds(%s.getX(), %s.getY(), %s.getWidth(), "
				+ "%s.getHeight());", varName, varName, varName, varName, varName));
		
		// this is fragile and will break if JavaWriter is changed, but it will work for now
		sb.append(String.format("%s.add(%s);", "mainPanel", varName));
		sb.append(String.format("%s.add(%s);", "elements", varName));
		sb.append("\n");
		
		// very important to increment, to prevent duplicate varnames in output!!
		++elementCounter;

		return sb.toString();
	}

}
