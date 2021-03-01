package com.test.resrservice;

import java.util.Date;

public class DateDiference {
    private long days;
    private long months;
    private long years;

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
    }

    public long getMonths() {
        return months;
    }

    public void setMonths(long months) {
        this.months = months;
    }

    public long getYears() {
        return years;
    }

    public void setYears(long years) {
        this.years = years;
    }

    public static long getDaysDiference(Date start, Date end){
        long endMillis  = end.getTime();
        long startMillis = start.getTime();
        long diffMillis = endMillis - startMillis;

        long result = (diffMillis / (1000 * 60 * 60 * 24)) % 365;

        //return ((end.getTime() - start.getTime()) / (1000 * 60 * 60)) % 365;
        return result;
    }

    public static long getMonthsDiference(Date start, Date end){
        return ((end.getTime() - start.getTime()) / (1000 * 60 * 60)) % 24;
    }

    public static long getYearsDiference(Date start, Date end){
        return (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24 * 365);
    }

    public static DateDiference build(Date start, Date end){
        DateDiference diference = new DateDiference();
        diference.setDays(getDaysDiference(start, end));
        diference.setMonths(getMonthsDiference(start, end));
        diference.setYears(getYearsDiference(start, end));
        return diference;
    }

    @Override
    public String toString(){
        return String.format("%d-%d-%d", years, months, days);
    }
}
