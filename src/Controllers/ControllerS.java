package Controllers;

import Models.Model;

import Views.ViewS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;
import java.io.*;
import java.util.stream.IntStream;

public class ControllerS implements ActionListener {
    private static final String FILE_account ="D:\\btoop\\testMVC\\src\\Data\\account.txt";

    private static final String FILE_data = "D:\\btoop\\testMVC\\src\\Data\\data.txt";

    private static ViewS view;
    private Model model;
    public DefaultTableModel model2;



    public ControllerS(ViewS view){
        this.view = view;


    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==view.jb_login) {
            CheckLogin();
        }
        if (e.getSource()==view.jb_dadn){
            Outaccount();
        }
        if (e.getSource()==view.jb_register){
            Register();
        }
        if (e.getSource()==view.jb_Add){
            addDataToFile();
            resetJText();
            reloadData(view.model1);
        }
        if (e.getSource()==view.jb_Remove){
            deleteSelectedRow(view.table);
            saveDataToFile(view.model1);
            loadDataFromFile(view.model1);
            reloadData(view.model1);
        }
        if (e.getSource()==view.jb_timkiem){
            String searchName = view.jt_timkiem.getText().trim().toLowerCase();
            searchNameInTable(searchName);

        }
    }
    private void Register() {
        String user = view.jtRegister_user.getText();
        String passwork = view.jtRegister_pass.getText();
        String matkhau = new String(passwork);
        String gmail = view.jtRegister_gmail.getText();

        Connection connection = null;
        String jdbcURL = "jdbc:mysql://localhost:3306/information?useSSL=false";
        String username = "root";
        String pass = "0976794934t";
        String driver = "com.mysql.cj.jdbc.Driver";
        if (model.isValidEmail(gmail)) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(jdbcURL, username, pass);

                String insertQuery = "INSERT INTO healli.acctout (User, Passworld, Gmail) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, matkhau);
                preparedStatement.setString(3, gmail);
                preparedStatement.executeUpdate();

                preparedStatement.close();
                connection.close();
                JOptionPane.showMessageDialog(view, "Đăng ký thành công");
                Reset_Register();
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(view, "Tài khoản đã tồn tại");
                e.printStackTrace();
            }
        }else {
            JOptionPane.showMessageDialog(view, "gmail không đúng");
        }
    }

    private void Reset_Register() {
        view.jtRegister_user.setText("");
        view.jtRegister_pass.setText("");
        view.jtRegister_gmail.setText("");
    }
    public static void loadDataFromFile(DefaultTableModel model) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_data))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String id = parts[0].trim();
                    String ten = parts[1].trim();
                    String toan = (parts[2].trim());
                    String ly = (parts[3].trim());
                    String hoa =(parts[4].trim());
                    model.addRow(new Object[]{id, ten, toan, ly, hoa});
                } else {
                    System.out.println("Dòng dữ liệu không đúng định dạng: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void addDataToFile() {
        String id = view.jt_masinhvien.getText();
        String ten = view.jt_ten.getText();
        String toan = view.jt_toan.getText();
        String ly = view.jt_ly.getText();
        String hoa = view.jt_hoa.getText();
        System.out.println("id");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_data, true))) {
            writer.write(id + "," + ten + "," + toan + "," + ly + "," + hoa);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadData(DefaultTableModel model) {
        model.setRowCount(0);
        loadDataFromFile(model);
    }
    public void CheckLogin() {

        String taikhoan = view.jt_user.getText();
        char[] password = view.jpass_pass.getPassword();
        String matkhau = new String(password);

        if (taikhoan.equals("") || matkhau.equals("")) {
            JOptionPane.showMessageDialog(view.jf_login, "Tài khoản và mật khẩu không được để trống");
            return;
        }

        String jdbcURL = "jdbc:mysql://localhost:3306/information?useSSL=false";
        String username = "root";
        String pass = "0976794934t";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, pass)) {
            String query = "SELECT * FROM healli.acctout WHERE( `user` = ? AND `passworld` = ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, taikhoan);
                preparedStatement.setString(2, matkhau);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    view.jf_login.setVisible(false);
                    view.menu();
                    Inaccount();

                } else {
                    JOptionPane.showMessageDialog(view.jf_login, "Tài khoản hoặc mặt khẩu không chính xác");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void  Inaccount(){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_account))) {
            writer.write(view.jt_user.getText());
            writer.write(",");
            writer.write(view.jpass_pass.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Outaccount(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FILE_account));
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String user = parts[0].trim();
                    String passwork = parts[1].trim();
                    view.jt_user.setText(user);
                    view.jpass_pass.setText(passwork);

                } else {
                    System.out.println("Lỗi ở dòng " + lineNumber + ": Dòng dữ liệu không đúng định dạng.");
                    System.out.println("Dòng lỗi: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi đọc dữ liệu từ tệp account.txt.");
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void  resetJText(){
        view.jt_masinhvien.setText("");
        view.jt_ten.setText("");
        view.jt_toan.setText("");
        view.jt_ly.setText("");
        view.jt_hoa.setText("");

    }



    public void deleteSelectedRow(JTable table) {
        // Lấy chỉ mục của hàng được chọn
        int selectedRow = table.getSelectedRow();

        // Kiểm tra xem có hàng nào được chọn không
        if (selectedRow != -1) {
            // Xóa hàng từ mô hình của JTable
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng cần xóa.");
        }
    }
    private static void saveDataToFile(DefaultTableModel model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_data))) {
            int rowCount = model.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < model.getColumnCount(); j++) {
                    line.append(model.getValueAt(i, j));
                    if (j < model.getColumnCount() - 1) {
                        line.append(",");
                    }
                }
                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void searchNameInTable(String searchName) {
        model2 = new DefaultTableModel();
        model2.addColumn("Mã SV");
        model2.addColumn("Tên");
        model2.addColumn("Lý");
        model2.addColumn("Điểm Toán");
        model2.addColumn("Hóa");
        view.table.setModel(model2);
        DefaultTableModel model = view.model1;
        DefaultTableModel rightTableModel = model2; // Sử dụng model2 đã được khởi tạo ở constructor

        // Xóa tất cả các hàng đã có trong bảng bên phải
        rightTableModel.setRowCount(0);

        // Sử dụng IntStream để duyệt qua tất cả các hàng trong bảng bên trái
        IntStream.range(0, model.getRowCount())
                .filter(row -> {
                    String name = model.getValueAt(row, 1).toString().toLowerCase(); // 1 là chỉ số cột của tên trong bảng bên trái
                    return name.contains(searchName);
                })
                .forEach(row -> {
                    // Nếu tìm thấy tên, thêm hàng tương ứng vào bảng bên phải
                    Object[] rowData = new Object[model.getColumnCount()];
                    for (int i = 0; i < model.getColumnCount(); i++) {
                        rowData[i] = model.getValueAt(row, i);
                    }
                    rightTableModel.addRow(rowData);
                });
    }
    }



