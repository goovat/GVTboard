package com.gvtboard.ui;
import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import java.util.List;
public class GVTKeyboardView extends KeyboardView {
    public GVTKeyboardView(Context context, AttributeSet attrs) { super(context, attrs); }
    public void setSuggestions(List<String> suggestions) { }
    public void setPredictions(List<String> predictions) { }
    public void setEmojiMode(boolean enabled) { }
    public void setTextColor(int color) { }
    public void setKeyBackground(Object drawable) { }
}
