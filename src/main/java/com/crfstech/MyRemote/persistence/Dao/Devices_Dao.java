package com.crfstech.MyRemote.persistence.Dao;

import com.crfstech.MyRemote.persistence.entity.Device.userDevices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface Devices_Dao extends JpaRepository<userDevices,String> {
}
