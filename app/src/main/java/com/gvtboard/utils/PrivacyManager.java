package com.gvtboard.utils;
import android.content.Context;
public class PrivacyManager {
    private boolean incognitoMode = false;
    public PrivacyManager(Context context) { }
    public void setIncognitoMode(boolean enabled) { incognitoMode = enabled; }
    public boolean isIncognitoMode() { return incognitoMode; }
}
