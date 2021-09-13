package com.hkaynar.couriertracking.Populator.Imp;

import com.hkaynar.couriertracking.Model.Dto.CourierTrackingDto;
import com.hkaynar.couriertracking.Model.Entity.CourierLog;
import com.hkaynar.couriertracking.Model.Response.TotalDistanceResponse;
import com.hkaynar.couriertracking.Populator.CourierLogPopulator;
import org.springframework.stereotype.Service;

/**
 * @author hkaynar on 09.09.2021
 */
@Service
public class CourierPopulatorImp implements CourierLogPopulator {
    @Override
    public CourierLog courierDtotoLog(CourierTrackingDto courierTrackingDto, String storeName) {
        CourierLog courierLog=new CourierLog();
        courierLog.setCourierName(courierTrackingDto.getCourierName());
        courierLog.setStoreName(storeName);
        courierLog.setTime(courierTrackingDto.getTime());
        return courierLog;
    }

    @Override
    public TotalDistanceResponse responseToTotalDistanceResponse(String name, float totalDistance) {
        TotalDistanceResponse totalDistanceResponse=new TotalDistanceResponse();
        totalDistanceResponse.setCourierName(name);
        totalDistanceResponse.setTotalDistance(totalDistance);
        return totalDistanceResponse;
    }
}
