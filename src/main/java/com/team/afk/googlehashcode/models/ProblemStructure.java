package com.team.afk.googlehashcode.models;

import java.util.ArrayList;
import java.util.List;

public class ProblemStructure
{
    long rowNumbers;
    long numberOfColumns;
    long fleetSize;
    long numberOfRides;
    long perRideBonus;
    long steps;

    List<Rides> rides = new ArrayList<>();

    public ProblemStructure(final long rowNumbers, final long numberOfColumns, final long fleetSize, final long numberOfRides, final long perRideBonus, final long steps) {
        this.rowNumbers = rowNumbers;
        this.numberOfColumns = numberOfColumns;
        this.fleetSize = fleetSize;
        this.numberOfRides = numberOfRides;
        this.perRideBonus = perRideBonus;
        this.steps = steps;
    }

    public long getRowNumbers() {
        return rowNumbers;
    }

    public void setRowNumbers(final long rowNumbers) {
        this.rowNumbers = rowNumbers;
    }

    public long getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(final long numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public long getFleetSize() {
        return fleetSize;
    }

    public void setFleetSize(final long fleetSize) {
        this.fleetSize = fleetSize;
    }

    public long getNumberOfRides() {
        return numberOfRides;
    }

    public void setNumberOfRides(final long numberOfRides) {
        this.numberOfRides = numberOfRides;
    }

    public long getPerRideBonus() {
        return perRideBonus;
    }

    public void setPerRideBonus(final long perRideBonus) {
        this.perRideBonus = perRideBonus;
    }

    public long getSteps() {
        return steps;
    }

    public void setSteps(final long steps) {
        this.steps = steps;
    }

    public List<Rides> getRides() {
        if (rides == null) {
            rides = new ArrayList<>();
        }

        return rides;
    }

    public void setRides(final List<Rides> rides) {
        this.rides = rides;
    }
}
