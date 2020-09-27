package eu.horyzont.auctions.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername step 1");
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if(!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("Cant find username in database");
        }

        System.out.println("loadUserByUsername step 2, user: " + optionalUser.get().getId());
        return new UserDetailsImpl(
            optionalUser.get()
        );
    }
}
