package com.uc.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

/*
* To access h2 db, url-http://localhost:8080/h2-console
*/
//@JsonIgnoreProperties("name")
@Entity
public class Item {

    @Id
    @GeneratedValue
    private int id;
    @Size(min = 2, message = "Name should be of minimum 2 characters")
//    @JsonProperty("item_name")
    private String name;
    private double length;
    private double breadth;
    private String unit;
    @Min(value = 1, message = "Rate must be greater than or equal to 1")
    private double rate;
    private double quantity;
    @PastOrPresent
    private LocalDate date;

    public Item() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Item(int id, String name, double length, double breadth, String unit, double rate, double quantity, LocalDate date) {
        super();
        this.id = id;
        this.name = name;
        this.length = length;
        this.breadth = breadth;
        this.unit = unit;
        this.rate = rate;
        this.quantity = quantity;
        this.date = date;
    }

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

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getBreadth() {
        return breadth;
    }

    public void setBreadth(double breadth) {
        this.breadth = breadth;
    }

    public String getUnit() {
        return unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", breadth=" + breadth +
                ", unit='" + unit + '\'' +
                ", rate=" + rate +
                ", date=" + date +
                '}';
    }
}
