package bg.stoyank.footballtix.repository;

import bg.stoyank.footballtix.model.FootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("FootballMatchRepository")
public interface FootballMatchRepository extends JpaRepository<FootballMatch, Integer> {
}
