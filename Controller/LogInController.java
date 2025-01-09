package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.Class.Db.DatabaseHandler;
import View.LoginForm;

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
                String queryUser = "SELECT * FROM Customer c WHERE password = ? AND noTlp = ?";

                // Periksa apakah user ada di tabel Users
                var preparedStatement = conn.prepareStatement(queryUser);
                preparedStatement.setString(1, password);
                preparedStatement.setString(2, noTlp);

                var resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(loginView, "LogIn Successfull", "LogIn Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Close LogIn window
                    loginView.dispose();
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
