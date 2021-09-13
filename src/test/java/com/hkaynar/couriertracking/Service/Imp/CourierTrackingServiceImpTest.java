package com.hkaynar.couriertracking.Service.Imp;

import com.hkaynar.couriertracking.Model.Dto.CourierDto;
import com.hkaynar.couriertracking.Model.Dto.CourierTrackingDto;
import com.hkaynar.couriertracking.Model.Entity.CourierLog;
import com.hkaynar.couriertracking.Populator.CourierLogPopulator;
import com.hkaynar.couriertracking.Repository.CourierLogRepository;
import com.hkaynar.couriertracking.Repository.CourierTrackingRepository;
import com.hkaynar.couriertracking.Service.CourierService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hkaynar on 13.09.2021
 */
class CourierTrackingServiceImpTest {

    @InjectMocks
    private  CourierTrackingServiceImp courierTrackingServiceImp;

    @Mock
    CourierTrackingRepository courierTrackingRepository;
    @Mock
    CourierLogRepository courierLogRepository;
    @Mock
    CourierLogPopulator courierLogPopulator;
    @Mock
    CourierService courierService;

    @Test
    void countMeter() {
    }

    @Test
    void logingCourier() {
    }

    @Test
    void saveCourierTrack() {
        Date date=new Date();

        CourierTrackingDto courierTrackingDto =new CourierTrackingDto();
        courierTrackingDto.setCourierName("Deneme");
        courierTrackingDto.setLatitude(40.9923307);
        courierTrackingDto.setLongitude(29.1244229);
        courierTrackingDto.setTime(date);
        courierTrackingServiceImp.saveCourierTrack(courierTrackingDto);
    }

    @Test
    void checkDistance() {
    }

    @Test
    void saveCourierLog() {
        CourierTrackingDto courierTrackingDto=new CourierTrackingDto();
        Date date=new Date();
        String storeName="Deneme-Store";
        courierTrackingDto.setCourierName("Test-Name");
        courierTrackingDto.setTime(date);
        courierTrackingDto.setLatitude(40.9923307);
        courierTrackingDto.setLongitude(29.1244229);

        CourierLog result=courierTrackingServiceImp.saveCourierLog(courierTrackingDto,storeName);

        assertEquals(result.getCourierName(),courierTrackingDto.getCourierName());
    }
}