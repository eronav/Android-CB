package com.example.cb;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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
            LinearLayout.LayoutParams img_layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            v.setBackgroundDrawable(new Border(R.color.hintColorGame, 10));
            img_layout.height = 120;
            img_layout.width = 120;
            img_layout.setMargins(0,0,4,0);
            v.setLayoutParams(img_layout);
            v.setClickable(true);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(appctxt, "View is clicked", Toast.LENGTH_SHORT).show();
                }
            });
            thebox.addView(v);
        }
    }

    public void reset_guess_box(boolean rebuild) {
        thebox.removeAllViews();
        if (rebuild)
            build_guess_box();
    }

    public void setImageAt(int pos, int img_resid) {
        if (pos < diff) {
            ImageView img = (ImageView) thebox.getChildAt(pos);
            img.setImageResource(img_resid);
        }
    }

    public void removeBorderAt(int pos) {
        thebox.getChildAt(pos).setBackgroundDrawable(null);
    }

    public void setBorderAt(int pos) {
        thebox.getChildAt(pos).setBackgroundDrawable(new Border(R.color.hintColorGame, 10));
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

    public int moveCursor (boolean[] posArray) {
        for (int i = 0; i < posArray.length; i++) {
            if (posArray[i] == false) {
                return i;
            }
        }
        return 0;
    }

}
