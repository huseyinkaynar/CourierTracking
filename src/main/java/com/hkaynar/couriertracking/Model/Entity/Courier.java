package com.hkaynar.couriertracking.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author hkaynar on 07.09.2021
 */
@Entity
@Table(name = "courier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Courier {

    @Id
    @Column(name = "courier_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String courierName;
}
