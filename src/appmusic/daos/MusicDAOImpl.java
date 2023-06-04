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
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

/**
 *
 * @author cuongpham
 */
public class MusicDAOImpl implements MusicDAO{
    private final Connection conn = MySQLConnection.getMySQLConnection();

    @Override
    public List<Music> findByUsername(String username) {
        String sql = "SELECT musics.*, users.*, categories.* FROM ((musics INNER JOIN users ON musics.username = users.username) INNER JOIN categories ON musics.category_id = categories.id) WHERE musics.username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            List<Music> musics = new ArrayList<>();
            while (rs.next()) {
                Music music = new Music();
                music.setId(rs.getInt("musics.id"));
                music.setSource(rs.getString("source"));
                music.setName(rs.getString("name"));
                
                User user = new User();
                user.setUsername(rs.getString("users.username"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setMobile(rs.getString("mobile"));
                
                music.setUser(user);
                
                Category category = new Category();
                category.setId(rs.getInt("categories.id"));
                category.setName(rs.getString("categories.name"));
                
                music.setCategory(category);
                
                musics.add(music);
            }
            
            return musics;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insert(Music music) {
        boolean isSuccess = true;
        String sql = "INSERT INTO musics (source, username, name, category_id) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, music.getSource());
            ps.setString(2, music.getUser().getUsername());
            ps.setString(3, music.getName());
            ps.setInt(4, music.getCategory().getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            isSuccess = false;
            e.printStackTrace();
        }
        
        return isSuccess;
    }
    
}
