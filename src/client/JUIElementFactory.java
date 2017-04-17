package client;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import lib.AbstractUIElementFactory;
import lib.UIElement;

public class JUIElementFactory extends AbstractUIElementFactory {

	@Override
	public UIElement create(String type) {
		switch (type) {
		case "JLabel":
			return new JUIElement(new JLabel());
		case "JButton":
			return new JUIElement(new JButton());
		case "JTextField":
			return new JUIElement(new JTextField());
		case "JCheckBox":
			return new JUIElement(new JCheckBox());
		}
		return null;
	}

	@Override
	public String[] getSupportedElements() {
		return new String[] { "JLabel", "JButton", "JTextField", "JCheckBox" };
	}

}
