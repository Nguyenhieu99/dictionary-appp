package com.hiepdt.dicitonaryapp.search.result;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.hiepdt.dicitonaryapp.R;
import com.hiepdt.dicitonaryapp.translate.DetectActivity;
import com.hiepdt.dicitonaryapp.translate.TranslateActivity;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ArrayList<Fragment> mListFragment;
    private ResultViewpagerAdapter mAdapter;

    private ImageView btnBack, btnMenu;
    private TextView tvWord;
    private RelativeLayout btnSearch;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().hide();

        init();
        action();

    }

    private void init() {
        mViewPager = findViewById(R.id.mViewPager);
        mTabLayout = findViewById(R.id.mTabLayout);
        mListFragment = new ArrayList<>();
        mListFragment.add(new ContentFragment());
        mAdapter = new ResultViewpagerAdapter(getSupportFragmentManager(), mListFragment);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.getTabAt(0).setText("Nội dung");

        btnBack = findViewById(R.id.btnBack);
        btnMenu = findViewById(R.id.btnMenu);
        tvWord = findViewById(R.id.tvWord);

        btnSearch = findViewById(R.id.btnSearch);
    }

    private void action() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvWord.setText(getIntent().getExtras().getString("word", ""));
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(ResultActivity.this);
                dialog.setContentView(R.layout.dialog_load_image);
                dialog.show();
                LinearLayout btnDoc = dialog.findViewById(R.id.btnDoc);
                LinearLayout btnOCR = dialog.findViewById(R.id.btnOCR);


                btnDoc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ResultActivity.this, TranslateActivity.class);
                        startActivity(intent);
                    }
                });

                btnOCR.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ResultActivity.this, DetectActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

}
