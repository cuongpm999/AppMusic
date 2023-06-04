/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmusic.controllers;

import appmusic.daos.CategoryDAO;
import appmusic.daos.CategoryDAOImpl;
import appmusic.models.Category;
import appmusic.models.Music;
import appmusic.models.User;
import appmusic.views.AddCategoryView;
import appmusic.views.ListCategoryView;
import appmusic.views.MenuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cuongpham
 */
public class ListCategoryController {
    private CategoryDAO categoryDAO;
    private ListCategoryView listCategoryView;
    private User user;

    public ListCategoryController(ListCategoryView listCategoryView, User user) {
        this.listCategoryView = listCategoryView;
        this.user = user;
        categoryDAO = new CategoryDAOImpl();
        this.listCategoryView.addListener(new ListCategorListener());
        
        List<Category>  categories = categoryDAO.findAll();
        
        DefaultTableModel model = (DefaultTableModel) listCategoryView.getjTable1().getModel();
        model.setRowCount(0);

        int stt = 1;
        for (Category category : categories) {
            model.addRow(category.toObject(stt));
            stt++;
        }
        
        
    }
    
    class ListCategorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == listCategoryView.getjButton1()) {
                listCategoryView.dispose();
                AddCategoryView addCategoryView = new AddCategoryView();
                AddCategoryController controller = new AddCategoryController(addCategoryView, user);
                addCategoryView.setVisible(true);
            }
            
            if (e.getSource() == listCategoryView.getjButton2()) {
                listCategoryView.dispose();
                MenuView menuView = new MenuView();
                MenuController controller = new MenuController(menuView, user);
                menuView.setVisible(true);
            }
        }
         
     }
    
}
