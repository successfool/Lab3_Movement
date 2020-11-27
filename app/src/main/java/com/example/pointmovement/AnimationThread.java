package com.example.pointmovement;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.util.Log;

public class AnimationThread extends Thread {
    static final long FPS = 30;
    private AnimView view;
    private boolean running = false;
    private MovingPoint movingPoint;
    public AnimationThread(AnimView view, MovingPoint movingPoint) {
        this.movingPoint = movingPoint;
        this.view = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @SuppressLint("WrongCall")
    @Override
    public void run() {
        long ticksPs = 1000 / FPS;
        long startTime;
        long sleepTime;
        while(running) {
            Canvas c = null;
            startTime = System.currentTimeMillis();
            try {
                c = view.getHolder().lockCanvas();
                movingPoint.update(ticksPs / 1000f);
//                Log.i("AnimationThread", "dTime" + ticksPs / 1000f);
//                Log.i("AnimationThread", "Coordinates: X = " + movingPoint.getPositionX() + " | Y = " + movingPoint.getPositionY());
                synchronized (view.getHolder()) {
                    view.onDraw(c);
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
            sleepTime = ticksPs - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {}
        }
    }
}
