/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmusic.controllers;

import appmusic.daos.CategoryDAO;
import appmusic.daos.CategoryDAOImpl;
import appmusic.models.Category;
import appmusic.models.User;
import appmusic.views.AddCategoryView;
import appmusic.views.ListCategoryView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author cuongpham
 */
public class AddCategoryController {

    private AddCategoryView addCategoryView;
    private CategoryDAO categoryDAO;
    private User user;

    public AddCategoryController(AddCategoryView addCategoryView, User user) {
        this.addCategoryView = addCategoryView;
        categoryDAO = new CategoryDAOImpl();
        this.user = user;
        this.addCategoryView.addListener(new AddCategoryListener());
    }

    class AddCategoryListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addCategoryView.getjButton1()) {
                String name = addCategoryView.getjTextField1().getText();
                if (name == null || name.isEmpty()) {
                    JOptionPane.showMessageDialog(addCategoryView, "Hãy nhập tên thể loại");
                    return;
                }

                Category category = new Category();
                category.setName(name);

                boolean isSuccess = categoryDAO.insert(category);
                if (isSuccess) {
                    JOptionPane.showMessageDialog(addCategoryView, "Thêm thể loại thành công");
                    addCategoryView.dispose();
                    ListCategoryView listCategoryView = new ListCategoryView();
                    ListCategoryController listCategoryController = new ListCategoryController(listCategoryView, user);
                    listCategoryView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(addCategoryView, "Thêm thể loại thất bại");
                }
            }

            if (e.getSource() == addCategoryView.getjButton2()) {
                addCategoryView.dispose();
                ListCategoryView listCategoryView = new ListCategoryView();
                ListCategoryController listCategoryController = new ListCategoryController(listCategoryView, user);
                listCategoryView.setVisible(true);
            }
        }

    }

}
