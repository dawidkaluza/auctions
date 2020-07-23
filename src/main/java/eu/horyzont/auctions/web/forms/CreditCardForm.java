package eu.horyzont.auctions.web.forms;

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
    private String expireDate;

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

    public YearMonth getExpireDate() {
        String[] data = expireDate.split("/");
        return YearMonth.of(
            2000 + Integer.parseInt(data[1]),
            Integer.parseInt(data[0])
        );
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    //TODO write it better
    public void setExpireDate(YearMonth expireDate) {
        this.expireDate = expireDate.getMonthValue() + "/" + expireDate.getYear();
    }
}
