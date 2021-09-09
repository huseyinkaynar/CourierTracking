package com.hkaynar.couriertracking.Populator.Imp;

import com.hkaynar.couriertracking.Model.Dto.CourierDto;
import com.hkaynar.couriertracking.Model.Entity.CourierLog;
import com.hkaynar.couriertracking.Populator.CourierLogPopulator;
import org.springframework.stereotype.Service;

/**
 * @author hkaynar on 09.09.2021
 */
@Service
public class CourierPopulatorImp implements CourierLogPopulator {
    @Override
    public CourierLog courierDtotoLog(CourierDto courierDto, String storeName) {
        CourierLog courierLog=new CourierLog();
        courierLog.setCourierName(courierDto.getCourierName());
        courierLog.setStoreName(storeName);
        courierLog.setTime(courierDto.getTime());
        return courierLog;
    }
}
