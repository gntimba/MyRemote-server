package com.crfstech.MyRemote.Controller;

import com.crfstech.MyRemote.DTO.smsDTO;
import com.crfstech.MyRemote.Exception.NotFoundException;
import com.crfstech.MyRemote.Service.Clickatell;
import com.crfstech.MyRemote.persistence.entity.Device.Device;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("sms")
public class MessageController {

    @Autowired
    Clickatell clickatell;

    @PostMapping("/send")
    public ResponseEntity<?> sendSMS(@RequestBody smsDTO sms ) {
        ResponseEntity<?> resp = null;
        try {

            resp = new ResponseEntity<>(  clickatell.sendMessage(sms), HttpStatus.OK);
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
}
