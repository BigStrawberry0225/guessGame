package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GameUI extends JFrame implements MouseListener{

    JPanel panel = new JPanel();
    JPanel rank = new JPanel();
    boolean isOpen = false;
    JScrollPane jsp;
    JList jl = new JList();

    String userID;
    JLabel userID1;
    JLabel point;
    JLabel rrank;
    JLabel rankName = new JLabel("排行榜");
    JLabel ruleItro = new JLabel("规则介绍");
    JLabel gameName = new JLabel("猜一猜游戏");
    JLabel costTip = new JLabel("(入场需要消耗1积分)");
    JLabel costTip2 = new JLabel("(入场需要消耗1积分)");
    JButton numGame1 = new JButton("数字免费场");
    JButton numGame2 = new JButton("数字大师场");
    JButton letGame1 = new JButton("字母免费场");
    JButton letGame2 = new JButton("字母大师场");

    Profile r = new Profile();

    public GameUI(String userID1) {
        super("猜一猜游戏");
        userID = userID1;
        this.userID1 = new JLabel("用户:"+userID1);
        rrank = new JLabel("当前积分:"+r.getPoint(userID1));
        point = new JLabel("当前排名"+r.getRank(userID1)+"/"+r.getUserNum());
        init();
    }

    private void init() {
        setSize(500, 350);
        setLayout(null);
        setLocationRelativeTo(null);

        panel.setBounds(0, 0, 500, 350);
        panel.setLayout(null);

        gameName.setFont(new Font("猜数字游戏",Font.BOLD, 40));
        gameName.setForeground(Color.BLUE);
        panel.add(gameName);
        gameName.setBounds(210, 50, 210, 40);

        panel.add(numGame1);
        numGame1.setBounds(190, 120, 110, 45);
        numGame1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new NGameOne(userID);
            }
        });

        panel.add(numGame2);
        numGame2.setBounds(190, 200, 110, 45);
        numGame2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new NGameTwo(userID);
            }
        });

        panel.add(letGame1);
        letGame1.setBounds(320, 120, 110, 45);
        letGame1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LGameOne(userID);
            }
        });

        panel.add(letGame2);
        letGame2.setBounds(320, 200, 110, 45);
        letGame2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LGameTwo(userID);
            }
        });

        //用户信息
        panel.add(userID1);
        userID1.setBounds(150, 5, 150, 25);
        panel.add(point);
        point.setBounds(270, 5, 80, 25);
        panel.add(rrank);
        rrank.setBounds(380, 5, 110, 25);

        //积分提示
        panel.add(costTip);
        costTip.setForeground(Color.red);
        costTip.setBounds(188, 235, 120, 45);
        panel.add(costTip2);
        costTip2.setForeground(Color.red);
        costTip2.setBounds(318, 235, 120, 45);

        //排名信息和规则提示
        panel.add(rankName);
        rankName.setForeground(Color.red);
        rankName.setBounds(35, 40, 100, 25);
        rankName.setFont(new Font("排行榜",Font.BOLD, 20));

        ruleItro.setFont(new Font("规则介绍",Font.BOLD, 20));
        ruleItro.setForeground(Color.magenta);
        ruleItro.setBounds(27, 10, 100, 20);
        ruleItro.addMouseListener(this);
        panel.add(ruleItro);

        rank.setLayout(null);

        String[] list = r.getRank();
        jl.setListData(list);
        jsp = new JScrollPane(jl);
        jsp.setBounds(5, 5, 140, 235);
        rank.add(jsp);

        rank.setBounds(5, 70, 150, 242);
        rank.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        add(rank);

        JLayeredPane jlp = new JLayeredPane();
        jlp.setPosition(panel, -1);
        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }


    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == ruleItro) {
            new RuleIntr();
        }
    }

    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
