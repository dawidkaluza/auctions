package eu.horyzont.auctions.modules.payment;

import eu.horyzont.auctions.modules.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccount> findAllByUser(User user) {
        return bankAccountRepository.findAllByUser(user);
    }

    public Optional<BankAccount> findById(Long id) {
        return bankAccountRepository.findById(id);
    }

    public BankAccount save(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public void delete(BankAccount bankAccount) {
        bankAccountRepository.delete(bankAccount);
    }
}
