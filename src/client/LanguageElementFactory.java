package client;

import lib.AbstractLanguageElementFactory;
import lib.AbstractUIElementFactory;

public class LanguageElementFactory extends AbstractLanguageElementFactory {

	public AbstractUIElementFactory create(String type) {
		switch (type) {
		case "Java":
			return new JUIElementFactory();
		case "HTML":
			return new HTMLElementFactory();
		}
		return null;
	}
	
	public String[] getLanguages() {
		return new String[] { "Java", "HTML" };
	}

}
