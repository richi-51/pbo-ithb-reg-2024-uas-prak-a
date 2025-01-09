package Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.Class.Db.DatabaseHandler;
import View.MainMenu;

public class AddTransController {
    public String[] getCategoryPackage() {
        try (Connection conn = DatabaseHandler.connect()) {
            ArrayList<String> results = new ArrayList<>();
            String query = "SELECT * FROM categoryPackage";

            var preparedStmtVehicle = conn.prepareStatement(query);

            var resultCategory = preparedStmtVehicle.executeQuery();

            while (resultCategory.next()) {
                results.add(resultCategory.getString("category"));
            }

            String[] categories = new String[results.size()];
            for (int i = 0; i < categories.length; i++) {
                categories[i] = results.get(i);
            }

            return categories;

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }

        return null;
    }

    public void checkInputAndInsert(String nama, String alamat, String noTlp, String beratPaket, String tipePaket) {
        double berat = 0;
        if (nama.equalsIgnoreCase("") || alamat.equalsIgnoreCase("") || noTlp.equalsIgnoreCase("") || beratPaket.equalsIgnoreCase("") || tipePaket.equalsIgnoreCase("")) {

            JOptionPane.showMessageDialog(null, "Maaf semua data harus diisi!", "Error Message", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!beratPaket.equalsIgnoreCase("0")) {
            try {
                berat = Integer.parseInt(beratPaket);
                if (berat > 0 && berat < 1) {
                    berat = 1;
                }else{
                    int temp = (int)(berat);
                    double sisa = berat - temp;
                    if (sisa >= 0.5) {
                        berat += 1;
                    }else{
                        berat = temp;
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Maaf input berat paket harus angka!", "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Maaf berat paket tidak boleh nol atau kosong!", "Error Message", JOptionPane.WARNING_MESSAGE);
        }

        try (Connection conn = DatabaseHandler.connect()) {
            String queryCheck = "INSERT INTO transaction (customer_id, beratPaket, tipePaket, total_cost, created_at, receipt_name, receipt_address, receipt_phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            

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
                    JOptionPane.showMessageDialog(registerView, "Data berhasil disimpan!", "Information Message",
                            JOptionPane.INFORMATION_MESSAGE);

                    registerView.dispose();

                    new MainMenu();

                }

            } else {
                JOptionPane.showMessageDialog(registerView, "Maaf email atau nomor telepon sudah digunakan!",
                        "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(registerView, "Database error: " + ex.getMessage());
        }
    }

}
