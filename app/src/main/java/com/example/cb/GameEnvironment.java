package com.example.cb;

public class GameEnvironment {
    static public UserPrefs ups;
    static public WordGenerator wgen;
    static public MusicManager musicman;
    static public game_screen main_game;
    static public KeyboardManager keymgr;
    static public LetterImageManager ltrmngr;
    static public GuessManager guessmngr;
    static public GuessHistory guesshistmngr;
    static public GuessInputBox gbox;

    static public DoneButton donebutton;
    static public HintButton hintbutton;
    // static public HintButton hintbutton;
    // static public SubmitButton submitbutton;

    static public int[] phoneDims;
    static public int hintCount;
    static public String word;
    static int diff;

    static private boolean game_over;

    public static void SetGameOver(boolean setting) { game_over = setting; }
    public static boolean IsGameOver() { return game_over; }
}


