package src.View;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import src.Controller.LogInController;
import src.Controller.RegisterController;
import src.Model.Class.Singleton.SingletonManger;

public class MainMenu {
    public MainMenu(){
        JFrame frame = new JFrame("Pratama Express");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        // Mengatur layout frame menjadi FlowLayout
        frame.setLayout(new GridLayout(2,2,20,20));

        // Menambahkan 4 tombol ke dalam frame
        JButton logIn = new JButton("LogIn");
        LoginForm logInFrame = new LoginForm();
        logIn.addActionListener(e-> {
            logInFrame.setVisible(true);
            frame.dispose();
            new LogInController(logInFrame);
        });
        
        JButton register = new JButton("Register");
        RegisterForm registerForm = new RegisterForm();
        register.addActionListener(e-> {
            registerForm.setVisible(true);
            frame.dispose();
            new RegisterController(registerForm);
        });
        
        JButton deliveryTrans = new JButton("Add Delivery Transaction");
        deliveryTrans.addActionListener(e->{
            new AddNewTrans();
            frame.dispose();
        });


        JButton history = new JButton("History Packet");
        history.addActionListener(e->{
            new HistoryPackage();
            frame.dispose();
        });

        frame.add(logIn);
        frame.add(register);
        frame.add(deliveryTrans);
        frame.add(history);

        // Menampilkan frame
        frame.setVisible(true);

        SwingUtilities.invokeLater(()->{
            if (SingletonManger.getInstance().getLoggedInUser() == null) {
                deliveryTrans.setEnabled(false);
                history.setEnabled(false);
            }
        });
    }
}
