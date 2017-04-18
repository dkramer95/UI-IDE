package client;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import lib.UIElement;

public class JUIElement extends UIElement {
	// underlying java component that we're wrapping
	protected JComponent m_component;
	
	public JUIElement(JComponent comp) {
		m_component = comp;
		m_component.setLayout(null);
		init();
	}
	
	protected void init() {
		// default starting size
		setWidth(100);
		setHeight(25);
		setText("Test");
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
		}else if (m_component instanceof JTextField) {
			JTextField textField = (JTextField)m_component;
			textField.setText(text);
		}
	}

	@Override
	public String generateSourceCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
