package com.crfstech.MyRemote.persistence.Dao;

import com.crfstech.MyRemote.persistence.entity.Device.UserDevices;
import com.crfstech.MyRemote.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface Devices_UserDao extends JpaRepository<UserDevices, String> {

    List<UserDevices> findByUser(User user);
}
