/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmusic.daos;

import appmusic.models.User;

/**
 *
 * @author cuongpham
 */
public interface UserDAO {
    boolean insert(User user);
    User login(String username, String password);
}
