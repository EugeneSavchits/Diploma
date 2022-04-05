package models;

import lombok.Builder;
import lombok.Data;

@Data
public class User {
    private String email;
    private String password;

//    public User() {
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public User setEmail(String email) {
//        this.email = email;
//        return this;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public User setPassword(String password) {
//        this.password = password;
//        return this;
//    }
}
