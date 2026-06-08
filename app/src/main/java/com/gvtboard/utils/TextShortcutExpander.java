package com.gvtboard.utils;
import java.util.*;
public class TextShortcutExpander {
    private Map<String,String> shortcuts = new HashMap<>();
    public TextShortcutExpander() { shortcuts.put("omw", "On my way!"); shortcuts.put("brb", "Be right back"); }
    public String expand(String text) { return shortcuts.getOrDefault(text, text); }
}
