package com.uc.bean;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Quote")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "Quote_Item",
            joinColumns = @JoinColumn(name = "quote_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    public List<Item> items;
    @OneToOne
    @JoinTable(
            name = "Quote_ItemDescription",
            joinColumns = @JoinColumn(name = "quote_id"),
            inverseJoinColumns = @JoinColumn(name = "itemDescription_id")
    )
    public ItemDescription itemDescription;
    @Column(name = "customerName")
    public String customerName;
    @Column(name = "installationCharge")
    public Double installationCharge;
    @Column(name = "gstCharge")
    public Double gstCharge;
    @Column(name = "cartage")
    public Double cartage;
    @Column(name = "grandTotal")
    public Double grandTotal;

    public Quote() {
    }

    public Quote(int id, List<Item> items, String customerName, Double installationCharge, Double gstCharge, Double cartage, Double grandTotal, ItemDescription itemDescription) {
        this.id = id;
        this.items = items;
        this.customerName = customerName;
        this.installationCharge = installationCharge;
        this.gstCharge = gstCharge;
        this.cartage = cartage;
        this.grandTotal = grandTotal;
        this.itemDescription = itemDescription;
    }

    public int getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Double getInstallationCharge() {
        return installationCharge;
    }

    public Double getGstCharge() {
        return gstCharge;
    }

    public Double getCartage() {
        return cartage;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public ItemDescription getItemDescription() {
        return itemDescription;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItem(List<Item> item) {
        this.items = item;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setInstallationCharge(Double installationCharge) {
        this.installationCharge = installationCharge;
    }

    public void setGstCharge(Double gstCharge) {
        this.gstCharge = gstCharge;
    }

    public void setCartage(Double cartage) {
        this.cartage = cartage;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public void setItemDescription(ItemDescription itemDescription) {
        this.itemDescription = itemDescription;
    }

}
