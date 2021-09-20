package bg.stoyank.footballtix.repository;

import bg.stoyank.footballtix.model.FootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FootballMatchRepository extends JpaRepository<FootballMatch, Integer> {
}
