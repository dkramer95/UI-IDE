package lib;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import client.JUIElementFactory;

public class Application extends JFrame {

	protected static AbstractUIElementFactory elementFactory;
	protected static AbstractUIElementFactory elementFactory2;
	protected Canvas m_canvas;
	protected JComponent m_sidebar;
	protected JComponent m_textPanel;
	protected JLabel label;
	
	public Application(AbstractUIElementFactory factory) {
		elementFactory = factory;
		elementFactory2 = new JUIElementFactory();
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
			setSize(200, 0);
			loadElements();
		}
		
		protected void loadElements() {
			m_elementButtons = new ArrayList<>();
			String[] elementTypes = elementFactory.getSupportedElements();
			
			label = new JLabel("HTML");
			label.setForeground(Color.WHITE);
			add(label);

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
			
			JButton switchButton = new JButton("Switch");
			switchButton.addActionListener(e -> {
				switchJ(elementFactory2);
			});
			add(switchButton);
			
			JButton deleteButton = new JButton("Delete");
			deleteButton.addActionListener(e -> {
				m_canvas.deleteSelection();
				m_canvas.repaint();
			});
			add(deleteButton);
			
			JButton runButton = new JButton("Run");
			runButton.addActionListener(e -> {
				runButton();
			});
			add(runButton);
		}
		
		protected void elementButtonClicked(JButton button) {
			m_activeElementButton = button;
			addElement(button.getText());
		}
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
		writer.writeFile("HTMLOutput"+writer.getExtension());
	}
	
	protected void switchJ(AbstractUIElementFactory factory) {
		elementFactory2 = elementFactory;
		elementFactory = factory;
		//TODO replace the Java String with bottom text
		//elementFactory.getWriter().getExtension()
		label.setText("Java");
		m_canvas.repaint();	
		
	}
	
	protected void runButton() {
		try {
			executeCommand("rundll32 url.dll,FileProtocolHandler \"HTMLOutput.html\"", false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
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
		
		m_textPanel = createTextField();
		mainPanel.add(m_textPanel, BorderLayout.NORTH);
		
		add(mainPanel);
		
		setSize(800, 600);
		setLocationRelativeTo(null);
		setTitle("UI IDE by David Kramer");
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
	
	private void executeCommand(String command, boolean wait) throws Exception {
		//Source: http://stackoverflow.com/questions/4842684/how-to-compile-run-java-program-in-another-java-program
		System.out.println(command);
		Process pro = Runtime.getRuntime().exec(command);
		if (wait)
		{
			printLines(command + " stdout:", pro.getInputStream());
			printLines(command + " stderr:", pro.getErrorStream());
			pro.waitFor();
			System.out.println(command + " exitValue() " + pro.exitValue());
		}
	}

	private static void printLines(String name, InputStream ins) throws Exception {
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));
		while ((line = in.readLine()) != null) {
			System.out.println(name + " " + line);
		}
	}

}
