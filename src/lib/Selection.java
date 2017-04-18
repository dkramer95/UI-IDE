package lib;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Selection {
	protected ArrayList<EditableUIElement> m_elements;
	protected Point m_resizeStartPt;
	protected Point m_dragStartPt;
	protected Point m_dragPtCurrent;
	protected boolean m_isOverResizeHandle;
	
	public Selection() {
		init();
	}
	
	protected void init() {
		m_elements = new ArrayList<>();
	}
	
	public boolean isEmpty() {
		boolean result = m_elements.isEmpty();
		return result;
	}
	
	public void add(UIElement element) {
		// wrap an editor element around the UI element
		EditableUIElement editableElement = new EditableUIElement(element);
		m_elements.add(editableElement);
		System.out.println(element + " added to selection");
	}
	
	public boolean contains(UIElement element) {
		boolean result = false;

		for (int j = 0; j < m_elements.size(); ++j) {
			EditableUIElement e = m_elements.get(j);
			if (e.getWrappedElement() == element) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	public boolean hasInitialDragPoint() {
		boolean result = (m_dragStartPt != null);
		return result;
	}
	
	public void setInitialDragPoint(Point p) {
		m_dragPtCurrent = p;
	}
	
	public void setCurrentDragPoint(Point p) {
		m_dragPtCurrent = p;
	}
	
	public void handleResize(Point currentPt) {
		for (int j = 0; j < m_elements.size(); ++j) {
			EditableUIElement e = m_elements.get(j);
			e.handleResize(currentPt);
		}
	}
	
	public void endResize() {
		for (int j = 0; j < m_elements.size(); ++j) {
			EditableUIElement e = m_elements.get(j);
			e.setStartResizePt(null);
		}
	}
	
	public void handleMove(Point p) {
//		System.out.println("Moving");
		for (int j = 0; j < m_elements.size(); ++j) {
			EditableUIElement e = m_elements.get(j);
			Rectangle r = e.getBounds();
			Point offsetPt = new Point(p.x - (r.width / 2), p.y - (r.height / 2));
			e.setLocation(offsetPt);
		}
	}
	
	public void remove(UIElement element) {
		for (int j = 0; j < m_elements.size(); ++j) {
			EditableUIElement e = m_elements.get(j);
			if (e.getWrappedElement() == element) {
				m_elements.remove(e);
				System.out.println(element + " removed from selection");
				break;
			}
		}
	}
	
	public boolean checkHandleHoverResize(Point p) {
		boolean result = false;
		for (int j = 0; j < m_elements.size(); ++j) {
			EditableUIElement e = m_elements.get(j);
			if (e.getBoundingBox().isHandleHovered(p)) {
				e.setStartResizePt(p);
				result = true;
				break;
			}
		}
		m_isOverResizeHandle = (result && !m_isOverResizeHandle);
		return result;
	}
	
	public boolean canResize(Point p) {
		if (!m_isOverResizeHandle) {
			checkHandleHoverResize(p);
		}
		return m_isOverResizeHandle;
	}
	
	public void clear() {
		m_elements.clear();
		m_dragStartPt = null;
		m_dragPtCurrent = null;
		System.out.println("selection cleared");
	}
	
	public void draw(Graphics2D g) {
		m_elements.forEach(e -> {
			e.draw(g);
		});
	}
}
