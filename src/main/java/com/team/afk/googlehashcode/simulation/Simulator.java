package com.team.afk.googlehashcode.simulation;

import com.team.afk.googlehashcode.models.Car;
import com.team.afk.googlehashcode.models.ProblemStructure;
import com.team.afk.googlehashcode.models.Ride;
import sun.misc.Cache;

import java.util.ArrayList;
import java.util.List;

public class Simulator {
    ProblemStructure problemStructure;
    private ArrayList<Car> unassignedVehicles;
    private ArrayList<Car> assignedVehicles;

    public Simulator(final ProblemStructure problemStructure) {
        this.problemStructure = problemStructure;
    }

    public void run() {

        Ride temp;
        long size = problemStructure.getRides().size();

        for (Ride ride : problemStructure.getRides()) {
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

        //print shortest paths
//        for (Ride ride : problemStructure.getRides()) {
//            System.err.println(ride.getEarliestStart());
//        }

        unassignedVehicles = new ArrayList<>();
        assignedVehicles = new ArrayList<>();

        //initialize vehicles
        for (int x = 0; x < problemStructure.getFleetSize(); x++) {
             unassignedVehicles.add(new Car(0, 0));
             if(x < size) {
                 Car car = unassignedVehicles.remove(x);
                 car.setRide(problemStructure.getRides().remove(x));
                 assignedVehicles.add(car);
             }
        }

        runSimulation();

    }

    private void runSimulation() {

        for(int i = 0; i < problemStructure.getSteps(); i++) {
            for (int x = 0; x < assignedVehicles.size(); x++) {
                Car car = assignedVehicles.get(x);
                car.move(i);
                if(car.isDone()) {
                    unassignedVehicles.add(assignedVehicles.remove(x));
                }
                //assign new ride

                //calculating shortest path to next ride
            }

        }
    }

    private Car findClosestCarToRide(Ride ride) {

        Car closest = null;
        Long shortestDistance = -1L;
        for (Car car : unassignedVehicles ) {
            long distance = car.pathLenghTo(ride.getColumnStart(), ride.getRowStart());
            if(distance < shortestDistance) {
                shortestDistance = distance;
                closest = car;
            }
        }
        return closest;
    }
}
