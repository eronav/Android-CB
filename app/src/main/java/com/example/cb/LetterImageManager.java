package com.example.cb;

public class LetterImageManager {
    public int[] LetterArray = new int[26];
    public int[] NumberArray = new int[10];
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
        if (chr_int >= 97 && chr_int <= 122) {
            return LetterArray[chr_int - 97];
        }
        else if (chr_int >= 48 && chr_int <= 57) {
            return NumberArray[chr_int - 48];
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

    public int get_cow_num_id(int cow) {
        return NumberArray[cow];
    }

    public int get_bull_num_id(int bull) {
        return NumberArray[bull];
    }
}
