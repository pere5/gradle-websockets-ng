package org.flowerbed.repository.plants;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Flower {

    private String name;
    private String family;
    private String planted;
    private int age;
    private int maxAge;
    private int height;
    private int maxHeight;

    public Flower() {

        DateTime dateTime = new DateTime();

        this.age = 0;
        this.height = 0;
        this.planted = dateTime.toString();

        this.name = "Rose";
        this.family = "Adelanthaceae";
        this.maxAge = 100;
        this.maxHeight = 100;
    }

    public Flower(String name,
                  String family,
                  int maxAge,
                  int maxHeight) {

        DateTime dateTime = new DateTime();

        this.age = 0;
        this.height = 0;
        this.planted = dateTime.toString();

        this.name = name;
        this.family = family;
        this.maxAge = maxAge;
        this.maxHeight = maxHeight;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getPlanted() {
        return planted;
    }

    public void setPlanted(String planted) {
        this.planted = planted;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", planted=" + planted +
                ", age=" + age +
                ", maxAge=" + maxAge +
                ", height=" + height +
                ", maxHeight=" + maxHeight +
                '}';
    }
}