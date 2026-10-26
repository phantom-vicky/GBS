package com.isl.gbm.user.management.dto;

import com.isl.gbm.user.management.security.shiro.config.ValidPassword;
import com.isl.gbm.user.management.util.UserAccountUtils;

public class PasswordRestDTO {

    private Integer userId;

    private String password;

    @ValidPassword
    private String decodedPassword;

    private String confirmPassword;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.decodedPassword = UserAccountUtils.decodeAesPassword(password);
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "PasswordRestDTO{" +
                "password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }


}
