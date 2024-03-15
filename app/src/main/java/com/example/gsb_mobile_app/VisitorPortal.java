package com.example.gsb_mobile_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

public class VisitorPortal extends AppCompatActivity {
/* Récupère les informations envoyées depuis la première activité et les affiche sur la deuxième */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_visitor_portal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        JSONObject jsonObject = null;
        String firstName = null;
        String lastName = null;
        String email = null;

        try {
            jsonObject = new JSONObject(message);
            firstName = jsonObject.getString("first_name");
            lastName = jsonObject.getString("last_name");
            email = jsonObject.getString("email");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        String formattedData = "Nom : " + lastName + "\n" + "Prénom : " + firstName + "\n" + "E-mail : " + email;
        TextView data = findViewById(R.id.data);
        data.setText(formattedData);
    }

    public void logout(View view) {
        finish();
    }
}