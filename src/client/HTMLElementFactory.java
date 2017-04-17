package client;

import lib.AbstractUIElementFactory;
import lib.UIElement;

public class HTMLElementFactory extends AbstractUIElementFactory {

	
	@Override
	public UIElement create(String type) {
		switch(type){
		case "<h1>":
			break;
		case "<p>":
			break;
		default:
			break;
		}
		return null;
	}

	@Override
	public String[] getSupportedElements() {
		return new String[] { "<p>", "<h1>"};
	}

}
