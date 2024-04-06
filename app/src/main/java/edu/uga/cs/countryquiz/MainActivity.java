package edu.uga.cs.countryquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    CountriesData countriesData;
    final String TAG = "CountryQuiz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countriesData = new CountriesData(getBaseContext());
        countriesData.open();
        new CountryDBWriter().execute();
    }

    public class CountryDBWriter extends AsyncTask<Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            Country country;
            if (countriesData.retrieveAllCountries().isEmpty()) {
                try {
                    // Open the CSV data file in the assets folder
                    InputStream in_s = getAssets().open("country_continent.csv");

                    // read the CSV data
                    CSVReader reader = new CSVReader(new InputStreamReader(in_s));
                    String[] nextRow;
                    while ((nextRow = reader.readNext()) != null) {
                        country = new Country(nextRow[0], nextRow[1]);
                        countriesData.storeCountry(country);
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
                return "Database created";
            } else return "Databased restored";
        }

        protected void onPostExecute(String msg) {
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}