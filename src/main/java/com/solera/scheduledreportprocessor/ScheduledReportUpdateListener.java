package com.solera.scheduledreportprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

/*
 * This component will listen for 'before', 'after', and 'error' events on "chunks" of the writer
 * 
 * TODO - This can be used to handle the updates to the scheduledReport rows (i.e. lastRun)
 */
@Component
public class ScheduledReportUpdateListener implements ItemWriteListener<ScheduledReport> {

    private static final Logger log = LoggerFactory.getLogger(ScheduledReportUpdateListener.class);

    @Override
    public void beforeWrite(List<? extends ScheduledReport> items) {
        log.info("beforeWrite: " + items);
    }

    @Override
    public void afterWrite(List<? extends ScheduledReport> items) {
        log.info("afterWrite: " + items);
    }

    @Override
    public void onWriteError(Exception exception, List<? extends ScheduledReport> items) {
        log.error("onWriteError: " + items);
    }
}
