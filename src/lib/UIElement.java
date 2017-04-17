package lib;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JComponent;

public abstract class UIElement extends JComponent {
	protected String m_text;
	
//	public UIElement() {
//
//	}
	
	/*
	 * Method to be implemented that draws this UIElement to the screen
	 */
	public abstract void draw(Graphics2D g);
	
	/*
	 * Method to be implemented that converts this UIElement into working source
	 * code to be used by its intended platform
	 */
	public abstract String generateSourceCode();
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		draw(g2d);
	}
	
	public String getText() {
		return m_text;
	}
	
	public void setX(int x) {
		Point loc = getLocation();
		setLocation(x, loc.y);
	}
	
	public void setY(int y) {
		Point loc = getLocation();
		setLocation(loc.x, y);
	}
	
	public void setWidth(int width) {
		setSize(width, getHeight());
	}
	
	public void setHeight(int height) {
		setSize(getWidth(), height);
	}
	
	public void setText(String text) {
		m_text = text;
	}
}
