package edu.uga.cs.countryquiz;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
public class Question {
    private CountriesData countriesData;
    private Country country;
    private String con1;
    private String con2;

    public Question(Context context, int num) {
        countriesData = new CountriesData(context);
        country = countriesData.retrieveAllCountries().get(num);
        String[] continents = {"Asia", "Europe", "Africa", "South America", "Oceania", "North America"};
        Random gen = new Random();
        do {
            con1 = continents[gen.nextInt(7)];
        } while (con1.equals(country.getContinent()));
        do {
            con2 = continents[gen.nextInt(7)];
        } while (con2.equals(country.getContinent()) || con2.equals(con1));
    }

    public Country getCountry() {
        return country;
    }

    public String getCon1() {
        return con1;
    }

    public String getCon2() {
        return con2;
    }

}
