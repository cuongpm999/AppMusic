/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appmusic;

import appmusic.controllers.AddMusicController;
import appmusic.controllers.ListeningMusicController;
import appmusic.controllers.LoginController;
import appmusic.controllers.RegisterController;
import appmusic.models.User;
import appmusic.views.AddMusicView;
import appmusic.views.ListeningMusicView;
import appmusic.views.LoginView;
import appmusic.views.RegisterView;
import jaco.mp3.player.MP3Player;
import java.io.File;

/**
 *
 * @author cuongpham
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(loginView);
        loginView.setVisible(true);
    }

}
