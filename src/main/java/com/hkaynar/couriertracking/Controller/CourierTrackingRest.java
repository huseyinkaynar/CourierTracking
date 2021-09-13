package com.hkaynar.couriertracking.Controller;

import com.hkaynar.couriertracking.Exception.NotFoundException;
import com.hkaynar.couriertracking.Model.Dto.CourierTrackingDto;
import com.hkaynar.couriertracking.Model.Response.ModelResponse;
import com.hkaynar.couriertracking.Service.CourierTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> loggingCourier(@RequestBody CourierTrackingDto courierTrackingDto) throws IOException, NotFoundException {
        return new ResponseEntity<>(courierTrackingService.logingCourier(courierTrackingDto), HttpStatus.OK);
    }
}
