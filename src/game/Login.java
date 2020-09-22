package game;

import com.jdbc.conn.ConnectionClass;
import com.jdbc.conn.InfoOperate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Login extends JFrame {
    JTextField usernumberText;
    JPasswordField passwordText;
    JButton loginButton;
    JButton cancelButton;
    JButton registerButton;
    JLabel usernameLabel;
    JLabel passwordLabel;

    String userID;
    String password;

    public static void main(String []args){
        Login login = new Login();
    }

    public Login(){
        usernameLabel = new JLabel("UserID");
        usernameLabel.setBounds(160, 100, 120, 35);  //设置位置大小
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        this.add(usernameLabel);

        usernumberText = new JTextField();
        usernumberText.setBounds(250, 100, 150, 35);
        usernumberText.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        usernumberText.setOpaque(false);
        usernumberText.setForeground(Color.white);
//		usernameText.setBorder(null);
        this.add(usernumberText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(150, 150, 150, 35);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        this.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(250, 150, 150, 35);
        passwordText.setOpaque(false);
        passwordText.setForeground(Color.WHITE);
//		passwordText.setBorder(null);
        this.add(passwordText);

        loginButton = new JButton("进入游戏");
        loginButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        loginButton.setBounds(200, 250, 100, 35);
        loginButton.setForeground(Color.WHITE);
        loginButton.setOpaque(false);
        loginButton.setContentAreaFilled(false);

        this.add(loginButton);
        registerButton = new JButton("注册");
        registerButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        registerButton.setBounds(410, 100, 80, 35);
        registerButton.setForeground(Color.WHITE);
        registerButton.setOpaque(false);
        registerButton.setContentAreaFilled(false);
        this.add(registerButton);

        cancelButton = new JButton("退出");
        cancelButton.setFont(new Font("微软雅黑",Font.PLAIN,18));
        cancelButton.setBounds(330, 250, 100, 35);
        cancelButton.setOpaque(false);
        cancelButton.setContentAreaFilled(false);
        cancelButton.setForeground(Color.WHITE);
//        cancelButton.setBorderPainted(false);
        this.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        this.add(new LoginPanel());

        //注册监听
        //监听
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //新建一个用户信息类
                userID=usernumberText.getText();
                password=passwordText.getText();

                //实例化一个管理类
                InfoOperate userInfo = new InfoOperate(new ConnectionClass());
                if(userInfo.selectPassword(userID,password)) {//用户名不重复
                    new GameUI(userID);
                    dispose();
                }
                else {//用户名重复
                    JLabel jl = new JLabel("用户名或密码错误");
                    jl.setForeground(Color.red);
                    new ReturnFrame(jl);
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Regist();
            }
        });
        //窗体属性
        Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(600,350);
        this.setLocationRelativeTo(null);
        this.setResizable(false);//窗体不可扩大
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭时退出程序
    }
    public class LoginPanel extends JPanel {
        private Image image;
        public LoginPanel() {
            try {
                image = ImageIO.read(new File("src/image/login.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void paint (Graphics g) {
            super.paint(g);
            g.drawImage(image, 0, 0, 600, 400, null);
        }
    }
}
