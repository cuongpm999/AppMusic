/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmusic.controllers;

import appmusic.daos.MusicDAO;
import appmusic.daos.MusicDAOImpl;
import appmusic.models.Music;
import appmusic.models.User;
import appmusic.views.ListeningMusicView;
import appmusic.views.MenuView;
import jaco.mp3.player.MP3Player;
import jaco.mp3.player.c;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cuongpham
 */
public class ListeningMusicController {

    private User user;
    private MusicDAO musicDAO;
    private ListeningMusicView listeningMusicView;
    private MP3Player mP3Player;
    private int index = 0;
    private List<Music> list;

    public ListeningMusicController(User user, ListeningMusicView listeningMusicView) {
        this.user = user;
        this.listeningMusicView = listeningMusicView;
        this.musicDAO = new MusicDAOImpl();
        this.listeningMusicView.addListener(new ListeningMusicListener());

        list = musicDAO.findByUsername(user.getUsername());
        if (list == null || list.size() == 0) {
            this.listeningMusicView.getjButton1().setEnabled(false);
            this.listeningMusicView.getjButton2().setEnabled(false);
            this.listeningMusicView.getjButton3().setEnabled(false);
            this.listeningMusicView.getjButton4().setEnabled(false);
            return;
        }

        mP3Player = new MP3Player(new File(list.get(0).getSource()));

        DefaultTableModel model = (DefaultTableModel) listeningMusicView.getjTable1().getModel();
        model.setRowCount(0);

        int stt = 1;
        for (Music music : list) {
            model.addRow(music.toObject(stt));
            stt++;
        }

        listeningMusicView.getjTable1().setRowSelectionInterval(0, 0);

        mP3Player.play();

        listeningMusicView.getjTable1().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = listeningMusicView.getjTable1().getSelectedRow();
                index = r;
                mP3Player.stop();
                mP3Player = new MP3Player(new File(list.get(index).getSource()));
                mP3Player.play();
            }

        });

    }

    class ListeningMusicListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == listeningMusicView.getjButton1()) {
                if (index == 0) {
                    return;
                }

                index--;
                mP3Player.stop();
                mP3Player = new MP3Player(new File(list.get(index).getSource()));
                mP3Player.play();
                listeningMusicView.getjTable1().setRowSelectionInterval(index, index);
            }

            if (e.getSource() == listeningMusicView.getjButton4()) {
                if (index == listeningMusicView.getjTable1().getRowCount() - 1) {
                    return;
                }
                index++;
                mP3Player.stop();
                mP3Player = new MP3Player(new File(list.get(index).getSource()));
                mP3Player.play();
                listeningMusicView.getjTable1().setRowSelectionInterval(index, index);
            }

            if (e.getSource() == listeningMusicView.getjButton3()) {
                if (mP3Player.isStopped() || mP3Player.isPaused()) {
                    return;
                }
                mP3Player.pause();
            }

            if (e.getSource() == listeningMusicView.getjButton2()) {
                if (mP3Player.isStopped()) {
                    return;
                }
                mP3Player.play();
            }

            if (e.getSource() == listeningMusicView.getjButton5()) {
                if (mP3Player != null) {
                    mP3Player.stop();
                }
                listeningMusicView.dispose();
                MenuView menuView = new MenuView();
                MenuController controller = new MenuController(menuView, user);
                menuView.setVisible(true);
            }

        }

    }

}
