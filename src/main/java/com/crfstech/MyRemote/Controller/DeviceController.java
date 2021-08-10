package com.crfstech.MyRemote.Controller;

import com.crfstech.MyRemote.DTO.DeviceUserDTO;
import com.crfstech.MyRemote.Exception.NotFoundException;
import com.crfstech.MyRemote.Service.DeviceService;
import com.crfstech.MyRemote.persistence.entity.Device.Device;
import com.crfstech.MyRemote.persistence.entity.Device.UserDevices;
import com.crfstech.MyRemote.security.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("device")
public class DeviceController {

    @Autowired
    DeviceService deviceService;
    @Autowired
    private JWTUtil util;


    @PostMapping("/add")
    public ResponseEntity<?> addDevice(@RequestBody Device device) {
        ResponseEntity<?> resp = null;
        try {
            Optional<Device> device1 = deviceService.add(device);
            resp = new ResponseEntity<Optional<Device>>(device1, HttpStatus.OK);
        } catch (NotFoundException nfe) {
            throw nfe;
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Check the device if its in Order",
                    HttpStatus.BAD_REQUEST);
        }
        return resp;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> addDevice(@PathVariable String id) {
        ResponseEntity<?> resp = null;
        try {
            Optional<Device> device1 = deviceService.find(id);
            resp = new ResponseEntity<Optional<Device>>(device1, HttpStatus.OK);
        } catch (NotFoundException nfe) {
            throw nfe;
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Device not found",
                    HttpStatus.NOT_FOUND);
        }
        return resp;
    }

    @PostMapping("/assign")
    public ResponseEntity<?> Assign(@RequestBody UserDevices d) {
        ResponseEntity<?> resp = null;
        try {
            Optional<UserDevices> device1 = deviceService.Assign(d);
            resp = new ResponseEntity<Optional<UserDevices>>(device1, HttpStatus.OK);
        } catch (NotFoundException nfe) {
            throw nfe;
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Device not found",
                    HttpStatus.NOT_FOUND);
        }
        return resp;
    }

    @GetMapping("/allDevices")
    public ResponseEntity<?> Assign() {
        ResponseEntity<?> resp = null;
        try {
            List<UserDevices> device1 = deviceService.getAll();
            resp = new ResponseEntity<List<UserDevices>>(device1, HttpStatus.OK);
        } catch (NotFoundException nfe) {
            throw nfe;
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Device not found",
                    HttpStatus.NOT_FOUND);
        }
        return resp;
    }

    @GetMapping("/getDevices/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        ResponseEntity<?> resp = null;
        try {
            List<DeviceUserDTO> device1 = deviceService.getAssignedDevices(id);
            resp = new ResponseEntity<List<DeviceUserDTO>>(device1, HttpStatus.OK);
        } catch (NotFoundException nfe) {
            throw nfe;
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Device not found",
                    HttpStatus.NOT_FOUND);
        }
        return resp;
    }


}
