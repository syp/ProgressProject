package com.example.progress;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;

/**
 * Created by stephen on 13-7-14.
 */
public class ProgressDialogFragment extends DialogFragment {

    private String message;
    private ProgressDialog dialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(null == savedInstanceState){
            savedInstanceState = getArguments();
        }
        dialog = (ProgressDialog) new ProgressDialog(getActivity());
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setTitle(savedInstanceState.getString("title"));
        dialog.setMax(10);
        return dialog;
    }



    public static ProgressDialogFragment newInstance(String message){
        ProgressDialogFragment dialogFragment = new ProgressDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", message);
        dialogFragment.setArguments(bundle);
        //dialogFragment.setRetainInstance(true);
        //cannot retain this fragment otherwise onCreateDialog won't be performed
        //and dialog won't be collected as garbage. dialog is binded on activity and needs to be collected.
        return dialogFragment;
    }

    public void setProgress(int progress){
        dialog.setProgress(progress);
    }
}
