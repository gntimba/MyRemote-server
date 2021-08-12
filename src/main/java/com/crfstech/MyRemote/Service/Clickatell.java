package com.crfstech.MyRemote.Service;



import ClickAtell.ClickatellRest;
import com.clickatell.platform.data.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Clickatell {

    @Value("${app.APIKey}")
    private String API;





    public String sendMessage(){
        try {
            ClickatellRest  clickRest = new ClickatellRest (API);
            Message [] response = clickRest.sendMessage("testing message", "fff277646878700");
          return   response[0].getStatus();
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
