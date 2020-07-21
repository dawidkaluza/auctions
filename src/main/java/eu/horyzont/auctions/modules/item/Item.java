package eu.horyzont.auctions.modules.item;

import eu.horyzont.auctions.modules.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128)
    private String name;

    @Column(precision = 9, scale = 2)
    private BigDecimal initialPrice;

    private LocalDateTime auctionEnd;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    protected Item() {
    }

    public Item(String name, BigDecimal initialPrice, LocalDateTime auctionEnd, User user) {
        this.name = name;
        this.initialPrice = initialPrice;
        this.auctionEnd = auctionEnd;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public LocalDateTime getAuctionEnd() {
        return auctionEnd;
    }

    public void setAuctionEnd(LocalDateTime actionEnd) {
        this.auctionEnd = actionEnd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User seller) {
        this.user = seller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;
        return id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
