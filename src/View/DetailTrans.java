package src.View;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.Controller.DetailTransController;
import src.Model.Enum.Status;

public class DetailTrans extends JFrame{
    private File pathPhoto;

    public DetailTrans(){
        setLayout(new BorderLayout());
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Detail Transaction Pengiriman", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        JPanel dataTrans = new JPanel(new GridLayout(6, 2, 10,10));
        dataTrans.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        dataTrans.add(new JLabel("ID Transaksi: "));
        JComboBox<String> idTransField = new JComboBox<>(new DetailTransController().getIdTrans()); // tambah datanya
        dataTrans.add(idTransField);

        dataTrans.add(new JLabel("Status: "));
        JComboBox <String> statusField = new JComboBox<>(new String[]{
            Status.DELIVERED.toString(),
            Status.PENDING.toString(),
            Status.TRANSIT.toString()
        });
        dataTrans.add(statusField);
        
        dataTrans.add(new JLabel("Current Position: "));
        JTextField currentPosField = new JTextField();
        dataTrans.add(currentPosField);

        dataTrans.add(new JLabel("Evidence: "));
        JButton chooseFileButton = new JButton("Choose File");

        JTextField evidenceLabelName = new JTextField();
        evidenceLabelName.setEditable(false);

        JPanel chooseFilePanel = new JPanel();
        chooseFilePanel.setLayout(new GridLayout(1, 2));
        chooseFilePanel.add(evidenceLabelName);
        chooseFilePanel.add(chooseFileButton);

        dataTrans.add(chooseFilePanel);

        // Action listener untuk tombol tanda tangan
        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pathPhoto = pilihFileGambar();
                evidenceLabelName.setText(pathPhoto.getName());
            }
        });

        dataTrans.add(new JLabel("Updated by: "));
        JTextField updatedByField = new JTextField();
        dataTrans.add(updatedByField);
        
        JPanel panelButton = new JPanel(new GridLayout(1,2,15,15));
        JButton simpanButton = new JButton("Simpan");
        simpanButton.addActionListener(e-> {
            new DetailTransController().checkInputAndInsert(idTransField.getSelectedItem().toString(), statusField.getSelectedItem().toString(), currentPosField.getText(), pathPhoto.getAbsolutePath(), updatedByField.getText());
            dispose();
        
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e-> {
            dispose();
            new MainMenu();
        });

        panelButton.add(backButton);
        panelButton.add(simpanButton);


        add(dataTrans, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);
        
        setVisible(true);
    }



    private File pilihFileGambar() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Filter hanya untuk file gambar
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                String name = f.getName().toLowerCase();
                return f.isDirectory() || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
            }

            @Override
            public String getDescription() {
                return "File Gambar (*.jpg, *.jpeg, *.png)";
            }
        });

        int result = fileChooser.showOpenDialog(null);
        // Jika pengguna memilih file
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return null;
    }
}
