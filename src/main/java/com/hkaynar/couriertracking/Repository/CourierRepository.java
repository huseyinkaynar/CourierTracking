package com.hkaynar.couriertracking.Repository;

import com.hkaynar.couriertracking.Model.Entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hkaynar on 07.09.2021
 */
public interface CourierRepository extends JpaRepository<Courier,Long> {

    List<Courier> findByCourierName(String name);

}
