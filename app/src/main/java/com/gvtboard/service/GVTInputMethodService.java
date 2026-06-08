package com.gvtboard.service;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.gvtboard.R;
import com.gvtboard.prediction.AutoCorrector;
import com.gvtboard.prediction.NextWordPredictor;
import com.gvtboard.ui.GVTKeyboardView;
import com.gvtboard.ui.ClipboardPanel;
import com.gvtboard.ui.EmojiPanel;
import com.gvtboard.utils.PrivacyManager;
import java.util.ArrayList;
import java.util.List;

public class GVTInputMethodService extends InputMethodService 
        implements KeyboardView.OnKeyboardActionListener {
    
    public static final int MODE_QWERTY = 0;
    public static final int MODE_SYMBOLS = 1;
    public static final int MODE_NUMERIC = 2;
    public static final int MODE_EMOJI = 3;
    
    private GVTKeyboardView keyboardView;
    private Keyboard qwertyKeyboard;
    private Keyboard symbolsKeyboard;
    private Keyboard numericKeyboard;
    private Keyboard emojiKeyboard;
    private int currentMode = MODE_QWERTY;
    
    private AutoCorrector autoCorrector;
    private NextWordPredictor wordPredictor;
    private ClipboardPanel clipboardPanel;
    private EmojiPanel emojiPanel;
    private PrivacyManager privacyManager;
    private Vibrator vibrator;
    private AudioManager audioManager;
    private StringBuilder currentWord = new StringBuilder();
    private List<String> suggestions = new ArrayList<>();
    private boolean capsLock = false;
    private boolean incognitoMode = false;
    
    @Override
    public void onCreate() {
        super.onCreate();
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        autoCorrector = new AutoCorrector(this);
        wordPredictor = new NextWordPredictor(this);
        privacyManager = new PrivacyManager(this);
        clipboardPanel = new ClipboardPanel(this);
        emojiPanel = new EmojiPanel(this);
    }
    
    @Override
    public View onCreateInputView() {
        keyboardView = (GVTKeyboardView) getLayoutInflater().inflate(
            R.layout.keyboard_main, null);
        
        qwertyKeyboard = new Keyboard(this, R.xml.keyboards.qwerty);
        symbolsKeyboard = new Keyboard(this, R.xml.keyboards.symbols);
        numericKeyboard = new Keyboard(this, R.xml.keyboards.numeric);
        emojiKeyboard = new Keyboard(this, R.xml.keyboards.emoji_categories);
        
        keyboardView.setKeyboard(qwertyKeyboard);
        keyboardView.setOnKeyboardActionListener(this);
        
        return keyboardView;
    }
    
    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;
        
        switch (primaryCode) {
            case Keyboard.KEYCODE_DELETE:
                handleDelete(ic);
                break;
            case Keyboard.KEYCODE_DONE:
                ic.performEditorAction(EditorInfo.IME_ACTION_DONE);
                break;
            case -2:
                commitCurrentWord();
                ic.commitText(" ", 1);
                break;
            case -3:
                commitCurrentWord();
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            case -4:
                toggleCaps();
                break;
            case -5:
                switchKeyboard(MODE_SYMBOLS);
                break;
            case -6:
                switchKeyboard(MODE_EMOJI);
                break;
            case -7:
                startVoiceInput();
                break;
            case -8:
                showClipboardPanel();
                break;
            case -9:
                showSettings();
                break;
            default:
                handleCharacterInput((char) primaryCode);
                break;
        }
        
        if (shouldVibrate()) vibrator.vibrate(20);
        if (shouldPlaySound()) playKeySound(primaryCode);
    }
    
    private void handleCharacterInput(char character) {
        InputConnection ic = getCurrentInputConnection();
        if (!incognitoMode) {
            currentWord.append(character);
            updateSuggestions();
        }
        if (capsLock) character = Character.toUpperCase(character);
        ic.commitText(String.valueOf(character), 1);
    }
    
    private void handleDelete(InputConnection ic) {
        if (currentWord.length() > 0) {
            currentWord.deleteCharAt(currentWord.length() - 1);
            updateSuggestions();
        } else {
            ic.deleteSurroundingText(1, 0);
        }
    }
    
    private void commitCurrentWord() {
        if (currentWord.length() > 0 && !incognitoMode) {
            String corrected = autoCorrector.correctWord(currentWord.toString());
            replaceCurrentWord(corrected);
            currentWord.setLength(0);
            suggestions.clear();
        }
    }
    
    private void updateSuggestions() {
        if (currentWord.length() > 0 && !incognitoMode) {
            suggestions = autoCorrector.getSuggestions(currentWord.toString());
            keyboardView.setSuggestions(suggestions);
        }
    }
    
    private void toggleCaps() {
        capsLock = !capsLock;
        keyboardView.setShifted(capsLock);
    }
    
    private void switchKeyboard(int mode) {
        currentMode = mode;
        switch (mode) {
            case MODE_QWERTY: keyboardView.setKeyboard(qwertyKeyboard); break;
            case MODE_SYMBOLS: keyboardView.setKeyboard(symbolsKeyboard); break;
            case MODE_NUMERIC: keyboardView.setKeyboard(numericKeyboard); break;
            case MODE_EMOJI: keyboardView.setKeyboard(emojiKeyboard); break;
        }
    }
    
    private void startVoiceInput() {
        VoiceInputHandler voiceHandler = new VoiceInputHandler(this);
        voiceHandler.startListening(text -> getCurrentInputConnection().commitText(text, 1));
    }
    
    private void showClipboardPanel() {
        clipboardPanel.show(text -> getCurrentInputConnection().commitText(text, 1));
    }
    
    private void showSettings() {
        // Launch settings activity
    }
    
    private void replaceCurrentWord(String newWord) {
        InputConnection ic = getCurrentInputConnection();
        ic.deleteSurroundingText(currentWord.length(), 0);
        ic.commitText(newWord, 1);
    }
    
    private void playKeySound(int keyCode) {
        int soundType = AudioManager.FX_KEYPRESS_STANDARD;
        if (keyCode == -2) soundType = AudioManager.FX_KEYPRESS_SPACEBAR;
        else if (keyCode == Keyboard.KEYCODE_DELETE) soundType = AudioManager.FX_KEYPRESS_DELETE;
        audioManager.playSoundEffect(soundType);
    }
    
    private boolean shouldVibrate() {
        return getSharedPreferences("gvtboard", MODE_PRIVATE).getBoolean("vibration_enabled", true);
    }
    
    private boolean shouldPlaySound() {
        return getSharedPreferences("gvtboard", MODE_PRIVATE).getBoolean("sound_enabled", true);
    }
    
    @Override public void onPress(int primaryCode) {}
    @Override public void onRelease(int primaryCode) {}
    @Override public void onText(CharSequence text) {}
    @Override public void swipeLeft() { switchKeyboard(MODE_SYMBOLS); }
    @Override public void swipeRight() { switchKeyboard(MODE_EMOJI); }
    @Override public void swipeDown() { requestHideSelf(0); }
    @Override public void swipeUp() { showClipboardPanel(); }
}
