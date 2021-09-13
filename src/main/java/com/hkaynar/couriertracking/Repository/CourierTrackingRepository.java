package com.hkaynar.couriertracking.Repository;

import com.hkaynar.couriertracking.Model.Entity.CourierTracking;
import com.hkaynar.couriertracking.Model.LatitudeAndLongitude;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author hkaynar on 08.09.2021
 */
public interface CourierTrackingRepository extends JpaRepository<CourierTracking,Long> {

    List<LatitudeAndLongitude> findByCourierName(String courierName);
}
