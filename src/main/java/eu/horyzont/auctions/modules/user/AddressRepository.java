package eu.horyzont.auctions.modules.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByUser(User user);
}
