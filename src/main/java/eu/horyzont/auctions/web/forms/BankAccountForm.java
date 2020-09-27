package eu.horyzont.auctions.web.forms;

import eu.horyzont.auctions.modules.payment.BankAccount;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BankAccountForm {
    private Long id;

    @NotNull
    @Size(min = 3, max = 64)
    private String bankName;

    @NotNull
    @Size(min = 3, max = 16)
    private String swiftCode;

    @NotNull
    @Min(28)
    private String iban;

    public BankAccountForm() {
    }

    public BankAccountForm(BankAccount account) {
        id = account.getId();
        bankName = account.getBankName();
        swiftCode = account.getSwiftCode();
        iban = account.getIban();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
