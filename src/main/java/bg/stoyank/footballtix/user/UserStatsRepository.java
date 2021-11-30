package bg.stoyank.footballtix.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UserStatsRepository extends JpaRepository<UserStats, Date> {
    List<UserStats> getAllByDateBetween(Date from, Date to);
}
