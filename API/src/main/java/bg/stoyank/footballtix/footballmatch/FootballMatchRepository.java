package bg.stoyank.footballtix.footballmatch;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FootballMatchRepository extends JpaRepository<FootballMatch, Long> {
    List<FootballMatch> getFootballMatchesByStartingDateTimeAfterOrderByStartingDateTimeAsc(Date dateTime);

    List<FootballMatch> getFootballMatchesByStartingDateTimeBeforeOrderByStartingDateTimeDesc(Date dateTime);
}
