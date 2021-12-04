package bg.stoyank.footballtix.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getAllByAccountEmailEqualsOrderByTransactionDateTimeDesc(String email);

    boolean existsByFootballMatchIdEqualsAndAccountEmailEqualsAndFullNameEquals(long footballMatchId,
                                                                                String accountEmail,
                                                                                String fullName);

    List<Order> getAllByFootballMatchId(Long footballMatchId);
}
