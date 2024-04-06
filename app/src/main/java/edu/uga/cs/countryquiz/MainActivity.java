package edu.uga.cs.countryquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
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
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countriesData = new CountriesData(getBaseContext());
        countriesData.open();
        new CountryDBWriter().execute();
        button = findViewById(R.id.button);
        button.setEnabled(false);
        button.setOnClickListener(new ButtonClickHandler());
    }

    class ButtonClickHandler implements View.OnClickListener {

        public void onClick( View v) {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            MainActivity.this.startActivity(intent);
        }
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
            button.setEnabled(true);
        }
    }
}