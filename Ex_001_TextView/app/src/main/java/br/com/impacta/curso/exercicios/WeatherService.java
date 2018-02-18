package br.com.impacta.curso.exercicios;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;



class WeatherService {

    private String date;
    private String time;


    private String toTitle(String s) {
        StringBuilder stb = new StringBuilder();
        for (String part:s.split(" "))
        {
            String s1 = part.substring(0,1).toUpperCase();
            String sTitle = s1 + part.substring(1);
            stb.append(sTitle);
            stb.append(" ");
        }

        return stb.toString().substring(0, stb.toString().length() - 1);
    }

    WeatherService() {
        TimeZone timeZone = TimeZone.getTimeZone("America/Sao_Paulo");
        TimeZone.setDefault(timeZone);
        Locale BRAZIL = new Locale("pt", "BR");
        Calendar cal = Calendar.getInstance(timeZone);
        SimpleDateFormat fmtTime = new SimpleDateFormat("HH:mm", BRAZIL);
        time = fmtTime.format(cal.getTime());
        SimpleDateFormat fmtDate = new SimpleDateFormat("EEE, dd MMMM", BRAZIL);
        date = fmtDate.format(cal.getTime());
    }

    String getDate() {
        return toTitle(date);
    }

    String getTime() {
        return time;
    }

    String getTemperature() {
        return "28ºc";
    }

    String getLocation() {
        return "São Paulo";
    }

    String getWeatherDescription() {
        return "nublado";
    }
}
