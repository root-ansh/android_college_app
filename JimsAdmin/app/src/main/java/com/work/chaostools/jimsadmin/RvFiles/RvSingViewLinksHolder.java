package com.work.chaostools.jimsadmin.RvFiles;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.work.chaostools.jimsadmin.R;

/**
 * Created by ansh on 6/1/18.
 */

public class RvSingViewLinksHolder extends RecyclerView.ViewHolder {
    TextView fileLinkTextHolder;

    public RvSingViewLinksHolder(View itemView) {
        super(itemView);
        fileLinkTextHolder =itemView.findViewById(R.id.dingleann_rveachrow_filelinks_tbox);
    }
}
