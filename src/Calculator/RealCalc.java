package Calculator;
// * * 科学计算器 * *

import java.awt.*;       //java抽象窗口工具包
import java.awt.event.*; //（java.awt.*）这个是引入awt包下的所有文件,而不能引用awt包里子包的所有文件,而event是awt包下的子包,所以要另加引用
import javax.swing.*;    //java图形可视包
import java.awt.Color;   //对照颜色代码对照表
import java.lang.String;

public class RealCalc extends JFrame implements ActionListener {    //JFrame图形界面，ActionListener用于接收操作事件的侦听器接口
    private final String[] KEYS = { "π", "e", "÷", "7", "8","9", "×", "4", "5", "6", "-", "1", "2", "3","+",".","0", "=" };
    private final String[] COMMAND = { "DEL", "AC" };
    private JButton keys[] = new JButton[KEYS.length];
    private JButton commands[] = new JButton[COMMAND.length];
    private JTextField resultText = new JTextField("0"); //计算结果文本框
    private boolean firstDigit = true; //标志用户按的是否是整个表达式的第一个数字,或者是运算符后的第一个数字
    private double resultNum = 0.0;    //计算的中间结果
    private String operator = "=";     //当前的运算符
    private boolean operateValidFlag = true;//操作是否合法
    private final String[] KEYS2= {" "," ","退出","√￣","3√￣","X!","X^2","X^3","X^Y","1/x","ln(X)","e^X","%","|X|"," "};
    private JButton keys2[]=new JButton[KEYS2.length];
    StringBuffer sb = new StringBuffer();
    String vl = null;
    private JPanel contentPane,contentpane2;
    private FlowLayout layout;
    public RealCalc(){
        setTitle("科学计算器");
        this.setBackground(Color.LIGHT_GRAY);
        this.setTitle("科学计算器");
        this.setLocation(50, 50);
        this.setSize(800, 550);
        this.setResizable(false);          //窗口大小不可改变
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
        menuItem_4.addActionListener(new ActionListener() {
                                       public void actionPerformed(ActionEvent e) {
                                           TriCalc ordinary = new TriCalc();
                                           ordinary.setVisible(true);
                                       }
                                   }
        );

        resultText.setHorizontalAlignment(JTextField.RIGHT);//输出结果右对齐
        resultText.setBackground(Color.white);              //设置输出框的背景颜色
        resultText.setFont(new Font("TimesRoman",Font.PLAIN,30));
        JPanel PPanel=new JPanel();//JPanel可以为添加到窗体中的轻型控件提供通用的容器
        PPanel.setLayout(new GridLayout(5, 4, 3, 3));//设置布局管理器为GridLayout，将界面区域划分为5行4列
        for (int i = 0; i < COMMAND.length; i++) {
            commands[i] = new JButton(COMMAND[i]);
            PPanel.add(commands[i]);
            commands[i].setFont(new Font("微软雅黑",Font.PLAIN,30));//设置commomds的字体为30磅、普通样式、微软雅黑
            commands[i].setForeground(Color.BLUE);//设置字体颜色
        }
        for (int i = 0; i < KEYS.length; i++) {
            keys[i] = new JButton(KEYS[i]);
            PPanel.add(keys[i]);
            keys[i].setFont(new Font("微软雅黑",Font.PLAIN,30));//设置keyss的字体为30磅、普通样式、微软雅黑
            keys[i].setBackground(Color.DARK_GRAY); //设置背景颜色
            keys[i].setForeground(Color.black);     //设置字体颜色
        }
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(3,5));
        panel1.add("Center", PPanel);
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        top.add("Center", resultText);
        contentPane=new JPanel();
        contentPane.setLayout(new BorderLayout(3,5));
        contentPane.add("North", top);
        contentPane.add("Center", panel1);
        JPanel ppanel=new JPanel();
        ppanel.setLayout(new GridLayout(5, 4, 3, 3));
        for (int i = 0; i < KEYS2.length; i++) {
            keys2[i] = new JButton(KEYS2[i]);
            ppanel.add(keys2[i]);
            keys2[i].setFont(new Font("微软雅黑",Font.PLAIN,30));//设置keys2的字体为30磅、普通样式、微软雅黑
            keys2[i].setBackground(Color.LIGHT_GRAY);  //设置背景颜色
            keys2[i].setForeground(Color.GRAY);       //设置字体颜色
        }
        contentpane2 = new JPanel();
        contentpane2.setLayout(new BorderLayout(10,15));
        contentpane2.add("Center", ppanel);

        layout=new FlowLayout();
        JPanel ane= new JPanel();
        ane.setLayout(layout);
        getContentPane().setLayout(new BorderLayout(3,5));
        getContentPane().add("Center", contentPane);
        getContentPane().add("East", contentpane2);

        for (int i = 0; i < KEYS.length; i++) {
            keys[i].addActionListener(this);
        }
        for (int i = 0; i < COMMAND.length; i++) {
            commands[i].addActionListener(this);
        }
        for (int i = 0; i < KEYS2.length; i++)
            keys2[i].addActionListener(this);
        setVisible(true);
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
        else if(label.equals("π"))
            resultText.setText(String.valueOf(3.141592));
        else if(label.equals("e"))
            resultText.setText(String.valueOf(2.718281));
        else if ( label.equals("退出") )
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
        if (operator.equals("÷")) {
            if (getNumberFromText() == 0.0) {
                operateValidFlag = false;
                resultText.setText("Math ERROR");
            }
            else
                resultNum /= getNumberFromText();
        }
        else if (operator.equals("1/x")) {
            if (resultNum == 0.0) {
                operateValidFlag = false;
                resultText.setText("Math ERROR");
            }
            else
                resultNum = 1 / resultNum;
        }
        else if (operator.equals("+")) {
            resultNum += getNumberFromText();
        }
        else if (operator.equals("-")) {
            resultNum -= getNumberFromText();
        }
        else if (operator.equals("×")) {
            resultNum *= getNumberFromText();
        }
        else if (operator.equals("|X|")) {
            resultNum = Math.abs(resultNum);
        }
        else if (operator.equals("X^2")) {
            resultNum = resultNum*resultNum;
        }
        else if (operator.equals("X^3")) {
            resultNum = resultNum*resultNum*resultNum;
        }
        else if (operator.equals("X^Y")) {
            resultNum = Math.pow(resultNum,getNumberFromText());
        }
        else if (operator.equals("√￣")) {
            resultNum = Math.sqrt(resultNum);
        }
        else if (operator.equals("3√￣")) {
            resultNum = Math.cbrt(resultNum);
        }
        else if (operator.equals("X!")) {
            int ee;
            double re=resultNum ;
            int c=1;
            for(ee=1;ee<=re;ee++)
            {
                c=c*ee;
                resultNum=c;}
        }else if (operator.equals("e^X")) {
            resultNum = Math.exp(resultNum);
        }
        else if (operator.equals("ln(X)")) {
            resultNum = Math.log(resultNum);
        }
        else if (operator.equals("%")) {
            resultNum = getNumberFromText()*0.01;
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
        String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RealCalc application = new RealCalc();
                    application.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        );
    }
}