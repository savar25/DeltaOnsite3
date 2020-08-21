package com.example.deltaonsite3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class viewer_screen extends View {
    Paint paint,bitmapPaint;
    Path path;
    Bitmap viewMap;
    Canvas viewCanvas;
    ViewDrawListener listener;
    
    public viewer_screen(Context context, @Nullable AttributeSet attrs,Path path,Paint paint) {
        super(context, attrs);
       this.paint=paint;
        this.path=path;
        bitmapPaint=new Paint();
        bitmapPaint.setColor(Color.parseColor("#eeeeee"));
        bitmapPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        bitmapPaint.setStrokeWidth(10f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        viewMap=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        viewCanvas=new Canvas(viewMap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.parseColor("#eeeeee"));
        canvas.drawBitmap(viewMap,0,0,bitmapPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x_pos=event.getX();
        float y_pos=event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x_pos,y_pos);
                listener.onViewListen(path);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x_pos,y_pos);
                viewCanvas.drawPath(path,paint);
                listener.onViewListen(path);
                break;
            case MotionEvent.ACTION_UP:
                viewCanvas.drawPath(path,paint);
                listener.onViewListen(path);
                path.reset();
                break;

        default:
        return false;
    }

        invalidate();

        return true;

    }

    public interface ViewDrawListener{
        public void onViewListen(Path path);
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
        viewCanvas.drawPath(path,paint);
        invalidate();
    }
}
