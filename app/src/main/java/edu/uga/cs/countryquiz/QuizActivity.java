package edu.uga.cs.countryquiz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class QuizActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.swipe_activity);
        ViewPager2 pager = findViewById(R.id.viewpager);
        QuestionsPagerAdapter qpAdapter = new QuestionsPagerAdapter(getSupportFragmentManager(), getLifecycle());
        pager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        pager.setAdapter(qpAdapter);
    }
}
