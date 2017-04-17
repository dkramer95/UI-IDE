package lib;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	protected ArrayList<UIElement> m_elements;
	protected Selection m_selection;
	
	public Canvas() {
		init();
	}
	
	protected void init() {
		m_elements = new ArrayList<>();
		m_selection = new Selection();

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (m_selection.hasUIElements()) {
					m_selection.moveTo(e.getPoint());
					repaint();
				}
			}
		});
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				checkSelection(e.getPoint());
			}
			
			public void mouseReleased(MouseEvent e) {
				m_selection.clear();
				repaint();
			}
		});
	}
	
	protected void checkSelection(Point p) {
		m_selection.clear();
		for (int j = 0; j < m_elements.size(); ++j) {
			UIElement e = m_elements.get(j);
			if (e.contains(p)) {
				System.out.println("Added element to selection " + e);
				m_selection.add(e);
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.clearRect(0, 0, getWidth(), getHeight());
		m_elements.forEach((e) -> {
			e.draw(g2d);
		});
		m_selection.draw(g2d);
		g2d.dispose();
	}
	
	public void addUIElement(UIElement element) {
		if (element != null) {
			m_elements.add(element);
			repaint();
		}
	}
}
