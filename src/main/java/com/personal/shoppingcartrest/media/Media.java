package com.personal.shoppingcartrest.media;

import com.personal.shoppingcartrest.product.Product;
import com.personal.shoppingcartrest.type.Type;
import com.personal.shoppingcartrest.user.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Media {
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

    @Column(nullable = false)
    private String mediaPath;

    @Column(nullable = false)
    private boolean main = false;

    @Column(nullable = false)
    private boolean active = false;

    @OneToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Media() {
    }

    public Media(String mediaPath, Type type, User user) {
        this.mediaPath = mediaPath;
        this.type = type;
        this.user = user;
    }

    public Media(String mediaPath, Type type, boolean main, User user, boolean active) {
        this.mediaPath = mediaPath;
        this.type = type;
        this.main = main;
        this.active = active;
        this.user = user;
    }

    public Media(String mediaPath, Type type, boolean main, User user, Product product, boolean active) {
        this.mediaPath = mediaPath;
        this.type = type;
        this.main = main;
        this.active = active;
        this.user = user;
        this.product = product;
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

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
