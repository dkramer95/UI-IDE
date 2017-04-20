package lib;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class BoundingBox extends Rectangle {
	
	// Constants for different resize handles
	public static final int RESIZE_NORTH = 0;
	public static final int RESIZE_SOUTH = 1;
	public static final int RESIZE_EAST = 2;
	public static final int RESIZE_WEST	 = 3;
	public static final int RESIZE_NORTH_EAST = 4;
	public static final int RESIZE_NORTH_WEST = 5;
	public static final int RESIZE_SOUTH_EAST = 6;
	public static final int RESIZE_SOUTH_WEST = 7;
	
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
		
		ArrayList<Rectangle> rectangles = new ArrayList<>();
		
		rectangles.add(new Rectangle(r.x + (r.width / 2), r.y, size, size)); // north
		rectangles.add(new Rectangle(r.x + (r.width / 2), r.y + r.height, size, size)); // south
		rectangles.add(new Rectangle(r.x + r.width, r.y + (r.height / 2), size, size)); // east
		rectangles.add(new Rectangle(r.x, r.y + (r.height / 2), size, size)); // west
		rectangles.add(new Rectangle(r.x + r.width, r.y, size, size)); // north east
		rectangles.add(new Rectangle(r.x, r.y, size, size)); // north west 
		rectangles.add(new Rectangle( new Rectangle(r.x + r.width, r.y + r.height, size, size))); // south east
		rectangles.add(new Rectangle( new Rectangle(r.x, r.y + r.height, size, size))); // south west
		
		Rectangle[] handles = new Rectangle[8];
		return rectangles.toArray(handles);
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
