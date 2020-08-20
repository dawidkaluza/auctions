package eu.horyzont.auctions.modules.user;

import eu.horyzont.auctions.web.forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    private boolean doesEmailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User register(RegistrationForm form) throws UserAlreadyExistsException {
        if(doesEmailExist(form.getEmail())) {
            throw new UserAlreadyExistsException(form.getEmail());
        }

        return userRepository.save(
            new User(
                form.getFirstName(), form.getLastName(),
                form.getEmail(), bCryptPasswordEncoder.encode(form.getPassword())
            )
        );
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser
            .filter(user -> user.getEncodedPassword().equals(password));
    }
}
