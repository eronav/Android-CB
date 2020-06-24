package com.example.cb;

public class UserPrefs {
    private boolean music_on;
    private boolean dups_on;

    UserPrefs() {
        music_on = false;
        dups_on = false;
    }

    public void SetMusic(boolean setting) { music_on = setting; }
    public void SetDups(boolean setting) { dups_on = setting; }

    public boolean IsMusicOn() { return music_on; }
    public boolean IsDupsOn() { return dups_on; }
}
