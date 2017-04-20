package lib;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JColorChooser;
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
	
	public void updateSelectionText(String text) {
		m_selection.setText(text);
		repaint();
	}
	
	public Selection getSelection() {
		return m_selection;
	}
	
	public void addUIElement(UIElement element) {
		element.setLocation(getRandomPoint(getWidth() - element.getWidth(), getHeight() - element.getHeight()));
		m_elements.add(element);
		repaint();
	}
	
	public void clear() {
		m_elements.clear();
		m_selection.clear();
		repaint();
	}
	
	public Point getRandomPoint(int maxX, int maxY) {
		Random rng = new Random();
		int x = rng.nextInt(maxX);
		int y = rng.nextInt(maxY);
		Point p = new Point(x, y);
		return p;
	}
	
	public ArrayList<UIElement> getUIElements() {
		return m_elements;
	}

	@Override
	public void mouseDragged(MouseEvent e) { 
		Point p = e.getPoint();
		
		if (m_selection.canResize(p)) {
			m_selection.handleResize(p);
		} else if (!m_selection.isEmpty()) {
			m_selection.handleMove(p);
		} else {
			checkSelection(p, false);
		}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) { 
		m_selection.checkHandleHoverResize(e.getPoint());
	}
	
	public boolean checkSelection(Point p, boolean removeIfSelected) {
		boolean didClickElements = false;
		
		for(int j = m_elements.size() - 1; j >= 0; --j) {
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
				break;
			}
		}
		
		// clear out any remaining elements in selection since we didn't hit them
		if (!didClickElements) {
			m_selection.clear();
		}
		return didClickElements;
	}
	
	public void deleteSelection() {
		m_selection.getElements().forEach(el -> {
			m_elements.remove(el);
		});
		m_selection.clear();
	}

	@Override
	public void mouseClicked(MouseEvent e) { 
		m_selection.clear();
		
		if (checkSelection(e.getPoint(), false)) {
			if (e.getClickCount() == 2) {
				Color c = JColorChooser.showDialog(this, "Choose Color", Color.RED);
				getSelection().setColor(c);
			}
		}
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
