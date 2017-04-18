package client;

import java.awt.Graphics2D;
import java.awt.Point;

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
	
	public boolean contains(Point p) {
		return getBounds().contains(p);
	}

	@Override
	public String generateSourceCode() {
		StringBuilder sb = new StringBuilder();
		sb.append("<" + m_tagName + " ");
		sb.append(" style=\"position: absolute;");
		int width = getWidth();
		sb.append("width=" + width + "px;");
		
		int height = getHeight();
		sb.append("height=" + height + "px;");
		
		int x = getX();
		sb.append("left=" + x + "px;");
		int y = getY();
		sb.append("top="+ y + "px;\"");
		
		sb.append(">" + getText() + "</" + m_tagName + ">");
		
		System.out.println("BUILD SOURCE CODE: " + sb.toString());
		
		// TODO Auto-generated method stub
		return sb.toString();
	}

}
