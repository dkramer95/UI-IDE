package client;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JComponent;

import lib.UIElement;

public class HTMLElement extends UIElement {
	protected String m_tagName;
	
	public HTMLElement(String tagName) {
		m_tagName = tagName;
		init();
	}
	
	protected void init() {
		setWidth(100);
		setHeight(25);
		setText("I am an html element");
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.drawString(getText(), getX(), getY());
		g.draw(getBounds());
	}

	@Override
	public String generateSourceCode() {
		StringBuilder sb = new StringBuilder();
		sb.append("<" + m_tagName + " ");
		
		int width = getWidth();
		sb.append("width=" + width + " ");
		
		int height = getHeight();
		sb.append("height=" + height + " ");
		
		int x = getX();
		sb.append("x=" + x + " ");
		
		sb.append("/>");
		
		System.out.println("BUILD SOURCE CODE: " + sb.toString());
		
		// TODO Auto-generated method stub
		return sb.toString();
	}

}
