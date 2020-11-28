package com.personal.shoppingcartrest.brand;

import com.personal.shoppingcartrest.user.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Brand {
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

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Column(nullable = false)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Brand() {
    }

    public Brand(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Brand(String name, String description, boolean active) {
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public Brand(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.user = user;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
