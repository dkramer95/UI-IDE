package lib;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener {
	protected ArrayList<UIElement> m_elements;
	protected Selection m_selection;
	

	public Canvas() {
		init();
	}
	
	protected void init() {
		m_elements = new ArrayList<>();
		m_selection = new Selection();
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.clearRect(0, 0, getWidth(), getHeight());
		m_elements.forEach(e -> {
			e.draw(g2d);
		});
		m_selection.draw(g2d);
		g2d.dispose();
	}
	
	public void addUIElement(UIElement element) {
		m_elements.add(element);
		repaint();
	}
	
	public ArrayList<UIElement> getUIElements() {
		return m_elements;
	}

	@Override
	public void mouseDragged(MouseEvent e) { 
		if (!m_selection.isEmpty()) {
			Point p = e.getPoint();

			if (m_selection.canResize(p)) {
				m_selection.handleResize(p);
			} else {
				m_selection.handleMove(p);
			}
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) { 
		m_selection.checkHandleHoverResize(e.getPoint());
	}
	
	public boolean checkSelection(Point p, boolean removeIfSelected) {
		boolean didClickElements = false;
		
		for(int j = 0; j < m_elements.size(); ++j) {
			UIElement el = m_elements.get(j);

			// we clicked on an element
			if (el.contains(p)) {
				didClickElements = true;
				if (m_selection.contains(el) && removeIfSelected) {
					// remove if already selected
					m_selection.remove(el);
				} else {
					m_selection.add(el);
				}
			}
		}
		
		// clear out any remaining elements in selection since we didn't hit them
		if (!didClickElements) {
			m_selection.clear();
		}
		return didClickElements;
	}

	@Override
	public void mouseClicked(MouseEvent e) { 
		checkSelection(e.getPoint(), false);
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }
}
