package eu.horyzont.auctions.modules.payment;

import eu.horyzont.auctions.modules.user.User;
import eu.horyzont.auctions.web.forms.CreditCardForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardService {
    private final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public List<CreditCard> findAllByUser(User user) {
        return creditCardRepository.findAllByUser(user);
    }

    public Optional<CreditCard> findById(Long id) {
        return creditCardRepository.findById(id);
    }

    public CreditCard save(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    public void delete(CreditCard creditCard) {
        creditCardRepository.delete(creditCard);
    }

    public CreditCard addCreditCard(CreditCardForm form, User user) {
        CreditCard creditCard = new CreditCard(form.getNumber(), form.getCvv(), form.getExpireDateAsYearMonth(), user);
        creditCardRepository.save(creditCard);
        return creditCard;
    }

    public void updateCreditCard(CreditCard creditCard, CreditCardForm form) {
        creditCard.setNumber(form.getNumber());
        creditCard.setCvv(form.getCvv());
        creditCard.setExpireDate(form.getExpireDateAsYearMonth());
        creditCardRepository.save(creditCard);
    }
}
