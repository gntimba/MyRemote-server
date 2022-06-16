package com.crfstech.MyRemote.Service;

import com.crfstech.MyRemote.DTO.userDTO;
import com.crfstech.MyRemote.model.ROLE;
import com.crfstech.MyRemote.persistence.Dao.UsersDao;
import com.crfstech.MyRemote.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UsersDao usersDao;

    @Autowired
    private BCryptPasswordEncoder bCryptEncoder;

    @Transactional
    public String save(userDTO user) {

        User newUser = new User(user);


        newUser.setPassword(bCryptEncoder.encode(user.getPassword()));
        Set<ROLE> roles = new HashSet<>();
        roles.add(ROLE.APP);
        roles.add(ROLE.USER);
        newUser.setRoles(roles);
        return usersDao.save(newUser).getId();
    }
    public Optional<User> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return usersDao.findByEmail(currentPrincipalName);
}

    public Optional<User> findById(String id) {
        Optional<User> Opuser = usersDao.findById(id);
        if (Opuser.isPresent()) {
            Opuser.get().setPassword(null);
        }
        return Opuser;
    }

    public Optional<User> findByemail(String email) {
        Optional<User> Opuser = usersDao.findByEmail(email);
        if (Opuser.isPresent()) {
            Opuser.get().setPassword(null);
        }
        return Opuser;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opt = usersDao.findByEmail(username);

        opt.get();

        org.springframework.security.core.userdetails.User springUser = null;

        if (opt.isEmpty()) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        } else {
            User user = opt.get();    //retrieving user from DB
            Set<ROLE> roles = user.getRoles();
            Set<GrantedAuthority> ga = new HashSet<>();
            for (ROLE role : roles) {
                ga.add(new SimpleGrantedAuthority(role.toString()));
            }

            springUser = new org.springframework.security.core.userdetails.User(
                    username,
                    user.getPassword(),
                    ga);
        }

        return springUser;
    }
}
