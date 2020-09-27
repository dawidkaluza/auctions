package eu.horyzont.auctions.web.forms;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class BidForm {
    private Long id;

    @NotNull
    @Range(min = 1L)
    private BigDecimal offer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getOffer() {
        return offer;
    }

    public void setOffer(BigDecimal offer) {
        this.offer = offer;
    }
}
