package lib;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Application extends JFrame {

	protected static AbstractUIElementFactory elementFactory;
	protected Canvas m_canvas;
	protected JComponent m_sidebar;
	
	public Application(AbstractUIElementFactory factory) {
		elementFactory = factory;
	}
	
	public void start() {
		createAndShowGUI();
	}
	
	class Sidebar extends JPanel {
		protected ArrayList<JButton> m_elementButtons;
		protected JButton m_activeElementButton;
		
		public Sidebar() {
			init();
		}
		
		protected void init() {
			setBackground(Color.DARK_GRAY);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			setSize(100, 0);
			loadElements();
		}
		
		protected void loadElements() {
			m_elementButtons = new ArrayList<>();
			String[] elementTypes = elementFactory.getSupportedElements();

			for(String s : elementTypes) {
				JButton button = new JButton(s);
				button.addActionListener((e) -> {
					elementButtonClicked(button);
				});
				button.setSize(100, 50);
				add(button);
				m_elementButtons.add(button);
			}
			
			JButton buildButton = new JButton("Build");
			buildButton.addActionListener(e -> {
				build();
			});
			add(buildButton);
		}
		
		protected void elementButtonClicked(JButton button) {
			m_activeElementButton = button;
			addElement(button.getText());
		}
	}
	
	protected void build() {
		ArrayList<UIElement> elements = m_canvas.getUIElements();
		elements.forEach(e -> {
			e.generateSourceCode();
		});
	}
	
	public void addElement(String type) {
		UIElement element = elementFactory.create(type);
		if (element != null) {
			m_canvas.addUIElement(element);
			System.out.println("Added element: " + element);
		}
	}
	
	protected void createAndShowGUI() {
		JComponent mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		m_canvas = new Canvas();
		mainPanel.add(m_canvas, BorderLayout.CENTER);
		
		m_sidebar = new Sidebar();
		mainPanel.add(m_sidebar, BorderLayout.WEST);
		
		add(mainPanel);
		
		setSize(800, 600);
		setLocationRelativeTo(null);
		setTitle("UI IDE by David Kramer");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
}
