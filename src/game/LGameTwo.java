package game;

import com.jdbc.conn.ConnectionClass;
import com.jdbc.conn.InfoOperate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LGameTwo extends JFrame{

    String userID;
    InfoOperate userInfo = new InfoOperate(new ConnectionClass());
    JPanel panel = new JPanel();
    JLabel one;
    JLabel two;
    JLabel three;
    JLabel four;

    JLabel times = new JLabel("次数:");
    JLabel time = new JLabel("0");
    int count = 0;

    JLabel maxTimes = new JLabel("限制次数:");
    JLabel maxTime = new JLabel("10");

    JTextField userGuess = new JTextField(4);
    JButton sub = new JButton("确认");
    JButton back = new JButton("退出游戏");

    JLabel record = new JLabel("猜测记录");
    JPanel rank = new JPanel();
    JScrollPane jsp;
    JList jl = new JList();
    String[] list = new String[20];

    Solution s = new Solution();
    String answer = "";
    char[] ans = new char[4];

    public LGameTwo(String userID){
        super("猜字母大师场");
        userInfo.subOne(userID);
        this.userID = userID;
        ans = s.getLetter();
        for(int i=0;i<4;i++) answer+=ans[i];
        init();
    }

    private void init(){
        setSize(500, 350);
        setLayout(null);
        setLocationRelativeTo(null);

        panel.setBounds(0, 0, 500, 350);
        panel.setLayout(null);

        one = new JLabel("？");
        two = new JLabel("？");
        three = new JLabel("？");
        four = new JLabel("？");

        one.setBounds(21, 20, 50, 120);
        two.setBounds(99, 20, 50, 120);
        three.setBounds(176, 20, 50, 120);
        four.setBounds(254, 20, 50, 120);
        one.setFont(new Font("one",Font.BOLD, 40));
        two.setFont(new Font("two",Font.BOLD, 40));
        three.setFont(new Font("three",Font.BOLD, 40));
        four.setFont(new Font("four",Font.BOLD, 40));
        one.setForeground(Color.blue);
        two.setForeground(Color.blue);
        three.setForeground(Color.blue);
        four.setForeground(Color.blue);

        panel.add(one);
        panel.add(two);
        panel.add(three);
        panel.add(four);

        userGuess.setBounds(100, 230, 90, 20);
        panel.add(userGuess);
        sub.setBounds(100, 260, 90, 25);
        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subAction();
            }
        });
        panel.add(sub);

        back.setBounds(360, 13, 87, 25);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameUI(userID);
                dispose();
            }
        });

        times.setBounds(61, 170, 45, 25);
        panel.add(times);
        time.setBounds(100, 170, 45, 25);
        panel.add(time);

        maxTimes.setBounds(171, 170, 145, 25);
        maxTimes.setForeground(Color.red);
        panel.add(maxTimes);
        maxTime.setBounds(230, 170, 45, 25);
        maxTime.setForeground(Color.red);
        panel.add(maxTime);

        rank.setLayout(null);
        jsp = new JScrollPane(jl);
        jsp.setBounds(5, 5, 140, 215);
        rank.add(jsp);

        rank.setBounds(330, 85, 150, 222);
        rank.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        panel.add(rank);

        record.setBounds(364, 48, 87, 25);
        record.setFont(new Font("one",Font.BOLD, 20));
        record.setForeground(Color.red);
        panel.add(record);

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void subAction() {
        String guessValue = userGuess.getText();
        char[] value = guessValue.toCharArray();
        if(value.length!=4) return;

        count++;
        time.setText(count+"");

        int[] tip = s.tip(value);
        if(tip[0]==4){
            JOptionPane.showMessageDialog(panel,"恭喜您挑战成功！共猜测"+count+"次，获得3积分！","标题",JOptionPane.DEFAULT_OPTION);
            userInfo.addThree(userID);
            dispose();
            new GameUI(userID);
        }
        else {
            one.setText(tip[0]+"");
            one.setForeground(Color.red);
            two.setText("A");
            three.setText(tip[1]+"");
            three.setForeground(Color.red);
            four.setText("B");
        }

        System.out.println(answer);
        if(count==10){
            String answer = "";
            for(int i=0;i<4;i++) answer+=ans[i];
            JOptionPane.showMessageDialog(panel,"挑战失败，正确答案为"+answer,"标题",JOptionPane.DEFAULT_OPTION);
            dispose();
            new GameUI(userID);
        }
        list[count] = userGuess.getText()+"-----"+tip[0]+"A"+tip[1]+"B";
        jl.setListData(list);
        validate();
    }
}
