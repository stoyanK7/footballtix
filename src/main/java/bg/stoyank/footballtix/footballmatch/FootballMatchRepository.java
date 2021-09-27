package bg.stoyank.footballtix.footballmatch;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FootballMatchRepository extends JpaRepository<FootballMatch, Integer> {
}
