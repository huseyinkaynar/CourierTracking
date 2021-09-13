package com.hkaynar.couriertracking.Model.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author hkaynar on 11.09.2021
 */
@Data

public class TotalDistanceResponse {
    private String courierName;
    private float totalDistance;

}
