/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmusic.controllers;

import appmusic.models.User;
import appmusic.views.AddMusicView;
import appmusic.views.ListCategoryView;
import appmusic.views.ListeningMusicView;
import appmusic.views.LoginView;
import appmusic.views.MenuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 *
 * @author cuongpham
 */
public class MenuController {

    private MenuView menuView;
    private User user;

    public MenuController(MenuView menuView, User user) {
        this.menuView = menuView;
        this.menuView.addListener(new MenuListener());
        this.user = user;
        menuView.getjLabel2().setText("Chào " + user.getFullName());
        
        if(!user.getPosition().equals("ADMIN")) {
            menuView.getjButton4().setVisible(false);
        }
    }

    class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == menuView.getjButton3()) {
                menuView.dispose();
                LoginView loginView = new LoginView();
                LoginController loginController = new LoginController(loginView);
                loginView.setVisible(true);
            }

            if (e.getSource() == menuView.getjButton1()) {
                menuView.dispose();
                AddMusicView addMusicView = new AddMusicView();
                AddMusicController addMusicController = new AddMusicController(addMusicView, user);
                addMusicView.setVisible(true);
            }

            if (e.getSource() == menuView.getjButton2()) {
                menuView.dispose();
                ListeningMusicView listeningMusicView = new ListeningMusicView();
                ListeningMusicController controller = new ListeningMusicController(user, listeningMusicView);
                listeningMusicView.setVisible(true);
            }
            
            if (e.getSource() == menuView.getjButton4()) {
                menuView.dispose();
                ListCategoryView listCategoryView = new ListCategoryView();
                ListCategoryController listCategoryController = new ListCategoryController(listCategoryView, user);
                listCategoryView.setVisible(true);
            }
        }
    }

}
