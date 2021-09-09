package com.hkaynar.couriertracking.Service.Imp;

import com.hkaynar.couriertracking.Model.Response.ModelResponse;
import com.hkaynar.couriertracking.Repository.CourierRepository;
import com.hkaynar.couriertracking.Service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hkaynar on 07.09.2021
 */
@Service
public class CourierServiceImp implements CourierService {

    @Autowired
    CourierRepository courierRepository;


    @Override
    public ModelResponse getAllCourier() {
        return new ModelResponse(courierRepository.findAll());
    }
}
