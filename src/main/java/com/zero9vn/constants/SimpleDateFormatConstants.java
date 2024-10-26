package com.zero9vn.constants;

import com.thoughtworks.xstream.XStream;
import com.zero9vn.utils.MapEntryConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SimpleDateFormatConstants {
    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("Gyy年MM", Locale.JAPAN);
        System.out.println(format.format(new Date()));
        String tmp = SimpleDateFormatConstants.convertToJPDateString(new Date());
        System.out.println(tmp);
    }

    public static final Map<String, String> simpleDateFormatMap;

    static {
        Map<String, String> map = new HashMap<>();
        try {
            StringBuilder xml = new StringBuilder();
            try (InputStream in = SimpleDateFormatConstants.class.getResourceAsStream("/simple_date_format.xml");
                 BufferedReader reader = new BufferedReader(new InputStreamReader(in, "Shift_JIS"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // buffer.append(line);
                    xml.append(line);
                }
            }
            XStream magicApi = new XStream();
            magicApi.registerConverter(new MapEntryConverter());
            magicApi.alias("root", Map.class);
            Map<String, String> extractedMap = (Map<String, String>) magicApi.fromXML(xml.toString());

            for (Map.Entry<String, String> entry : extractedMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                map.put(key, value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        simpleDateFormatMap = Collections.unmodifiableMap(map);
    }

    /**
     * yyyy
     */
    public static final SimpleDateFormat formatteryyyy = new SimpleDateFormat(simpleDateFormatMap.get("formatteryyyy"));
    /**
     * yyyy/MM/dd
     */
    public static final SimpleDateFormat formatteryyyyMMdd = new SimpleDateFormat(simpleDateFormatMap.get("formatteryyyyMMdd"));
    /**
     * yyyyMMdd
     */
    public static final SimpleDateFormat formatteryyyyMMdd2 = new SimpleDateFormat(simpleDateFormatMap.get("formatteryyyyMMdd2"));

    /**
     * yyyy\u5E74MM\u6708dd\u65E5
     */
    public static final SimpleDateFormat formatteryyyyMMddJP = new SimpleDateFormat(simpleDateFormatMap.get("formatteryyyyMMddJP"), Locale.JAPAN);
    /**
     * yyyy/MM/dd HH:mm
     */
    public static final SimpleDateFormat formatteryyyyMMddHHmm = new SimpleDateFormat(simpleDateFormatMap.get("formatteryyyyMMddHHmm"));
    /**
     * yyyyMMddHHmmssSSS
     */
    public static final SimpleDateFormat formatteryyyyMMddHHmmssSSS = new SimpleDateFormat(simpleDateFormatMap.get("formatteryyyyMMddHHmmssSSS"));
    public static final SimpleDateFormat formatteryyyyMMdd_HHmmss_SSS = new SimpleDateFormat(simpleDateFormatMap.get("formatteryyyyMMdd_HHmmss_SSS"));
    /**
     * yyyy/MM/dd HH:mm:ss
     */
    public static final SimpleDateFormat formatteryyyyMMddHHmmss = new SimpleDateFormat(simpleDateFormatMap.get("formatteryyyyMMddHHmmss"));
    /**
     * yyyyMMddHHmmss
     */
    public static final SimpleDateFormat formatteryyyyMMddHHmmss2 = new SimpleDateFormat(simpleDateFormatMap.get("formatteryyyyMMddHHmmss2"));
    /**
     * yyyyMMddHHmmss
     */
    public static final SimpleDateFormat formatteryyyyMMddHHmm2 = new SimpleDateFormat(simpleDateFormatMap.get("formatteryyyyMMddHHmm2"));
    /**
     * HHmm
     */
    public static final SimpleDateFormat formatterHHmm = new SimpleDateFormat(simpleDateFormatMap.get("formatterHHmm"));
    /**
     * HH:mm
     */
    public static final SimpleDateFormat formatterHHmm2 = new SimpleDateFormat(simpleDateFormatMap.get("formatterHHmm2"));
    /**
     * MM/dd
     */
    public static final SimpleDateFormat formatterMMdd = new SimpleDateFormat(simpleDateFormatMap.get("formatterMMdd"));
    /**
     * MM/dd (EEE)
     */
    public static final SimpleDateFormat formatterMMddEEEJP = new SimpleDateFormat(simpleDateFormatMap.get("formatterMMddEEEJP"), Locale.JAPAN);

    /**
     * yyyy\u5E74MM\u6708dd\u65E5 (EEE)
     * eg: 2021\u5E744\u67089\u65E5 (\u91D1)
     */
    public static final SimpleDateFormat formatteryyMMddEEEJP = new SimpleDateFormat(simpleDateFormatMap.get("formatteryyMMddEEEJP"), Locale.JAPAN);
    /**
     * /**
     * MM\u6708dd\u65E5
     */
    public static final SimpleDateFormat formatterMMddJP = new SimpleDateFormat(simpleDateFormatMap.get("formatterMMddJP"), Locale.JAPAN);
    /**
     * /**
     * yy.MM.dd
     */
    public static final SimpleDateFormat formatteryyMMdd = new SimpleDateFormat(simpleDateFormatMap.get("formatteryyMMdd"));

    /**
     * yyyy-MM
     */
    public static final SimpleDateFormat formatteryyyyMM = new SimpleDateFormat(simpleDateFormatMap.get("formatteryyyyMM"));
    public static final SimpleDateFormat formatteryyyyMMdd3 = new SimpleDateFormat(simpleDateFormatMap.get("formatteryyyyMMdd3"));

    /**
     * yyyy-MM
     */
    public static final SimpleDateFormat formatteryyyyMM4 = new SimpleDateFormat(simpleDateFormatMap.get("formatteryyyyMM4"));
    /**
     * yyyy年MM
     */
    public static final SimpleDateFormat formatteryyyyMMJP = new SimpleDateFormat(simpleDateFormatMap.get("formatteryyyyMMJP"), Locale.JAPANESE);

    public static final String convertToJPDateString(Date date) {
        return convertToJPDateString(date, simpleDateFormatMap.get("formatterGyyMMJP"));
    }

    public static final String convertToJPDateString(Date date, String pattern) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern, Locale.JAPANESE);
        LocalDate isoDate = LocalDate.from(date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        JapaneseDate japaneseDate = JapaneseDate.from(isoDate);
        return japaneseDate.format(dateFormatter);
    }


    public static String getDayForDate(Date date) {
        return new SimpleDateFormat("EEEE", Locale.JAPANESE).format(date);
    }


}
