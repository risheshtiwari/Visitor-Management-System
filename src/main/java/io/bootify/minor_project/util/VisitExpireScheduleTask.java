package io.bootify.minor_project.util;

import io.bootify.minor_project.repos.VisitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class VisitExpireScheduleTask {

    private static Logger LOGGER = LoggerFactory.getLogger(VisitExpireScheduleTask.class);

    @Autowired
    private VisitRepository visitRepository;

    @Scheduled(fixedDelay = 1000)
    public void markVisitExpire(){
        LOGGER.info("Marking visit as Expired");
        // fetch visits from DB where status is waiting and
        //if data is blank return
        //
        //
        //
        //

    }
}