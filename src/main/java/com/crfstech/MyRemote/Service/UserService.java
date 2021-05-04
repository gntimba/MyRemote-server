package com.crfstech.MyRemote.Service;

import com.crfstech.MyRemote.model.ROLE;
import com.crfstech.MyRemote.persistence.Dao.UsersDao;
import com.crfstech.MyRemote.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UsersDao usersDao;

    @Autowired
    private BCryptPasswordEncoder bCryptEncoder;

    @Transactional
    public UUID save(User user){
        user.setPassword(bCryptEncoder.encode(user.getPassword()));
        Set<ROLE> roles = new HashSet<>();
        roles.add(ROLE.APP);
        roles.add(ROLE.USER);
        user.setRoles(roles);
       return usersDao.save(user).getId();
    }

//   // public Optional<User> findById(UUID id){
//       return usersDao.findById(id);
//    }
    public Optional<User> findByemail(String email){
      Optional<User> Opuser=  usersDao.findByEmail(email);
      if(Opuser.isPresent()){
          Opuser.get().setPassword(null);
      }
      return Opuser;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opt = usersDao.findByEmail(username);

        opt.get();

        org.springframework.security.core.userdetails.User springUser=null;

        if(opt.isEmpty()) {
            throw new UsernameNotFoundException("User with username: " +username +" not found");
        }else {
            User user =opt.get();	//retrieving user from DB
            Set<ROLE> roles = user.getRoles();
            Set<GrantedAuthority> ga = new HashSet<>();
            for(ROLE role:roles) {
                ga.add(new SimpleGrantedAuthority(role.toString()));
            }

            springUser = new org.springframework.security.core.userdetails.User(
                    username,
                    user.getPassword(),
                    ga );
        }

        return springUser;
    }
}
