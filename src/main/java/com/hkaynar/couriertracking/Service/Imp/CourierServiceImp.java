package com.hkaynar.couriertracking.Service.Imp;

import com.hkaynar.couriertracking.Exception.NotFoundException;
import com.hkaynar.couriertracking.Model.Dto.CourierDto;
import com.hkaynar.couriertracking.Model.Entity.Courier;
import com.hkaynar.couriertracking.Model.LatitudeAndLongitude;
import com.hkaynar.couriertracking.Model.Response.ModelResponse;
import com.hkaynar.couriertracking.Model.Response.TotalDistanceResponse;
import com.hkaynar.couriertracking.Populator.CourierLogPopulator;
import com.hkaynar.couriertracking.Repository.CourierRepository;
import com.hkaynar.couriertracking.Repository.CourierTrackingRepository;
import com.hkaynar.couriertracking.Service.CourierService;
import com.hkaynar.couriertracking.Service.CourierTrackingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hkaynar on 07.09.2021
 */
@Service
public class CourierServiceImp implements CourierService {

    @Autowired
    CourierRepository courierRepository;

    @Autowired
    CourierTrackingRepository courierTrackingRepository;

    @Autowired
    CourierTrackingService courierTrackingService;

    @Autowired
    CourierLogPopulator courierLogPopulator;



    @Override
    public ModelResponse getAllCourier() {
        return new ModelResponse(courierRepository.findAll());
    }

    @Override
    public ModelResponse  countTracking(CourierDto courierDto) {
        //Check courier
        checkCourier(courierDto.getCourierName());

        List<LatitudeAndLongitude> data;
        TotalDistanceResponse totalDistanceResponse;
        float sumTravel=0;
        //Get courier tracking lat and long
        data=courierTrackingRepository.findByCourierName(courierDto.getCourierName());

        for (int i=0;i<data.size()-1;i++){
            float distance=courierTrackingService.countMeter(data.get(i).getLatitude(),data.get(i).getLongitude(),
                    data.get(i+1).getLatitude(),data.get(i+1).getLongitude());
            sumTravel=sumTravel+distance;
        }
        totalDistanceResponse=courierLogPopulator.responseToTotalDistanceResponse(courierDto.getCourierName(),sumTravel);

        return new ModelResponse(totalDistanceResponse);
    }

    @Override
    public void checkCourier(String name) throws NotFoundException {

        List<Courier> courier=courierRepository.findByCourierName(name);
        if (courier.size()==0){
            throw new NotFoundException("This courier not found.");
        }else if (courier.size()>1) {
            throw new NotFoundException("This courier name has been taken.");
        }

    }

    @Override
    public CourierDto saveCourier(CourierDto courierDto) {
        ModelMapper modelMapper=new ModelMapper();
        Courier courier=modelMapper.map(courierDto,Courier.class);
        courierRepository.save(courier);

        return courierDto;
    }
}
