package Calculator;
// * * 三角计算器 * *

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;
public class TriCalc extends JFrame implements ActionListener {
    private final String[] KEYS = {" "," ","关闭","sin","arcsin","7","8","9","cos","arccos","4","5","6","tan","arctan","1","2","3","cot","arccot",".","0","="};
    private final String[] COMMAND = { "DEL", "AC"};
    private JButton keys[] = new JButton[KEYS.length];
    private JButton commands[] = new JButton[COMMAND.length];
    private JTextField resultText = new JTextField("0");
    private boolean firstDigit = true;
    private double resultNum = 0.0;
    private String operator = "=";
    private boolean operateValidFlag = true;
    StringBuffer sb = new StringBuffer();
    String vl = null;
    public TriCalc() {
        setTitle("三角型");
        this.setBackground(Color.LIGHT_GRAY);
        this.setTitle("三角计算器");
        this.setLocation(50, 50);
        this.setSize(550, 550);
        this.setResizable(false);
//计算器的切换（标准计算器·科学计算器·程序员计算器·三角函数计算器）
        JMenuBar menuBar = new JMenuBar(); //创建菜单
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("计算器切换");
        menuBar.add(menu);
        JMenuItem menuItem_1 = new JMenuItem("· 标准计算器      ");
        menu.add(menuItem_1);
        JMenuItem menuItem_2 = new JMenuItem("· 科学计算器      ");
        menu.add(menuItem_2);
        JMenuItem menuItem_3 = new JMenuItem("· 程序员计算器    ");
        menu.add(menuItem_3);
        JMenuItem menuItem_4 = new JMenuItem("· 三角函数计算器  ");
        menu.add(menuItem_4);
        menuItem_1.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                             Calculator ordinary = new Calculator();
                                             ordinary.setVisible(true);
                                         }
                                     }
        );
        menuItem_3.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                             BaseCalc ordinary = new BaseCalc();
                                             ordinary.setVisible(true);
                                         }
                                     }
        );
        menuItem_2.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                             RealCalc ordinary = new RealCalc();
                                             ordinary.setVisible(true);
                                         }
                                     }
        );

        resultText.setHorizontalAlignment(JTextField.RIGHT);
        resultText.setBackground(Color.white);
        resultText.setFont(new Font("TimesRoman",Font.BOLD,46));
        JPanel PPanel = new JPanel();
        PPanel.setLayout(new GridLayout(5, 4, 4, 3));
        for (int i = 0; i < COMMAND.length; i++) {
            commands[i] = new JButton(COMMAND[i]);
            PPanel.add(commands[i]);
            commands[i].setFont(new Font("微软雅黑",Font.PLAIN,20));
            commands[i].setForeground(Color.BLUE);//设置字体颜色
        }
        for (int i = 0; i < KEYS.length; i++) {
            keys[i] = new JButton(KEYS[i]);
            PPanel.add(keys[i]);
            keys[i].setFont(new Font("微软雅黑",Font.PLAIN,20));
        }
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(3,3));
        panel1.add("Center", PPanel);
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        top.add("Center", resultText);

        getContentPane().setLayout(new BorderLayout(3, 5));
        getContentPane().add("North", top);
        getContentPane().add("Center", panel1);
        for (int i = 0; i < KEYS.length; i++) {
            keys[i].addActionListener(this);
        }
        for (int i = 0; i < COMMAND.length; i++) {
            commands[i].addActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent e){
        String label = e.getActionCommand();
        if (label.equals(COMMAND[0])) {
            handleDEL();
        }
        else if (label.equals(COMMAND[1])) {
            handleAC();
        }
        else if ("0123456789.".indexOf(label) >= 0) {
            handleNumber(label);
        }
        else if(label.equals("π")){
            resultText.setText(String.valueOf(3.1415926));
        }
        else if ( label.equals("关闭") )
        {
            System.exit( 1 );
        }
        else
            handleOperator(label);
    }

    private void handleDEL() {
        String text = resultText.getText();
        int i = text.length();
        if (i > 0) {
            text = text.substring(0, i - 1);
            if (text.length() == 0) {
                resultText.setText("0");
                firstDigit = true;
                operator = "=";
            }
            else
                resultText.setText(text);
        }
    }

    private void handleNumber(String key) {
        if (firstDigit) {
            resultText.setText(key);
        }
        else if ((key.equals(".")) && (resultText.getText().indexOf(".") < 0)) {
            resultText.setText(resultText.getText() + ".");
        }
        else if (!key.equals(".")) {
            resultText.setText(resultText.getText() + key);
        }
        firstDigit = false;
    }

    private void handleAC() {
        resultText.setText("0");
        firstDigit = true;
        operator = "=";
    }

    private void handleOperator(String key) {
        if (operator.equals("sin")) {
            resultNum = Math.sin(resultNum);
        }
        else if (operator.equals("cos")) {
            resultNum = Math.cos(resultNum);
        }
        else if (operator.equals("tan")) {
            resultNum = Math.tan(resultNum);
        }
        else if (operator.equals("cot")) {
            resultNum =1/( Math.tan(resultNum));
        }
        else if (operator.equals("arcsin")) {
            resultNum =1/( Math.asin(resultNum));
        }
        else if (operator.equals("arccos")) {
            resultNum = Math.acos(resultNum);
        }
        else if (operator.equals("arctan")) {
            resultNum = Math.atan(resultNum);
        }
        else if (operator.equals("arccot")) {
            resultNum =1/( Math.atan(resultNum));
        }
        else if (operator.equals("=")) {
            resultNum = getNumberFromText();
        }
        if (operateValidFlag) {
            long t1;
            double t2;
            t1 = (long) resultNum;
            t2 = resultNum - t1;
            if (t2 == 0) {
                resultText.setText(String.valueOf(t1));
            }
            else {
                resultText.setText(String.valueOf(resultNum));
            }
        }
        operator = key;
        firstDigit = true;
        operateValidFlag = true;
    }

    private double getNumberFromText() {
        double result = 0;
        try {
            result = Double.valueOf(resultText.getText()).doubleValue();
        } catch (NumberFormatException e) {}
        return result;
    }

    public static void main(String args[]) {
        TriCalc application = new TriCalc();
        application.setVisible(true);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
