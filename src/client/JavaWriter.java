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
//		addLine("Timer hackTimer = new Timer();");
//		addLine("hackTimer.schedule(new TimerTask() { public void run() { window.repaint(); } }, 2000);");
	}
	
	public void run() {
		String filename = getFileName();
		Thread t = new Thread(() -> {
			String os = System.getProperty("os.name").toLowerCase();
			
			try {
				String runtimePath = os.contains("win") ? "\"C:\\Program Files\\Java\\jdk1.8.0_131\\bin\\java\"" :
					DevEnvironment.executeCommand("which java", true);
				DevEnvironment.executeCommand(runtimePath + " " + filename, true);
				
				System.out.println("Java application running!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Failed to Run Java Application! :(");
			}
		});
		t.start();
	}
	
	public void Compile() {
		String filename = getFileName();
		Thread t = new Thread(() -> {
			String os = System.getProperty("os.name").toLowerCase();
			
			try {
				String compilerPath = os.contains("win") ? "\"C:\\Program Files\\Java\\jdk1.8.0_131\\bin\\javac.exe\"" :
					DevEnvironment.executeCommand("where javac", true);
				DevEnvironment.executeCommand(compilerPath + " " + filename, true);
								
				System.out.println("Java compiled successfully!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Build failed! Update your Java to 8.131 :)");
			}
		});
		t.start();
	}

	public void writeFile(String filename) {
		addLine("}"); // closing main method brace
		addLine("}"); // closing class brace

		// write the java file
		super.writeFile(filename);
		Compile();

	}

	@Override
	public String getExtension() {
		return ".java";
	}

	@Override
	public String getFileName() {
		return "Output";
	}

}
