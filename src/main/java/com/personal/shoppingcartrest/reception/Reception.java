package com.personal.shoppingcartrest.reception;

import com.personal.shoppingcartrest.user.User;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reception {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private long id;

    @CreatedDate
    @CreationTimestamp
    private Date creationDate;

    @Column(name = "total_quantity", nullable = false)
    private int totalQuantity = 0;

    @Column(nullable = false)
    private boolean cancelled = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Reception() {
    }

    public Reception(int totalQuantity, User user) {
        this.totalQuantity = totalQuantity;
        this.user = user;
    }

    public Reception(int totalQuantity, boolean cancelled, User user) {
        this.totalQuantity = totalQuantity;
        this.cancelled = cancelled;
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

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
