package com.hkaynar.couriertracking.Controller;

import com.hkaynar.couriertracking.Model.Dto.CourierDto;
import com.hkaynar.couriertracking.Model.Response.ModelResponse;
import com.hkaynar.couriertracking.Service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author hkaynar on 07.09.2021
 */
@RestController
@RequestMapping(value = "/courier")
public class CourierRest {

    @Autowired
    CourierService courierService;

    @GetMapping("list")
    public ResponseEntity<ModelResponse> getCourierList(){
        return new ResponseEntity<>(courierService.getAllCourier(), HttpStatus.OK);

    }
    @PostMapping("save")
    public void saveCourier(@RequestBody CourierDto courierDto){
        courierService.saveCourier(courierDto);
    }
    @GetMapping("check")
    public ResponseEntity<ModelResponse> checkCourier(){
        return new ResponseEntity<>(courierService.getAllCourier(), HttpStatus.OK);

    }

    @PostMapping("total_distance")
    public ResponseEntity<?>  totalDistanceCourier(@RequestBody CourierDto courierDto){

        return new ResponseEntity<>(courierService.countTracking(courierDto),HttpStatus.OK);
    }


}
