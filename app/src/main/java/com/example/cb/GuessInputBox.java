package com.example.cb;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GuessInputBox {
    private int diff;
    private Context appctxt;
    private LinearLayout thebox;

    GuessInputBox(Context appctxt, View v, int diff) {
        this.diff = diff;
        this.appctxt = appctxt;
        thebox = (LinearLayout) v;
    }

    public void build_guess_box () {
        for (int i = 0; i < diff; i++) {
            ImageView v = new ImageView(appctxt);
            ViewGroup.LayoutParams img_layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            img_layout.height = 60;
            img_layout.width = 60;
            v.setLayoutParams(img_layout);
            thebox.addView(v);
        }
    }

    public void reset_guess_box() {
        thebox.removeAllViews();
        build_guess_box();
    }

    public void setImageAt(int pos, int img_resid) {
        if (pos < diff) {
            ImageView img = (ImageView) thebox.getChildAt(pos);
            img.setImageResource(img_resid);
        }
    }

    public void deleteImageAt(int pos) {
        int abs_pos = (pos > 0) ? pos : -pos;
        int num_child = 0;

        try {
            num_child = thebox.getChildCount();
        } catch (Exception e){
            e.printStackTrace();
        }

        if (abs_pos < num_child) {
            if (pos < 0) {
                ImageView img = (ImageView) thebox.getChildAt(num_child - abs_pos);
                img.setImageResource(0);
            } else {
                ImageView img = (ImageView) thebox.getChildAt(pos);
                img.setImageResource(0);
            }
        }
    }

}
