package com.apple.xhs.five_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.apple.xhs.R;
import com.apple.xhs.five_fragment.search_activity.SearchAdd;
import com.apple.xhs.five_fragment.view_util.MyLoopViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by limeng on 2017/7/22.
 */

public class SearchFragment extends Fragment implements View.OnClickListener {
    private ImageView addFriend;
    private ViewPager viewPager;
    private int[] views = new int[]{R.mipmap.search_v1,R.mipmap.search_v2,R.mipmap.search_v3,R.mipmap.search_v4,
            R.mipmap.search_v5,R.mipmap.search_v6,R.mipmap.search_v7};
    private List<ImageView> viewList = new ArrayList<>();
    private int currentViewId = 0;
    public static Handler handler = new Handler();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_layout,container,false);
        initData();
        initView(view);
        vipAutoToNext();
        initListener(view);
        return view;
    }

    private void initView(View view) {
        addFriend = view.findViewById(R.id.search_add_friend);
        viewPager = view.findViewById(R.id.search_viewpager);
        PagerAdapter adapter = new MyLoopViewAdapter(viewList);
        viewPager.setAdapter(adapter);
    }

    private void initData() {
        for(int v : views){
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(v);
            viewList.add(imageView);
        }
    }

    private void initListener(View view) {
        addFriend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_add_friend:
                startActivity(new Intent(getActivity(), SearchAdd.class));
                break;
        }
    }
    private void vipAutoToNext() {
        handler.postDelayed(new MyRunnable(),4000);
    }
    public class MyRunnable implements Runnable{
        @Override
        public void run() {
            if(currentViewId < 100 && currentViewId == viewPager.getCurrentItem()){
                viewPager.setCurrentItem(++currentViewId);
            }else if(currentViewId < 100 && currentViewId != viewPager.getCurrentItem()){
                currentViewId = viewPager.getCurrentItem();
                viewPager.setCurrentItem(++currentViewId);
            }
            handler.postDelayed(this,4000);
        }
    }
}
