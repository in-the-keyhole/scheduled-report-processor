package com.solera.scheduledreportprocessor;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ScheduledReportResultRowMapper implements RowMapper<ScheduledReport> {
    @Override
    public ScheduledReport mapRow(ResultSet rs, int i) throws SQLException {
        ScheduledReport scheduledReport = new ScheduledReport();
        // TODO - map all columns
        // scheduledReport.setId(rs.getLong("id"));
        scheduledReport.setName(rs.getString("name"));
        return scheduledReport;
    }
}
