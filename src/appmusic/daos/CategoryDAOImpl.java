/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmusic.daos;

import appmusic.models.Category;
import appmusic.models.Music;
import appmusic.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cuongpham
 */
public class CategoryDAOImpl implements CategoryDAO{
    private final Connection conn = MySQLConnection.getMySQLConnection();

    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM categories";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            List<Category> categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                
                categories.add(category);
            }
            
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insert(Category category) {
        boolean isSuccess = true;
        String sql = "INSERT INTO categories (name) VALUES (?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getName());

            ps.executeUpdate();
        } catch (SQLException e) {
            isSuccess = false;
            e.printStackTrace();
        }
        
        return isSuccess;
    }
    
}
