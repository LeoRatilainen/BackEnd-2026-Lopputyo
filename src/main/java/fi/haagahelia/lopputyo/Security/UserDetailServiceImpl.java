package fi.haagahelia.lopputyo.Security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fi.haagahelia.lopputyo.domain.User;
import fi.haagahelia.lopputyo.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    private final UserRepository repository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currentuser = repository.findByUsername(username);
        if (currentuser == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
            username,
            currentuser.getPasswordHash(),
            AuthorityUtils.createAuthorityList(currentuser.getRole())
        );
    }
}
