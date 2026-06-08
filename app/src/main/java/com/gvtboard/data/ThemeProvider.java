package com.gvtboard.data;
import android.content.Context;
public class ThemeProvider {
    public ThemeProvider(Context context) { }
    public void downloadTheme(String themeId, ThemeDownloadCallback callback) { }
    public interface ThemeDownloadCallback { void onSuccess(); void onError(String error); }
}
