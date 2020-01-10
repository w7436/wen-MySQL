package com.com.bit.data;

/**
 * @ClassName Course
 * @Description TODO
 * @Author DELL
 * @Data 2020/1/7 9:26
 * @Version 1.0
 **/
public class Course {
    private int id;
    private String name;
    private int credit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "id="+id+",name="+name+",credit="+credit;
    }
}
