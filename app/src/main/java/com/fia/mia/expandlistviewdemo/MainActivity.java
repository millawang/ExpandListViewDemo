package com.fia.mia.expandlistviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.fia.mia.expandlistviewdemo.adapter.MyDemoAdapter;
import com.fia.mia.expandlistviewdemo.data.MyDemoData;

import java.util.ArrayList;


public class MainActivity extends Activity {

    ListView mExpandListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mExpandListView = (ListView)findViewById(R.id.expandListView);

        //Test data
        ArrayList<MyDemoData> array = new ArrayList<MyDemoData>();
        MyDemoData testData = new MyDemoData();
        testData.setIndexId("key0");
        testData.setTitle("test 1 test 1 test 1 test 1 test 1");
        testData.setContent("content 1 content 1 content 1 content 1 \ncontent 1 content 1 content 1 \ncontent 1 dddd ");
        array.add(testData);
        testData = new MyDemoData();
        testData.setIndexId("key1");
        testData.setTitle("test 2 test 2 test 2 test 2 test 2 test 2 test 2 test 2 test 2 test 2 test 2 test 2 test 2 test 2 test 2");
        testData.setContent("content 2 content 2 content 2 ");
        array.add(testData);

        testData = new MyDemoData();
        testData.setIndexId("key2");
        testData.setTitle("test 3 test 3 test 3 test 3 test 3");
        testData.setContent("content 3 content 3 content 3 content 3 \ncontent 3 content 3 content 3 content 3 ");
        array.add(testData);

        MyDemoAdapter adapter = new MyDemoAdapter(MainActivity.this, array);
        mExpandListView.setAdapter(adapter);


    }


}
