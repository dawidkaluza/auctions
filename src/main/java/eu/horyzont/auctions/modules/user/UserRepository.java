package eu.horyzont.auctions.modules.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
