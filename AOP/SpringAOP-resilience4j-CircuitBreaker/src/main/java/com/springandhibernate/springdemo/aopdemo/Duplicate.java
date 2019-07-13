package com.springandhibernate.springdemo.aopdemo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.DuplicateMessageFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

public class Duplicate extends DuplicateMessageFilter {


    public int a1;
    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {

        return super.decide(marker, logger, level, format, params, t);
    }
}
