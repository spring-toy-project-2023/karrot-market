package com.karrot.domain.demo_postgis_docker;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@ToString
@Entity
@Table(name = "activity_areas")
public class ActivityAreas {
    @Id
    private ActivityAreasPK id;

    //TODO: 이게 맞나?..
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="user_id", referencedColumnName="id", insertable = false, updatable = false)
    })
    private Users user;

    //TODO: 이게 맞나?..
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="reference_area_id", referencedColumnName="id", insertable = false, updatable = false)
    })
    private Areas referenceArea;

    @Column(name = "distance_meters")
    @NotNull()
    private Integer distanceMeters;

    @Column(name = "area_ids")
    @NotNull()
    private String area_ids;

    //@Temporal should only be set on a java.util.Date or java.util.Calendar property
    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "authenticated_at")
    private LocalDateTime authenticatedAt;
}
