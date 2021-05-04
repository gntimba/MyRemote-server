package com.crfstech.MyRemote.Service;

import com.clickatell.platform.ClickatellRest;
import org.springframework.stereotype.Service;

@Service
public class clickatell {
    ClickatellRest clickRest = new ClickatellRest("API_KEY");
}
