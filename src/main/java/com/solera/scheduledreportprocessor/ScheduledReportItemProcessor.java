package com.solera.scheduledreportprocessor;

import org.springframework.batch.item.ItemProcessor;

/*
 * This could be used to determine if the report should still be sent 
 * Returning 'null' from here will halt processing on this item
 */
public class ScheduledReportItemProcessor implements ItemProcessor<ScheduledReport, ScheduledReport> {

    /*
     * This currently is a noop - but could be used for checking if the user is
     * active and has a valid subscription
     */
    @Override
    public ScheduledReport process(final ScheduledReport scheduledReport) throws Exception {
        // TODO - implement the needed logic or delete this processor and remove it from
        // the BatchConfiguration
        final String name = scheduledReport.getName().toUpperCase();
        final ScheduledReport transformedScheduledReport = new ScheduledReport(name);
        return transformedScheduledReport;
    }

}