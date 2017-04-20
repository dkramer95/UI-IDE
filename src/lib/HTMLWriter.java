package lib;

public class HTMLWriter extends ElementWriter{
	
	public HTMLWriter() {
		Init();
	}
	
	private void Init() {
		text.add("<!DOCTYPE html>");
		text.add("<html>");
		text.add("<head>");
		text.add("<style>");
		text.add("body.relative {");
		text.add("position: relative;");
		text.add("width: 800px;");
		text.add("height: 800px;");
		text.add("border: 3px solid #73AD21;");
		text.add("}");
		text.add("</style>");
		text.add("</head>");
		text.add("<body class=\"relative\">");
	}
	
	public void addStyle(String line){
		
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
