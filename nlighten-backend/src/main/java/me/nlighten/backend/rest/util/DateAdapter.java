package me.nlighten.backend.rest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter for transforming Date object to required date format
 * 
 * @author Matus_Majchrak
 */
public class DateAdapter extends XmlAdapter<String, Date> {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Date unmarshal(String v) throws Exception {
        try {
            return sdf.parse(v);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("The date is not in correct format!");
        }
    }

    public String marshal(Date v) throws Exception {
        return sdf.format(v);
    }

}
