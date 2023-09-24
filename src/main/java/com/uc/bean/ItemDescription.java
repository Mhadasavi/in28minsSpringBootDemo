package com.uc.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/*
* To access h2 db, url-http://localhost:8080/h2-console
*/
//@JsonIgnoreProperties("name")
@Entity
public class ItemDescription {

    @Id
    @GeneratedValue
    private int id;
    @Size(min = 2, message = "Name should be of minimum 2 characters")
    private String profileName;
    private double profileColor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public double getProfileColor() {
        return profileColor;
    }

    public void setProfileColor(double profileColor) {
        this.profileColor = profileColor;
    }

    public double getHardware() {
        return hardware;
    }

    public void setHardware(double hardware) {
        this.hardware = hardware;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public ItemDescription(int id, String profileName, double profileColor, double hardware, String glass) {
        this.id = id;
        this.profileName = profileName;
        this.profileColor = profileColor;
        this.hardware = hardware;
        this.glass = glass;
    }

    private double hardware;
    private String glass;

    public ItemDescription() {
    }


}
