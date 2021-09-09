package com.hkaynar.couriertracking.Controller;

import com.hkaynar.couriertracking.Model.Response.ModelResponse;
import com.hkaynar.couriertracking.Service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hkaynar on 07.09.2021
 */
@RestController
@RequestMapping(value = "/courier")
public class CourierRest {

    @Autowired
    CourierService courierService;



    @GetMapping("list")
    public ResponseEntity<ModelResponse> readCheckServices(){
        return new ResponseEntity<>(courierService.getAllCourier(), HttpStatus.OK);

    }


}
