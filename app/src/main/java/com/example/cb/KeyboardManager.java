package com.example.cb;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

public class KeyboardManager implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        // Handles a keystroke
        String idString = v.getResources().getResourceEntryName(v.getId());
        int newKeyCode = getKeyCodeFromId(idString);
        KeyEvent event = new KeyEvent(KeyEvent.ACTION_UP, newKeyCode);
        GameEnvironment.main_game.onKeyLogic(event, newKeyCode);
    }

    private enum KeyColor {KEY_COLOR_DEFAULT, KEY_COLOR_EXISTS, KEY_COLOR_BOMB, KEY_COLOR_HINT}
    private int int_a = (int) 'a';
    private int int_z = (int) 'z';
    private int int_0 = (int) '0';
    private int int_9 = (int) '9';
    private Map<String, KeyColor> keystate = new HashMap<String, KeyColor>();
    
    private Activity activity;

    KeyboardManager(Activity activity) {
        for (int i = 97; i <= 122; i++) {
            char c = (char) i;
            keystate.put(String.valueOf(c), KeyColor.KEY_COLOR_DEFAULT);
        }
        
        this.activity = activity;
    }

    public int getViewIdFromLetter (char letter) {
        switch (letter) {
            case 'q': return R.id.imageView1;
            case 'w': return R.id.imageView2;
            case 'e': return R.id.imageView3;
            case 'r': return R.id.imageView4;
            case 't': return R.id.imageView5;
            case 'y': return R.id.imageView6;
            case 'u': return R.id.imageView7;
            case 'i': return R.id.imageView8;
            case 'o': return R.id.imageView9;
            case 'p': return R.id.imageView10;
            case 'a': return R.id.imageView11;
            case 's': return R.id.imageView12;
            case 'd': return R.id.imageView13;
            case 'f': return R.id.imageView14;
            case 'g': return R.id.imageView15;
            case 'h': return R.id.imageView16;
            case 'j': return R.id.imageView17;
            case 'k': return R.id.imageView18;
            case 'l': return R.id.imageView19;
            case 'z': return R.id.imageView20;
            case 'x': return R.id.imageView21;
            case 'c': return R.id.imageView22;
            case 'v': return R.id.imageView23;
            case 'b': return R.id.imageView24;
            case 'n': return R.id.imageView25;
            case 'm': return R.id.imageView26;
            default:
                return 0;
        }
    }

    public int getKeyCodeFromId (String ID) {
        switch (ID) {
            case "imageView1": return KeyEvent.KEYCODE_Q;
            case "imageView2": return KeyEvent.KEYCODE_W;
            case "imageView3": return KeyEvent.KEYCODE_E;
            case "imageView4": return KeyEvent.KEYCODE_R;
            case "imageView5": return KeyEvent.KEYCODE_T;
            case "imageView6": return KeyEvent.KEYCODE_Y;
            case "imageView7": return KeyEvent.KEYCODE_U;
            case "imageView8": return KeyEvent.KEYCODE_I;
            case "imageView9": return KeyEvent.KEYCODE_O;
            case "imageView10": return KeyEvent.KEYCODE_P;
            case "imageView11": return KeyEvent.KEYCODE_A;
            case "imageView12": return KeyEvent.KEYCODE_S;
            case "imageView13": return KeyEvent.KEYCODE_D;
            case "imageView14": return KeyEvent.KEYCODE_F;
            case "imageView15": return KeyEvent.KEYCODE_G;
            case "imageView16": return KeyEvent.KEYCODE_H;
            case "imageView17": return KeyEvent.KEYCODE_J;
            case "imageView18": return KeyEvent.KEYCODE_K;
            case "imageView19": return KeyEvent.KEYCODE_L;
            case "imageView20": return KeyEvent.KEYCODE_Z;
            case "imageView21": return KeyEvent.KEYCODE_X;
            case "imageView22": return KeyEvent.KEYCODE_C;
            case "imageView23": return KeyEvent.KEYCODE_V;
            case "imageView24": return KeyEvent.KEYCODE_B;
            case "imageView25": return KeyEvent.KEYCODE_N;
            case "imageView26": return KeyEvent.KEYCODE_M;
            default: return 0;
        }
    }


    // Set image for the character in the keyboard to the Bomb (does-not-exist) image
    public void set_bomb_image(char c) {
        ImageView img = GameEnvironment.main_game.findViewById(getViewIdFromLetter(c));
        int resID = GameEnvironment.ltrmngr.getBlackResId(c);
        img.setImageResource(resID);
        keystate.put(String.valueOf(c), KeyColor.KEY_COLOR_BOMB);
    }

    // Set image for the character in the keyboard to the "Exists" image
    public void set_exists_image(char c) {
        if (keystate.get(String.valueOf(c)) == KeyColor.KEY_COLOR_DEFAULT) {
            ImageView img = GameEnvironment.main_game.findViewById(getViewIdFromLetter(c));
            int resID = GameEnvironment.ltrmngr.getRedResId(c);
            img.setImageResource(resID);

            keystate.put(String.valueOf(c), KeyColor.KEY_COLOR_EXISTS);
        }
    }

    // Set image for the character in the keyboard to the hint image
    public void set_hint_image(char c) {
        ImageView img = GameEnvironment.main_game.findViewById(getViewIdFromLetter(c));
        int resID = GameEnvironment.ltrmngr.getHintResId(c);
        img.setImageResource(resID);

        keystate.put(String.valueOf(c), KeyColor.KEY_COLOR_HINT);
    }

    // Given a guess string and the result of the string
    // update the color of the keys in the keyboard to reflect status
    public void updateKeyboard(String instr, int[] result) {
        if (result[1] == 0 && result[2] == 0) {
            for (int i = 0; i < instr.length(); i++) {
                set_bomb_image(instr.charAt(i));
            }
        }
        if (result[1] != 0 || result[2] != 0) {
            for (int i = 0; i < instr.length(); i++) {
                set_exists_image(instr.charAt(i));
            }
        }
    }
    
    public void initialize_keyboard () {
        ImageView keyboardButton1, keyboardButton2, keyboardButton3, keyboardButton4;
        ImageView keyboardButton5, keyboardButton6, keyboardButton7, keyboardButton8;
        ImageView keyboardButton9, keyboardButton10, keyboardButton11, keyboardButton12;
        ImageView keyboardButton13, keyboardButton14, keyboardButton15, keyboardButton16;
        ImageView keyboardButton17, keyboardButton18, keyboardButton19, keyboardButton20;
        ImageView keyboardButton21, keyboardButton22, keyboardButton23, keyboardButton24;
        ImageView keyboardButton25, keyboardButton26;
        
        keyboardButton1 = activity.findViewById(R.id.imageView1);
        keyboardButton1.setClickable(true);
        keyboardButton1.setOnClickListener(this);

        keyboardButton2 = activity.findViewById(R.id.imageView2);
        keyboardButton2.setClickable(true);
        keyboardButton2.setOnClickListener(this);

        keyboardButton3 = activity.findViewById(R.id.imageView3);
        keyboardButton3.setClickable(true);
        keyboardButton3.setOnClickListener(this);

        keyboardButton4 = activity.findViewById(R.id.imageView4);
        keyboardButton4.setClickable(true);
        keyboardButton4.setOnClickListener(this);

        keyboardButton5 = activity.findViewById(R.id.imageView5);
        keyboardButton5.setClickable(true);
        keyboardButton5.setOnClickListener(this);

        keyboardButton6 = activity.findViewById(R.id.imageView6);
        keyboardButton6.setClickable(true);
        keyboardButton6.setOnClickListener(this);

        keyboardButton7 = activity.findViewById(R.id.imageView7);
        keyboardButton7.setClickable(true);
        keyboardButton7.setOnClickListener(this);

        keyboardButton8 = activity.findViewById(R.id.imageView8);
        keyboardButton8.setClickable(true);
        keyboardButton8.setOnClickListener(this);

        keyboardButton9 = activity.findViewById(R.id.imageView9);
        keyboardButton9.setClickable(true);
        keyboardButton9.setOnClickListener(this);

        keyboardButton10 = activity.findViewById(R.id.imageView10);
        keyboardButton10.setClickable(true);
        keyboardButton10.setOnClickListener(this);

        keyboardButton11 = activity.findViewById(R.id.imageView11);
        keyboardButton11.setClickable(true);
        keyboardButton11.setOnClickListener(this);

        keyboardButton12 = activity.findViewById(R.id.imageView12);
        keyboardButton12.setClickable(true);
        keyboardButton12.setOnClickListener(this);

        keyboardButton13 = activity.findViewById(R.id.imageView13);
        keyboardButton13.setClickable(true);
        keyboardButton13.setOnClickListener(this);

        keyboardButton14 = activity.findViewById(R.id.imageView14);
        keyboardButton14.setClickable(true);
        keyboardButton14.setOnClickListener(this);

        keyboardButton15 = activity.findViewById(R.id.imageView15);
        keyboardButton15.setClickable(true);
        keyboardButton15.setOnClickListener(this);

        keyboardButton16 = activity.findViewById(R.id.imageView16);
        keyboardButton16.setClickable(true);
        keyboardButton16.setOnClickListener(this);

        keyboardButton17 = activity.findViewById(R.id.imageView17);
        keyboardButton17.setClickable(true);
        keyboardButton17.setOnClickListener(this);

        keyboardButton18 = activity.findViewById(R.id.imageView18);
        keyboardButton18.setClickable(true);
        keyboardButton18.setOnClickListener(this);

        keyboardButton19 = activity.findViewById(R.id.imageView19);
        keyboardButton19.setClickable(true);
        keyboardButton19.setOnClickListener(this);

        keyboardButton20 = activity.findViewById(R.id.imageView20);
        keyboardButton20.setClickable(true);
        keyboardButton20.setOnClickListener(this);

        keyboardButton21 = activity.findViewById(R.id.imageView21);
        keyboardButton21.setClickable(true);
        keyboardButton21.setOnClickListener(this);

        keyboardButton22 = activity.findViewById(R.id.imageView22);
        keyboardButton22.setClickable(true);
        keyboardButton22.setOnClickListener(this);

        keyboardButton23 = activity.findViewById(R.id.imageView23);
        keyboardButton23.setClickable(true);
        keyboardButton23.setOnClickListener(this);

        keyboardButton24 = activity.findViewById(R.id.imageView24);
        keyboardButton24.setClickable(true);
        keyboardButton24.setOnClickListener(this);

        keyboardButton25 = activity.findViewById(R.id.imageView25);
        keyboardButton25.setClickable(true);
        keyboardButton25.setOnClickListener(this);

        keyboardButton26 = activity.findViewById(R.id.imageView26);
        keyboardButton26.setClickable(true);
        keyboardButton26.setOnClickListener(this);
    }
}