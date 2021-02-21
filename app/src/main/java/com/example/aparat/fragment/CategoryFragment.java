package com.example.aparat.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aparat.R;
import com.example.aparat.adapter.CategoryAdapter;
import com.example.aparat.databinding.FragmentCategoryBinding;
import com.example.aparat.models.Category;
import com.example.aparat.models.IMessageListener;
import com.example.aparat.webservice.WebserviceCaller;

import java.util.List;

import retrofit2.Call;


public class CategoryFragment extends Fragment {
    FragmentCategoryBinding categoryBinding;
    WebserviceCaller webserviceCaller;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        webserviceCaller = new WebserviceCaller();
        categoryBinding = FragmentCategoryBinding.inflate(getLayoutInflater());
        return categoryBinding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categoryBinding.progressCategory.setVisibility(View.VISIBLE);
        webserviceCaller.getAllCategory(new IMessageListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                List<Category> categoryList = (List<Category>) responseMessage;
                CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(), categoryList);
                categoryBinding.recyclerCategory.setAdapter(categoryAdapter);
                categoryBinding.recyclerCategory.setLayoutManager(new LinearLayoutManager(getActivity(),
                        RecyclerView.VERTICAL, false));


                categoryBinding.progressCategory.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Object errorResponseMessage) {
                categoryBinding.progressCategory.setVisibility(View.VISIBLE);

            }
        });
    }
}