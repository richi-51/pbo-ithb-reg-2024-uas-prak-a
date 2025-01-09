package src.Controller;

import java.awt.Image;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import src.Model.Class.Db.DatabaseHandler;

public class DetailHistoryController {
    public String[][] getHistoryPackage(String id_trans) {
        try (Connection conn = DatabaseHandler.connect()) {
            ArrayList<String[]> results = new ArrayList<>();
            
            String query = "SELECT * FROM shipmentdetails WHERE transaction_id = ? ORDER BY date DESC";

            var preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, id_trans);

            var rs = preparedStmt.executeQuery();

            while (rs.next()) {
                results.add(new String[]{rs.getString("status"), rs.getString("evidence"), String.valueOf(rs.getDate("date")), rs.getString("updated_by")});
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

    public ImageIcon getImageIcon(String path){
        ImageIcon imageIcon = new ImageIcon(path);

        Image image = imageIcon.getImage().getScaledInstance(300, 300,
                Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}
