package lib;

public abstract class AbstractUIElementFactory {
	
	public abstract UIElement create(String type);

	public abstract String[] getSupportedElements();
	
	public abstract ElementWriter getWriter();
}
