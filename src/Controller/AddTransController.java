package src.Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import src.Model.Class.Db.DatabaseHandler;
import src.Model.Class.Singleton.SingletonManger;
import src.View.MainMenu;

public class AddTransController {
    public String[] getCategoryPackage() {
        try (Connection conn = DatabaseHandler.connect()) {
            ArrayList<String> results = new ArrayList<>();
            String query = "SELECT * FROM categorypackage";

            var preparedStmt = conn.prepareStatement(query);

            var resultCategory = preparedStmt.executeQuery();

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
                berat = Double.parseDouble(beratPaket);
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
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Maaf input berat paket harus angka!", "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Maaf berat paket tidak boleh nol atau kosong!", "Error Message", JOptionPane.WARNING_MESSAGE);
        }

        try (Connection conn = DatabaseHandler.connect()) {
            String queryCheck = "INSERT INTO transaction (id, customer_id, package_type, package_weight, total_cost, created_at, receipt_name, receipt_address, receipt_phone) VALUES (?, ?, ?, ?, ?, CURRENT_DATE(), ?, ?, ?)";
            
            Random rd = new Random();
            String idTrans = "PER-TX" + rd.nextInt(4);

            var preparedStatementInsert = conn.prepareStatement(queryCheck);
            preparedStatementInsert.setString(1, idTrans);
            preparedStatementInsert.setInt(2, SingletonManger.getInstance().getLoggedInUser().getId_customer());
            preparedStatementInsert.setString(3, tipePaket);
            preparedStatementInsert.setDouble(4, Double.parseDouble(beratPaket));
            preparedStatementInsert.setInt(5, (int)(berat));
            preparedStatementInsert.setString(6, nama);
            preparedStatementInsert.setString(7, alamat);
            preparedStatementInsert.setString(8, noTlp);

            int rows = preparedStatementInsert.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan!", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                new MainMenu();
                
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal disimpan!",
                        "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }
    }

}
