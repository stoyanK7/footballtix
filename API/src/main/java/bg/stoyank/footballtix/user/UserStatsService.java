package bg.stoyank.footballtix.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserStatsService {
    private UserStatsRepository userStatsRepository;

    public List<UserStats> getAllByDateBetween(Date from, Date to) {
        return userStatsRepository.getAllByDateBetween(from, to);
    }

    public void createNewDayStat(UserStats userStats) {
        userStatsRepository.save(userStats);
    }

    public void increaseCountByOne(Date date) {
        if(!existsById(date))
            createNewDayStat(new UserStats(new Date(), 0));
        UserStats userStats = userStatsRepository.getById(date);
        userStats.setRegisteredUsers(userStats.getRegisteredUsers() + 1);
        userStatsRepository.save(userStats);
    }

    private boolean existsById(Date id) {
        return userStatsRepository.existsById(id);
    }
}
