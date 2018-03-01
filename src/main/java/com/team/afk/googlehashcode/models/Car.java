package com.team.afk.googlehashcode.models;

import java.util.ArrayList;
import java.util.List;

public class Car {
    long x;
    long y;
    Ride ride;
    boolean started = false;
    List<Ride> rides;

    public Car(final long x, final long y) {
        this.x = x;
        this.y = y;
        rides = new ArrayList<>();
    }

    public void move(long step) {
        System.err.println("=============================================================");
        System.err.println("Ride start " + ride.columnStart + " " + ride.rowStart + " | " + ride.columnFinish + " " + ride.rowFinish);
        System.err.println("Ride id: " + ride.rideId  +  " Car start location: " + x + " " + y);
        if(ride != null) {

            if (x == ride.columnFinish && y == ride.rowFinish) {
                ride = null;
                return;
            }
            else if (x == ride.columnStart && y == ride.rowStart && step >= ride.getEarliestStart()) {
                started = true;
            }
            long largestX;
            long largestY;

            if (started) {
                System.err.println("Car moving to end");
                //Trip started moving to end
                if (x != ride.columnFinish) {
                    moveX(ride.columnFinish);
                }
                else if (y != ride.rowFinish) {
                    moveY(ride.rowFinish);
                }
                else {
                    //waiting
                }
            }
            else {
                System.err.println("Car moving to start");
                //Trip not started moving to start
                if (x != ride.columnStart) {
                    moveX(ride.columnStart);
                } else if (y != ride.rowStart) {
                    moveY(ride.rowStart);
                }
            }
        }

        System.err.println("Car end location: " + x + " " + y);
        System.err.println("=============================================================");
    }

    private void moveX(long destX) {
        if(x > destX) {
            x--;
        }
        else {
            x++;
        }
    }

    private void moveY(long destY) {
        if(y > destY) {
            y--;
        }
        else {
            y++;
        }
    }

    public long pathLenghTo(long destX, long destY) {
        long dest = destX + destY;
        long start = x + y;

        return Math.abs(dest - start);
    }

    public long getX() {
        return x;
    }

    public void setX(final long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(final long y) {
        this.y = y;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(final Ride ride) {
        this.ride = ride;
        rides.add(ride);
    }

    public boolean isDone() {

        return (ride == null);
    }

    public boolean isStarted() {
        return started;
    }

    public List<Ride> getRides() {
        return rides;
    }
}
