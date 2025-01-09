package View;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Class.Db.DatabaseHandler;

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
        JComboBox<String> tipePaket = new JComboBox<>(); // tambah datanya !!!

        
        JPanel panelButton = new JPanel(new GridLayout(1,2,15,15));
        JButton simpanButton = new JButton("Simpan");
        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ubah nilai dari datePicker ke String
                Date validDari = (Date) ((UtilDateModel) (validFromPckrAdd.getModel())).getValue();
                String formattedValidDari = formatDate(validDari, "yyyy-MM-dd");

                Date validSampai = (Date) ((UtilDateModel) (validToPckrAdd.getModel())).getValue();
                String formattedValidSampai = formatDate(validSampai, "yyyy-MM-dd");

                // Panggil fungsi updateVoucher
                new VoucherController().addVoucher(SingletonManger.getInstance().getLoggedInUser().getIdUser(), namaField.getText(), alamatField.getText(), Double.parseDouble(noTlpField.getText()), java.sql.Date.valueOf(formattedValidDari), java.sql.Date.valueOf(formattedValidSampai));
                dispose();

            }
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
}
