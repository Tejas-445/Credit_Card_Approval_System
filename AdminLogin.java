import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminLogin extends JFrame
{
    JLabel Title,Username,Password;

    JTextField jtUsername;

    JPasswordField jtPassword;
    JButton Login,Clear;

    Font font = new Font("Verdana",Font.BOLD,30);


    Font font_label = new Font("Times New Roman",Font.PLAIN,30);

    public String user_name,user_password;
    AdminLogin()
    {
        setTitle("Admin Login");
        setLocation(150, 30);
        getContentPane().setBackground(new Color(238, 232, 170));
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        JLabel label =new JLabel(i1);
        add(label);

        ImageIcon i2 = new ImageIcon("C:\\Users\\Tejas Ashok Sawant\\Desktop\\CreditCardApprovalSyatem-main\\icons\\logo.png");
        setIconImage(i2.getImage());

        Title = new JLabel("Welcome to Admin Login Page");
        Title.setFont(font);
        Title.setBounds(450,40,520,40);
        add(Title);


        Username = new JLabel("Username");
        Username.setFont(font_label);
        Username.setBounds(460,180,200,40);
        add(Username);

        jtUsername = new JTextField();
        jtUsername.setBounds(610,180,280,40);
        add(jtUsername);

        Password = new JLabel("Password");
        Password.setFont(font_label);
        Password.setBounds(460,260,280,40);
        add(Password);

        jtPassword = new JPasswordField();
        jtPassword.setBounds(610,260,280,40);
        add(jtPassword);

        Login = new JButton("Sign In");
        Login.setBounds(580, 370, 120, 40);
        add(Login);

        Clear = new JButton("Clear");
        Clear.setBounds(720, 370, 120, 40);
        add(Clear);


        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = jtUsername.getText();
                String password = new String(jtPassword.getPassword());

                if (password.isEmpty() && username.isEmpty()) {
                    JOptionPane.showMessageDialog(AdminLogin.this, "Please enter both username and password");
                } else if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(AdminLogin.this, "Please enter password");
                } else if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(AdminLogin.this, "Please enter username.");
                } else {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/creditcardapprovalsystem", "captain", "captain@1234");
                        PreparedStatement pst = con.prepareStatement("select * from adminlogin  where  (username=? and password=?)");
                        pst.setString(1, username);
                        pst.setString(2, password);
                        ResultSet r = pst.executeQuery();

                        if (r.next()) {
//                            user_id = r.getInt("userid");
                            user_name = r.getString("username");
                            user_password = r.getString("password");

                            JOptionPane.showMessageDialog(AdminLogin.this, "Login Successful ");
                            dispose();
                            new AdminHomePage();
                        } else {
                            JOptionPane.showMessageDialog(AdminLogin.this, "Invalid Username/Password or UserID");
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });


        Clear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                jtUsername.setText("");
                jtPassword.setText("");
            }
        });

        setLayout(null);
        setSize(1300,700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args)
    {
        String uname="",passwd="";
        AdminLogin login = new AdminLogin();
    }
}
