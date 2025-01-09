package src.Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import src.Model.Class.Db.DatabaseHandler;
import src.Model.Class.Singleton.SingletonManger;
import src.View.MainMenu;

public class DetailTransController {
    public String[] getIdTrans() {
        try (Connection conn = DatabaseHandler.connect()) {
            ArrayList<String> results = new ArrayList<>();
            String query = "SELECT * FROM transaction WHERE customer_id = ?";

            var preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, SingletonManger.getInstance().getLoggedInUser().getId_customer());

            var resultCategory = preparedStmt.executeQuery();

            while (resultCategory.next()) {
                results.add(resultCategory.getString("id"));
            }

            String[] idTrans = new String[results.size()];
            for (int i = 0; i < idTrans.length; i++) {
                idTrans[i] = results.get(i);
            }

            return idTrans;

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }

        return null;
    }


    public void checkInputAndInsert(String idTrans, String status, String currentPos, String evidencePath, String updatedBy) {
        if (idTrans.equalsIgnoreCase("") || status.equalsIgnoreCase("") || currentPos.equalsIgnoreCase("") || evidencePath.equalsIgnoreCase("") || updatedBy.equalsIgnoreCase("")) {

            JOptionPane.showMessageDialog(null, "Maaf semua data harus diisi!", "Error Message", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseHandler.connect()) {
            String queryCheck = "INSERT INTO shipmentdetails (transaction_id, status, current_position, evidence, date, updated_by) VALUES (?, ?, ?, ?, CURRENT_DATE(), ?)";
            

            var preparedStatementInsert = conn.prepareStatement(queryCheck);

            preparedStatementInsert.setString(1, idTrans);
            preparedStatementInsert.setString(2, status);
            preparedStatementInsert.setString(3, currentPos);
            preparedStatementInsert.setString(4, evidencePath);
            preparedStatementInsert.setString(5, updatedBy);

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
