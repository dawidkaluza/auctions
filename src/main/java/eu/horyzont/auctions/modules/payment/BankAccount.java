package eu.horyzont.auctions.modules.payment;

import eu.horyzont.auctions.modules.user.User;

import javax.persistence.*;

@Entity(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, nullable = false)
    private String bankName;

    @Column(length = 32, nullable = false)
    private String swiftCode;

    @Column(length = 28, nullable = false, unique = true)
    private String iban;

    @ManyToOne(optional = false)
    private User user;

    protected BankAccount() {
    }

    public BankAccount(String bankName, String swiftCode, String iban, User user) {
        this.bankName = bankName;
        this.swiftCode = swiftCode;
        this.iban = iban;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
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

        BankAccount that = (BankAccount) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
