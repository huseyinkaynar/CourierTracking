package com.hkaynar.couriertracking.Populator;

import com.hkaynar.couriertracking.Model.Dto.CourierTrackingDto;
import com.hkaynar.couriertracking.Model.Entity.CourierLog;
import com.hkaynar.couriertracking.Model.Response.TotalDistanceResponse;

/**
 * @author hkaynar on 09.09.2021
 */
public interface CourierLogPopulator {

    CourierLog courierDtotoLog(CourierTrackingDto courierTrackingDto, String storeName);
    TotalDistanceResponse responseToTotalDistanceResponse(String name,float totalDistance);
}
