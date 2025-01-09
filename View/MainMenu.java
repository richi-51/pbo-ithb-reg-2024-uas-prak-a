package View;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainMenu {
    public MainMenu(){
        JFrame frame = new JFrame("Pratama Express");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Mengatur layout frame menjadi FlowLayout
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Menambahkan 4 tombol ke dalam frame
        JButton logIn = new JButton("LogIn");
        JButton register = new JButton("Register");
        JButton deliveryTrans = new JButton("Add Delivery Transaction");
        JButton history = new JButton("History Packet");

        frame.add(logIn);
        frame.add(register);
        frame.add(deliveryTrans);
        frame.add(history);

        // Menampilkan frame
        frame.setVisible(true);
    }
}
