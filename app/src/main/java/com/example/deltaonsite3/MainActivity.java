package com.example.deltaonsite3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements drawFragment.drawListener,viewFragment.ViewListener{

    Button erase,eraser;
    Paint brush;
    boolean isEraser=false;
    public static viewFragment viewFragment;
    public static drawFragment drawFragment;
    ImageButton choice;
    TextView chosen;
    Integer[] colourList=new Integer[]{Color.BLACK,Color.YELLOW,Color.RED,Color.BLUE,Color.GREEN,Color.parseColor("#ff4500")};
    String[] nameList=new String[]{"Black","Yellow","Red","Blue","Green","Orange"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eraser=findViewById(R.id.eraser);
        choice=findViewById(R.id.colChoice);
        chosen=findViewById(R.id.colChosen);

        brush=new Paint();
        brush.setColor(Color.BLACK);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeJoin(Paint.Join.MITER);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeWidth(5f);
        erase=findViewById(R.id.erase);

        drawFragment=new drawFragment(this,new Path(),brush);
        viewFragment=new viewFragment(this,new Path(),brush);

        getSupportFragmentManager().beginTransaction().add(R.id.container, drawFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.view_container, viewFragment).commit();




        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawFragment=new drawFragment(MainActivity.this,new Path(),brush);
                viewFragment=new viewFragment(MainActivity.this,new Path(),brush);

                getSupportFragmentManager().beginTransaction().replace(R.id.container, drawFragment).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.view_container, viewFragment).commit();


            }
        });

        eraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEraser) {
                    brush.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                    brush.setStrokeWidth(45f);
                    viewFragment.setPaint(brush);
                    drawFragment.setPaint(brush);
                    eraser.setText("Marker");
                }else{
                    brush.setXfermode(null);
                    brush.setStrokeWidth(5f);
                    viewFragment.setPaint(brush);
                    drawFragment.setPaint(brush);
                    eraser.setText("Eraser");

                }
                isEraser=!isEraser;
            }
        });


        choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ListAdapter adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.select_dialog_item,android.R.id.text1,nameList){
                    public View getView(int position, View convertView, ViewGroup parent) {
                        //Use super class to create the View
                        View v = super.getView(position, convertView, parent);
                        TextView tv = (TextView)v.findViewById(android.R.id.text1);

                        //Put the image on the TextView

                        tv.setBackgroundColor(Color.parseColor("#dddddd"));
                        tv.setText(nameList[position]);
                        tv.setTextColor(colourList[position]);
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);

                        //Add margin between image and text (support various screen densities)


                        return v;
                    }
                };
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Choose sketch colour")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                chosen.setText(nameList[i]);
                                chosen.setTextColor(colourList[i]);
                                brush.setColor(colourList[i]);
                                viewFragment.setPaint(brush);
                                drawFragment.setPaint(brush);

                            }
                        }).create().show();
            }
        });

    }

    @Override
    public void onDrawChange(Path path) {
            viewFragment.setPath(path);


    }

    @Override
    public void onViewListener(Path path) {
        drawFragment.setPath(path);
    }
}