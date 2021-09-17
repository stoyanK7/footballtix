package bg.stoyank.footballtix;

import bg.stoyank.footballtix.model.FootballMatch;
import bg.stoyank.footballtix.service.FootballMatchService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FootballServiceTests {

    private final FootballMatchService footballMatchService;

    @Autowired
    public FootballServiceTests(FootballMatchService footballMatchService) {
        this.footballMatchService = footballMatchService;
    }

    @Test
    @Order(1)
    public void testAddFootballMatch() {
        FootballMatch footballMatch = new FootballMatch();

        footballMatchService.addFootballMatch(footballMatch);

        Assertions.assertEquals(1, footballMatchService.getAllFootballMatches().size());
    }

    @Test
    @Order(2)
    public void testDeleteFootballMatch() {
        footballMatchService.deleteFootballMatch(1);

        Assertions.assertEquals(0, footballMatchService.getAllFootballMatches().size());
    }

    @Test
    @Order(3)
    public void testGetAllFootballMatches() {
        FootballMatch footballMatch1 = new FootballMatch();
        FootballMatch footballMatch2 = new FootballMatch();
        FootballMatch footballMatch3 = new FootballMatch();
        FootballMatch footballMatch4 = new FootballMatch();

        footballMatchService.addFootballMatch(footballMatch1);
        footballMatchService.addFootballMatch(footballMatch2);
        footballMatchService.addFootballMatch(footballMatch3);
        footballMatchService.addFootballMatch(footballMatch4);


        Assertions.assertEquals(4, footballMatchService.getAllFootballMatches().size());
    }




}
