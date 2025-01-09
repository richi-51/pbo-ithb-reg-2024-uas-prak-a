package src.View;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;



class CircularImageLabel extends JLabel {
    private BufferedImage image;

    public CircularImageLabel(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath)); // Membaca gambar dari file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g.create();

            // Menyesuaikan ukuran lingkaran dengan ukuran komponen
            int diameter = Math.min(getWidth(), getHeight());
            BufferedImage circleImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = circleImage.createGraphics();

            // Membuat lingkaran menggunakan clip
            g2.setClip(new Ellipse2D.Double(0, 0, diameter, diameter));
            g2.drawImage(image, 0, 0, diameter, diameter, null);
            g2.dispose();

            // Menggambar gambar lingkaran ke label
            g2d.drawImage(circleImage, 0, 0, null);
            g2d.dispose();
        } else {
            super.paintComponent(g);
        }
    }
}


public class LoginForm extends JFrame {
    private JTextField noTlpField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;

    private String profilePic = "D:/Perkuliahan/File kuliah Semester 3/PBO/UAS_Prak PBO/pbo-ithb-reg-2024-uas-prak-a/src/Asset/imageLogo.jpg";
    private JLabel greetLabel, profileLabel;
    private JPanel profilePanel;

    // Constructor
    public LoginForm() {
        setTitle("Login");
        setSize(350, 350);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Wadah Foto dan Sapaan
        profilePanel = new JPanel(new BorderLayout());
        profilePanel.setSize(25, 35);

        // Bagian Foto
        profileLabel = new CircularImageLabel(profilePic);
        profileLabel.setSize(profilePanel.getWidth(), (int) (profilePanel.getHeight() * 0.75));
        profileLabel.setBorder(BorderFactory.createEmptyBorder(10, 0,30,-40));


        // Bagian Sapaan
        greetLabel = new JLabel("Welcome to Pratama Express.", SwingConstants.CENTER);
        greetLabel.setFont(new Font("Arial", Font.BOLD, 16));
        greetLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        profilePanel.add(profileLabel, BorderLayout.CENTER);
        profilePanel.add(greetLabel, BorderLayout.SOUTH);





        // Panel buat form
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        formPanel.add(new JLabel("No. Telepon:"));
        noTlpField = new JTextField();
        formPanel.add(noTlpField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        loginButton = new JButton("Login");
        backButton = new JButton("Back");
        backButton.addActionListener(e->{
            this.dispose();
            new MainMenu();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);
        
        add(profilePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        SwingUtilities.invokeLater(this::tampilkanGambar);
    }

    private void tampilkanGambar() {
        // Menampilkan gambar pada JLabel
        ImageIcon imageIcon = new ImageIcon(profilePic);
        // Menyesuaikan ukuran gambar dengan ukuran label
        Image image = imageIcon.getImage().getScaledInstance(profileLabel.getWidth(), profileLabel.getHeight(),
                Image.SCALE_SMOOTH);
        profileLabel.setIcon(new ImageIcon(image));
    }

    public String getNoTlp() {
        return noTlpField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addRegisterListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}
