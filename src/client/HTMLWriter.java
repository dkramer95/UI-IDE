package client;

import lib.ElementWriter;

public class HTMLWriter extends ElementWriter{
	
	public HTMLWriter() {
		Init();
	}
	
	private void Init() {
		text.add("<!DOCTYPE html>");
		text.add("<html>");
		text.add("<head></head>");
		text.add("<head></head>");
		text.add("<body>");
	}
	
	@Override
	public void writeFile(String filename) {
		text.add("</body>");
		text.add("</html>");
		super.writeFile(filename);
	}

	@Override
	public String getExtension() {
		return ".html";
	}
}