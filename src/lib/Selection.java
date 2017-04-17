package lib;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Selection {
	protected ArrayList<UIElement> m_selectedElements;

	public Selection() {
		init();
	}

	protected void init() {
		m_selectedElements = new ArrayList<>();
	}
	
	public void moveTo(Point p) {
		System.out.println("Moving: " + m_selectedElements.size() + "elements");

		m_selectedElements.forEach((e) -> {
			e.setLocation(p);
		});
	}
	
	public void draw(Graphics2D g) {
		m_selectedElements.forEach((e) -> {
			e.draw(g);
		});
	}
	
	public void add(UIElement element) {
		EditableUIElement editableUIElement = new EditableUIElement(element);
		editableUIElement.setSelected(true);
		m_selectedElements.add(editableUIElement);
	}
	
	public void clear() {
		m_selectedElements.clear();
		System.out.println("selection cleared");
	}
	
	public boolean hasUIElements() {
		boolean result = m_selectedElements.size() > 0;
		return result;
	}
}
