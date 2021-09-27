package bg.stoyank.footballtix.footballmatch;

import bg.stoyank.footballtix.footballmatch.FootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FootballMatchRepository extends JpaRepository<FootballMatch, Integer> {
}
