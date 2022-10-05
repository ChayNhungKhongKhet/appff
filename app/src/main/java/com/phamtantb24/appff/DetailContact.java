package com.phamtantb24.appff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailContact extends AppCompatActivity {
    TextView detailContactTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);
        detailContactTxt = findViewById(R.id.detail);
        Intent intent = getIntent();
        String contact = intent.getStringExtra("Contact");
        detailContactTxt.setText(contact);

    }
}