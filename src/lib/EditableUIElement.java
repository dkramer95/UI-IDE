package lib;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class EditableUIElement extends UIElement {
	protected BoundingBox m_boundingBox;
	protected UIElement m_element;
	protected Point m_resizeStartPt;
	protected Rectangle m_startBounds;

	protected boolean m_isSelected;
	
	public EditableUIElement(UIElement element) {
		m_element = element;
		m_boundingBox = new BoundingBox(m_element);
	}

	@Override
	public void draw(Graphics2D g) {
		m_element.draw(g);
		g.setColor(Color.ORANGE);
		g.draw(m_element.getBounds());
		m_boundingBox.draw(g);
	}
	
	public BoundingBox getBoundingBox() {
		return m_boundingBox;
	}
	
	public Rectangle getBounds() {
		return m_element.getBounds();
	}
	
	public void setSize(int x, int y) {
		super.setSize(x, y);
		m_element.setSize(x, y);
	}
	
	public void setStartResizePt(Point p) {
		m_resizeStartPt  = p;
		m_startBounds = getBounds();
	}
	
	public void handleResize(Point p) {
		if (m_startBounds != null) {
			int dx = p.x - (m_startBounds.x + m_startBounds.width);
			int dy = p.y - (m_startBounds.y + m_startBounds.height);
			
			int newX = m_startBounds.x;
			int newY = m_startBounds.y;
			int newWidth = m_startBounds.width;
			int newHeight = m_startBounds.height;
			
			// determine new (x,y) and width/height based on where we resized from
			int resizeDirection = getBoundingBox().getResizeDirection();

			switch (resizeDirection) {
			case BoundingBox.RESIZE_NORTH_WEST:
				newX = p.x;
				newY = p.y;
				newWidth = (m_startBounds.width + m_startBounds.x - p.x);
				break;
			case BoundingBox.RESIZE_SOUTH_WEST:
				newX = p.x;
				newY = p.y - m_startBounds.height;
				newWidth = (m_startBounds.width + m_startBounds.x - p.x);
				break;
			case BoundingBox.RESIZE_NORTH_EAST:
				newWidth = (m_startBounds.width + dx);
				newHeight = (m_startBounds.height + dy);
				break;
			case BoundingBox.RESIZE_SOUTH_EAST:
				newWidth = (m_startBounds.width + dx);
				newHeight = (m_startBounds.height + dy);
				break;
			}
			
			if (newWidth < 0) {
				newX = m_startBounds.x - Math.abs(newWidth);
				newWidth = Math.abs(m_startBounds.x - newX);
			}
			if (newHeight < 0) {
				newY = m_startBounds.y - Math.abs(newHeight);
				newHeight = Math.abs(m_startBounds.y - newY);
			}
			m_element.setX(newX);
			m_element.setY(newY);
			m_element.setWidth(newWidth);
			m_element.setHeight(newHeight);
			m_boundingBox.update();
			m_startBounds = getBounds();
		}
	}
	
	public void setLocation(Point p) {
		super.setLocation(p);
		m_element.setLocation(p);
		m_boundingBox.update();
	}
	
	public void setSelected(boolean isSelected) {
		m_isSelected = isSelected;
	}

	public String generateSourceCode() {
		return m_element.generateSourceCode();
	}
	
	public UIElement getWrappedElement() {
		return m_element;
	}
}
