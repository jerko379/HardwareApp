package hr.tvz.josipovic.hardwareapp;

import hr.tvz.josipovic.hardwareapp.Jobs.HardwareJob;
import hr.tvz.josipovic.hardwareapp.Jobs.SchedulerConfig;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HardwareappApplication {

    public static void main(String[] args)  {
        SpringApplication.run(HardwareappApplication.class, args);



    }

}
