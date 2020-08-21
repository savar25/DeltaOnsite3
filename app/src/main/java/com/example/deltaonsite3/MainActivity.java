package com.example.deltaonsite3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements drawFragment.drawListener{

    Button erase;
    Paint brush;
    boolean swap=false;
    ImageButton swapB;
    TextView upper,lower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swapB=findViewById(R.id.swapBtn);
        upper=findViewById(R.id.upper);
        lower=findViewById(R.id.lower);

        brush=new Paint();
        brush.setColor(Color.BLACK);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeJoin(Paint.Join.MITER);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeWidth(5f);
        erase=findViewById(R.id.erase);

        if(!swap) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new drawFragment(brush)).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.view_container, new viewFragment(new Path(), brush)).commit();
        }else {
            getSupportFragmentManager().beginTransaction().add(R.id.view_container, new drawFragment(brush)).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.container, new viewFragment(new Path(), brush)).commit();
        }



        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!swap) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new drawFragment(brush)).commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.view_container, new viewFragment(new Path(), brush)).commit();

                }else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.view_container, new drawFragment(brush)).commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new viewFragment(new Path(), brush)).commit();

                }
            }
        });

        swapB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swap=!swap;
                if(!swap) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new drawFragment(brush)).commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.view_container, new viewFragment(new Path(), brush)).commit();
                    upper.setText("Draw Screen");
                    lower.setText("View Screen");
                }else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.view_container, new drawFragment(brush)).commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new viewFragment(new Path(), brush)).commit();
                    upper.setText("View Screen");
                    lower.setText("Draw Screen");
                }
                Toast.makeText(MainActivity.this, "Screen Swapped", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onDrawChange(Path path) {
        if(!swap) {
            getSupportFragmentManager().beginTransaction().replace(R.id.view_container, new viewFragment(path, brush)).commit();
        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new viewFragment(path, brush)).commit();
        }
    }
}