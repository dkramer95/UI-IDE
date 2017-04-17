package client;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JComponent;

import lib.UIElement;

public class HTMLElement extends UIElement {

	protected JComponent m_component;
	
	//Constructor
	public HTMLElement(JComponent comp) {
		m_component = comp;
		m_component.setLayout(null);
	}
	
	protected void init() {
		setWidth(50);
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
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.draw(m_component.getBounds());
		g.translate(getX(), getY());
		m_component.paint(g);
		g.translate(-getX(),-getY());
	}

	@Override
	public String generateSourceCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
