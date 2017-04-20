package client;

import javax.swing.JOptionPane;

import lib.DevEnvironment;
import lib.ElementWriter;

public class JavaWriter extends ElementWriter {
	
	public JavaWriter() {
		Init();
	}
	
	private void Init() {
		// import statements
		addLine("/** Automatically generated code **/\n\n");
		addLine("import javax.swing.*;");
		addLine("import java.util.ArrayList;");
		addLine("import java.util.Timer;");
		addLine("import java.util.TimerTask;");

		// class name
		addLine("public class Output {");
		
		// main method
		addLine("public static void main(String[] args) {");
		
		// gui stuff
		addLine("final JFrame window = new JFrame();");
		addLine("window.setSize(800, 800);");
		addLine("JPanel mainPanel = new JPanel();");
		addLine("ArrayList<JComponent> elements = new ArrayList<>();");
		addLine("mainPanel.setLayout(null);");
		addLine("window.add(mainPanel);");
		addLine("window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);");
		addLine("window.setLocationRelativeTo(null);");
		addLine("window.setTitle(\"GUI Output Demo\");");
		addLine("window.setVisible(true);");
		
		// hack to make window repaint and show elements
		addLine("Timer hackTimer = new Timer();");
		addLine("hackTimer.schedule(new TimerTask() { public void run() { window.repaint(); } }, 2000);");
	}

	public void writeFile(String filename) {
		addLine("}"); // closing main method brace
		addLine("}"); // closing class brace

		// write the java file
		super.writeFile(filename);
		
		Thread t = new Thread(() -> {
			String os = System.getProperty("os.name").toLowerCase();
			String compilerCommand = os.contains("win") ? "where javac" : "which javac";
			String runCommand = os.contains("win") ? "where java" : "which java";
			
			try {
				// compile the file
				String compilerPath = DevEnvironment.executeCommand(compilerCommand, true);
				DevEnvironment.executeCommand(compilerPath + " " + filename, true);
				
				String javaRuntimePath = DevEnvironment.executeCommand(runCommand, true);
				String javaFile = filename.substring(0, filename.indexOf('.'));
				DevEnvironment.executeCommand(javaRuntimePath + " " + javaFile, false);
				
				System.out.println("Java compiled successfully!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Build failed! You probably have an "
						+ "incorrect path somewhere.. Maybe lines 54 / 55 of JavaWriter!?? "
						+ "Replace this with your path to make it work!! :)");
			}
		});
		t.start();
	}

	@Override
	public String getExtension() {
		return ".java";
	}

}
