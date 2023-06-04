/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmusic.controllers;

import appmusic.daos.CategoryDAO;
import appmusic.daos.CategoryDAOImpl;
import appmusic.daos.MusicDAO;
import appmusic.daos.MusicDAOImpl;
import appmusic.models.Category;
import appmusic.models.Music;
import appmusic.models.User;
import appmusic.views.AddMusicView;
import appmusic.views.LoginView;
import appmusic.views.MenuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author cuongpham
 */
public class AddMusicController {

    private AddMusicView addMusicView;
    private User user;
    private File file;
    private static final String LOCATION_SAVE_FILE = "/home/cuongpham/Desktop/ExternalProject/";
    private MusicDAO musicDAO;
    private CategoryDAO categoryDAO;
    private List<Category> categories;

    public AddMusicController(AddMusicView addMusicView, User user) {
        this.addMusicView = addMusicView;
        this.user = user;
        this.addMusicView.addListener(new AddMusicListener());
        musicDAO = new MusicDAOImpl();
        categoryDAO = new CategoryDAOImpl();
        
        DefaultComboBoxModel model = (DefaultComboBoxModel) addMusicView.getjComboBox1().getModel();
        model.removeAllElements();

        categories = categoryDAO.findAll();
        for (Category category : categories) {
            model.addElement(category.getName());
        }
    }

    class AddMusicListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addMusicView.getjButton1()) {
                JFileChooser j = new JFileChooser();
                int r = j.showSaveDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
                    file = j.getSelectedFile();
                    addMusicView.getjLabel4().setText(file.getName());
                }
            }

            if (e.getSource() == addMusicView.getjButton2()) {
                String nameFile = addMusicView.getjTextField1().getText();
                if (nameFile == null || nameFile.isEmpty()) {
                    JOptionPane.showMessageDialog(addMusicView, "Hãy nhập tên file");
                    return;
                }
                if (file == null) {
                    JOptionPane.showMessageDialog(addMusicView, "Hãy chọn file upload");
                    return;
                }

                String source = LOCATION_SAVE_FILE + nameFile + ".mp3";
                try {
                    byte[] fileBytes = FileUtils.readFileToByteArray(file);
                    FileUtils.writeByteArrayToFile(new File(source), fileBytes);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(addMusicView, "Upload file thất bại");
                    return;
                }

                Music music = new Music();
                music.setName(nameFile);
                music.setSource(source);
                music.setUser(user);
                
                music.setCategory(categories.get(addMusicView.getjComboBox1().getSelectedIndex()));

                boolean isSuccess = musicDAO.insert(music);

                if (isSuccess) {
                    JOptionPane.showMessageDialog(addMusicView, "Thêm nhạc thành công");

                    addMusicView.dispose();
                    MenuView menuView = new MenuView();
                    MenuController controller = new MenuController(menuView, user);
                    menuView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(addMusicView, "Thêm nhạc thất bại");
                    return;
                }
            }

            if (e.getSource() == addMusicView.getjButton3()) {
                addMusicView.dispose();
                MenuView menuView = new MenuView();
                MenuController controller = new MenuController(menuView, user);
                menuView.setVisible(true);
            }
        }
    }

}
