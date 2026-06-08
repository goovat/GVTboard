package com.gvtboard.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import com.gvtboard.ui.GVTKeyboardView;

public class ThemeManager {
    private Context context;
    private SharedPreferences prefs;
    private Theme currentTheme;
    
    public static class Theme {
        public String id;
        public String name;
        public int backgroundColor;
        public int keyBackgroundColor;
        public int keyPressedColor;
        public int keyTextColor;
        public int accentColor;
        
        public Theme(String id, String name, int bgColor, int keyBg, int keyPressed, int textColor, int accent) {
            this.id = id;
            this.name = name;
            this.backgroundColor = bgColor;
            this.keyBackgroundColor = keyBg;
            this.keyPressedColor = keyPressed;
            this.keyTextColor = textColor;
            this.accentColor = accent;
        }
    }
    
    public ThemeManager(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences("gvtboard_themes", Context.MODE_PRIVATE);
        loadCurrentTheme();
    }
    
    public void applyTheme(GVTKeyboardView keyboardView) {
        if (currentTheme == null) {
            currentTheme = getDefaultTheme();
        }
        
        keyboardView.setBackgroundColor(currentTheme.backgroundColor);
        keyboardView.setTextColor(currentTheme.keyTextColor);
        keyboardView.setKeyBackground(createKeyDrawable());
    }
    
    private GradientDrawable createKeyDrawable() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(8 * context.getResources().getDisplayMetrics().density);
        drawable.setColor(currentTheme.keyBackgroundColor);
        drawable.setStroke(2, currentTheme.accentColor);
        return drawable;
    }
    
    public Theme getDefaultTheme() {
        return new Theme("default", "GVT Dark", 
            Color.parseColor("#1a1a2e"), 
            Color.parseColor("#16213e"),
            Color.parseColor("#0f3460"),
            Color.WHITE,
            Color.parseColor("#e94560"));
    }
    
    public Theme getAmoledTheme() {
        return new Theme("amoled", "AMOLED Black",
            Color.BLACK,
            Color.parseColor("#111111"),
            Color.parseColor("#222222"),
            Color.WHITE,
            Color.GRAY);
    }
    
    private void loadCurrentTheme() {
        String themeId = prefs.getString("current_theme", "default");
        if (themeId.equals("amoled")) {
            currentTheme = getAmoledTheme();
        } else {
            currentTheme = getDefaultTheme();
        }
    }
    
    public void setTheme(String themeId) {
        prefs.edit().putString("current_theme", themeId).apply();
        loadCurrentTheme();
    }
}
