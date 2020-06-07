package com.example.cb;

public class UserPrefs {
    private boolean music_on;
    private boolean dups_on;
    private boolean game_over;

    UserPrefs() {
        music_on = false;
        dups_on = false;
        game_over = false;
    }

    public void SetMusic(boolean setting) { music_on = setting; }
    public void SetDups(boolean setting) { dups_on = setting; }
    public void SetGameOver(boolean setting) { game_over = setting; }

    public boolean IsMusicOn() { return music_on; }
    public boolean IsDupsOn() { return dups_on; }
    public boolean IsGameOver() { return game_over; }
}
