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

paintScreen pS;
drawListener listener;
Paint paint;
    private static final String TAG = "drawFragment";

    public drawFragment(Paint paint) {
        this.paint=paint;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        pS=new paintScreen(getContext(),null,paint);
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
}