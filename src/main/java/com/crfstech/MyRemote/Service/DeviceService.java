package com.crfstech.MyRemote.Service;

import com.crfstech.MyRemote.persistence.Dao.DeviceDao;
import com.crfstech.MyRemote.persistence.Dao.Devices_UserDao;
import com.crfstech.MyRemote.persistence.entity.Device.Device;
import com.crfstech.MyRemote.persistence.entity.Device.UserDevices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    DeviceDao deviceDao;

    @Autowired
    Devices_UserDao devices_userDao;


    @Transactional
    public Optional<Device> add(Device device){

      return Optional.of( deviceDao.save(device));
    }
    public Optional<Device> find(String id){
        return  deviceDao.findById(id);
    }

    @Transactional
    public Optional<UserDevices> Assign(UserDevices userDevices){
        return Optional.of( devices_userDao.save(userDevices));
    }
    @Transactional
    public List<UserDevices> getAll(){
        return  devices_userDao.findAll();
    }



}
