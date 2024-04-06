package edu.uga.cs.countryquiz;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
public class Question {
    private CountriesData countriesData;
    private Country country;
    private String con1;
    private String con2;

    public Question(Context context) {
        countriesData = new CountriesData(context);
        Random gen = new Random();
        countriesData.open();
        country = countriesData.retrieveAllCountries().get(gen.nextInt(195));
        String[] continents = {"Asia", "Europe", "Africa", "South America", "Oceania", "North America"};

        do {
            con1 = continents[gen.nextInt(6)];
        } while (con1.equals(country.getContinent()));
        do {
            con2 = continents[gen.nextInt(6)];
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
