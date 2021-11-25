package bg.stoyank.footballtix.footballmatch;

import bg.stoyank.footballtix.email.EmailService;
import bg.stoyank.footballtix.email.EmailTemplateService;
import bg.stoyank.footballtix.footballmatch.exception.FootballMatchNotFoundException;
import bg.stoyank.footballtix.order.Order;
import bg.stoyank.footballtix.order.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class FootballMatchService {
    private FootballMatchRepository footballMatchRepository;
    private OrderRepository orderRepository;
    private EmailService emailService;
    private EmailTemplateService emailTemplateService;

    public List<FootballMatch> getAllFootballMatches() {
        return footballMatchRepository.findAll();
    }

    public List<FootballMatch> getAllUpcomingFootballMatches() {
        return footballMatchRepository
                .getFootballMatchesByStartingDateTimeAfterOrderByStartingDateTimeAsc(new Date());
    }

    public FootballMatch getFootballMatchById(int footballMatchId)
            throws FootballMatchNotFoundException {
        if (footballMatchExistsById(footballMatchId))
            return footballMatchRepository.getById(footballMatchId);

        throw new FootballMatchNotFoundException("Could not find football match with id: " + footballMatchId + ".");
    }

    public int createFootballMatch(FootballMatch footballMatch) {
        footballMatchRepository.save(footballMatch);
        return footballMatch.getId();
    }

    public void deleteFootballMatchById(int footballMatchId)
            throws FootballMatchNotFoundException {
        if (!footballMatchExistsById(footballMatchId))
            throw new FootballMatchNotFoundException
                    ("Could not find football match with id: " + footballMatchId + ".");

        footballMatchRepository.deleteById(footballMatchId);
    }

    @Transactional
    public void updateFootballMatch(int footballMatchId, FootballMatch footballMatch) {
        FootballMatch oldFootballMatch = footballMatchRepository.getById(footballMatchId);
        oldFootballMatch.setHomeTeam(footballMatch.getHomeTeam());
        oldFootballMatch.setAwayTeam(footballMatch.getAwayTeam());
        oldFootballMatch.setStartingDateTime(footballMatch.getStartingDateTime());
        oldFootballMatch.setStadium(footballMatch.getStadium());
        oldFootballMatch.setLocation(footballMatch.getLocation());
        oldFootballMatch.setLeague(footballMatch.getLeague());
        oldFootballMatch.setTicketsAvailable(footballMatch.getTicketsAvailable());
        oldFootballMatch.setPricePerTicket(footballMatch.getPricePerTicket());
        footballMatchRepository.save(oldFootballMatch);

        List<Order> orders = orderRepository.getAllByFootballMatch(footballMatch);
        for (Order order : orders) {
            String email = order.getEmail();
            String accountEmail = order.getAccountEmail();
            String subject = "Match: " + footballMatch.getHomeTeam() + " vs " + footballMatch.getAwayTeam();
//            emailService.send(email, subject,
//                    emailTemplateService.buildEmail
//                            (subject, email, footballMatch.getStartingDateTime()));
//            if (!email.equals(accountEmail))
//                emailService.send(accountEmail, subject,
//                        emailTemplateService.buildEmail
//                                (subject, accountEmail, footballMatch.getStartingDateTime()));

        }
    }

    private boolean footballMatchExistsById(int footballMatchId) {
        return footballMatchRepository.existsById(footballMatchId);
    }
}
