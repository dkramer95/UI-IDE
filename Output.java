/** Automatically generated code **/


import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
public class Output {
public static void main(String[] args) {
final JFrame window = new JFrame();
window.setSize(800, 800);
JPanel mainPanel = new JPanel();
ArrayList<JComponent> elements = new ArrayList<>();
mainPanel.setLayout(null);
window.add(mainPanel);
window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
window.setLocationRelativeTo(null);
window.setTitle("GUI Output Demo");
window.setVisible(true);
Timer hackTimer = new Timer();
hackTimer.schedule(new TimerTask() { public void run() { window.repaint(); } }, 2000);
JLabel jlabel1 = new JLabel();
jlabel1.setSize(100, 25);
jlabel1.setLocation(42, 511);
jlabel1.setBackground(null);
jlabel1.setText("JLabel");
jlabel1.setBounds(jlabel1.getX(), jlabel1.getY(), jlabel1.getWidth(), jlabel1.getHeight());mainPanel.add(jlabel1);elements.add(jlabel1);

JLabel jlabel2 = new JLabel();
jlabel2.setSize(100, 25);
jlabel2.setLocation(366, 243);
jlabel2.setBackground(null);
jlabel2.setText("JLabel");
jlabel2.setBounds(jlabel2.getX(), jlabel2.getY(), jlabel2.getWidth(), jlabel2.getHeight());mainPanel.add(jlabel2);elements.add(jlabel2);

JLabel jlabel3 = new JLabel();
jlabel3.setSize(100, 25);
jlabel3.setLocation(89, 395);
jlabel3.setBackground(null);
jlabel3.setText("JLabel");
jlabel3.setBounds(jlabel3.getX(), jlabel3.getY(), jlabel3.getWidth(), jlabel3.getHeight());mainPanel.add(jlabel3);elements.add(jlabel3);

JLabel jlabel4 = new JLabel();
jlabel4.setSize(100, 25);
jlabel4.setLocation(99, 114);
jlabel4.setBackground(null);
jlabel4.setText("JLabel");
jlabel4.setBounds(jlabel4.getX(), jlabel4.getY(), jlabel4.getWidth(), jlabel4.getHeight());mainPanel.add(jlabel4);elements.add(jlabel4);

JLabel jlabel5 = new JLabel();
jlabel5.setSize(100, 25);
jlabel5.setLocation(392, 480);
jlabel5.setBackground(null);
jlabel5.setText("JLabel");
jlabel5.setBounds(jlabel5.getX(), jlabel5.getY(), jlabel5.getWidth(), jlabel5.getHeight());mainPanel.add(jlabel5);elements.add(jlabel5);

}
}
