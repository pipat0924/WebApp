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
 * Created by imake on 27/10/2016.
 */
@Component("invoiceLockTask")
public class InvoiceLockTask implements Tasklet {
    private static final Logger logger = Logger.getLogger(InvoiceLockTask.class.getName());
    @Resource(name="episJdbcTemplate")
    JdbcTemplate episJdbcTemplate;
    @Value("${epis.task.active}") String taskActive;
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        logger.info("into invoiceLockTask");
        if(taskActive.equals("1")) {
            String intervalTime = null;
            Object obj = chunkContext.getStepContext().getJobParameters().get("intervalTime");
            if (obj != null)
                intervalTime = (String) obj;
            episJdbcTemplate.update("DELETE FROM INV_LOCK "
                    + " where CREATED_TIME <=  ( systimestamp - INTERVAL '" + intervalTime + "' MINUTE ) "
            );
        }
        return RepeatStatus.FINISHED;
    }
}
