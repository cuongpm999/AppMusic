/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package appmusic.daos;

import appmusic.models.Category;
import java.util.List;

/**
 *
 * @author cuongpham
 */
public interface CategoryDAO {
    List<Category> findAll();
    boolean insert(Category category);
    
}
