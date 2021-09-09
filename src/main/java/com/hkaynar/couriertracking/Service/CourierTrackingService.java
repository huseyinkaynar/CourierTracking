package com.hkaynar.couriertracking.Service;

import com.hkaynar.couriertracking.Model.Dto.CourierDto;
import com.hkaynar.couriertracking.Model.Response.CourierLogResponse;
import com.hkaynar.couriertracking.Model.Response.ModelResponse;

import java.io.IOException;

/**
 * @author hkaynar on 08.09.2021
 */
public interface CourierTrackingService {
    float countMeter(double lat1, double lng1, double lat2, double lng2);
    ModelResponse logingCourier(CourierDto courierDto) throws IOException;
    void saveCourierTrack(CourierDto courierDto);
    void checkDistance(CourierDto courierDto) ;
    void saveCourierLog(CourierDto courierDto, String storeName);


}
