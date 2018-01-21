package th.net.cat.epis.batch.task;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by imake on 02/01/2017.
 */
@Component("invoiceSummaryTask")
public class InvoiceSummaryTask implements Tasklet {
    private static final Logger logger = Logger.getLogger(InvoiceLockTask.class.getName());
    @Resource(name="episJdbcTemplate")
    JdbcTemplate episJdbcTemplate;
    @Value("${epis.task.active}") String taskActive;
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        logger.info("into InvoiceSummaryTask");

        return RepeatStatus.FINISHED;
    }
}
