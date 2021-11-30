package bg.stoyank.footballtix.task;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TaskDefinition {
    private Long footballMatchId;
    private Date executionDateTime;
    private String actionType;
}
