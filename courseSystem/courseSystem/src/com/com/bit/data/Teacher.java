package com.com.bit.data;

/**
 * @ClassName Teacher
 * @Description TODO
 * @Author DELL
 * @Data 2020/1/7 9:29
 * @Version 1.0
 **/
public class Teacher {
    private int id;
    private String name;

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

    @Override
    public String toString() {
        return "id="+id+",name="+name;
    }
}
