package com.isl.gbm.common.dto;

public class LevelDetailsTocSharingDto {

    private String id;

    private String levelId;

    private String levelName;

    private String sharingPercentage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getSharingPercentage() {
        return sharingPercentage;
    }

    public void setSharingPercentage(String sharingPercentage) {
        this.sharingPercentage = sharingPercentage;
    }

    @Override
    public String toString() {
        return "LevelDetailsTocSharingDto{" +
                "id='" + id + '\'' +
                ", levelId='" + levelId + '\'' +
                ", levelName='" + levelName + '\'' +
                ", sharingPercentage='" + sharingPercentage + '\'' +
                '}';
    }
}
