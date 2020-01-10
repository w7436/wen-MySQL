package com.com.bit.data;

/**
 * @ClassName Class
 * @Description TODO
 * @Author DELL
 * @Data 2020/1/6 20:05
 * @Version 1.0
 **/
public class Class {
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
