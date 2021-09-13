package com.hkaynar.couriertracking.Service;

import com.hkaynar.couriertracking.Exception.NotFoundException;
import com.hkaynar.couriertracking.Model.Dto.CourierDto;
import com.hkaynar.couriertracking.Model.Entity.Courier;
import com.hkaynar.couriertracking.Model.Response.ModelResponse;

import java.util.List;

/**
 * @author hkaynar on 07.09.2021
 */
public interface CourierService {

    ModelResponse getAllCourier();

    ModelResponse  countTracking(CourierDto courierDto);

    void checkCourier(String name) throws NotFoundException;

    CourierDto saveCourier(CourierDto courierDto);

}
