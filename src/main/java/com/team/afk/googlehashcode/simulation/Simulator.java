package com.team.afk.googlehashcode.simulation;

import com.team.afk.googlehashcode.models.Car;
import com.team.afk.googlehashcode.models.ProblemStructure;
import com.team.afk.googlehashcode.models.Ride;
import sun.misc.Cache;

import java.util.ArrayList;
import java.util.List;

public class Simulator {
    ProblemStructure problemStructure;
    // private ArrayList<Car> unassignedVehicles;
// private ArrayList<Car> assignedVehicles;
    private ArrayList<Car> cars;

    public Simulator(final ProblemStructure problemStructure) {
        this.problemStructure = problemStructure;
    }

    public List<Car> run() {

        Ride temp;
        long size = problemStructure.getRides().size();
           
   //     for (Ride ride : problemStructure.getRides()) { //Unneeded for loop
            for (int x = 0; x < size; x++) {
                
                Long innerSize = (size - x);
                System.out.println("Processing line " + x + " of " +size + " - Inner size " + innerSize);
                for (int y = 1; y < innerSize; y++) {
                    if (problemStructure.getRides().get(y - 1).getEarliestStart() > problemStructure.getRides().get(y).getEarliestStart()) {
                        temp = problemStructure.getRides().get(y - 1);
                        problemStructure.getRides().set(y - 1, problemStructure.getRides().get(y));
                        problemStructure.getRides().set(y, temp);
                    }
                }
            }
      //  }

//print shortest paths
// for (Ride ride : problemStructure.getRides()) {
// System.err.println(ride.getEarliestStart());
// }

// unassignedVehicles = new ArrayList<>();
// assignedVehicles = new ArrayList<>();
        cars = new ArrayList<>();
//initialize vehicles
        System.err.println("Fleet size: " + problemStructure.getFleetSize());
        for (int x = 0; x < problemStructure.getFleetSize(); x++) {
            System.out.println("adding car");
            cars.add(new Car(0, 0));
            if (x < problemStructure.getRides().size()) {
                System.err.println("Assigning ride");

                Car car = cars.get(x);
                car.setRide(problemStructure.getRides().remove(0));

            }
        }

        runSimulation();


        return cars;

    }

    private void runSimulation() {
        System.out.println("Rides: " + problemStructure.getRides().size());

        for (int i = 0; i < problemStructure.getSteps(); i++) {
            System.out.println("Step " + i);
            for (int x = 0; x < cars.size(); x++) {
//Move car
                System.out.println("Step " + i + " of " + problemStructure.getSteps()  + " -  Car " + x + " of " + cars.size() );
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
            if (car != null) {
                car.setRide(problemStructure.getRides().get(i));
            }

        }
    }

    private void moveCar(final Car car, final int carIndex, int step) {

        if (car != null) {
            car.move(step);
        }

    }

    private Car findClosestAvailableCarToRide(Ride ride) {

        Car closest = null;
        Long shortestDistance = -1L;
        for (Car car : cars) {
            if (car.getRide() == null) {
                long distance = car.pathLenghTo(ride.getColumnStart(), ride.getRowStart());
                if (distance < shortestDistance || closest == null) {
                    shortestDistance = distance;
                    closest = car;
                }
            }
        }
        return closest;
    }
}
