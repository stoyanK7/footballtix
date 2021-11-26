package bg.stoyank.footballtix.order;

import bg.stoyank.footballtix.footballmatch.FootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getAllByAccountEmailEqualsOrderByTransactionDateTimeDesc(String email);

    List<Order> getAllByFootballMatch(FootballMatch footballMatch);
}
