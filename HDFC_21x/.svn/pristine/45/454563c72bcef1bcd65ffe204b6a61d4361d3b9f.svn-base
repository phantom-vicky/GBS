package com.isl.gbm.user.management.dto;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

public class UserAdminDTO {
    private Integer userId;
    private String userName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date disabledTill;

    private Set<Integer> roleIds;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDisabledTill() {
        return disabledTill;
    }

    public void setDisabledTill(Date disabledTill) {
        this.disabledTill = disabledTill;
    }

    public Set<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<Integer> roleIds) {
        this.roleIds = roleIds;
    }
}
