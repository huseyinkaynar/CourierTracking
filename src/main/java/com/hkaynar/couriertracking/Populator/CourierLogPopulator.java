package com.hkaynar.couriertracking.Populator;

import com.hkaynar.couriertracking.Model.Dto.CourierDto;
import com.hkaynar.couriertracking.Model.Entity.CourierLog;

/**
 * @author hkaynar on 09.09.2021
 */
public interface CourierLogPopulator {

    CourierLog courierDtotoLog(CourierDto courierDto, String storeName);
}
