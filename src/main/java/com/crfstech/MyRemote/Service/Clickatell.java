package com.crfstech.MyRemote.Service;


//import com.clickatell.platform.data.Message;
import com.crfstech.MyRemote.DTO.smsDTO;
import com.crfstech.MyRemote.security.CryptoUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Clickatell {

    @Value("${app.APIKey}")
    private String API;

    public smsDTO sendMessage(smsDTO sms) {
//        try {
//      //      ClickatellRest clickRest = new ClickatellRest(API);
//        //    Message[] response = clickRest.sendMessage(sms.getCommand(), CryptoUtil.decrypt(sms.getPhone()));
//
//         //   return new smsDTO(response[0].getMessage_id());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new smsDTO(e.getMessage());
        return new smsDTO("Not Working");
        }
    }

