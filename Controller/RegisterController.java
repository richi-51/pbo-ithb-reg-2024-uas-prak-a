package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.Class.Db.DatabaseHandler;
import View.MainMenu;
import View.RegisterForm;

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
                String queryCheck = "SELECT * FROM Customer WHERE email = ? OR noTlp = ?";
                // String queryCheck = "INSERT INTO Customer (plateNumber, vehicleName, vehicleType, jumlahSeat) VALUES (?, ?, ?, ?)";

                var preparedStatementCheck = conn.prepareStatement(queryCheck);
                preparedStatementCheck.setString(1, email);

                int rows = preparedStatementCheck.executeUpdate();

               

                if (rows > 0) {
                    String queryInsert = "INSERT INTO Customer (name, alamat, noTlp, email, password) VALUES (?, ?, ?, ?, ?)";

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