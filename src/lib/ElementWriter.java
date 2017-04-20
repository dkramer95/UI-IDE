package lib;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class ElementWriter {
	public List<String> text = new ArrayList<>();
	
	public void addLine(String line) {
		text.add(line);
	}
	
	public void writeFile(String filename) {
		
		try(FileWriter fw = new FileWriter(filename);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)
			) {	
			for(String line : text) {
				out.println(line);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public abstract String getExtension();
	
	public abstract void run();
	
	public abstract String getFileName();
}
