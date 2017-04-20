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
	
	protected Point[] getResizePoints(Point curPt) {
		Rectangle r = m_startBounds;
		Point startPt = new Point(r.x, r.y);
		Point endPt  = new Point(r.x + r.width, r.y + r.height);

		// the new points to assign
		Point newStartPt = (Point)startPt.clone();
		Point newEndPt = (Point)endPt.clone();

		int direction = getBoundingBox().getResizeDirection();

		switch (direction) {
		case BoundingBox.RESIZE_NORTH: 	 	// intentional fallthrough
		case BoundingBox.RESIZE_NORTH_EAST:
		case BoundingBox.RESIZE_NORTH_WEST:
			if (curPt.y > endPt.y) {
				newStartPt.y = endPt.y;
				newEndPt.y = curPt.y;
			} else {
				newStartPt.y = curPt.y;
			}
			break;
		case BoundingBox.RESIZE_SOUTH: 		// intentional fallthrough
		case BoundingBox.RESIZE_SOUTH_EAST:
		case BoundingBox.RESIZE_SOUTH_WEST:
			if (curPt.y < startPt.y) {
				newStartPt.y = curPt.y;
				newEndPt.y = startPt.y;
			} else {
				newEndPt.y = curPt.y;
			}
			break;
		}
		switch (direction) {
		case BoundingBox.RESIZE_EAST: 		// intentional fallthrough
		case BoundingBox.RESIZE_NORTH_EAST:
		case BoundingBox.RESIZE_SOUTH_EAST:
			if (curPt.x < startPt.x) {
				newEndPt.x = startPt.x;
				newStartPt.x = curPt.x;
			} else {
				newEndPt.x = curPt.x;
			}
			break;
		case BoundingBox.RESIZE_WEST: 		// intentional fallthrough
		case BoundingBox.RESIZE_NORTH_WEST:
		case BoundingBox.RESIZE_SOUTH_WEST:
			if (curPt.x > endPt.x) {
				newStartPt.x = endPt.x;
				newEndPt.x = curPt.x;
			} else {
				newStartPt.x = curPt.x;
			}
		}
		return new Point[] { newStartPt, newEndPt };
	}
	
	public void handleResize(Point p) {
		if (m_startBounds != null) {
			Point[] resizePts = getResizePoints(p);
			Point newStartPt = resizePts[0];
			Point newEndPt = resizePts[1];
			
			m_element.setX(newStartPt.x);
			m_element.setY(newStartPt.y);
			m_element.setWidth(Math.abs(newEndPt.x - newStartPt.x));
			m_element.setHeight(Math.abs(newEndPt.y - newStartPt.y));
		}
		m_boundingBox.update();
	}
	
	public void setLocation(Point p) {
		super.setLocation(p);
		m_element.setLocation(p);
		m_boundingBox.update();
	}
	
	public void setSelected(boolean isSelected) {
		m_isSelected = isSelected;
	}
	
	public void setText(String text) {
		m_element.setText(text);
	}
	
	public void setColor(Color color) {
		m_element.setColor(color);
	}

	public String generateSourceCode() {
		return m_element.generateSourceCode();
	}
	
	public UIElement getWrappedElement() {
		return m_element;
	}
}
