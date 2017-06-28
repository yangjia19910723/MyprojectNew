package com.example.pangjia.test;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Fruit[] fruits={new Fruit("appleappleappleappleappleappleappleappleappleappleappleappleappleappleapple",R.mipmap.apple),
            new Fruit("pearpearpearpearpearpearpearpearpear",R.mipmap.caomei),
            new Fruit("juzi",R.mipmap.fruits),
            new Fruit("caomeicaomeicaomeicaomeicaomeicaomeicaomeicaomeicaomeicaomeicaomeicaomeicaomeicaomeicaomeicaomeicaomeicaomeicaomeicaomeicaomei",R.mipmap.lizhi),
            new Fruit("orangeorangeorangeorangeorangeorangeorangeorangeorangeorangeorange",R.mipmap.mangguo),
            new Fruit("mihoutaomihoutaomihoutaomihoutaomihoutao",R.mipmap.mihoutao),
            new Fruit("mangguomangguomangguomangguomangguo",R.mipmap.orange),
            new Fruit("putaoputaoputaoputaoputaoputaoputaoputaoputaoputaoputaoputaoputaoputaoputaoputaoputaoputaoputao",R.mipmap.putao),
            new Fruit("fruitfruit",R.mipmap.yingtao),};
    private List<Fruit> fruitList=new ArrayList<>();
    private FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        toobar的设置
        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


//        左侧滑动菜单的设置
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
//       左侧滑动菜单的布局设置
        navView=(NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
             //设置导航栏左侧的home按钮的显示和隐藏
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置导航栏左侧的home按钮的图标
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }

        //       左侧滑动菜单的布局的默认选中项以及点击事件
        navView.setCheckedItem(R.id.call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                左侧滑动布局的关闭
                mDrawerLayout.closeDrawers();
                return true;
            }
        });




//        右下角浮动图标的设置
        FloatingActionButton floatButton=(FloatingActionButton)findViewById(R.id.float_button);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                snackbar和toast相似，只是多了一个按钮
                Snackbar.make(v,"FloatActionButton clicked",Snackbar.LENGTH_SHORT)
//                        snackbar按钮的内容设置和点击事件
                        .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"Data restore",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });




//        RecycleView的设置
        initFruits();
        RecyclerView  mRecycleView=(RecyclerView)findViewById(R.id.recycle_view);
        StaggeredGridLayoutManager manger=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//        GridLayoutManager manger=new GridLayoutManager(this,2);
        mRecycleView.setLayoutManager(manger);
        adapter=new FruitAdapter(fruitList);
        mRecycleView.setAdapter(adapter);



//        下拉刷新的设置
        mSwipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
              refreshFruits();
            }
        });
    }

    public void refreshFruits(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    public  void  initFruits(){
        fruitList.clear();
        for (int i=0;i<50;i++){
            Random ran=new Random();
            int index=ran.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }


//    菜单的创建方法
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }


//    菜单的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                Toast.makeText(MainActivity.this,"you click delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                Toast.makeText(MainActivity.this,"you click add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.update:
                Toast.makeText(MainActivity.this,"you click update",Toast.LENGTH_SHORT).show();
                break;
//        toolbar左侧home按钮的点击事件
            case android.R.id.home:
//            左侧滑动布局的打开
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
