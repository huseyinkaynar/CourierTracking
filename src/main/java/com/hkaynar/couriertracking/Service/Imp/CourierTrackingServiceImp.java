package com.hkaynar.couriertracking.Service.Imp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hkaynar.couriertracking.Model.Dto.CourierDto;
import com.hkaynar.couriertracking.Model.Entity.CourierLog;
import com.hkaynar.couriertracking.Model.Response.CourierLogResponse;
import com.hkaynar.couriertracking.Model.Response.ModelResponse;
import com.hkaynar.couriertracking.Model.Dto.StoreModel;
import com.hkaynar.couriertracking.Model.Entity.CourierTracking;
import com.hkaynar.couriertracking.Populator.CourierLogPopulator;
import com.hkaynar.couriertracking.Repository.CourierLogRepository;
import com.hkaynar.couriertracking.Repository.CourierTrackingRepository;
import com.hkaynar.couriertracking.Service.CourierTrackingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public ModelResponse logingCourier(CourierDto courierDto) throws IOException {
        //Save all courier location
        saveCourierTrack(courierDto);

        checkDistance(courierDto);

        return new ModelResponse(courierDto);
    }


    @Override
    public void saveCourierTrack(CourierDto courierDto) {
        ModelMapper mapper = new ModelMapper();

        CourierTracking courierTracking=null;
        courierTracking=mapper.map(courierDto,CourierTracking.class);
        courierTrackingRepository.save(courierTracking);
    }

    @Override
    public void checkDistance(CourierDto courierDto)  {
        List<StoreModel> storeModels = null;

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
            float distance=countMeter(courierDto.getLatitude(),courierDto.getLongitude(),s.getLat(),s.getLng());
            if(distance<100){
                saveCourierLog(courierDto,s.getName());
            }
        }
    }

    @Override
    public void saveCourierLog(CourierDto courierDto, String storeName) {
        CourierLog courierLog;
        long diffInMillies;
        CourierLog courierLogCompare=courierLogRepository.findTopByCourierNameOrderByTimeDesc(courierDto.getCourierName());

        if (courierLogCompare!=null){
            diffInMillies = courierDto.getTime().getTime() - courierLogCompare.getTime().getTime();

            //If in a minute enter another store zone
            if (!storeName.equals(courierLogCompare.getStoreName())){
                courierLog=courierLogPopulator.courierDtotoLog(courierDto,storeName);
                courierLogRepository.save(courierLog);
                //Same store over a minute
            }else if (storeName.equals(courierLogCompare.getStoreName())  && diffInMillies>60000){
                courierLog=courierLogPopulator.courierDtotoLog(courierDto,storeName);
                courierLogRepository.save(courierLog);
            }
        }
        //First Loging Courier
        else{
            courierLog=courierLogPopulator.courierDtotoLog(courierDto,storeName);
            courierLogRepository.save(courierLog);
        }


    }
}
