package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by fabiodocoutooliveira on 9/14/17.
 */

public class StringConverter {

    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy - hh:mm a");
    private final Integer ONE = 1;
    private final Integer ZERO = 0;
    private final Long ONE_MINUTE_LONG = 1l;
    private final Integer SIXTY = 60;


    public String getLocalDateTimeFormatted(){
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return FORMAT.format(date);
    }


    public String getLocalTimeFormatted(){
        Calendar cal = Calendar.getInstance(); // get current time in a Calendar
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        String currentTimeString = String.valueOf(hour);
        return currentTimeString;
    }

    public String convertedTimeFromSeconds(Long seconds){

        String output = "";
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / SIXTY;
        if (hours > ZERO && hours < ONE) {
            output = String.valueOf(minutes).concat(" minuto(s)");
        } else {
            output = String.valueOf(hours).concat(" hora(s) ").concat(String.valueOf(minutes).concat(" minuto(s)"));
        }
        return output;
    }

    public String convertedTimeFromMinutes(Long minute){

        String output = "";
        long hours = minute / SIXTY;
        if (minute > ZERO && minute < SIXTY) {
            output = String.valueOf(minute).concat(" minuto(s)");
        } else {
            output = String.valueOf(hours).concat(" hora(s) ").concat(String.valueOf(minute).concat(" minuto(s)"));
        }
        return output;
    }

    public String generateActivityLogTitle(Integer id){
        return "#" + String.valueOf(id) + " Atividade";
    }


    /**
     * Convert a millisecond duration to a string format
     *
     * @param millis A duration to convert to a string form
     * @return A string of the form "X Days Y Hours Z Minutes A Seconds".
     */
    public static String getDurationBreakdownComplete(long millis) {
        if(millis < 0)
        {
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }

        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        StringBuilder sb = new StringBuilder(64);
        sb.append(days);
        sb.append(" Days ");
        sb.append(hours);
        sb.append(" Hora(s) ");
        sb.append(minutes);
        sb.append(" minuto(s) ");
        sb.append(seconds);
        sb.append(" second(s)");

        return(sb.toString());
    }


    public static String getDurationBreakdownInHours(long millis) {
        if(millis < 0)
        {
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }

        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);

        StringBuilder sb = new StringBuilder(64);
        sb.append(hours);
        sb.append(" h ");
        sb.append(minutes);
        sb.append(" m ");
        return(sb.toString());
    }


    public static String getDurationBreakdownInSeconds(long millis) {
        if(millis < 0){
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
        StringBuilder sb = new StringBuilder(64);
        sb.append(seconds);
        sb.append(" Seconds");

        return(sb.toString());
    }

}
