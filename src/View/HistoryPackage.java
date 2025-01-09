package src.View;

import javax.swing.*;
import javax.swing.table.*;

import src.Controller.HistoryPackageController;

import java.awt.*;
import java.awt.event.*;

public class HistoryPackage {

    public HistoryPackage(){
        JFrame frame = new JFrame("History Package");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("History Package", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        frame.add(title, BorderLayout.NORTH);
        
        
        Object[][] data = new HistoryPackageController().getHistoryPackage();
        String[] columns = {"Transaction ID", "Package Type", "Package Weight", "Total Cost", "Created At", "Updated At", "Lihat Detail History Paket"};
    
        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
    
        table.getColumnModel().getColumn(columns.length-1).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(columns.length-1).setCellEditor(new ButtonEditor(new JCheckBox()));
    
        // Scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e-> {
            frame.dispose();
            new MainMenu();
        });

        frame.add(backButton, BorderLayout.SOUTH);
    
        frame.setSize(500, 400);
        frame.setVisible(true);
    }
}



// ButtonRenderer untuk menggambar tombol pada sel
class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value != null) ? value.toString() : "");
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label, id_trans;
    private boolean clicked;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.id_trans = table.getValueAt(row, 0).toString();
        label = (value != null) ? value.toString() : "";
        button.setText(label);
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            new DetailHistory(id_trans);
        }
        clicked = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}