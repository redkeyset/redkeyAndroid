package com.redkey.baselibrary.bean;

/**
 * 知识点分类
 */
public class classItemBean {
    private String activityArouter;
    private String className;
    private String description;
    private String iconUrl;
    private boolean isSelect;

    public String getActivityArouter() {
        return activityArouter;
    }

    public void setActivityArouter(String activityArouter) {
        this.activityArouter = activityArouter;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public String toString() {
        return "classItemBean{" +
                "activityArouter='" + activityArouter + '\'' +
                ", className='" + className + '\'' +
                ", description='" + description + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", isSelect=" + isSelect +
                '}';
    }
}
