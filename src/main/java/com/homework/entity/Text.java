package com.homework.entity;

import java.io.Serializable;

/**
 * (Text)实体类
 *
 * @author makejava
 * @since 2021-05-13 01:22:18
 */
public class Text implements Serializable {
    private static final long serialVersionUID = -83847096054553197L;
    /**
     * id
     */
    private Integer id;

    private String name;

    private Integer age;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Text{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
