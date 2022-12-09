package com.solera.scheduledreportprocessor;

// TODO - add needed attributes 
public class ScheduledReport {

    private String name;

    public ScheduledReport() {
    }

    public ScheduledReport(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "name: " + name;
    }

}