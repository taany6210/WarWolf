package com.ty.warwolf.model.bean.base;

/**
 * @ 文件名:   User
 * @ 创建者:   ty
 * @ 时间:    2017/8/11 上午10:38
 * @ 描述:    用户
 */

public class User extends BaseBean {
    private int userId;//id
    private int level;//等级
    private String userName;//用户昵称
    private String levelName;//等级名称
    private String token;//token
    private String birthday;//出生年月
    private String photo;//头像
    private String sex;//性别
    private String mobile;//手机号码


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", level=" + level +
                ", userName='" + userName + '\'' +
                ", levelName='" + levelName + '\'' +
                ", token='" + token + '\'' +
                ", birthday='" + birthday + '\'' +
                ", photo='" + photo + '\'' +
                ", sex='" + sex + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
