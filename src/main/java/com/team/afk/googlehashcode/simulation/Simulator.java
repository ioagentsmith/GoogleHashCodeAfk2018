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
        System.err.println("Fleet size: " + problemStructure.getFleetSize());
        for (int x = 0; x < problemStructure.getFleetSize(); x++) {
            System.err.println("adding car");
             unassignedVehicles.add(new Car(0, 0));
             if(x < problemStructure.getRides().size() && x < unassignedVehicles.size()) {
                 System.err.println("Assigning ride");
                 Car car = unassignedVehicles.remove(x);
                 if (car != null){
                 car.setRide(problemStructure.getRides().remove(0));
                 assignedVehicles.add(car);
                 }
             }
        }

        runSimulation();

    }

    private void runSimulation() {

        for(int i = 0; i < problemStructure.getSteps(); i++) {
            System.err.println("Step " + i);
            for (int x = 0; x < assignedVehicles.size(); x++) {
                //Move car
                System.err.println("Before Moving car " + x + " --> " + assignedVehicles.get(x));
                if (assignedVehicles.get(x)!= null){
                moveCar(assignedVehicles.get(x), x, i);
                }
            }
            //assign new ride
            assignRides();

        }
    }

    private void assignRides() {
        for (int i = 0; i < problemStructure.getRides().size(); i++) {
            //calculating shortest path to next ride
            Car car = findClosestAvailableCarToRide(problemStructure.getRides().get(i));
            unassignedVehicles.remove(car);
            assignedVehicles.add(car);
        }
    }

    private void moveCar(final Car car, final int carIndex, int step) {
        car.move(step);
        if(car.isDone()) {
            unassignedVehicles.add(assignedVehicles.remove(carIndex));
        }
    }

    private Car findClosestAvailableCarToRide(Ride ride) {

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
