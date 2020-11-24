package com.personal.shoppingcartrest.type;

import com.personal.shoppingcartrest.media.Media;
import com.personal.shoppingcartrest.user.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Type {
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
    private String name;

    @Column(nullable = false)
    private boolean active = false;

    @OneToOne
    @JoinColumn(name="media_id", nullable = false)
    private Media media;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Type() {
    }

    public Type(String name, Media media, User user) {
        this.name = name;
        this.media = media;
        this.user = user;
    }

    public Type(String name, Media media, User user, boolean active) {
        this.name = name;
        this.active = active;
        this.media = media;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
