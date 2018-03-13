package edu.CalTestApp1.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import edu.CalTestApp1.activity.MainActivity;

/**
 * Utils class
 * @author albert.suarez.molgo
 */
public class Utils {

    public static String[] days = new String[9];

    public static final String JANUARY = "JANUARY";
    public static final String FEBRUARY = "FEBRUARY";
    public static final String MARCH = "MARCH";
    public static final String APRIL = "APRIL";
    public static final String MAY = "MAY";
    public static final String JUNE = "JUNE";
    public static final String JULY = "JULY";
    public static final String AUGUST = "AUGUST";
    public static final String SEPTEMBER = "SEPTEMBER";
    public static final String OCTOBER = "OCTOBER";
    public static final String NOVEMBER = "NOVEMBER";
    public static final String DECEMBER = "DECEMBER";


    public static Date createDate(int day, int month, int year) {
        Calendar cDate = Calendar.getInstance();
        cDate.set(Calendar.DATE, day);
        cDate.set(Calendar.MONTH, month);
        cDate.set(Calendar.YEAR, year);
        return cDate.getTime();
    }

    public static long createDate(int day, int month, int year, int hour, int minute) {
        Calendar cDate = Calendar.getInstance();
        cDate.set(Calendar.DATE, day);
        cDate.set(Calendar.MONTH, month);
        cDate.set(Calendar.YEAR, year);
        cDate.set(Calendar.HOUR_OF_DAY, hour);
        cDate.set(Calendar.MINUTE, minute);
        cDate.set(Calendar.SECOND, 0);
        return cDate.getTimeInMillis();
    }

    public static Date createHour(Date date, int hour, int minute) {
        Calendar cHour = Calendar.getInstance();
        cHour.setTime(date);
        cHour.set(Calendar.HOUR_OF_DAY, hour);
        cHour.set(Calendar.MINUTE, minute);
        return cHour.getTime();
    }

    public static int getDay(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        return cDay.get(Calendar.DATE);
    }

    public static String getDayOfWeekInString(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        int day = cDay.get(Calendar.DAY_OF_WEEK);
        if (day == Calendar.MONDAY) return MainActivity.MONDAY;
        if (day == Calendar.TUESDAY) return MainActivity.TUESDAY;
        if (day == Calendar.WEDNESDAY) return MainActivity.WEDNESDAY;
        if (day == Calendar.THURSDAY) return MainActivity.THURSDAY;
        if (day == Calendar.FRIDAY) return MainActivity.FRIDAY;
        if (day == Calendar.SATURDAY) return MainActivity.SATURDAY;
        if (day == Calendar.SUNDAY) return MainActivity.SUNDAY;
        return "";
    }

    public static int getMonth(Date date) {
        Calendar cMonth = Calendar.getInstance();
        cMonth.setTime(date);
        return cMonth.get(Calendar.MONTH);
    }

    public static int getYear(Date date) {
        Calendar cYear = Calendar.getInstance();
        cYear.setTime(date);
        return cYear.get(Calendar.YEAR);
    }

    public static int getHour(Date date) {
        Calendar cHour = Calendar.getInstance();
        cHour.setTime(date);
        return cHour.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(Date date) {
        Calendar cMinute = Calendar.getInstance();
        cMinute.setTime(date);
        return cMinute.get(Calendar.MINUTE);
    }

    public static int compareTo(Date date1, Date date2) {
        return date1.compareTo(date2);
    }

    public static String dateToString(Date date) {
        return Utils.getDay(date) + "/" + (Utils.getMonth(date) + 1) +  "/" + Utils.getYear(date) + "\n";
    }

    public static String hourToString(Date date) {
        String result = "";
        int hour = Utils.getHour(date);
        if (hour < 10) result += "0" + hour; else result += hour;
        result += ":";
        int minute = Utils.getMinute(date);
        if (minute < 10) result += "0" + minute; else result += minute;
        return result + "\n";
    }

    public static String hourAndMinuteToString(int hour, int minute) {
        String result = "";
        if (hour < 10) result += "0" + hour; else result += hour;
        result += ":";
        if (minute < 10) result += "0" + minute; else result += minute;
        return result;
    }

    /**
     * days[0]-[6]: number of the days
     * days[7]: month of the first day of the week
     * days[8]: year of the first day of the week
     */
    public static String[] getDaysOfWeek() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar now = Calendar.getInstance();
        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1;
        now.add(Calendar.DAY_OF_MONTH, delta);

        String year = "-1";
        String month = "-1";

        for (int i = 0; i < 7; i++) {
            String day = dateFormat.format(now.getTime());
            days[i] = day.substring(0, 2);
            if (i == 0) {
                month = day.substring(3, 5);
                year = day.substring(6);
            }
            now.add(Calendar.DAY_OF_MONTH, 1);
        }
        days[7] = month;
        days[8] = year;
        return days;
    }

