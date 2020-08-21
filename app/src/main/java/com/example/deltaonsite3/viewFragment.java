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


public class viewFragment extends Fragment implements viewer_screen.ViewDrawListener {

    Path path;
    Paint paint;
    static viewer_screen vS;
    ViewListener vListener;
    private static final String TAG = "viewFragment";
    
    public viewFragment(Context context,Path path, Paint paint) {
        this.path=path;
        this.paint=paint;
        vS=new viewer_screen(context,null,path,paint);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vS.listener=this;
        return vS;
    }

    @Override
    public void onViewListen(Path path) {
        Log.d(TAG, "onListen: called");
        vListener.onViewListener(path);
    }

    public interface ViewListener{
        public void onViewListener(Path path);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof drawFragment.drawListener) {
            vListener = (ViewListener) context;
        } else {
            throw new ClassCastException("Error");
        }
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
        vS.setPath(path);
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
        vS.setPaint(paint);
    }
}