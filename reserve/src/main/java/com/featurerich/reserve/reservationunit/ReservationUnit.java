package com.featurerich.reserve.reservationunit;

import java.util.concurrent.TimeUnit;
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
@Table(name = "reservation_reservation_unit")
public class ReservationUnit {

    @Id
    @Column("id")
    private String id;

    @Version
    @Column("version")
    private Long version;

    @Column("time_unit")
    private TimeUnit timeUnit;

    @Column("min_unit")
    private Long minUnit;

    @Column("max_unit")
    private Long maxUnit;

    @Column("start_at")
    private Long startAt;

    @Column("end_at")
    private Long endAt;

    @Column("updated_at")
    private Long updatedAt;

    @Column("updated_by")
    private String updatedBy;
}
