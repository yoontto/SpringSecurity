package com.spring.security.request;

import com.spring.security.config.JasyptConfig;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String id;
    private String pwd;
    private String role;
    private String email;
    private String nickname;
    private String phone;

    private int pwd_cnt;

    public String checkProperties() throws IllegalAccessException {
        for(Field f : getClass().getDeclaredFields()) {
            if(f.get(this) == null) {
                String[] arr = f.toString().split("\\.");
                String t = arr[arr.length - 1];

                if(t.equalsIgnoreCase("id")
                        || t.equalsIgnoreCase("pwd")
                            || t.equalsIgnoreCase("role")) {
                    return t;
                }
            }
        }
        return null;
    }
/*
    public void setId(String id) {
        this.email = stringEncryptor.encrypt(id);
    }

    public void setEmail(String email) {
        this.email = stringEncryptor.encrypt(email);
    }

    public void setPhone(String phone) {
        this.phone = stringEncryptor.encrypt(phone);
    }

    public String getDecId() {
        return stringEncryptor.decrypt(this.getId());
    }

    public String getDecEmail() {
        return stringEncryptor.decrypt(this.getEmail());
    }

    public String getDecPhone() {
        return stringEncryptor.decrypt(this.getPhone());
    }*/

}
