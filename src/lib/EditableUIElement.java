package lib;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class EditableUIElement extends UIElement {
	protected UIElement m_element;

	protected boolean m_isSelected;
	
	public EditableUIElement(UIElement element) {
		m_element = element;
	}

	@Override
	public void draw(Graphics2D g) {
		m_element.draw(g);
		
		if (m_isSelected) {
			g.setColor(Color.ORANGE);
			g.draw(m_element.getBounds());
		}
	}
	
	public void setLocation(Point p) {
		super.setLocation(p);
		m_element.setLocation(p);
	}
	
	public void setSelected(boolean isSelected) {
		m_isSelected = isSelected;
	}

	public String generateSourceCode() {
		return m_element.generateSourceCode();
	}
}
