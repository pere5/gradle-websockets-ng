package org.flowerbed.repository.plants;

import org.joda.time.DateTime;

public class Flower implements Spot {

    private int id;
    private boolean empty = false;
    private boolean withered = false;
    private String name;
    private String family;
    private String planted;
    private int age = 0;
    private int maxAge;
    private int height = 0;
    private int maxHeight;

    public Flower() {
        DateTime dateTime = new DateTime();
        this.planted = dateTime.toString();
    }

    public Flower(String name,
                  String family,
                  int maxAge,
                  int maxHeight) {

        DateTime dateTime = new DateTime();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean isWithered() {
        return withered;
    }

    public void setWithered(boolean withered) {
        this.withered = withered;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "id=" + id +
                ", empty=" + empty +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", planted='" + planted + '\'' +
                ", age=" + age +
                ", maxAge=" + maxAge +
                ", height=" + height +
                ", maxHeight=" + maxHeight +
                ", withered=" + withered +
                '}';
    }
}