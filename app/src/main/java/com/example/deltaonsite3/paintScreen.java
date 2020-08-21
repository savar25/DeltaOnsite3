package com.example.deltaonsite3;

import android.content.Context;
import android.graphics.Bitmap;
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
    Paint brush,bitmapPaint;
    Path path;
    DrawListener drawListener;
    Canvas drawCanvas;
    Bitmap drawBitmap;

    private static final String TAG = "paintScreen";

    public paintScreen(Context context, @Nullable AttributeSet attrs,Path path,Paint paint) {
        super(context, attrs);

      this.brush=paint;

        this.path=path;
        bitmapPaint=new Paint(Paint.DITHER_FLAG);
        bitmapPaint.setAntiAlias(false);
        bitmapPaint.setColor(Color.parseColor("#eeeeee"));
        bitmapPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        bitmapPaint.setStrokeWidth(10f);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        drawBitmap=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        drawCanvas=new Canvas(drawBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.parseColor("#eeeeee"));
        canvas.drawBitmap(drawBitmap,0,0,bitmapPaint);




    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x_pos=event.getX();
        float y_pos=event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x_pos,y_pos);
                drawListener.onListen(path);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x_pos,y_pos);
                drawCanvas.drawPath(path,brush);
                drawListener.onListen(path);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(path,brush);
                drawListener.onListen(path);
                path.reset();
                break;
            default:
                return false;
        }

        invalidate();

        return true;

    }




    public interface DrawListener{
        public void onListen(Path path);
    }

    public Paint getBrush() {
        return brush;
    }

    public void setBrush(Paint brush) {
        this.brush = brush;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
        drawCanvas.drawPath(path,brush);
        invalidate();
    }
}

