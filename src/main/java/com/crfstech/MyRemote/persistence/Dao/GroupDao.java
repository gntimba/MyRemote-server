package com.crfstech.MyRemote.persistence.Dao;

import com.crfstech.MyRemote.persistence.entity.group.Group;
import com.crfstech.MyRemote.persistence.entity.group.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface GroupDao extends JpaRepository<Group, String> {
    @Query("select g from Group g where g.userId = ?1")
    List<Group> findByuserId(String userID);


    Optional<Group> findByNameAndUserId(String name, String userID);

}
