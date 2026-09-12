package ir.anjoman.zeroone.khoshtip;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    private static final String PREF_NAME = "userData";
    private static final String KEY = "type";
    private static final String KEY_START = "start";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public PrefManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    // ----------- Save methods --------------
    public void save(String type) {
        editor.putString(KEY, type);
        editor.apply();
    }

    public void start(String start) {
        editor.putString(KEY_START, start);
        editor.apply();
    }
    // ---------- Load methods -------------
    public String getSave() {
        return prefs.getString(KEY, "");
    }
    public String getStart() {return prefs.getString(KEY_START, "");}


}
