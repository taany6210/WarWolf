package com.ty.warwolf.model.bean.base;

/**
 * @ 文件名:   Today
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午4:19
 * @ 描述:
 */

public class Today extends BaseBean {

    private String _id;
    private String title;
    private String pic;
    private int year;
    private int month;
    private int day;
    private String des;
    private String lunar;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    @Override
    public String toString() {
        return "Today{" +
                "_id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", pic='" + pic + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", des='" + des + '\'' +
                ", lunar='" + lunar + '\'' +
                '}';
    }
}
