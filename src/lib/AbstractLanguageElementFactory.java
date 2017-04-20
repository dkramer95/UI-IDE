package lib;

public abstract class AbstractLanguageElementFactory {

	public abstract AbstractUIElementFactory create(String type);
	public abstract String[] getLanguages();
}
