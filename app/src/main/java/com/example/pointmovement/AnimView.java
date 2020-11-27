package com.example.pointmovement;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AnimView extends SurfaceView {
    private AnimationThread animationThread;
    private SurfaceHolder holder;
    private int x = 0;
    private MovingPoint movingPoint;
    public AnimView(Context context, MovingPoint movingPoint) {
        super(context);
        animationThread = new AnimationThread(this, movingPoint);
        this.movingPoint = movingPoint;
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                animationThread.setRunning(true);
                animationThread.start();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                boolean retry = true;
                animationThread.setRunning(retry);
                while(retry) {
                    try {
                        animationThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
    }
    public AnimView(Context context) {
        super(context);
    }

    public AnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AnimView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void resetPoint(MovingPoint movingPoint) {
        animationThread.setRunning(false);
        this.movingPoint = movingPoint;
        animationThread = new AnimationThread(this, movingPoint);
        animationThread.setRunning(true);
        animationThread.start();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        Paint paint = new Paint();
        paint.setStrokeWidth(15);
        paint.setColor(Color.GREEN);
//        Log.i("AnimView#onDraw", "Coordinates: X = " + movingPoint.getPositionX() + " | Y = " + movingPoint.getPositionY());
        canvas.drawPoint(movingPoint.getPositionX(), movingPoint.getPositionY(), paint);
    }
}
