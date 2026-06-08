package com.gvtboard.ui;
import android.content.Context;
public class ClipboardPanel {
    public ClipboardPanel(Context context) { }
    public void show(ClipboardCallback callback) { }
    public interface ClipboardCallback { void onTextSelected(String text); }
}
