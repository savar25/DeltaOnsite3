package com.example.deltaonsite3;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class drawFragment extends Fragment implements paintScreen.DrawListener {

static paintScreen pS;
drawListener listener;
Paint paint;
Path path;
    private static final String TAG = "drawFragment";

    public drawFragment(Context context,Path path,Paint paint) {
        this.paint=paint;
        this.path=path;
        pS=new paintScreen(context,null,path,paint);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        pS.drawListener=this;
        return pS;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof drawListener) {
            listener = (drawListener) context;
        } else {
            throw new ClassCastException("Error");
        }
    }

    @Override
    public void onListen(Path path) {
        Log.d(TAG, "onListen: called");
        listener.onDrawChange(path);
    }

    public interface drawListener{
        public void onDrawChange(Path path);
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
        pS.setBrush(paint);
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
        pS.setPath(path);
    }
}