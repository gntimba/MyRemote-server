package com.crfstech.MyRemote.persistence.Dao;

import com.crfstech.MyRemote.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface UsersDao extends JpaRepository<User, String> {
 Optional<User> findByEmail(String username);
}
