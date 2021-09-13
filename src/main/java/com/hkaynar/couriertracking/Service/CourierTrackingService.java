package com.hkaynar.couriertracking.Service;

import com.hkaynar.couriertracking.Exception.NotFoundException;
import com.hkaynar.couriertracking.Model.Dto.CourierTrackingDto;
import com.hkaynar.couriertracking.Model.Entity.CourierLog;
import com.hkaynar.couriertracking.Model.Response.ModelResponse;

import java.io.IOException;

/**
 * @author hkaynar on 08.09.2021
 */
public interface CourierTrackingService {
    float countMeter(double lat1, double lng1, double lat2, double lng2);
    ModelResponse logingCourier(CourierTrackingDto courierTrackingDto) throws IOException, NotFoundException;
    void saveCourierTrack(CourierTrackingDto courierTrackingDto);
    CourierLog checkDistance(CourierTrackingDto courierTrackingDto) ;
    CourierLog saveCourierLog(CourierTrackingDto courierTrackingDto, String storeName);


}
