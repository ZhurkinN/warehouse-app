package ru.zhurkin.warehouseapp.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.zhurkin.warehouseapp.model.user.User;
import ru.zhurkin.warehouseapp.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.zhurkin.warehouseapp.support.constant.SecurityRoleConstantsKeeper.*;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    @Value("${spring.security.user.name}")
    private String moderatorName;

    @Value("${spring.security.user.password}")
    private String moderatorPassword;

    @Value("${spring.security.user.roles}")
    private String moderatorRole;

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(moderatorName)) {
            return new CustomUserDetails(null, username, moderatorPassword, List.of(new SimpleGrantedAuthority("ROLE_" + moderatorRole)));
        } else {
            User user = userRepository.findUserByLogin(username);
            List<GrantedAuthority> authorities = new ArrayList<>();
            String role = "ROLE_";
            if (user.getRole().getId() == 1L || user.getRole().getId() == 4L) {
                role += WORKER;
            } else if (user.getRole().getId() == 2) {
                role += SALES_MANAGER;
            } else {
                role += ASSISTANT;
            }
            authorities.add(new SimpleGrantedAuthority(role));
            return new CustomUserDetails(user.getId().intValue(), username, user.getPassword(), authorities);
        }
    }
}
