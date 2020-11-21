package com.personal.shoppingcartrest.systemconfiguration;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Configuration {
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

    @Column(name = "percentage_a", nullable = false)
    private float percentageA = 0.0f;

    @Column(name = "percentage_b", nullable = false)
    private float percentageB = 0.0f;

    @Column(name = "percentage_c", nullable = false)
    private float percentageC = 0.0f;

    @Column(name = "percentage_abc", nullable = false)
    private float percentageABC = 0.0f;

    public Configuration() {
    }

    public Configuration(float percentageA, float percentageB, float percentageC, float percentageABC) {
        this.percentageA = percentageA;
        this.percentageB = percentageB;
        this.percentageC = percentageC;
        this.percentageABC = percentageABC;
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

    public float getPercentageA() {
        return percentageA;
    }

    public void setPercentageA(float percentageA) {
        this.percentageA = percentageA;
    }

    public float getPercentageB() {
        return percentageB;
    }

    public void setPercentageB(float percentageB) {
        this.percentageB = percentageB;
    }

    public float getPercentageC() {
        return percentageC;
    }

    public void setPercentageC(float percentageC) {
        this.percentageC = percentageC;
    }

    public float getPercentageABC() {
        return percentageABC;
    }

    public void setPercentageABC(float percentageABC) {
        this.percentageABC = percentageABC;
    }
}
