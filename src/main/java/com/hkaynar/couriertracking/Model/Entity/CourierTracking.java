package com.hkaynar.couriertracking.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @author hkaynar on 07.09.2021
 */
@Entity
@Table(name = "courier_tracking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourierTracking {

    @Id
    @Column(name = "courier_track_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String courierName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date time;
    private float latitude;
    private float longitude;

}
