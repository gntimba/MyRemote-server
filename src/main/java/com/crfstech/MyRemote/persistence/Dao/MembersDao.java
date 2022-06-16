package com.crfstech.MyRemote.persistence.Dao;

import com.crfstech.MyRemote.persistence.entity.group.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MembersDao extends JpaRepository<Member,String> {

    @Query("select m from Member m where m.userId = ?1")
    public List<Member> findByuserId(String groupId);
}
