package com.karrot.domain.demo_postgis_docker;

import static org.assertj.core.api.Assertions.assertThat;

import com.karrot.TestContainer;
import com.karrot.domain.demo_postgis_docker.repository.AreasRepository;
import com.karrot.domain.demo_postgis_docker.service.AreasService;
import com.karrot.domain.demo_postgis_docker.service.AreasServiceImpl;

import org.junit.*;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
public class AreasServiceImplTest extends TestContainer {
//public class AreasServiceImplTest {
    @TestConfiguration
    static class AreasServiceTestContextConfiguration {
        @Bean
        public AreasServiceImpl areasService() {
            return new AreasServiceImpl();
        }
    }

    @Autowired
    private AreasService areasService;

    @MockBean(name = "AreasRepository")
    private AreasRepository areasRepository;

    @AfterClass
    public static void sunSet(){
        composeContainer.stop();
    }

    @BeforeClass
    public static void sunRise(){
        composeContainer.start();
    }

    @Before
    public void setUp() {
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
            // 행정동 정보는 경도가 먼저 나오는데, Coordinate는 위도가 먼저 온다.
            coordinatesList.add(new Coordinate(coordinate[1], coordinate[0]));
        }

        Polygon polygon = geometryFactory.createPolygon(coordinatesList.toArray(new Coordinate[]{}));
        Point point = polygon.getCentroid();

        Areas area = new Areas();
        UUID id = UUID.randomUUID();
        area.setId(id);
        area.setName("서울특별시 종로구 사직동");
        area.setBoundaryPolygon(polygon);
        area.setCentricCoordinates(point);

        List<Areas> allAreas = Arrays.asList(area);

        Mockito.when(areasRepository.findById(area.getId())).thenReturn(Optional.of(area));
        Mockito.when(areasRepository.findAll()).thenReturn(allAreas);
        Mockito.when(areasRepository.findById(id)).thenReturn(Optional.empty());
    }

    @Test
    public void whenCoordinatesGiven_thenAreasCouldCalculate() {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        Point point = geometryFactory.createPoint(new Coordinate(37.574103, 126.970654));

        List<Areas> areasList = areasService.getAllAreas();

        assertThat(areasList.size()).isEqualTo(1);
        for (Areas area : areasList) {
            System.out.println("TYPARK READABLE RESULTS >>>> Centric Coordinates " + area.getCentricCoordinates());
            assertThat(area.getBoundaryPolygon().contains(area.getCentricCoordinates())).isTrue();
            System.out.println("TYPARK READABLE RESULTS >>>> Random Coordinates" + point);
            assertThat(area.getBoundaryPolygon().contains(point)).isTrue();
        }
    }


}