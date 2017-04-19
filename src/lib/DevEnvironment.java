package lib;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DevEnvironment {

	public static void main(String[] args) throws Exception {
		System.out.println("Main starting");
		DevEnvironment app = new DevEnvironment();
//		
//		app.executeCommand("notepad.exe", false);
//		
//		app.executeCommand("ping google.com", true);
//		
//		//http://stackoverflow.com/questions/5226212/how-to-open-the-default-webbrowser-using-java
//		//opens the page with the default browser
//		app.executeCommand("rundll32 url.dll,FileProtocolHandler \"C:\\g\\csc360 workspace\\Factory\\src\\edu\\neumont\\csc360\\ide\\HTMLOutput.html\"", false); 
//		
//		//Compile a java application
//		app.executeCommand("\"C:\\Program Files\\Java\\jdk1.8.0_121\\bin\\javac.exe\" -d \"c:\\g\\csc360 workspace\\Factory\\classes\" \"C:\\g\\csc360 workspace\\Factory\\src\\edu\\neumont\\csc360\\ide\\SimpleConsoleApp.java\"", true);
//		app.executeCommand("\"C:\\Program Files\\Java\\jdk1.8.0_121\\bin\\java\" -classpath \"C:\\g\\csc360 workspace\\Factory\\classes\" edu.neumont.csc360.ide.SimpleConsoleApp", true);
//		
//		System.out.println("Main done");
	}

	public static String executeCommand(String command, boolean wait) throws Exception {
		//Source: http://stackoverflow.com/questions/4842684/how-to-compile-run-java-program-in-another-java-program
		System.out.println(command);
		Process pro = Runtime.getRuntime().exec(command);
		String result = "";
		if (wait)
		{
			result = printLines(command + " stdout:", pro.getInputStream());
			printLines(command + " stderr:", pro.getErrorStream());
			pro.waitFor();
			System.out.println(command + " exitValue() " + pro.exitValue());
		}
		return result;
	}

	private static String printLines(String name, InputStream ins) throws Exception {
		StringBuilder sb = new StringBuilder();
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));
		while ((line = in.readLine()) != null) {
			sb.append(line);
			System.out.println(name + " " + line);
		}
		return sb.toString();
	}

	
}
