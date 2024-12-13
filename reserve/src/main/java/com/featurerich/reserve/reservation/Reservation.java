package com.featurerich.reserve.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "reservation_reservation")
public class Reservation {

    @Id
    @Column("id")
    private String id;

    @Version
    @Column("version")
    private Long version;

    @Column("unit_id")
    private Long unitId;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("start_at")
    private Long startAt;

    @Column("end_at")
    private Long endAt;
}
