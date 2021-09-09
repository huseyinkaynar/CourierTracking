package com.hkaynar.couriertracking.Model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * @author hkaynar on 08.09.2021
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreModel {

    private String name;
    private double lat;
    private double lng;
}
