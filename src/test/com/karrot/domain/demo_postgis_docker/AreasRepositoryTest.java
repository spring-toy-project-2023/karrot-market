package com.karrot.domain.demo_postgis_docker;

import com.karrot.TestContainer;
import com.karrot.domain.demo_postgis_docker.repository.AreasRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AreasRepositoryTest extends TestContainer {
//public class AreasRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AreasRepository areasRepository;

    @AfterClass
    public static void sunSet(){
        composeContainer.stop();
    }

    @BeforeClass
    public static void sunRise(){
        composeContainer.start();
    }

    @Test
    public void shouldSaveAreas() {
        double[][] coordinates = new double[][]{
                { 126.976888842748167, 37.575650779448786 },
                { 126.977034498877501, 37.569194530054553 },
                { 126.975974728212492, 37.569336299425771 },
                { 126.975374709912543, 37.569315567021562 },
                { 126.974331935623255, 37.569261800517531 },
                { 126.969048370018541, 37.568194417708327 },
                { 126.968544936033837, 37.568427679612753 },
                { 126.966649959821197, 37.569491655206583 },
                { 126.966281750244846, 37.569700734798694 },
                { 126.966097327080405, 37.569856509723706 },
                { 126.965728529225771, 37.570183936115114 },
                { 126.965926998221278, 37.570318805686206 },
                { 126.96601094018429, 37.571548395577473 },
                { 126.963659220521961, 37.575174660660373 },
                { 126.963086004345101, 37.576485920015543 },
                { 126.962840990511978, 37.57666158609274 },
                { 126.962810410472628, 37.579448809656768 },
                { 126.967424315843317, 37.579601537124489 },
                { 126.967421763026508, 37.579263521441646 },
                { 126.967430060184597, 37.579192577998604 },
                { 126.967457090095607, 37.578975250585437 },
                { 126.968066046996256, 37.578246780467879 },
                { 126.968955116954774, 37.577935262340283 },
                { 126.969212842969057, 37.577935299309388 },
                { 126.969414538865792, 37.578121124142172 },
                { 126.969664426694706, 37.578531136682216 },
                { 126.969667219148718, 37.578736205134931 },
                { 126.969668773533087, 37.578992879009881 },
                { 126.969669499103631, 37.57911252674959 },
                { 126.969904573616262, 37.579301753628727 },
                { 126.97135197544759, 37.57951327793981 },
                { 126.973819257844539, 37.579372140302624 },
                { 126.973917363383421, 37.578487073041011 },
                { 126.973939619980882, 37.578240429978088 },
                { 126.974331538357575, 37.575749906299862 },
                { 126.975803789978045, 37.57564946882421 },
                { 126.976888842748167, 37.575650779448786 }
        };

        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        ArrayList<Coordinate> coordinatesList = new ArrayList<>();
        for (double[] coordinate : coordinates) {
            coordinatesList.add(new Coordinate(coordinate[0], coordinate[1]));
        }

        Polygon polygon = geometryFactory.createPolygon(coordinatesList.toArray(new Coordinate[]{}));
        Point point = polygon.getCentroid();

        Areas area = new Areas();
        area.setName("서울특별시 종로구 사직동");
        area.setBoundaryPolygon(polygon);
        area.setCentricCoordinates(point);

        System.out.println("TYPARK READABLE RESULTS >>>> before save area " + area);
        areasRepository.save(area);
        System.out.println("TYPARK READABLE RESULTS >>>> after save area " + area);

        entityManager.persistAndFlush(area);

        System.out.println("TYPARK READABLE RESULTS >>>> area list " + areasRepository.findAll());
        assertThat(areasRepository.findAll().size()).isEqualTo(1);
    }

}
