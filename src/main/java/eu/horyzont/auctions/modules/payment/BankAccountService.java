package eu.horyzont.auctions.modules.payment;

import eu.horyzont.auctions.modules.user.User;
import eu.horyzont.auctions.web.forms.BankAccountForm;
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

    public BankAccount addBankAccount(BankAccountForm form, User user) {
        BankAccount bankAccount = new BankAccount(form.getBankName(), form.getSwiftCode(), form.getIban(), user);
        bankAccountRepository.save(bankAccount);
        return bankAccount;
    }

    public void updateBankAccount(BankAccount bankAccount, BankAccountForm form) {
        bankAccount.setBankName(form.getBankName());
        bankAccount.setIban(form.getIban());
        bankAccount.setSwiftCode(form.getSwiftCode());
        bankAccountRepository.save(bankAccount);
    }
}
