package com.example.progress;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private BKDataFetchTask bkTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Object retained = getLastNonConfigurationInstance();
        if(retained!=null && retained instanceof BKDataFetchTask){
            bkTask = (BKDataFetchTask) retained;
            bkTask.setmActivity(this);
        }else{
            bkTask = new BKDataFetchTask(this);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onStartBackgroundTask(View view){
        bkTask.execute(null);
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        bkTask.setmActivity(null);
        return bkTask;
    }

    public void onBKTaskCompleted(String result){
        TextView resultView = (TextView)findViewById(R.id.result);
        resultView.setText(result);
    }
    
}
