package com.example.cb;

import android.content.Context;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LetterImageManager {
    public int[] LetterArray = new int[26];
    public int[] NumberArray = new int[10];
    private int int_a = (int) 'a';
    private int int_z = (int) 'z';
    private int int_0 = (int) '0';
    private int int_9 = (int) '9';
    LetterImageManager() {


        LetterArray[0] = R.drawable.letter_a;
        LetterArray[1] = R.drawable.letter_b;
        LetterArray[2] = R.drawable.letter_c;
        LetterArray[3] = R.drawable.letter_d;
        LetterArray[4] = R.drawable.letter_e;
        LetterArray[5] = R.drawable.letter_f;
        LetterArray[6] = R.drawable.letter_g;
        LetterArray[7] = R.drawable.letter_h;
        LetterArray[8] = R.drawable.letter_i;
        LetterArray[9] = R.drawable.letter_j;
        LetterArray[10] = R.drawable.letter_k;
        LetterArray[11] = R.drawable.letter_l;
        LetterArray[12] = R.drawable.letter_m;
        LetterArray[13] = R.drawable.letter_n;
        LetterArray[14] = R.drawable.letter_o;
        LetterArray[15] = R.drawable.letter_p;
        LetterArray[16] = R.drawable.letter_q;
        LetterArray[17] = R.drawable.letter_r;
        LetterArray[18] = R.drawable.letter_s;
        LetterArray[19] = R.drawable.letter_t;
        LetterArray[20] = R.drawable.letter_u;
        LetterArray[21] = R.drawable.letter_v;
        LetterArray[22] = R.drawable.letter_w;
        LetterArray[23] = R.drawable.letter_x;
        LetterArray[24] = R.drawable.letter_y;
        LetterArray[25] = R.drawable.letter_z;


        NumberArray[0] = R.drawable.letter_0;
        NumberArray[1] = R.drawable.letter_1;
        NumberArray[2] = R.drawable.letter_2;
        NumberArray[3] = R.drawable.letter_3;
        NumberArray[4] = R.drawable.letter_4;
        NumberArray[5] = R.drawable.letter_5;
        NumberArray[6] = R.drawable.letter_6;
        NumberArray[7] = R.drawable.letter_7;
        NumberArray[8] = R.drawable.letter_8;
        NumberArray[9] = R.drawable.letter_9;
    }

    public int getLetter(char chr) {
        int chr_int = (int)chr;
        if (chr_int >= int_a && chr_int <= int_z) {
            return LetterArray[chr_int - int_a];
        }
        else if (chr_int >= int_0 && chr_int <= int_9) {
            return NumberArray[chr_int - int_0];
        }
        else {
            return R.drawable.bad_input;
        }

    }
    public int[] getLetter(String input_str, int[] id_array) {
        for (int i = 0; i < input_str.length(); i++) {
            char input_str_part = input_str.charAt(i);
            id_array[i] = getLetter(input_str_part);
        }
        return id_array;
    }
    public int[] cows_bulls(int cows, int bulls, int[] id_array) {
        for(int i = 0; i > cows; i++) {
            id_array[i] = R.drawable.cow_head;
        }
        for (int i = cows; i < bulls + cows; i++) {
            id_array[i] = R.drawable.bulls_head;
        }
        return id_array;
    }

    public int get_imgres_for_key(int keycode) {
        char c;
        int resid = -1;

        if(KeyEvent.KEYCODE_A <= keycode && keycode <= KeyEvent.KEYCODE_Z) {
            resid = LetterArray[keycode - KeyEvent.KEYCODE_A];
        } else if(KeyEvent.KEYCODE_0 <= keycode && keycode <= KeyEvent.KEYCODE_9) {
            resid = NumberArray[keycode - KeyEvent.KEYCODE_0];
        }

        return resid;
    }

    public ImageView get_image_for_a_letter(Context myctxt, char c) {
        ImageView img = new ImageView(myctxt);
        img.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ViewGroup.LayoutParams img_layout = img.getLayoutParams();
        img_layout.height = 60;
        img_layout.width = 60;

        int img_code = (int) c;
        if (img_code >= int_a && img_code <= int_z) {
            img.setImageResource(LetterArray[img_code - int_a]);
        } else if (img_code >= int_0 && img_code <= int_9) {
            img.setImageResource(NumberArray[img_code - int_0]);
        } else {
            img.setImageResource(R.drawable.bad_input);
        }

        return img;
    }


    // Given a string, it gets the images for each letter and populates the passed-in LinearLayout
    public LinearLayout get_img_word(String guess, LetterImageManager ltrmngr, LinearLayout ll, Context myctxt) {
        for(int i = 0; i < guess.length(); i++) {
            ImageView img = get_image_for_a_letter(myctxt, guess.charAt(i));
            ll.addView(img);
        }
        return ll;
    }

    public int get_cow_num_id(int cow) {
        return NumberArray[cow];
    }

    public int get_bull_num_id(int bull) {
        return NumberArray[bull];
    }
}
