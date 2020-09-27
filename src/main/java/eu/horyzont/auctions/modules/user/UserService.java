package eu.horyzont.auctions.modules.user;

import eu.horyzont.auctions.web.forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User register(RegistrationForm form) throws UserAlreadyExistsException {
        if(findByEmail(form.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(form.getEmail());
        }

        System.out.println("Save user with login: '" + form.getEmail() + "', password: '" + form.getPassword() + "'");
        return userRepository.save(
            new User(
                form.getFirstName(), form.getLastName(),
                form.getEmail(), passwordEncoder.encode(form.getPassword())
            )
        );
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
