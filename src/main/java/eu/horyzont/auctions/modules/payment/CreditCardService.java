package eu.horyzont.auctions.modules.payment;

import eu.horyzont.auctions.modules.user.User;
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
}
