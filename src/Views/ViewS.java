package Views;

import Controllers.ControllerS;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;


import static java.awt.Font.*;

public class ViewS extends JFrame {
    private Font font_name = new Font("Time new Roman", Font.BOLD, 40);

    public JFrame jf_login = new JFrame();
    public JFrame jf_menu = new JFrame();

    public JTextField jt_user;
    public JPasswordField jpass_pass;
    public JButton jb_login;
    public  JButton jb_dadn;
    public ImageIcon icon = new ImageIcon("");
    public Image image = icon.getImage();
    public JLabel jl_dadn;
    public JTextField jtRegister_user;
    public JTextField jtRegister_pass;
    public JTextField jtRegister_gmail;
    public JButton jb_register;
    public JTextField jt_masinhvien;
    public JTextField jt_ten;
    public JTextField jt_ly;
    public JTextField jt_hoa;
    public JTextField jt_toan;
    public JTextField jt_timkiem;
    public JButton jb_Add;
    public JButton jb_Remove;
    public JButton jb_timkiem;
    private JPanel panel = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    public DefaultTableModel model1;
    public DefaultTableModel model2;
    public JTable table;

    ControllerS contronller = new ControllerS(this);



    public void menu() {
        jf_menu.setTitle("Quản lí học sinh");
        jf_menu.setSize(700, 350);
        jf_menu.setLocationRelativeTo(null);
        jf_menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JLabel jl_ID = new JLabel("Mã SV");
        jl_ID.setBounds(0, 0, 64, 30);
        JLabel jl_name = new JLabel("Tên");
        jl_name.setBounds(0, 30, 64, 30);
        JLabel jl_ly = new JLabel("Điểm Lý");
        jl_ly.setBounds(0, 60, 64, 30);
        JLabel jl_toan = new JLabel("Điểm hóa");
        jl_toan.setBounds(0, 90, 64, 30);
        JLabel jl_hoa = new JLabel("Điểm toán");
        jl_hoa.setBounds(0, 120, 64, 30);
        JLabel jl_timkiem = new JLabel("Tìm kiếm");
        jl_timkiem.setBounds(0, 150, 64, 30);


        jt_masinhvien = new JTextField();
        jt_masinhvien.setBounds(62, 0, 140, 30);
        jt_ten = new JTextField();
        jt_ten.setBounds(62, 30, 140, 30);
        jt_ly = new JTextField();
        jt_ly.setBounds(62, 60, 140, 30);
        jt_hoa = new JTextField();
        jt_hoa.setBounds(62, 90, 140, 30);
        jt_toan = new JTextField();
        jt_toan.setBounds(62, 120, 140, 30);
        jt_timkiem = new JTextField();
        jt_timkiem.setBounds(62, 150, 140, 30);

        jb_Add = new JButton("Thêm");
        jb_Add.addActionListener(contronller);

        jb_Remove = new JButton("Xóa");
        jb_Remove.addActionListener(contronller);

        jb_timkiem = new JButton("Tìm kiếm");
        jb_timkiem.addActionListener(contronller);

        panel3.add(jb_Add);
        panel3.add(jb_Remove);
        panel3.add(jb_timkiem);

        panel3.setLayout(new FlowLayout());
        panel.setLayout(new GridLayout(6,2));

        panel.add(jl_ID);
        panel.add(jt_masinhvien);
        panel.add(jl_name);
        panel.add(jt_ten);
        panel.add(jl_ly);
        panel.add(jt_ly);
        panel.add(jl_hoa);
        panel.add(jt_hoa);
        panel.add(jl_toan);
        panel.add(jt_toan);
        panel.add(jl_timkiem);
        panel.add(jt_timkiem);
        panel4.setLayout(new GridLayout(2, 1));
        panel4.add(panel, BorderLayout.CENTER);
        panel4.add(panel3, BorderLayout.SOUTH);

        //bảng 1
        JPanel jp_table = new JPanel();
         table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

         model1 = new DefaultTableModel();
        model1.addColumn("ID");
        model1.addColumn("Tên");
        model1.addColumn("Lý");
        model1.addColumn("Toán");
        model1.addColumn("Hóa");
      model2=new DefaultTableModel();
        model2.addColumn("ID");
        model2.addColumn("Tên");
        model2.addColumn("Lý");
        model2.addColumn("Toán");
        model2.addColumn("Hóa");

        ControllerS.loadDataFromFile(model1);


        table.setModel(model1);
        jp_table.add(scrollPane);


        jf_menu.getContentPane().add(panel4, BorderLayout.WEST);
        jf_menu.getContentPane().add(jp_table,BorderLayout.EAST);
        jf_menu.setVisible(true);
    }


