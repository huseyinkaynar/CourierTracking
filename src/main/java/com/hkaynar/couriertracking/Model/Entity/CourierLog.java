package com.hkaynar.couriertracking.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author hkaynar on 07.09.2021
 */
@Entity
@Table(name = "courier_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourierLog {

    @Id
    @Column(name = "courier_log_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String courierName;
    private String storeName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date time;
}
