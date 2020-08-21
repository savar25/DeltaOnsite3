package com.example.deltaonsite3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class viewer_screen extends View {
    Paint paint;
    Path path;
    
    public viewer_screen(Context context, @Nullable AttributeSet attrs,Path path,Paint paint) {
        super(context, attrs);
       this.paint=paint;
        this.path=path;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#eeeeee"));
        canvas.drawPath(path,paint);
    }
}
