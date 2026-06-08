package com.gvtboard.prediction;
import android.content.Context;
import java.util.*;
public class AutoCorrector {
    private Context context;
    public AutoCorrector(Context context) { this.context = context; }
    public String correctWord(String word) { return word; }
    public List<String> getSuggestions(String word) { return Arrays.asList(word, word + "ing", word + "ed"); }
    public void learnWord(String word) { }
}
