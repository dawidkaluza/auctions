package eu.horyzont.auctions.modules.payment;

import eu.horyzont.auctions.modules.user.User;

import javax.persistence.*;
import java.time.YearMonth;

@Entity(name = "credit_card")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 16, nullable = false)
    private String number;

    @Column(length = 3, nullable = false)
    private String cvv;

    @Column(nullable = false)
    private YearMonth expireDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    protected CreditCard() {
    }

    public CreditCard(String number, String cvv, YearMonth expireDate, User user) {
        this.number = number;
        this.cvv = cvv;
        this.expireDate = expireDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public YearMonth getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(YearMonth expireDate) {
        this.expireDate = expireDate;
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

        CreditCard that = (CreditCard) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
