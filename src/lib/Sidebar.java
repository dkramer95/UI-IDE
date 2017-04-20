package lib;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Sidebar extends JPanel {
	protected ArrayList<JButton> m_elementButtons;
	protected JButton m_activeElementButton;
	protected JPanel m_elementButtonsPanel;
	protected Application m_app;
	
	public Sidebar(Application app) {
		m_app = app;
		init();
	}
	
	protected void init() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMaximumSize(new Dimension(150, 0));


		JComboBox<String> languageChooserBox = createLanguageChooserBox();
		add(languageChooserBox);		
		updateElementButtons();
	}
	
	protected JComboBox<String> createLanguageChooserBox() {
		String[] languages = Application.languageFactory.getLanguages();

		JComboBox<String> languageChoiceBox = new JComboBox<>(languages);
		languageChoiceBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String language = languageChoiceBox.getSelectedItem().toString();
				Application.setElementFactory(Application.languageFactory.create(language));
				m_app.getCanvas().clear();
				updateElementButtons();
			}
		});
		return languageChoiceBox;
	}
	
	protected void updateElementButtons() {
		if (m_elementButtonsPanel != null) {
			remove(m_elementButtonsPanel);
		}
		revalidate();
		m_elementButtonsPanel = createElementButtonsPanel();
		add(m_elementButtonsPanel);
	}
	
	protected JPanel createElementButtonsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;

		String[] elementTypes = Application.elementFactory.getSupportedElements();
		for(String s : elementTypes) {
			JButton button = new JButton(s);
			button.addActionListener((e) -> {
				elementButtonClicked(button);
			});
			button.setSize(100, 50);
			panel.add(button, gbc);
		}

		JButton buildButton = new JButton("Build");
		buildButton.addActionListener(e -> {
			m_app.build();
		});
		panel.add(buildButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(e -> {
			m_app.getCanvas().deleteSelection();
			m_app.getCanvas().repaint();
		});
		panel.add(deleteButton);
		
		JButton runButton = new JButton("Run");
		runButton.addActionListener(e -> {
			m_app.runButton();
		});
		panel.add(runButton);
		
		return panel;
	}
	
	protected void elementButtonClicked(JButton button) {
		m_activeElementButton = button;
		m_app.addElement(button.getText());
	}
}
