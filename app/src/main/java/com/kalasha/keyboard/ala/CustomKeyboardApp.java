package com.kalasha.keyboard.ala;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.view.inputmethod.InputConnection;
import java.util.HashMap;
import java.util.Map;


public class CustomKeyboardApp extends  InputMethodService
        implements KeyboardView.OnKeyboardActionListener {

    private boolean isShifted = false;
    private KeyboardView keyboardView;
    private Map<Integer, Integer> shiftedCharacters;
    private Map<Integer, String> originalLabels;


    @Override
    public View onCreateInputView() {
        initializeShiftedCharacters();
        
        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.custom_layout, null);
        Keyboard keyboard = new Keyboard(this, R.xml.custom_keypad);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        
        // Store original labels
        storeOriginalLabels();

        return keyboardView;

    }
    
    private void initializeShiftedCharacters() {
        shiftedCharacters = new HashMap<>();
        
        // Numbers to symbols mapping
        shiftedCharacters.put(1777, (int)'!'); // ۱ -> !
        shiftedCharacters.put(1778, (int)'@'); // ۲ -> @
        shiftedCharacters.put(1779, (int)'#'); // ۳ -> #
        shiftedCharacters.put(1780, (int)'$'); // ۴ -> $
        shiftedCharacters.put(1781, (int)'%'); // ۵ -> %
        shiftedCharacters.put(1782, (int)'^'); // ۶ -> ^
        shiftedCharacters.put(1783, (int)'&'); // ۷ -> &
        shiftedCharacters.put(1784, (int)'*'); // ۸ -> *
        shiftedCharacters.put(1785, (int)'('); // ۹ -> (
        shiftedCharacters.put(1776, (int)')'); // ۰ -> )
        
        // Row 2 - First row of letters  
        shiftedCharacters.put(1590, (int)'؟'); // ض -> ؟
        shiftedCharacters.put(1589, (int)'؛'); // ص -> ؛
        shiftedCharacters.put(1579, (int)'،'); // ث -> ،
        shiftedCharacters.put(1602, (int)':'); // ق -> :
        shiftedCharacters.put(1601, (int)'"'); // ف -> "
        shiftedCharacters.put(1594, (int)'\''); // غ -> '
        shiftedCharacters.put(1593, (int)'ۊ'); // ع -> [
        shiftedCharacters.put(1607, (int)'ۉ'); // ه -> ]
        shiftedCharacters.put(1582, (int)'ۇ'); // خ -> {
        shiftedCharacters.put(1581, (int)'ۂ'); // ح -> }
        shiftedCharacters.put(1580, (int)'ڇ'); // ج -> |
        shiftedCharacters.put(1670, (int)'څ'); // چ -> \
        
        // Row 3 - Second row of letters
        shiftedCharacters.put(1588, (int)'ښ'); // ش -> ~
        shiftedCharacters.put(1587, (int)'ۍ'); // س -> `
        shiftedCharacters.put(1740, (int)'ي'); // ی -> =
        shiftedCharacters.put(1576, (int)'پ'); // ب -> +
        shiftedCharacters.put(1604, (int)'أ'); // ل -> -
        shiftedCharacters.put(1575, (int)'آ'); // ا -> _
        shiftedCharacters.put(1578, (int)'ټ'); // ت -> <
        shiftedCharacters.put(1606, (int)'ڼ'); // ن -> >
        shiftedCharacters.put(1605, (int)'ݩ'); // م -> /
        shiftedCharacters.put(1603, (int)'?'); // ک -> ?
        shiftedCharacters.put(1711, (int)'&'); // گ -> &
        
        // Row 4 - Third row of letters
        shiftedCharacters.put(1592, (int)'ئ'); // ظ -> €
        shiftedCharacters.put(1591, (int)'ې'); // ط -> £
        shiftedCharacters.put(1586, (int)'ژ'); // ز -> ¥
        shiftedCharacters.put(1585, (int)'ڙ'); // ر -> ©
        shiftedCharacters.put(1584, (int)'ډ'); // ذ -> ®
        shiftedCharacters.put(1583, (int)'ۋ'); // د -> ™
        shiftedCharacters.put(1683, (int)'ؤ'); // ړ -> §
        shiftedCharacters.put(1608, (int)'،'); // و -> ¡
        shiftedCharacters.put(1686, (int)'.'); // ږ -> ¿
    }
    
    private void storeOriginalLabels() {
        originalLabels = new HashMap<>();
        if (keyboardView != null && keyboardView.getKeyboard() != null) {
            Keyboard keyboard = keyboardView.getKeyboard();
            for (Keyboard.Key key : keyboard.getKeys()) {
                if (key.codes != null && key.codes.length > 0) {
                    originalLabels.put(key.codes[0], key.label != null ? key.label.toString() : "");
                }
            }
        }
    }
    
    private void updateKeyboardLabels() {
        if (keyboardView != null && keyboardView.getKeyboard() != null) {
            Keyboard keyboard = keyboardView.getKeyboard();
            for (Keyboard.Key key : keyboard.getKeys()) {
                if (key.codes != null && key.codes.length > 0) {
                    if (isShifted && shiftedCharacters.containsKey(key.codes[0])) {
                        key.label = String.valueOf((char)(int)shiftedCharacters.get(key.codes[0]));
                    } else if (!isShifted && originalLabels.containsKey(key.codes[0])) {
                        // Reset to original label
                        key.label = originalLabels.get(key.codes[0]);
                    }
                }
            }
            keyboardView.invalidateAllKeys();
        }
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection inputConnection = getCurrentInputConnection();
        if(inputConnection == null){
            return;
        }

        switch (primaryCode) {
            case -1: // Shift key
                isShifted = !isShifted;
                keyboardView.setShifted(isShifted);
                updateKeyboardLabels();
                break;
            case -5: // Delete key
                inputConnection.deleteSurroundingText(1, 0);
                break;
            case 32: // Space key
                inputConnection.commitText(" ", 1);
                break;
            case 10: // Enter key
                inputConnection.sendKeyEvent(new android.view.KeyEvent(android.view.KeyEvent.ACTION_DOWN, android.view.KeyEvent.KEYCODE_ENTER));
                inputConnection.sendKeyEvent(new android.view.KeyEvent(android.view.KeyEvent.ACTION_UP, android.view.KeyEvent.KEYCODE_ENTER));
                break;
            default:
                // Handle Unicode characters for Kalasha Ala with shift support
                if (isShifted && shiftedCharacters.containsKey(primaryCode)) {
                    // Use shifted character
                    String character = String.valueOf((char)(int)shiftedCharacters.get(primaryCode));
                    inputConnection.commitText(character, 1);
                    // Auto-disable shift after typing (like normal keyboards)
                    isShifted = false;
                    keyboardView.setShifted(false);
                    updateKeyboardLabels();
                } else {
                    // Use normal character
                    String character = String.valueOf((char) primaryCode);
                    inputConnection.commitText(character, 1);
                    // Auto-disable shift after typing
                    if (isShifted) {
                        isShifted = false;
                        keyboardView.setShifted(false);
                        updateKeyboardLabels();
                    }
                }
                break;
        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
