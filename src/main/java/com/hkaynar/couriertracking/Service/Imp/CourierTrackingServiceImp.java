package com.hkaynar.couriertracking.Service.Imp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hkaynar.couriertracking.Exception.NotFoundException;
import com.hkaynar.couriertracking.Model.Dto.CourierTrackingDto;
import com.hkaynar.couriertracking.Model.Entity.CourierLog;
import com.hkaynar.couriertracking.Model.Response.ModelResponse;
import com.hkaynar.couriertracking.Model.Dto.StoreModel;
import com.hkaynar.couriertracking.Model.Entity.CourierTracking;
import com.hkaynar.couriertracking.Populator.CourierLogPopulator;
import com.hkaynar.couriertracking.Repository.CourierLogRepository;
import com.hkaynar.couriertracking.Repository.CourierTrackingRepository;
import com.hkaynar.couriertracking.Service.CourierService;
import com.hkaynar.couriertracking.Service.CourierTrackingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

/**
 * @author hkaynar on 08.09.2021
 */
@Service
public class CourierTrackingServiceImp implements CourierTrackingService {

    @Value("${Json.File.Path}")
    private String jsonFilePath;
    @Autowired
    CourierTrackingRepository courierTrackingRepository;
    @Autowired
    CourierLogRepository courierLogRepository;
    @Autowired
    CourierLogPopulator courierLogPopulator;
    @Autowired
    CourierService courierService;

    @Override
    public float countMeter(double lat1, double lng1, double lat2, double lng2) {

        double earthRadius = 6371000; //meters

        double degreeLat = Math.toRadians(lat2-lat1);//Radian Degree of between two Latitudes

        double degreeLng = Math.toRadians(lng2-lng1);//Radian Degree of Between Two Longitudes

        //Operations that give meter value
        double a = Math.sin(degreeLat/2) * Math.sin(degreeLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(degreeLng/2) * Math.sin(degreeLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);

        return dist;
    }

    @Override
    public ModelResponse logingCourier(CourierTrackingDto courierTrackingDto) throws NotFoundException {

        courierService.checkCourier(courierTrackingDto.getCourierName());
        //Save all courier location
        saveCourierTrack(courierTrackingDto);

        return new ModelResponse(checkDistance(courierTrackingDto));
    }


    @Override
    public void saveCourierTrack(CourierTrackingDto courierTrackingDto) {
        ModelMapper mapper = new ModelMapper();

        CourierTracking courierTracking=null;
        courierTracking=mapper.map(courierTrackingDto,CourierTracking.class);
        courierTrackingRepository.save(courierTracking);
    }

    @Override
    public CourierLog checkDistance(CourierTrackingDto courierTrackingDto)  {
        List<StoreModel> storeModels = null;
        CourierLog courierLog = null;
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            InputStream inputStream=new FileInputStream(new File(jsonFilePath));
            TypeReference<List<StoreModel>> typeReference= new TypeReference<>() {
            };
             storeModels=objectMapper.readValue(inputStream,typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (StoreModel s:storeModels) {
            float distance=countMeter(courierTrackingDto.getLatitude(), courierTrackingDto.getLongitude(),s.getLat(),s.getLng());
            if(distance<100){
                courierLog=saveCourierLog(courierTrackingDto,s.getName());
            }
        }

        return courierLog;
    }

    @Override
    public CourierLog saveCourierLog(CourierTrackingDto courierTrackingDto, String storeName) {
        CourierLog courierLog = null;
        long diffInMillies;
        CourierLog courierLogCompare=courierLogRepository.findTopByCourierNameOrderByTimeDesc(courierTrackingDto.getCourierName());

        if (courierLogCompare!=null){
            diffInMillies = courierTrackingDto.getTime().getTime() - courierLogCompare.getTime().getTime();

            //If in a minute enter another store zone
            if (!storeName.equals(courierLogCompare.getStoreName())){
                courierLog=courierLogPopulator.courierDtotoLog(courierTrackingDto,storeName);
                courierLogRepository.save(courierLog);
                //Same store over a minute
            }else if (storeName.equals(courierLogCompare.getStoreName())  && diffInMillies>60000){
                courierLog=courierLogPopulator.courierDtotoLog(courierTrackingDto,storeName);
                courierLogRepository.save(courierLog);
            }
        }
        //First Loging Courier
        else{
            courierLog=courierLogPopulator.courierDtotoLog(courierTrackingDto,storeName);
            courierLogRepository.save(courierLog);
        }

        return courierLog;

    }
}
