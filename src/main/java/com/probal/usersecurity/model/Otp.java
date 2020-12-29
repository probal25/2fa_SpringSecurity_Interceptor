package com.probal.usersecurity.model;


import javax.persistence.*;

@Entity
@Table(name = "tbl_key")
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String key_value;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key_value;
    }

    public void setKey(String key) {
        this.key_value = key;
    }

    @Override
    public String toString() {
        return "UserSecretKey{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", key='" + key_value + '\'' +
                '}';
    }
}

