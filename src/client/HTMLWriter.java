package client;

import lib.DevEnvironment;
import lib.ElementWriter;

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
	
	public void run() {
		try {
			DevEnvironment.executeCommand(String.format("rundll32 url.dll,FileProtocolHandler \"%s\"", getFileName()+getExtension()), false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
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

	@Override
	public String getFileName() {
		return "HTMLOutput";
	}
}
