/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appmusic.models;

/**
 *
 * @author cuongpham
 */
public class Music {
    private int id;
    private String source;
    private String name;
    private User user;
    private Category category;

    public Music() {
    }

    public Music(int id, String source, String name, User user, Category category) {
        this.id = id;
        this.source = source;
        this.name = name;
        this.user = user;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    public Object[] toObject(int index) {
        return new Object[]{index, name, source, category.getName()};
    }


     
    
}
