package game;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.jdbc.conn.ConnectionClass;
import com.jdbc.conn.InfoOperate;

/*
 * 注册界面框架
 */
public class Regist extends JFrame{

    JTextField usernumberText;
    JTextField passwordText;

    JButton registerButton;
    JButton cancelButton;

    JLabel usernumberLabel;
    JLabel passwordLabel;

    String userID;
    String password;

    int power=0;
    public Regist() {

        usernumberLabel = new JLabel("UserID");
        usernumberLabel.setBounds(45, 100, 120, 35);
        usernumberLabel.setForeground(Color.red);
        usernumberLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        this.add(usernumberLabel);

        usernumberText = new JTextField();
        usernumberText.setBounds(140, 100, 100, 35);
        usernumberText.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        usernumberText.setOpaque(false);
        usernumberText.setForeground(Color.pink);
//		usernumberText.setBorder(null);
        this.add(usernumberText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(30, 150, 150, 35);
        passwordLabel.setForeground(Color.red);
        passwordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        this.add(passwordLabel);

        passwordText = new JTextField();
        passwordText.setBounds(140, 150, 100, 35);
        passwordText.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        passwordText.setOpaque(false);
        passwordText.setForeground(Color.pink);
//		passwordText.setBorder(null);
        this.add(passwordText);

        registerButton = new JButton("完成注册");
        registerButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        registerButton.setBounds(35, 270, 100, 35);
        registerButton.setForeground(Color.red);
        registerButton.setOpaque(false);
        registerButton.setContentAreaFilled(false);
        this.add(registerButton);

        cancelButton = new JButton("取消");
        cancelButton.setFont(new Font("微软雅黑",Font.PLAIN,18));
        cancelButton.setBounds(160, 270, 100, 35);
        cancelButton.setOpaque(false);
        cancelButton.setContentAreaFilled(false);
        cancelButton.setForeground(Color.red);
        this.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //注册监听
        registerButton.addActionListener(new ActionListener() {//为注册按钮注册监听
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取填写的信息
                userID=usernumberText.getText();
                password=passwordText.getText();
                if(userID.equals("")|password.equals("")) {//如果任何一项为空
                    JLabel jl=new JLabel("不能为空");
                    jl.setForeground(Color.red);
                    new ReturnFrame(jl);
                }
                else {
                    //实例化一个管理类
                    InfoOperate userInfo = new InfoOperate(new ConnectionClass());
                    if(!userInfo.selectID(userID)) {//用户名不重复
                        userInfo.insertUser(userID, password);//添加新用户
                        JLabel jl = new JLabel("注册成功!");
                        jl.setForeground(Color.red);
                        new ReturnFrame(jl);
                        dispose();
                    }
                    else {//用户名重复
                        JLabel jl = new JLabel("该用户已存在,不可以注册");
                        jl.setForeground(Color.red);
                        new ReturnFrame(jl);
                    }
                }
            }
        });

        //创建窗口
        this.setTitle("注册");
        this.setSize(300, 400);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //获取屏幕长宽
        Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        int width=screensize.width;
        int height=screensize.height;
        this.setLocation((width-300)/2, (height-400)/2);//使程序显示在屏幕中间
        this.add(new RegisterPanel());
        this.setUndecorated(true);
        this.setVisible(true);
    }
    public class RegisterPanel extends JPanel {
        private Image image;
        public RegisterPanel() {
            try {
                image = ImageIO.read(new File("src/image/regist.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void paint (Graphics g) {
            super.paint(g);
            g.drawImage(image, 0, 0, 300, 500, null);

        }
    }
}
