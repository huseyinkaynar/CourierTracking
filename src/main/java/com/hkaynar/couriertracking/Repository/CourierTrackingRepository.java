package com.hkaynar.couriertracking.Repository;

import com.hkaynar.couriertracking.Model.Entity.CourierTracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

/**
 * @author hkaynar on 08.09.2021
 */
public interface CourierTrackingRepository extends JpaRepository<CourierTracking,Long> {

    ArrayList<> findByCourierName
}
