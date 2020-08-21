package com.example.deltaonsite3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;



public class paintScreen extends View {
    Paint brush;
    Path path;
    DrawListener drawListener;
    private static final String TAG = "paintScreen";

    public paintScreen(Context context, @Nullable AttributeSet attrs,Paint paint) {
        super(context, attrs);

      this.brush=paint;

        path=new Path();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#eeeeee"));
        canvas.drawPath(path,brush);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x_pos=event.getX();
        float y_pos=event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x_pos,y_pos);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x_pos,y_pos);
                break;


            default:
                break;
        }

        invalidate();
        drawListener.onListen(path);
        return true;

    }




    public interface DrawListener{
        public void onListen(Path path);
    }



}

