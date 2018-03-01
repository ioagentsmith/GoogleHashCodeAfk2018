package com.team.afk.googlehashcode.simulation;

import com.team.afk.googlehashcode.models.ProblemStructure;
import com.team.afk.googlehashcode.models.Rides;

import java.util.List;

public class Simulator {
    ProblemStructure problemStructure;

    public Simulator(final ProblemStructure problemStructure) {
        this.problemStructure = problemStructure;
    }

    public void run() {

        Rides temp;
        long size = problemStructure.getRides().size();

        for (Rides ride : problemStructure.getRides()) {
            for (int x = 0; x < size; x++) {
                for (int y = 1; y < (size - x); y++) {
                    if(problemStructure.getRides().get(y - 1).getEarliestStart() >  problemStructure.getRides().get(y).getEarliestStart()) {
                        temp = problemStructure.getRides().get(y - 1);
                        problemStructure.getRides().set(y - 1, problemStructure.getRides().get(y));
                        problemStructure.getRides().set(y, temp);
                    }
                }
            }
        }

        for (Rides ride : problemStructure.getRides()) {
            System.err.println(ride.getEarliestStart());
        }
    }
}
