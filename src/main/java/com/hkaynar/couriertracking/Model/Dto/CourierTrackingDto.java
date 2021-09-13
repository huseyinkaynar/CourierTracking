package com.hkaynar.couriertracking.Model.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @author hkaynar on 07.09.2021
 */
@Data
public class CourierTrackingDto {

    private String courierName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date time;
    private double latitude;
    private double longitude;
}
