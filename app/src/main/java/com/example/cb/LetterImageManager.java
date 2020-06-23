package com.example.cb;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LetterImageManager {
    public int[] LetterArray = new int[26];
    public int[] NumberArray = new int[10];
    public int[] HintLetterArray = new int[26];
    public int[] HintNumberArray = new int[10];
    public int[] BlackLetterArray = new int[26];
    public int[] BlackNumberArray = new int[10];
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

        HintLetterArray[0] = R.drawable.hint_letter_a;
        HintLetterArray[1] = R.drawable.hint_letter_b;
        HintLetterArray[2] = R.drawable.hint_letter_c;
        HintLetterArray[3] = R.drawable.hint_letter_d;
        HintLetterArray[4] = R.drawable.hint_letter_e;
        HintLetterArray[5] = R.drawable.hint_letter_f;
        HintLetterArray[6] = R.drawable.hint_letter_g;
        HintLetterArray[7] = R.drawable.hint_letter_h;
        HintLetterArray[8] = R.drawable.hint_letter_i;
        HintLetterArray[9] = R.drawable.hint_letter_j;
        HintLetterArray[10] = R.drawable.hint_letter_k;
        HintLetterArray[11] = R.drawable.hint_letter_l;
        HintLetterArray[12] = R.drawable.hint_letter_m;
        HintLetterArray[13] = R.drawable.hint_letter_n;
        HintLetterArray[14] = R.drawable.hint_letter_o;
        HintLetterArray[15] = R.drawable.hint_letter_p;
        HintLetterArray[16] = R.drawable.hint_letter_q;
        HintLetterArray[17] = R.drawable.hint_letter_r;
        HintLetterArray[18] = R.drawable.hint_letter_s;
        HintLetterArray[19] = R.drawable.hint_letter_t;
        HintLetterArray[20] = R.drawable.hint_letter_u;
        HintLetterArray[21] = R.drawable.hint_letter_v;
        HintLetterArray[22] = R.drawable.hint_letter_w;
        HintLetterArray[23] = R.drawable.hint_letter_x;
        HintLetterArray[24] = R.drawable.hint_letter_y;
        HintLetterArray[25] = R.drawable.hint_letter_z;

        HintNumberArray[0] = R.drawable.hint_letter_0;
        HintNumberArray[1] = R.drawable.hint_letter_1;
        HintNumberArray[2] = R.drawable.hint_letter_2;
        HintNumberArray[3] = R.drawable.hint_letter_3;
        HintNumberArray[4] = R.drawable.hint_letter_4;
        HintNumberArray[5] = R.drawable.hint_letter_5;
        HintNumberArray[6] = R.drawable.hint_letter_6;
        HintNumberArray[7] = R.drawable.hint_letter_7;
        HintNumberArray[8] = R.drawable.hint_letter_8;
        HintNumberArray[9] = R.drawable.hint_letter_9;

        BlackLetterArray[0] = R.drawable.black_letter_a;
        BlackLetterArray[1] = R.drawable.black_letter_b;
        BlackLetterArray[2] = R.drawable.black_letter_c;
        BlackLetterArray[3] = R.drawable.black_letter_d;
        BlackLetterArray[4] = R.drawable.black_letter_e;
        BlackLetterArray[5] = R.drawable.black_letter_f;
        BlackLetterArray[6] = R.drawable.black_letter_g;
        BlackLetterArray[7] = R.drawable.black_letter_h;
        BlackLetterArray[8] = R.drawable.black_letter_i;
        BlackLetterArray[9] = R.drawable.black_letter_j;
        BlackLetterArray[10] = R.drawable.black_letter_k;
        BlackLetterArray[11] = R.drawable.black_letter_l;
        BlackLetterArray[12] = R.drawable.black_letter_m;
        BlackLetterArray[13] = R.drawable.black_letter_n;
        BlackLetterArray[14] = R.drawable.black_letter_o;
        BlackLetterArray[15] = R.drawable.black_letter_p;
        BlackLetterArray[16] = R.drawable.black_letter_q;
        BlackLetterArray[17] = R.drawable.black_letter_r;
        BlackLetterArray[18] = R.drawable.black_letter_s;
        BlackLetterArray[19] = R.drawable.black_letter_t;
        BlackLetterArray[20] = R.drawable.black_letter_u;
        BlackLetterArray[21] = R.drawable.black_letter_v;
        BlackLetterArray[22] = R.drawable.black_letter_w;
        BlackLetterArray[23] = R.drawable.black_letter_x;
        BlackLetterArray[24] = R.drawable.black_letter_y;
        BlackLetterArray[25] = R.drawable.black_letter_z;

        BlackNumberArray[0] = R.drawable.black_letter_0;
        BlackNumberArray[1] = R.drawable.black_letter_1;
        BlackNumberArray[2] = R.drawable.black_letter_2;
        BlackNumberArray[3] = R.drawable.black_letter_3;
        BlackNumberArray[4] = R.drawable.black_letter_4;
        BlackNumberArray[5] = R.drawable.black_letter_5;
        BlackNumberArray[6] = R.drawable.black_letter_6;
        BlackNumberArray[7] = R.drawable.black_letter_7;
        BlackNumberArray[8] = R.drawable.black_letter_8;
        BlackNumberArray[9] = R.drawable.black_letter_9;


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

    public int getHintLetter(char chr) {
        int chr_int = chr;
        if (chr_int >= int_a && chr_int <= int_z) {
            return HintLetterArray[chr_int - int_a];
        }
        else if (chr_int >= int_0 && chr_int <= int_9) {
            return HintNumberArray[chr_int - int_0];
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
        img.setLayoutParams(getLayoutParams());

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

    public ImageView get_image_for_a_hint(Context myctxt, char c) {
        ImageView img = new ImageView(myctxt);
        img.setLayoutParams(getLayoutParams());

        int img_code = (int) c;
        if (img_code >= int_a && img_code <= int_z) {
            img.setImageResource(HintLetterArray[img_code - int_a]);
        } else if (img_code >= int_0 && img_code <= int_9) {
            img.setImageResource(HintNumberArray[img_code - int_0]);
        } else {
            img.setImageResource(R.drawable.bad_input);
        }

        return img;
    }

    public int getBlackResId (char c) {
        int img_code = (int) c;
        if (img_code >= int_a && img_code <= int_z) {
            return (BlackLetterArray[img_code - int_a]);
        } else if (img_code >= int_0 && img_code <= int_9) {
            return (BlackNumberArray[img_code - int_0]);
        } else {
            return (R.drawable.bad_input);
        }
    }

    public int getRedResId (char c) {
        int img_code = (int) c;
        if (img_code >= int_a && img_code <= int_z) {
            return (LetterArray[img_code - int_a]);
        } else if (img_code >= int_0 && img_code <= int_9) {
            return (NumberArray[img_code - int_0]);
        } else {
            return (R.drawable.bad_input);
        }
    }

    public int getHintResId (char c) {
        int img_code = (int) c;
        if (img_code >= int_a && img_code <= int_z) {
            return (HintLetterArray[img_code - int_a]);
        } else if (img_code >= int_0 && img_code <= int_9) {
            return (HintNumberArray[img_code - int_0]);
        } else {
            return (R.drawable.bad_input);
        }
    }

    // Given a string, it gets the images for each letter and populates the passed-in LinearLayout
    public LinearLayout get_image_for_word(String guess, HintManager guessmngr, LinearLayout ll, Context myctxt) {
        ImageView img;
        for(int i = 0; i < guess.length(); i++) {
            // If there is a hint, use the Hint character set else use the Regular character set
            if (guessmngr.hasHintAt(i)) {
                img = get_image_for_a_hint(myctxt, guess.charAt(i));
            } else {
                img = get_image_for_a_letter(myctxt, guess.charAt(i));
            }
            // img.setBackgroundDrawable(new Border(0xffff0000, 50));
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

    public static LinearLayout.LayoutParams getLayoutParams () {
        LinearLayout.LayoutParams img_layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        img_layout.height = GameEnvironment.phoneDims[0] / 15;
        img_layout.width = GameEnvironment.phoneDims[0] / 15;
        img_layout.setMargins(0,0, 2, 10);
        return img_layout;
    }
}
