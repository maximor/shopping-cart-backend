package com.personal.shoppingcartrest.inventory;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private long id;

    @CreatedDate
    @CreationTimestamp
    private Date creationDate;

    @LastModifiedDate
    @UpdateTimestamp
    private Date updatedDate;

    @Column(name = "unit_in_stock", nullable = false)
    private int unitInStock = 0;

    @Column(name = "total_unit_sold")
    private int totalUnitSold = 0;

    @Column(name = "gross_sale", nullable = false)
    private float grossSale = 0.0f;

    public Inventory() {
    }

    public Inventory(int unitInStock, int totalUnitSold, float grossSale) {
        this.unitInStock = unitInStock;
        this.totalUnitSold = totalUnitSold;
        this.grossSale = grossSale;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getUnitInStock() {
        return unitInStock;
    }

    public void setUnitInStock(int unitInStock) {
        this.unitInStock = unitInStock;
    }

    public int getTotalUnitSold() {
        return totalUnitSold;
    }

    public void setTotalUnitSold(int totalUnitSold) {
        this.totalUnitSold = totalUnitSold;
    }

    public float getGrossSale() {
        return grossSale;
    }

    public void setGrossSale(float grossSale) {
        this.grossSale = grossSale;
    }
}
