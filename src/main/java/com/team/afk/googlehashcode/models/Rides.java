package com.team.afk.googlehashcode.models;

public class Rides {
    long rowStart;
    long columnStart;
    long rowFinish;
    long columnFinish;
    long earliestStart;
    long latestFinish;

    public Rides(final long rowStart, final long columnStart, final long rowFinish, final long columnFinish, final long earliestStart, final long latestFinish) {
        this.rowStart = rowStart;
        this.columnStart = columnStart;
        this.rowFinish = rowFinish;
        this.columnFinish = columnFinish;
        this.earliestStart = earliestStart;
        this.latestFinish = latestFinish;
    }

    public long getRowStart() {
        return rowStart;
    }

    public void setRowStart(final long rowStart) {
        this.rowStart = rowStart;
    }

    public long getColumnStart() {
        return columnStart;
    }

    public void setColumnStart(final long columnStart) {
        this.columnStart = columnStart;
    }

    public long getRowFinish() {
        return rowFinish;
    }

    public void setRowFinish(final long rowFinish) {
        this.rowFinish = rowFinish;
    }

    public long getColumnFinish() {
        return columnFinish;
    }

    public void setColumnFinish(final long columnFinish) {
        this.columnFinish = columnFinish;
    }

    public long getEarliestStart() {
        return earliestStart;
    }

    public void setEarliestStart(final long earliestStart) {
        this.earliestStart = earliestStart;
    }

    public long getLatestFinish() {
        return latestFinish;
    }

    public void setLatestFinish(final long latestFinish) {
        this.latestFinish = latestFinish;
    }
}
