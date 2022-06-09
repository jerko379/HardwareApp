package hr.tvz.josipovic.hardwareapp.Jobs;

import hr.tvz.josipovic.hardwareapp.Hardware.HardwareDTO;
import hr.tvz.josipovic.hardwareapp.Hardware.HardwareServ;
import hr.tvz.josipovic.hardwareapp.Hardware.HardwareService;
import hr.tvz.josipovic.hardwareapp.Hardware.JdbcHardwareRepo;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Component
public class HardwareJob extends QuartzJobBean {

    @Autowired
    private HardwareServ hardwareService;


    @Override
    protected void executeInternal(JobExecutionContext context) {
            System.out.println("-------------------------------------------------------");
            hardwareService.informationOnStock();
            System.out.println("-------------------------------------------------------");

    }

}