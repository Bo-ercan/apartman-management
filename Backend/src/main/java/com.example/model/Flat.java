package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name= "flats")
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="title")
    private String title;
    @Column(name="room")
    private String room;
    @Column(name="floor")
    private String floor;
    @Column(name="age")
    private int age;
    @Column(name="balcony")
    private int balcony;
    @Column(name="heating")
    private String heating;
    @Column(name="otopark")
    private String otopark;
    @Column(name="m2")
    private int m2;
    @Column(name="price")
    private int price;
    @Column(name="tapu")
    private String tapu;

    @Column(name="ImgURL")
    private String ImgURL="https://source.unsplash.com/collection/44204348/200x200";

    public Flat(){

    }
    public Flat(String title, String room, String floor, int age, int balcony, String heating, String otopark, int m2, int price, String tapu,String ImgURL) {
        this.title = title;
        this.room = room;
        this.floor = floor;
        this.age = age;
        this.balcony = balcony;
        this.heating = heating;
        this.otopark = otopark;
        this.m2 = m2;
        this.price = price;
        this.tapu = tapu;
        this.ImgURL=ImgURL;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBalcony() {
        return balcony;
    }

    public void setBalcony(int balcony) {
        this.balcony = balcony;
    }

    public String getHeating() {
        return heating;
    }

    public void setHeating(String heating) {
        this.heating = heating;
    }

    public String getOtopark() {
        return otopark;
    }

    public void setOtopark(String otopark) {
        this.otopark = otopark;
    }

    public int getM2() {
        return m2;
    }

    public void setM2(int m2) {
        this.m2 = m2;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTapu() {
        return tapu;
    }

    public void setTapu(String tapu) {
        this.tapu = tapu;
    }

    public String getImgUrL() {
        return ImgURL;
    }

    public void setImgUrL(String ImgURL) {
        this.ImgURL = ImgURL;
    }
}
