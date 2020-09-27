package eu.horyzont.auctions.modules.bid;

import eu.horyzont.auctions.modules.user.User;
import eu.horyzont.auctions.modules.item.Item;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "bid")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal offer;

    private LocalDateTime updatedAt;

    @ManyToOne(optional = false)
    private Item item;

    @ManyToOne(optional = false)
    private User user;

    protected Bid() {
    }

    public Bid(BigDecimal offer, Item item, User user) {
        this.offer = offer;
        this.item = item;
        this.user = user;
    }

    @PrePersist
    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getOffer() {
        return offer;
    }

    public void setOffer(BigDecimal offer) {
        this.offer = offer;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid = (Bid) o;
        return id.equals(bid.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
