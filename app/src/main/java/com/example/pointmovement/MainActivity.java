package com.example.pointmovement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private AnimView animView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LinearLayout surface = (LinearLayout) findViewById(R.id.surface);
        int positionX = Integer.parseInt(((EditText) findViewById(R.id.coord_x)).getText().toString());
        int positionY = Integer.parseInt(((EditText) findViewById(R.id.coord_y)).getText().toString());
        int velocity = Integer.parseInt(((EditText) findViewById(R.id.velocity)).getText().toString());
        int acceleration = Integer.parseInt(((EditText) findViewById(R.id.acceleration)).getText().toString());
        MovingPoint movingPoint = new MovingPoint(positionX, positionY, velocity, acceleration);
        animView = new AnimView(this, movingPoint);
        surface.addView(animView);
        ((Button) findViewById(R.id.reset_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int positionX = Integer.parseInt(((EditText) findViewById(R.id.coord_x)).getText().toString());
                int positionY = Integer.parseInt(((EditText) findViewById(R.id.coord_y)).getText().toString());
                int velocity = Integer.parseInt(((EditText) findViewById(R.id.velocity)).getText().toString());
                int acceleration = Integer.parseInt(((EditText) findViewById(R.id.acceleration)).getText().toString());
                MovingPoint movingPoint = new MovingPoint(positionX, positionY, velocity, acceleration);
                animView.resetPoint(movingPoint);
            }
        });
    }
}