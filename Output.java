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
JCheckBox jcheckbox6 = new JCheckBox();
jcheckbox6.setSize(168, 59);
jcheckbox6.setLocation(255, 78);
jcheckbox6.setBackground(null);
jcheckbox6.setText("JCheckBox");
jcheckbox6.setBounds(jcheckbox6.getX(), jcheckbox6.getY(), jcheckbox6.getWidth(), jcheckbox6.getHeight());mainPanel.add(jcheckbox6);elements.add(jcheckbox6);

JTextField jtextfield7 = new JTextField();
jtextfield7.setSize(158, 48);
jtextfield7.setLocation(72, 104);
jtextfield7.setBackground(null);
jtextfield7.setText("Text");
jtextfield7.setBounds(jtextfield7.getX(), jtextfield7.getY(), jtextfield7.getWidth(), jtextfield7.getHeight());mainPanel.add(jtextfield7);elements.add(jtextfield7);

JButton jbutton8 = new JButton();
jbutton8.setSize(147, 39);
jbutton8.setLocation(332, 212);
jbutton8.setBackground(null);
jbutton8.setText("JButton");
jbutton8.setBounds(jbutton8.getX(), jbutton8.getY(), jbutton8.getWidth(), jbutton8.getHeight());mainPanel.add(jbutton8);elements.add(jbutton8);

JLabel jlabel9 = new JLabel();
jlabel9.setSize(199, 66);
jlabel9.setLocation(119, 188);
jlabel9.setBackground(null);
jlabel9.setText("MyLabel");
jlabel9.setBounds(jlabel9.getX(), jlabel9.getY(), jlabel9.getWidth(), jlabel9.getHeight());mainPanel.add(jlabel9);elements.add(jlabel9);

JButton jbutton10 = new JButton();
jbutton10.setSize(100, 25);
jbutton10.setLocation(90, 58);
jbutton10.setBackground(null);
jbutton10.setText("MyButton");
jbutton10.setBounds(jbutton10.getX(), jbutton10.getY(), jbutton10.getWidth(), jbutton10.getHeight());mainPanel.add(jbutton10);elements.add(jbutton10);

JTextField jtextfield11 = new JTextField();
jtextfield11.setSize(100, 25);
jtextfield11.setLocation(218, 302);
jtextfield11.setBackground(null);
jtextfield11.setText("JTextField");
jtextfield11.setBounds(jtextfield11.getX(), jtextfield11.getY(), jtextfield11.getWidth(), jtextfield11.getHeight());mainPanel.add(jtextfield11);elements.add(jtextfield11);

}
}
