package com.example.pessy.corpacabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.vstechlab.easyfonts.EasyFonts;

import io.github.sporklibrary.Spork;
import io.github.sporklibrary.annotations.BindView;

public class BookingActivity extends AppCompatActivity {

    @BindView(R.id.booking_textview)
    TextView booking_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Spork.bind(this);

        booking_text.setTypeface(EasyFonts.recognition(this));
    }
}
