package com.work.chaostools.jimsadmin.RvFiles;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.work.chaostools.jimsadmin.R;

/**
 * Created by ansh on 6/1/18.
 */

public class RvFilelistHolder extends RecyclerView.ViewHolder {
    TextView filenameHolder;
    ImageButton btCancelHolder;

    public RvFilelistHolder(View itemView) {
        super(itemView);
        filenameHolder=itemView.findViewById(R.id.mainrv_eachrow_filenames_l);
        btCancelHolder=itemView.findViewById(R.id.mainrv_eachrow_bt_cancel_l);
    }
}
