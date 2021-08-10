package com.crfstech.MyRemote.Service;

import com.crfstech.MyRemote.DTO.DeviceUserDTO;
import com.crfstech.MyRemote.Exception.NotFoundException;
import com.crfstech.MyRemote.persistence.Dao.DeviceDao;
import com.crfstech.MyRemote.persistence.Dao.Devices_UserDao;
import com.crfstech.MyRemote.persistence.entity.Device.Device;
import com.crfstech.MyRemote.persistence.entity.Device.UserDevices;
import com.crfstech.MyRemote.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    DeviceDao deviceDao;

    @Autowired
    Devices_UserDao devices_userDao;


    @Transactional
    public Optional<Device> add(Device device) {

        return Optional.of(deviceDao.save(device));
    }

    public Optional<Device> find(String id) {
        return deviceDao.findById(id);
    }

    @Transactional
    public Optional<UserDevices> Assign(UserDevices userDevices) {
        return Optional.of(devices_userDao.save(userDevices));
    }

    public List<UserDevices> getAll() {
        return devices_userDao.findAll();
    }

    public List<DeviceUserDTO> getAssignedDevices(String id) {
        List<UserDevices> userDevices = devices_userDao.findByUser(new User(id));
        List<DeviceUserDTO> dto = new ArrayList<>();
        if (!userDevices.isEmpty()) {
            for (UserDevices d : userDevices) {
                dto.add(new DeviceUserDTO(d.getDevice(), d.getPhone()));
            }
            return dto;
        } else throw new NotFoundException();
    }


}
