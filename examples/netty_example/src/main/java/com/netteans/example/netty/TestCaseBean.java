package com.netteans.example.netty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCaseBean {
    private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

    private Date date;
    private String message;
    private Date rcvDate;

    public Date getDate() {
        return date;
    }

    public String getSimpleDate() {
        if (date == null) {
            return "null";
        }
        return formatter.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void parseDate(String dateStr) {
        try {
            setDate(formatter.parse(dateStr));
        } catch (ParseException pe) {
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRcvDate(Date rcvDate) {
        this.rcvDate = rcvDate;
    }

    public void parseRcvDate(String rcvDateStr) {
        try {
            setRcvDate(formatter.parse(rcvDateStr));
        } catch (ParseException pe) {
        }
    }

    public Date getRcvDate() {
        return rcvDate;
    }

    public String getSimpleRcvDate() {
        if (rcvDate == null) {
            return "null";
        }
        return formatter.format(rcvDate);
    }

    @Override
    public String toString() {
        return super.toString() + "#" + getSimpleDate() + "#" + message + "#" + getSimpleRcvDate();
    }
}
