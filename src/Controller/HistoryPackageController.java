package src.Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import src.Model.Class.Db.DatabaseHandler;
import src.Model.Class.Singleton.SingletonManger;

public class HistoryPackageController {
    public String[][] getHistoryPackage() {
        try (Connection conn = DatabaseHandler.connect()) {
            ArrayList<String[]> results = new ArrayList<>();
            
            String query = "SELECT DISTINCT * FROM transaction t INNER JOIN shipmentdetails s ON t.id = s.transaction_id  WHERE customer_id = ? ORDER BY date DESC";

            var preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, SingletonManger.getInstance().getLoggedInUser().getId_customer());

            var rs = preparedStmt.executeQuery();

            while (rs.next()) {
                results.add(new String[]{rs.getString("transaction_id"), rs.getString("package_type"), String.valueOf(rs.getDouble("package_weight")), String.valueOf(rs.getInt("total_cost")), String.valueOf(rs.getDate("created_at")), String.valueOf(rs.getDate("date")), "Detail History"});
            }

            String[][] historyPac = new String[results.size()][results.get(0).length];
            for (int i = 0; i < historyPac.length; i++) {
                historyPac[i] = results.get(i);
            }

            return historyPac;

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }

        return null;
    }
}
