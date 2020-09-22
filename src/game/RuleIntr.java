package game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

class RuleIntr extends JFrame{
    JLabel jl;
    String content = "<html>用户可以选择猜数字(0,1,2,3,4,5,6,7,8,9)或者字母(A,B,C,D,E,F,G,H,I,J)。" +
            "开始游戏后，产生一个没有重复数字/字母的4位随机数，下面以数字为例讲解规则。" +
            "用户每猜一个数字，显示出“完全猜中的数字个数”和“猜中数字但位置错误的数字个数”，" +
            "比如nAmB，数字n表示猜中的位置正确的数字个数，数字m表示数字正确而位置不对的数字个数。" +
            "例如，正确答案为5234，如果用户猜5346，则显示：1A2B，数字1表示数字5及其位置猜对了，" +
            "数字3和4这两个数字猜对了，但是位置没对，记为2B。然后，用户根据游戏提示的信息继续猜，" +
            "直到猜中为止。游戏设计了两种场次，普通场次入场不需要消耗积分，要求在15次之内猜出可以获得一个积分，" +
            "大师场入场需要消耗1积分，要求在10次内猜出答案，可以获得3个积分，若在规定次数内未获得正确答案，" +
            "入场积分不反退给用户，用户可以自选计分规则，系统根据猜中的次数计算积分，并可以显示不同用户的排行榜。</html>";
    public RuleIntr() {
        jl = new JLabel(content);
        jl.setFont(new Font("黑体",0,12));
        Container jp=this.getContentPane();
        jp.setBackground(Color.white);
//        jp.setLayout(new FlowLayout(FlowLayout.CENTER));//为了让提示显示正中间
        jp.add(jl);

        this.setBackground(Color.WHITE);
        this.setSize(300, 350);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //获取屏幕长宽
        Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        int width=screensize.width;
        int height=screensize.height;
        this.setLocation((width-300)/2, (height-350)/2);//使程序显示在屏幕中间
        this.setResizable(false);//窗体不可扩大
        this.setVisible(true);
    }
}