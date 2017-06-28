package com.example.pangjia.test;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FruitActivity extends AppCompatActivity {
    public static final String FRUIT_NAME="fruit_name";
    public static final String FRUIT_IMAGE_ID="fruit_img_id";
    private String fruitName;
    private int fruitImgId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        Intent mIntent=getIntent();
        fruitName=mIntent.getStringExtra(FRUIT_NAME);
        fruitImgId=mIntent.getIntExtra(FRUIT_IMAGE_ID,0);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collpseLayout=(CollapsingToolbarLayout)findViewById(R.id.collaps_layout);
        ImageView fruitImg=(ImageView)findViewById(R.id.fruit_img);
        TextView fruitContent=(TextView)findViewById(R.id.fruit_content);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collpseLayout.setTitle(fruitName);
        Glide.with(this).load(fruitImgId).into(fruitImg);
        String con=generateCon(fruitName);
        fruitContent.setText(con);
    }

    public String generateCon(String name){
        StringBuilder content=new StringBuilder();
        for(int i=0;i<500;i++){
            content.append(name);
        }
        return content.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