    public static void setDaysOfWeek(long date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int delta = -calendar.get(GregorianCalendar.DAY_OF_WEEK) + 1;
        calendar.add(Calendar.DAY_OF_MONTH, delta);

        String year = "-1";
        String month = "-1";

        for (int i = 0; i < 7; i++) {
            String day = dateFormat.format(calendar.getTime());
            days[i] = day.substring(0, 2);
            if (i == 0) {
                month = day.substring(3, 5);
                year = day.substring(6);
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        days[7] = month;
        days[8] = year;
    }

    public static String[] nextWeek() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();

        int delta = -calendar.get(GregorianCalendar.DAY_OF_WEEK) + 1;
        calendar.add(Calendar.DAY_OF_MONTH, delta);

        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(days[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(days[7])-1);
        calendar.set(Calendar.YEAR, Integer.parseInt(days[8]));

        calendar.add(Calendar.DAY_OF_MONTH, 7);
        String year = "-1";
        String month = "-1";

        for (int i = 0; i < 7; i++) {
            String day = dateFormat.format(calendar.getTime());
            days[i] = day.substring(0, 2);
            if (i == 0) {
                month = day.substring(3, 5);
                year = day.substring(6);
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        days[7] = month;
        days[8] = year;
        return days;
    }

    public static String[] previousWeek() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();

        int delta = -calendar.get(GregorianCalendar.DAY_OF_WEEK) + 1;
        calendar.add(Calendar.DAY_OF_MONTH, delta);

        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(days[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(days[7])-1);
        calendar.set(Calendar.YEAR, Integer.parseInt(days[8]));

        calendar.add(Calendar.DAY_OF_MONTH, -7);
        String year = "-1";
        String month = "-1";

        for (int i = 0; i < 7; i++) {
            String day = dateFormat.format(calendar.getTime());
            days[i] = day.substring(0, 2);
            if (i == 0) {
                month = day.substring(3, 5);
                year = day.substring(6);
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        days[7] = month;
        days[8] = year;
        return days;
    }

    public static String compress(String month) {
        if (month.equals("01")) return "JA";
        if (month.equals("02")) return "FE";
        if (month.equals("03")) return "MR";
        if (month.equals("04")) return "AP";
        if (month.equals("05")) return "MY";
        if (month.equals("06")) return "JN";
        if (month.equals("07")) return "JL";
        if (month.equals("08")) return "AU";
        if (month.equals("09")) return "SE";
        if (month.equals("10")) return "OC";
        if (month.equals("11")) return "NO";
        if (month.equals("12")) return "DE";
        return "ERR";
    }

    public static int getHour(String hour) throws ParseException{
        String[] parts = hour.split(":");
        if (parts.length != 2) throw new ParseException("Wrong format", 127);
        int result = Integer.parseInt(parts[0]);
        if (result < 0 || result >= 24) throw new ParseException("Format incorrect", 129);
        return result;
    }

    public static int getMinute(String hour) throws ParseException{
        String[] parts = hour.split(":");
        if (parts.length != 2) throw new ParseException("Wrong format", 134);
        int result = Integer.parseInt(parts[1]);
        if (result < 0 || result >= 60) throw new ParseException("Format incorrect", 136);
        return result;
    }

    public static String getNextWeekDay(String day) {
        if (day.equals(MainActivity.MONDAY)) return MainActivity.TUESDAY;
        if (day.equals(MainActivity.TUESDAY)) return MainActivity.WEDNESDAY;
        if (day.equals(MainActivity.WEDNESDAY)) return MainActivity.THURSDAY;
        if (day.equals(MainActivity.THURSDAY)) return MainActivity.FRIDAY;
        if (day.equals(MainActivity.FRIDAY)) return MainActivity.SATURDAY;
        if (day.equals(MainActivity.SATURDAY)) return MainActivity.SUNDAY;
        if (day.equals(MainActivity.SUNDAY)) return MainActivity.MONDAY;
        return "";
    }

    public static String getFirstDayOfTheCurrentWeek() {
        return days[0] + "/" + days[7] + "/" + days[8];
    }

    public static String getLastDayOfTheCurrentWeek() {
        String month, year;
        if (Integer.parseInt(days[6]) < Integer.parseInt(days[0])) {
            if (days[7].equals("12")) {
                month = "01";
                year = Integer.toString(Integer.parseInt(days[8])+1);
            }
            else {month = Integer.toString(Integer.parseInt(days[7])+1); year = days[8];}
        }
        else {month = days[7]; year = days[8];}
        return days[6] + "/" + month + "/" + year;
    }

    public static int dayOfWeekToInteger(String day) {
        if (day.equals(MainActivity.SUNDAY)) return 1;
        if (day.equals(MainActivity.MONDAY)) return 2;
        if (day.equals(MainActivity.TUESDAY)) return 3;
        if (day.equals(MainActivity.WEDNESDAY)) return 4;
        if (day.equals(MainActivity.THURSDAY)) return 5;
        if (day.equals(MainActivity.FRIDAY)) return 6;
        if (day.equals(MainActivity.SATURDAY)) return 7;
        return -1;
    }

    public static String integerToDayOfWeek(int day) {
        if (day == 1) return MainActivity.SUNDAY;
        if (day == 2) return MainActivity.MONDAY;
        if (day == 3) return MainActivity.TUESDAY;
        if (day == 4) return MainActivity.WEDNESDAY;
        if (day == 5) return MainActivity.THURSDAY;
        if (day == 6) return MainActivity.FRIDAY;
        if (day == 7) return MainActivity.SATURDAY;
        return "";
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if (getDay(date1) == getDay(date2) && getMonth(date1) == getMonth(date2) && getYear(date1) == getYear(date2)) return true;
        return false;
    }

    public static String getDateInStringOfCurrentWeek(int i) {
        String result = "";
        try {
            result = getFirstDayOfTheCurrentWeek();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(result);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, i);
            result = dateToString(calendar.getTime());
        }
        catch (ParseException pe) {
            pe.getStackTrace();
        }
        return result;
    }

    public static String getDateInLongFormat(String date) {
        String[] dateSeparated = date.split("/");
        String result = "";
        result += dateSeparated[0];
        if (dateSeparated[0].equals("1")) result += "st ";
        else if (dateSeparated[0].equals("2")) result += "nd ";
        else if (dateSeparated[0].equals("3")) result += "rd ";
        else result += "th ";
        if (dateSeparated[1].equals("1")) result += JANUARY + " ";
        else if (dateSeparated[1].equals("2")) result += FEBRUARY + " ";
        else if (dateSeparated[1].equals("3")) result += MARCH + " ";
        else if (dateSeparated[1].equals("4")) result += APRIL + " ";
        else if (dateSeparated[1].equals("5")) result += MAY + " ";
        else if (dateSeparated[1].equals("6")) result += JUNE + " ";
        else if (dateSeparated[1].equals("7")) result += JULY + " ";
        else if (dateSeparated[1].equals("8")) result += AUGUST + " ";
        else if (dateSeparated[1].equals("9")) result += SEPTEMBER + " ";
        else if (dateSeparated[1].equals("10")) result += OCTOBER + " ";
        else if (dateSeparated[1].equals("11")) result += NOVEMBER + " ";
        else if (dateSeparated[1].equals("12")) result += DECEMBER + " ";
        result += dateSeparated[2];
        return result;
    }

    /**
     * limits[0] = first day of the current week
     * limits[1] = last day of the current week
     */
    public static Date[] getLimitsOfCurrentWeek() {
        Date[] limits = new Date[2];
        Date date = new Date();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String s = days[0] + "/" + days[7] + "/" + days[8];
            date = dateFormat.parse(s);
            limits[0] = date;
        }
        catch (ParseException pe) {
            pe.getStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        date = calendar.getTime();
        limits[1] = date;
        return limits;
    }

    public static String getDayInString(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return dateToString(calendar.getTime());
    }

    public static int getCurrentFirstDay() {
        return Integer.parseInt(days[0]);
    }

    public static int getCurrentMonth() {
        return Integer.parseInt(days[7]);
    }

    public static int getCurrentYear() {
        return Integer.parseInt(days[8]);
    }
}
