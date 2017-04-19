package client;

import lib.ElementWriter;

public class JavaWriter extends ElementWriter {
	
	public JavaWriter() {
		Init();
	}
	
	private void Init() {
		addLine("/** Automatically generated code **/\n\n");
		addLine("import javax.swing.*;");
		addLine("import java.util.ArrayList;");

		// class name
		addLine("public class Output {");
		
		// main method
		addLine("public static void main(String[] args) {");
		
		// gui stuff
		addLine("JFrame window = new JFrame();");
		addLine("window.setSize(800, 800);");
		addLine("JPanel mainPanel = new JPanel();");
		addLine("ArrayList<JComponent> elements = new ArrayList<>();");
		addLine("mainPanel.setLayout(null);");
		addLine("window.add(mainPanel);");
		addLine("window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);");
		addLine("window.setLocationRelativeTo(null);");
		addLine("window.setTitle(\"GUI Output Demo\");");
		addLine("window.setVisible(true);");
	}

	public void writeFile(String filename) {
		addLine("}"); // closing main method brace
		addLine("}"); // closing class brace

		// write the java file
		super.writeFile(filename);
		// compile the file
		
	}

	@Override
	public String getExtension() {
		return ".java";
	}

}
