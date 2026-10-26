package com.isl.gbm.user.management.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PostLoginUserDetailsDTO {

    private String solDate;

    private String solDesc;

    private String lastLoginSuccessTime;

    private Set<String> permissionList;

    private Map<String, Object> menuLinksList;

    private Map<String, PermissionMenuLinkDTO> linksDataMap;

    public static class ColumnWiseMenuList{

        private String columnWiseMenu;

        public String getColumnWiseMenu() {
            return columnWiseMenu;
        }

        public void setColumnWiseMenu(String columnWiseMenu) {
            this.columnWiseMenu = columnWiseMenu;
        }
    }

    private List<ColumnWiseMenuList> columnWiseMenuList;

    public List<ColumnWiseMenuList> getColumnWiseMenuList() {
        return columnWiseMenuList;
    }

    public void setColumnWiseMenuList(List<ColumnWiseMenuList> columnWiseMenuList) {
        this.columnWiseMenuList = columnWiseMenuList;
    }

    public Map<String, PermissionMenuLinkDTO> getLinksDataMap() {
        return linksDataMap;
    }

    public void setLinksDataMap(Map<String, PermissionMenuLinkDTO> linksDataMap) {
        this.linksDataMap = linksDataMap;
    }

    public Map<String, Object> getMenuLinksList() {
        return menuLinksList;
    }

    public void setMenuLinksList(Map<String, Object> menuLinksList) {
        this.menuLinksList = menuLinksList;
    }

    public String getArgStatus() {
        return argStatus;
    }

    public void setArgStatus(String argStatus) {
        this.argStatus = argStatus;
    }

    public String argStatus;

    public String getSolDesc() {
        return solDesc;
    }

    public void setSolDesc(String solDesc) {
        this.solDesc = solDesc;
    }

    public String getLastLoginSuccessTime() {
        return lastLoginSuccessTime;
    }

    public String getSolDate() {
        return solDate;
    }

    public void setSolDate(String solDate) {
        this.solDate = solDate;
    }

    public void setLastLoginSuccessTime(String lastLoginSuccessTime) {
        this.lastLoginSuccessTime = lastLoginSuccessTime;
    }

    public Set<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(Set<String> permissionList) {
        this.permissionList = permissionList;
    }
}