    private void login_2() {
        login_1();
        jf_login.setTitle("Login");
        jf_login.setSize(800, 550);
        jf_login.setLocationRelativeTo(null);
        jf_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jp1 = new JPanel();

        JLabel jl_icon = new JLabel(icon);
        EmptyBorder kc_logo = new EmptyBorder(100, 10, 10, 10);
        jl_icon.setBorder(kc_logo);
        jl_icon.setBackground(Color.cyan);
        jl_icon.setOpaque(true);

        JLabel jl_name = new JLabel("Manage money");
        jl_name.setFont(new Font("Times New Roman", BOLD, 50));
        jl_name.setOpaque(true);
        jl_name.setBackground(Color.cyan);
        MatteBorder kc_name = new MatteBorder(0, 60, 5, 5, Color.cyan);
        jl_name.setBorder(kc_name);

        JPanel jp_phu = new JPanel();
        jp_phu.setLayout(new BorderLayout());
        jp_phu.add(jl_icon, BorderLayout.CENTER);
        jp_phu.add(jl_name, BorderLayout.SOUTH);

        jp1.add(jp_phu);

        jf_login.add(jp1, BorderLayout.NORTH);
        jf_login.setIconImage(image); // Set icon cho cửa sổ mở lên
        jp1.setBackground(Color.cyan);

        jf_login.setVisible(true);

    }
    private void login_1() {
        // Tạo nơi nhập tài khoản, mật khẩu để Login
        JPanel jp2 = new JPanel();

        // Panel chứa chữ Login
        JPanel jp_login = new JPanel();
        jp_login.setLayout(new FlowLayout());
        JLabel jl_login = new JLabel("Login");

        jl_login.setFont(new Font("Times New Roman", BOLD, 40));
        jp_login.add(new Label());
        jp_login.add(jl_login);
        jp_login.add(new JLabel());

        // Panel chứa JLabel và Jtextfield để nhập
        JPanel jp_nhap = new JPanel();

        JLabel jl_user = new JLabel("User         ");
        JLabel jl_pass = new JLabel("Passwork ");

        jt_user = new JTextField();
        jpass_pass = new JPasswordField();

        JPanel jp_user = new JPanel();
        jp_user.setLayout(new FlowLayout());
        jp_user.add(jl_user);
        jp_user.add(jt_user);

        JPanel jp_pass = new JPanel();
        jp_user.setLayout(new FlowLayout());
        jp_pass.add(jl_pass);
        jp_pass.add(jpass_pass);
        jp_user.setBackground(Color.cyan);
        jp_pass.setBackground(Color.cyan);

        // Set kích thức cho JLabel và JTextfield
        jt_user.setColumns(20);
        jpass_pass.setColumns(20);
        jl_user.setFont(new Font("Times New Roman", BOLD, 23));
        jl_pass.setFont(new Font("Times New Roman", BOLD, 23));

        jp_nhap.setLayout(new GridLayout(2, 1));
        jp_nhap.add(jp_user);
        jp_nhap.add(jp_pass);
        jp_nhap.setBackground(Color.cyan);

        // Panel chứa Button Login
        JPanel jp_button = new JPanel();



        jb_login = new JButton("Login");
        jb_dadn = new JButton("logged in last time");
        jb_login.addActionListener(contronller);
        jb_dadn.setPreferredSize(new Dimension(135, 35));
        jb_dadn.addActionListener(contronller);

        jb_login.setPreferredSize(new Dimension(135, 35));
        jp_button.add(new JLabel());
        jp_button.add(new JLabel());
        jp_button.add(jb_dadn);
        jp_button.add(jb_login);
        jp_login.setBackground(Color.cyan);
        jp_button.setBackground(Color.cyan);

        // Phần Quên mật khẩu
        jl_dadn = new JLabel("Register");
        jl_dadn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                jp2.setVisible(false);
                Register();

            }
        });


        EmptyBorder kc_qmk = new EmptyBorder(0, 230, 0, 0);
        jl_dadn.setBorder(kc_qmk);

        // Thêm phần nhập dữ liệu vào jp2
        jp2.setLayout(new GridLayout(5, 1));
        jp2.add(new JLabel());
        jp2.add(jp_login);
        jp2.add(jp_nhap);
        jp2.add(jp_button);
        jp2.add(jl_dadn);
        jp2.setBackground(Color.cyan);

        jf_login.add(jp2, BorderLayout.CENTER);
    }
    private void Register() {

        JPanel jp2 = new JPanel();

        // Panel chứa chữ Login
        JPanel jp_login = new JPanel();
        JLabel jl_login = new JLabel("Register");

        jl_login.setFont(font_name);
        jp_login.add(jl_login);
        jp_login.setBackground(Color.cyan);


        JPanel jp_nhap = new JPanel();

        JLabel jl_user = new JLabel("User        ");
        JLabel jl_pass = new JLabel("Passwork");
        JLabel jl_name = new JLabel("Gmail:   ");

        jtRegister_user = new JTextField();
        jtRegister_pass = new JPasswordField();
        jtRegister_gmail = new JTextField();

        JPanel jp_user = new JPanel();
        jp_user.setLayout(new FlowLayout());
        jp_user.add(jl_user);
        jp_user.add(jtRegister_user);
        jp_user.setBackground(Color.cyan);

        JPanel jp_pass = new JPanel();
        jp_user.setLayout(new FlowLayout());
        jp_pass.add(jl_pass);
        jp_pass.add(jtRegister_pass);
        jp_pass.setBackground(Color.cyan);


        JPanel jp_name = new JPanel();
        jp_name.setLayout(new FlowLayout());
        jp_name.add(jl_name);
        jp_name.add(jtRegister_gmail);
        jp_name.setBackground(Color.cyan);

        // Set kích thức cho JLabel và JTextfield
        jtRegister_user.setColumns(20);
        jtRegister_pass.setColumns(20);
        jtRegister_gmail.setColumns(20);
        jl_user.setFont(new Font("Times New Roman", BOLD, 20));
        jl_pass.setFont(new Font("Times New Roman", BOLD, 20));
        jl_name.setFont(new Font("Times New Roman", BOLD, 20));

        jp_nhap.setLayout(new GridLayout(3, 1));
        jp_nhap.add(jp_user);
        jp_nhap.add(jp_pass);
        jp_nhap.add(jp_name);
        // Panel chứa Button Login
        JPanel jp_button = new JPanel();
        jb_register = new JButton("Register");
        jb_register.addActionListener(contronller);
        jb_register.setPreferredSize(new Dimension(135, 35));
        jp_button.add(new JLabel());
        jp_button.add(new JLabel());
        jp_button.add(jb_register);
        jp_button.setBackground(Color.cyan);


        // Phần Quên mật khẩu
        JLabel jl_dn = new JLabel("Login");

        jl_dn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                jp2.setVisible(false);
                login_2();
            }
        });

        EmptyBorder kc_qmk = new EmptyBorder(0, 230, 0, 0);
        jl_dn.setBorder(kc_qmk);

        // Thêm phần nhập dữ liệu vào jp2
        jp2.setLayout(new GridLayout(4,1));
        jp2.add(jp_login,BorderLayout.NORTH);
        jp2.add(jp_nhap);
        jp2.add(jp_button);
        jp2.add(jl_dn);
        jp2.setBackground(Color.cyan);

        jf_login.add(jp2,BorderLayout.CENTER);
    }

    public ViewS() {
        login_2();
    }

}
