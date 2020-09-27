package eu.horyzont.auctions.web.forms;

import eu.horyzont.auctions.modules.payment.CreditCard;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.YearMonth;

public class CreditCardForm {
    private Long id;

    @Min(16)
    private String number;

    @Min(3)
    private String cvv;

    @Size(min = 5, max = 5)
    //TODO write your own validator to check if expire date is correct
    private String expireDate;

    public CreditCardForm() {
    }

    public CreditCardForm(CreditCard creditCard) {
        id = creditCard.getId();
        number = creditCard.getNumber();
        cvv = creditCard.getCvv();
        expireDate = String.format("%2d/%2d", creditCard.getExpireDate().getMonthValue(), creditCard.getExpireDate().getYear() - 2000);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public YearMonth getExpireDateAsYearMonth() {
        String[] data = expireDate.split("/");
        return YearMonth.of(
            2000 + Integer.parseInt(data[1]),
            Integer.parseInt(data[0])
        );
    }
}
