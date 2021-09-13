package com.hkaynar.couriertracking.Service.Imp;

import com.hkaynar.couriertracking.Model.Dto.CourierDto;
import com.hkaynar.couriertracking.Model.Entity.Courier;
import com.hkaynar.couriertracking.Populator.CourierLogPopulator;
import com.hkaynar.couriertracking.Repository.CourierRepository;
import com.hkaynar.couriertracking.Repository.CourierTrackingRepository;
import com.hkaynar.couriertracking.Service.CourierTrackingService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author hkaynar on 13.09.2021
 */
@ExtendWith(MockitoExtension.class)
class CourierServiceImpTest {

    @InjectMocks
    private  CourierServiceImp courierServiceImp;

    @Mock
    CourierRepository courierRepository;

    @Mock
    CourierTrackingRepository courierTrackingRepository;

    @Mock
    CourierTrackingService courierTrackingService;

    @Mock
    CourierLogPopulator courierLogPopulator;

    @Test
    void getAllCourier() {

        List<Courier> couriers=new ArrayList<>();
        Assertions.assertThat(couriers).size().isGreaterThan(0);

    }

    @Test
    void countTracking() {
    }

    @Test
    void checkCourier() {
    }

    @Test
    void saveCourier() {

        CourierDto courierDto = new CourierDto();
        courierDto.setCourierName("Test-Name");

        CourierDto result=courierServiceImp.saveCourier(courierDto);

        assertEquals(result.getCourierName(),courierDto.getCourierName());
    }
}