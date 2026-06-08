package com.gvtboard.ui;
import android.content.Context;
public class EmojiPanel {
    public EmojiPanel(Context context) { }
    public void show(EmojiCallback callback) { }
    public interface EmojiCallback { void onEmojiSelected(String emoji); }
}
