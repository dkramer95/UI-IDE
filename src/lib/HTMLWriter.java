package lib;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class HTMLWriter {
	
	public List<String> HTMLText = new ArrayList();
	
	public HTMLWriter() {
		Init();
	}
	
	private void Init() {
		HTMLText.add("<!DOCTYPE html>");
		HTMLText.add("<html>");
		HTMLText.add("<head></head>");
		HTMLText.add("<head></head>");
		HTMLText.add("<body>");
	}
	
	private void CloseFile() {
		HTMLText.add("</body>");
		HTMLText.add("</html>");
	}
	
	private String[] parseStringToHTML(String[] lines) {
		String[] HTMLString = null;
			
		
		
		return HTMLString;
	}
	
	private void writeHTMLFile(String[] lines) {
		try(FileWriter fw = new FileWriter("HTMLOutput.html", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))
			{
				for (String line : lines) {
					out.println(line);
				}
					
				} catch (IOException e) {
			}
	}
	
	private void writeHTMLFile(String line) {
		try(FileWriter fw = new FileWriter("HTMLOutput.html", true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw))
		{
				out.println(line);
			
		} catch (IOException e) {
		    //exception 
		}
	}

}
