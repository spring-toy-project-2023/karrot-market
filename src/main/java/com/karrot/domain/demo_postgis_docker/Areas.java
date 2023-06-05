package com.karrot.domain.demo_postgis_docker;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@ToString
@Entity
@Table(name = "areas")
public class Areas {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Lob
    @Column(name = "boundary_polygon")
    @NotNull
    private Polygon boundaryPolygon;

    @Lob
    @Column(name = "centric_coordinates")
    @NotNull
    private Point centricCoordinates;
}
