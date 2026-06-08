package com.gvtboard.service;

import android.graphics.Point;
import android.inputmethodservice.Keyboard;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.List;

public class GestureProcessor {
    private List<Point> touchPoints = new ArrayList<>();
    private static final int GESTURE_DETECTION_THRESHOLD = 50;
    
    public String processGesturePath(List<MotionEvent> events, Keyboard keyboard) {
        // Convert touch points to key presses
        StringBuilder result = new StringBuilder();
        List<Keyboard.Key> touchedKeys = new ArrayList<>();
        
        for (Point point : touchPoints) {
            Keyboard.Key nearest = findNearestKey(point, keyboard);
            if (nearest != null && !touchedKeys.contains(nearest)) {
                touchedKeys.add(nearest);
                result.append(nearest.label);
            }
        }
        
        return result.toString();
    }
    
    private Keyboard.Key findNearestKey(Point point, Keyboard keyboard) {
        List<Keyboard.Key> keys = keyboard.getKeys();
        Keyboard.Key nearest = null;
        double minDistance = Double.MAX_VALUE;
        
        for (Keyboard.Key key : keys) {
            int centerX = key.x + key.width / 2;
            int centerY = key.y + key.height / 2;
            double distance = Math.hypot(point.x - centerX, point.y - centerY);
            
            if (distance < minDistance) {
                minDistance = distance;
                nearest = key;
            }
        }
        
        return nearest;
    }
    
    public void addTouchPoint(MotionEvent event) {
        touchPoints.add(new Point((int) event.getX(), (int) event.getY()));
    }
    
    public void clear() {
        touchPoints.clear();
    }
}
