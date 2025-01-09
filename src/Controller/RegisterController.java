package src.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import src.Model.Class.Db.DatabaseHandler;
import src.View.MainMenu;
import src.View.RegisterForm;

public class RegisterController {
    private RegisterForm registerView;

    public RegisterController(RegisterForm registerView) {
        this.registerView = registerView;
        
        this.registerView.addRegisterListener(new RegisterAction());
    }


    private class RegisterAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = registerView.getName();
            String phone = registerView.getPhoneNumber();
            String address = registerView.getAddress();
            String password = registerView.getPassword();
            String email = registerView.getEmail();

            if (name.equalsIgnoreCase("") || phone.equalsIgnoreCase("") || address.equalsIgnoreCase("") || password.equalsIgnoreCase("") || email.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(registerView, "Maaf semua data harus diisi!", "Error Message", JOptionPane.WARNING_MESSAGE);
                return;
            }

            
            try (Connection conn = DatabaseHandler.connect()) {
                String queryCheck = "SELECT * FROM Customer WHERE email = ? OR phone = ?";

                var preparedStatementCheck = conn.prepareStatement(queryCheck);
                preparedStatementCheck.setString(1, email);
                preparedStatementCheck.setString(2, phone);

                var rs = preparedStatementCheck.executeQuery();

               

                if (!rs.next()) {
                    String queryInsert = "INSERT INTO Customer (name, address, phone, email, password) VALUES (?, ?, ?, ?, ?)";

                    var preparedStatementInsert = conn.prepareStatement(queryInsert);
                    preparedStatementInsert.setString(1, name);
                    preparedStatementInsert.setString(2, address);
                    preparedStatementInsert.setString(3, phone);
                    preparedStatementInsert.setString(4, email);
                    preparedStatementInsert.setString(5, password);

                    int rowAffected = preparedStatementInsert.executeUpdate();

                    if (rowAffected > 0) {
                        JOptionPane.showMessageDialog(registerView, "Data berhasil disimpan!", "Information Message", JOptionPane.INFORMATION_MESSAGE);

                        registerView.dispose();

                        new MainMenu();
                        
                    }


                } else {
                    JOptionPane.showMessageDialog(registerView, "Maaf email atau nomor telepon sudah digunakan!", "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(registerView, "Database error: " + ex.getMessage());
            }
        }
    }
}
