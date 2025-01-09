package src.View;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.Controller.AddTransController;
public class AddNewTrans extends JFrame{

    public AddNewTrans(){
        setLayout(new BorderLayout());
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Add New Transaction Delivery", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        JPanel dataTrans = new JPanel(new GridLayout(6, 2, 10,10));
        dataTrans.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        dataTrans.add(new JLabel("Nama Penerima: "));
        JTextField namaField = new JTextField();
        dataTrans.add(namaField);

        dataTrans.add(new JLabel("Alamat: "));
        JTextField alamatField = new JTextField();
        dataTrans.add(alamatField);
        
        dataTrans.add(new JLabel("No. Telepon: "));
        JTextField noTlpField = new JTextField();
        dataTrans.add(noTlpField);

        dataTrans.add(new JLabel("Berat Paket: "));
        JTextField beratPaketField = new JTextField();
        dataTrans.add(beratPaketField);

        dataTrans.add(new JLabel("Tipe Paket: "));
        JComboBox<String> tipePaket = new JComboBox<>(new AddTransController().getCategoryPackage());
        dataTrans.add(tipePaket);
        
        JPanel panelButton = new JPanel(new GridLayout(1,3,15,15));
        JButton simpanButton = new JButton("Simpan");
        simpanButton.addActionListener(e-> {
            new AddTransController().checkInputAndInsert(namaField.getText(), alamatField.getText(), noTlpField.getText(), beratPaketField.getText(), tipePaket.getSelectedItem().toString());
            dispose();
        
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e-> {
            dispose();
            new MainMenu();
        });

        JButton addDetailTransButton = new JButton("Add Detail Transaction"); // penambahan button add detail transaction disini dikarenakan di dalam soal tidak diberi tahu harus ditambahkan dimana.
        addDetailTransButton.addActionListener(e-> {
            dispose();
            new DetailTrans();
        });

        panelButton.add(backButton);
        panelButton.add(simpanButton);
        panelButton.add(addDetailTransButton);


        add(dataTrans, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);
        
        setVisible(true);
    }
}
