/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmusic.controllers;

import appmusic.daos.UserDAO;
import appmusic.daos.UserDAOImpl;
import appmusic.models.User;
import appmusic.views.LoginView;
import appmusic.views.MenuView;
import appmusic.views.RegisterView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author cuongpham
 */
public class LoginController {

    private UserDAO userDAO;
    private LoginView loginView;

    public LoginController(LoginView loginView) {
        this.userDAO = new UserDAOImpl();
        this.loginView = loginView;
        this.loginView.addListener(new LoginListener());
    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginView.getjButton1()) {
                String username = loginView.getjTextField1().getText();
                if (username == null || username.isEmpty()) {
                    JOptionPane.showMessageDialog(loginView, "Username không được để trống");
                    return;
                }

                String password = loginView.getjPasswordField1().getText();
                if (password == null || password.isEmpty()) {
                    JOptionPane.showMessageDialog(loginView, "Password không được để trống");
                    return;
                }

                User user = userDAO.login(username, password);

                if (user == null) {
                    JOptionPane.showMessageDialog(loginView, "Đăng nhập thất bại");
                } else {
                    JOptionPane.showMessageDialog(loginView, "Đăng nhập thành công");

                    loginView.dispose();
                    MenuView menuView = new MenuView();
                    MenuController controller = new MenuController(menuView, user);
                    menuView.setVisible(true);

                }

            }

            if (e.getSource() == loginView.getjButton2()) {
                loginView.dispose();

                RegisterView registerView = new RegisterView();
                RegisterController controller = new RegisterController(registerView);
                registerView.setVisible(true);
            }
        }

    }

}
