package src.View;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import src.Controller.DetailHistoryController;

public class DetailHistory {
    public DetailHistory(String id_trans){
        JFrame frame = new JFrame("Detail History Package");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Detail History Package", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        frame.add(title, BorderLayout.NORTH);

        String dataString[][] = new DetailHistoryController().getHistoryPackage(id_trans);
        Object[][] data = new Object[dataString.length][dataString[0].length];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(j == 1){
                    data[i][j] = new DetailHistoryController().getImageIcon(dataString[i][j]);
                }else{
                    data[i][j] = dataString[i][j];
                }
            }
        }

        String[] columns = {"Status", "Evidence", "Date", "Updated By"};

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) {
                    return ImageIcon.class;
                }
                return String.class;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(300);

        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e-> {
            frame.dispose();
            new HistoryPackage();
        });

        frame.add(backButton, BorderLayout.SOUTH);

        frame.setSize(500, 400);
        frame.setVisible(true);
    }
}
