package com.example.deltaonsite3;

import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class viewFragment extends Fragment {

    Path path;
    Paint paint;
    viewer_screen vS;

    public viewFragment(Path path, Paint paint) {
        this.path=path;
        this.paint=paint;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vS=new viewer_screen(getContext(),null,path,paint);
        return vS;
    }
}