package bg.stoyank.footballtix;

import bg.stoyank.footballtix.model.FootballMatch;
import bg.stoyank.footballtix.service.FootballMatchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
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

        Assertions.assertEquals(1, footballMatchService.getFootballMatches().size());
    }

    @Test
    @Order(2)
    public void testDeleteFootballMatch() {
        footballMatchService.deleteFootballMatch(1);

        Assertions.assertEquals(0, footballMatchService.getFootballMatches().size());
    }

//    @Test
//    @Order(3)
//    public void testGetFootballMatchById() {
//        FootballMatch footballMatch = new FootballMatch(3 , "hometeam",
//                "awayteam",
//                LocalDateTime.now(),
//                "stadium 1",
//                "location 1",
//                "league");
//
//        footballMatchService.addFootballMatch(footballMatch);
//
//        Assertions.assertEquals(footballMatch, footballMatchService.getFootballMatch(3));
// }


}
