package com.example.lab6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    public FirstFragment() {
        // Required empty public constructor
    }

//    inflater.inflate(R.layout.fragment_first, container, false) là cách bạn sử dụng LayoutInflater
//    để chuyển đổi file XML fragment_first.xml thành một đối tượng View.
//    R.layout.fragment_first là tài nguyên XML định nghĩa layout của fragment.
//    container là ViewGroup cha mà layout này sẽ được gắn vào.
//    Đây thường là ViewGroup trong layout của activity hoặc fragment cha chứa fragment hiện tại.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }
}

