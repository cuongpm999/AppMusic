/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmusic.controllers;

import appmusic.daos.UserDAO;
import appmusic.daos.UserDAOImpl;
import appmusic.models.User;
import appmusic.views.LoginView;
import appmusic.views.RegisterView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author cuongpham
 */
public class RegisterController {

    private UserDAO userDAO;
    private RegisterView registerView;

    public RegisterController(RegisterView registerView) {
        this.userDAO = new UserDAOImpl();
        this.registerView = registerView;
        this.registerView.addListener(new RegisterListener());
    }

    class RegisterListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == registerView.getjButton1()) {
                String username = registerView.getjTextField1().getText();
                if (username == null || username.isEmpty()) {
                    JOptionPane.showMessageDialog(registerView, "Username không được để trống");
                    return;
                }

                String password = registerView.getjPasswordField1().getText();
                if (password == null || password.isEmpty()) {
                    JOptionPane.showMessageDialog(registerView, "Password không được để trống");
                    return;
                }

                String fullName = registerView.getjTextField3().getText();
                if (fullName == null || fullName.isEmpty()) {
                    JOptionPane.showMessageDialog(registerView, "Full name không được để trống");
                    return;
                }

                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setFullName(fullName);
                user.setMobile(registerView.getjTextField4().getText());
                user.setEmail(registerView.getjTextField5().getText());
                user.setPosition("USER");
                boolean isSuccess = userDAO.insert(user);
                if (isSuccess) {
                    JOptionPane.showMessageDialog(registerView, "Đăng kí tài khoản thành công");

                    registerView.dispose();

                    LoginView loginView = new LoginView();
                    LoginController loginController = new LoginController(loginView);
                    loginView.setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(registerView, "Đăng kí tài khoản thất bại");
                }

            }

            if (e.getSource() == registerView.getjButton2()) {
                registerView.dispose();

                LoginView loginView = new LoginView();
                LoginController loginController = new LoginController(loginView);
                loginView.setVisible(true);
            }
        }

    }

}
