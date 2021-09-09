package com.hkaynar.couriertracking.Model.Response;

import lombok.Data;

import java.util.Calendar;

/**
 * @author hkaynar on 07.09.2021
 */
@Data
public class CourierResponse {
    private Long id;
    private String courierName;
    private Calendar time;
    private float latitude;
    private float longitude;
}
