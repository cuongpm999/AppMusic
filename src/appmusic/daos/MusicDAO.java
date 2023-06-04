/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package appmusic.daos;

import appmusic.models.Music;
import java.util.List;

/**
 *
 * @author cuongpham
 */
public interface MusicDAO {
    List<Music> findByUsername(String username);
    boolean insert(Music music);
}
