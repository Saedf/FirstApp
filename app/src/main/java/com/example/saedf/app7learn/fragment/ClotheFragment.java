package com.example.saedf.app7learn.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saedf.app7learn.Adapter.ClotheAdapter;
import com.example.saedf.app7learn.R;
import com.example.saedf.app7learn.dataFake.ClotheDataFakeGenerator;


public class ClotheFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_view, container, false);
        recyclerView = view.findViewById(R.id.rv_fragmentview_layoutclothe);
        ClotheAdapter clotheAdapter=new ClotheAdapter(ClotheDataFakeGenerator.getData(getActivity()),getActivity());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(clotheAdapter);
        return view;

    }

    public static ClotheFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ClotheFragment fragment = new ClotheFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
