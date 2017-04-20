package client;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;

import javax.swing.JLabel;

public class HTMLElement extends JUIElement {
	protected String m_tagName;
	
	
	public HTMLElement(String tagName) {
		super(new JLabel());
		m_tagName = tagName;
		init();
	}
	
	protected void init() {
		setWidth(100);
		setHeight(25);
		setText("I am an html element");
	}
	
	public Rectangle getStringBounds(Graphics2D g2d) {
		FontRenderContext frc = g2d.getFontRenderContext();
		GlyphVector gv = g2d.getFont().createGlyphVector(frc, getText());
		Rectangle r = gv.getPixelBounds(null, getX(), getY());
		return r;
	}

	@Override
	public String generateSourceCode() {
		StringBuilder sb = new StringBuilder();
		sb.append("<" + m_tagName + " ");
		sb.append("class=\"absolute\" style=\"margin=0 1.5%;");
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
