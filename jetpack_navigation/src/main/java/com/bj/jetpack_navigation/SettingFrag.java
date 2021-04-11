package com.bj.jetpack_navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

/**
 * 测试传递参数
 * */
public class SettingFrag extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_setting, null);

        view.findViewById(R.id.tv_01).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("msg01", "来自设置页面的值");

                NavHostFragment
                        .findNavController(SettingFrag.this)
                        .navigate(R.id.action_settingFrag_to_homeFrag, bundle);
            }
        });
        return view;
    }
}