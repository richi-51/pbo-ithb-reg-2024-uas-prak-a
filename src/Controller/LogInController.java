package src.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import src.Model.Class.Customer;
import src.Model.Class.Db.DatabaseHandler;
import src.Model.Class.Singleton.SingletonManger;
import src.View.LoginForm;
import src.View.MainMenu;

public class LogInController {
    private LoginForm loginView;

    public LogInController() {

    }

    public LogInController(LoginForm loginView) {
        this.loginView = loginView;
        
        this.loginView.addLoginListener(new LoginAction());
    }

    // Getter and Setter
    public LoginForm getLoginView() {
        return loginView;
    }

    public void setLoginView(LoginForm loginView) {
        this.loginView = loginView;
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String password = loginView.getPassword();
            String noTlp = loginView.getNoTlp();

            try (Connection conn = DatabaseHandler.connect()) {
                String queryUser = "SELECT * FROM Customer c WHERE password = ? AND phone = ?";

                // Periksa apakah user ada di tabel Users
                var preparedStatement = conn.prepareStatement(queryUser);
                preparedStatement.setString(1, password);
                preparedStatement.setString(2, noTlp);

                var resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    SingletonManger.getInstance().setLoggedInUser(new Customer(resultSet.getInt("id"), resultSet.getString("password"), resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("phone"), resultSet.getString("email")));

                    
                    JOptionPane.showMessageDialog(loginView, "LogIn Successfull", "LogIn Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Close LogIn window
                    loginView.dispose();
                    new MainMenu();
                } else {
                    JOptionPane.showMessageDialog(loginView, "Invalid No. Telepon or password!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(loginView, "Database error: " + ex.getMessage());
            }
        }
    }
}
