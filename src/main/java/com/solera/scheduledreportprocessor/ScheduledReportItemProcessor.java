package com.solera.scheduledreportprocessor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

/*
 * This could be used to determine if the report should still be sent 
 * Returning 'null' from here will halt processing on this item
 */
public class ScheduledReportItemProcessor implements ItemProcessor<ScheduledReport, ScheduledReport> {

    /*
     * This currently is a noop (it just transforms name to uppercase) - but it
     * could be used for checking if the user is active and has a valid subscription
     */
    @Override
    public ScheduledReport process(@NonNull final ScheduledReport scheduledReport) throws Exception {
        // TODO - implement the needed logic
        final String name = scheduledReport.getName().toUpperCase();
        final ScheduledReport transformedScheduledReport = new ScheduledReport(name);
        return transformedScheduledReport;
    }

}