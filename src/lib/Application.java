package lib;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import client.LanguageElementFactory;

public class Application extends JFrame {
	public static LanguageElementFactory languageFactory;
	protected static AbstractUIElementFactory elementFactory;

	protected Canvas m_canvas;
	protected JComponent m_sidebar;
	protected JComponent m_textPanel;
	protected JLabel label;
	
	public Application(LanguageElementFactory factory) {
		languageFactory = factory;
		
		// default to the first language in the factory
		String defaultLanguage = factory.getLanguages()[0];
		elementFactory = languageFactory.create(defaultLanguage);
	}
	
	public static void setElementFactory(AbstractUIElementFactory factory) {
		elementFactory = factory;
	}
	
	public void start() {
		createAndShowGUI();
	}
	
	public Canvas getCanvas() {
		return m_canvas;
	}
	
	protected void updateText(String text) {
		m_canvas.getSelection().setText(text);
		m_canvas.repaint();
	}
	
	protected void build() {
		ArrayList<UIElement> elements = m_canvas.getUIElements();
		ElementWriter writer = elementFactory.getWriter();
		elements.forEach(e -> {
			writer.addLine(e.generateSourceCode());
		});
		writer.writeFile(writer.getFileName() + writer.getExtension());
	}
	
	protected void runButton() {
		ElementWriter writer = elementFactory.getWriter();
		writer.run();
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
		
		m_sidebar = new Sidebar(this);
		mainPanel.add(m_sidebar, BorderLayout.WEST);
		
		m_textPanel = createTextField();
		mainPanel.add(m_textPanel, BorderLayout.NORTH);
		
		add(mainPanel);
		
		setSize(800, 600);
		setLocationRelativeTo(null);
		setTitle("UI IDE by David Kramer, James Castrejon, and Alfonso Vazques ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	protected JTextField createTextField() {
		JTextField textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				m_canvas.updateSelectionText(textField.getText());
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) { 
				m_canvas.updateSelectionText(textField.getText());
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				m_canvas.getSelection().setText(textField.getText());
			}
		});	
		return textField;
	}

}
