package com.example.pointmovement;

import android.util.Log;

import java.util.Vector;

public class MovingPoint {
    private float positionX;
    private float positionY;
    private int velocity;
    private int acceleration;
    private float prevPosition;

    public MovingPoint(int positionX, int positionY, int velocity, int acceleration) {
        this.positionX = positionX + velocity / 30f;
        this.positionY = positionY;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.prevPosition = positionX;
    }

    public void update(float dTime) {
        float newPosition = 2 * positionX - prevPosition + acceleration * dTime * dTime;
        Log.i("MovingPoint#update", "prevPosition: " + prevPosition);
        Log.i("MovingPoint#update", "positionX: " + positionX);
        Log.i("MovingPoint#update", "newPosition: " + newPosition);
        prevPosition = positionX;
        positionX = newPosition;
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }
}
