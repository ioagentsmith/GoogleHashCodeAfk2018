package com.team.afk.googlehashcode.simulation;

import com.team.afk.googlehashcode.models.Car;
import com.team.afk.googlehashcode.models.ProblemStructure;
import com.team.afk.googlehashcode.models.Ride;
import sun.misc.Cache;

import java.util.ArrayList;
import java.util.List;

public class Simulator {
    ProblemStructure problemStructure;
//    private ArrayList<Car> unassignedVehicles;
//    private ArrayList<Car> assignedVehicles;
    private ArrayList<Car> cars;

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

//        unassignedVehicles = new ArrayList<>();
//        assignedVehicles = new ArrayList<>();
        cars = new ArrayList<>();
        //initialize vehicles
        System.err.println("Fleet size: " + problemStructure.getFleetSize());
        for (int x = 0; x < problemStructure.getFleetSize(); x++) {
            System.err.println("adding car");
            cars.add(new Car(0, 0));
             if(x < problemStructure.getRides().size()) {
                 System.err.println("Assigning ride");
//                 Car car = unassignedVehicles.remove(0);
                 Car car = cars.get(x);
                 car.setRide(problemStructure.getRides().remove(0));
//                 assignedVehicles.add(car);
             }
        }

        runSimulation();

    }

    private void runSimulation() {

//        System.err.println("Assigned: " + assignedVehicles.size());
//        System.err.println("Unassigned: " + unassignedVehicles.size());
        System.err.println("Rides: " + problemStructure.getRides().size());

        for(int i = 0; i < problemStructure.getSteps(); i++) {
            System.err.println("Step " + i);
            for (int x = 0; x < cars.size(); x++) {
                //Move car
                moveCar(cars.get(x), x, i);
            }
            //assign new ride
            assignRides();

        }
    }

    private void assignRides() {
        for (int i = 0; i < problemStructure.getRides().size(); i++) {
            //calculating shortest path to next ride
            Car car = findClosestAvailableCarToRide(problemStructure.getRides().get(i));
            if(car != null) {
                car.setRide(problemStructure.getRides().get(i));
            }

//            unassignedVehicles.remove(car);
//            cars.add(car);
        }
    }

    private void moveCar(final Car car, final int carIndex, int step) {

            if(car != null) {
                car.move(step);
            }

    }

    private Car findClosestAvailableCarToRide(Ride ride) {

        Car closest = null;
        Long shortestDistance = -1L;
        for (Car car : cars ) {
            if(car.getRide() == null) {
                long distance = car.pathLenghTo(ride.getColumnStart(), ride.getRowStart());
                if(distance < shortestDistance) {
                    shortestDistance = distance;
                    closest = car;
                }
            }
        }
        return closest;
    }
}
