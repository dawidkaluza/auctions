package eu.horyzont.auctions.modules.payment;

import eu.horyzont.auctions.modules.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findAllByUser(User user);
}
