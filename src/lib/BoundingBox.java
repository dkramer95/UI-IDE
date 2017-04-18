package lib;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class BoundingBox extends Rectangle {
	public static final int RESIZE_NORTH_WEST = 0;
	public static final int RESIZE_NORTH_EAST = 1;
	public static final int RESIZE_SOUTH_WEST = 2;
	public static final int RESIZE_SOUTH_EAST = 3;
	
	
	protected Rectangle[] m_resizeHandles;
	protected UIElement m_element;
	protected int m_resizeDirection;
	

	public BoundingBox(UIElement element) {
		m_element = element;
		init();
	}
	
	protected void init() {
		m_resizeHandles = createResizeHandles();
		setBounds(m_element.getBounds());
	}
	
	protected Rectangle[] createResizeHandles() {
		Rectangle r = m_element.getBounds();
		// size of handle
		final int size = 10;

		// offset to account for handle
		r.x -= (size / 2);
		r.y -= (size / 2);
		
		Rectangle topLeftHandle = new Rectangle(r.x, r.y, size, size);
		Rectangle topRightHandle = new Rectangle(r.x + r.width, r.y, size, size);
		Rectangle bottomLeftHandle = new Rectangle(r.x, r.y + r.height, size, size);
		Rectangle bottomRightHandle = new Rectangle(r.x + r.width, r.y + r.height, size, size);
		
		Rectangle[] resizeHandles = new Rectangle[4];
		resizeHandles[0] = topLeftHandle;
		resizeHandles[1] = topRightHandle;
		resizeHandles[2] = bottomLeftHandle;
		resizeHandles[3] = bottomRightHandle;
		
		return resizeHandles;
	}
	
	public boolean isHandleHovered(Point p) {
		boolean result = false;
		
		for (int j = 0; j < m_resizeHandles.length; ++j) {
			Rectangle r = m_resizeHandles[j];
			if (r.contains(p)) {
				result = true;
				m_resizeDirection = j;
			}
		}
//
//		for(Rectangle r : m_resizeHandles) {
//			if (r.contains(p)) {
//				result = true;
//				break;
//			}
//		}
		return result;
	}
	
	public int getResizeDirection() {
		return m_resizeDirection;
	}
	
	public void update() {
		m_resizeHandles = createResizeHandles();
	}

	public void draw(Graphics2D g) {
		Rectangle bounds = m_element.getBounds();
		g.setColor(Color.MAGENTA);
		for(Rectangle r : m_resizeHandles) {
			g.draw(r);
		}
		g.draw(bounds);
	}

}
