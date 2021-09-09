package com.hkaynar.couriertracking.Controller;

import com.hkaynar.couriertracking.Model.Dto.CourierDto;
import com.hkaynar.couriertracking.Service.CourierTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author hkaynar on 08.09.2021
 */
@RestController
@RequestMapping(value = "/couriertracking")
public class CourierTrackingRest {

    @Autowired
    CourierTrackingService courierTrackingService;


    @PostMapping("log")
    public void loggingCourier(@RequestBody CourierDto courierDto) throws IOException {
        courierTrackingService.logingCourier(courierDto);
    }
}
