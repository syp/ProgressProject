package com.example.progress;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Date;

/**
 * Created by stephen on 13-7-14.
 */
public class BKDataFetchTask extends AsyncTask<Void, Integer, String>{

    private MainActivity mActivity;
    private boolean isCompleted = false;
    private String result = null;

    @Override
    protected String doInBackground(Void... params) {
        try {
            for(int i=0; i<10; i++){
                Thread.sleep(1000);
                publishProgress(new Integer(i+1));
            }
            return "Task completed at " + new Date();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        ProgressDialogFragment dialog = ProgressDialogFragment.newInstance("Loading...");
        dialog.show(mActivity.getFragmentManager(), "progressDialog");
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        if(mActivity!=null &&
                mActivity.getFragmentManager().findFragmentByTag("progressDialog")!=null){
            ((ProgressDialogFragment)mActivity.getFragmentManager().findFragmentByTag("progressDialog"))
                    .setProgress(values[0].intValue());
        }
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        ((DialogFragment)mActivity.getFragmentManager().findFragmentByTag("progressDialog")).dismiss();
        isCompleted = true;
        result = s;
        notifyCompleted(s);
        super.onPostExecute(s);
    }

    public BKDataFetchTask(MainActivity mActivity){
        this.mActivity = mActivity;
    }

    public void setmActivity(MainActivity mActivity) {
        this.mActivity = mActivity;
        if(!isCancelled() && isCompleted){
            notifyCompleted(result);
        }
    }

    private void notifyCompleted(String result){
        if(mActivity!=null){
            mActivity.onBKTaskCompleted(result);

        }
    }
}
