package com.hkaynar.couriertracking.Repository;

import com.hkaynar.couriertracking.Model.Entity.CourierLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hkaynar on 08.09.2021
 */
public interface CourierLogRepository extends JpaRepository<CourierLog,Long> {

    CourierLog findTopByCourierNameOrderByTimeDesc(String name);

}
