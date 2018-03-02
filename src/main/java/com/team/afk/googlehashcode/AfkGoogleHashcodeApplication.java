package com.team.afk.googlehashcode;

import com.team.afk.googlehashcode.models.Car;
import com.team.afk.googlehashcode.models.ProblemStructure;
import com.team.afk.googlehashcode.models.Ride;
import com.team.afk.googlehashcode.simulation.Simulator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class AfkGoogleHashcodeApplication {

    public static void main(String[] args) {
      String[] files = new String[]{"a_example.in","b_should_be_easy.in","c_no_hurry.in","d_metropolis.in","e_high_bonus.in"};
      for (String file : files){
         runForFile(file);
      }   
       
   }

    private static void runForFile(String fileName){
          String file = "C:\\Source\\GoogleHashCodeAfk2018\\src\\main\\resources\\" +fileName;
      String fileOut = file.replace(".in",".out");
		ProblemStructure problemStructure = readProblemStructure(file);
		
        List<Car> carList = new Simulator(problemStructure).run();

        try {
            System.out.println("Reading to file -->" + fileOut);
            writeToFile(carList, fileOut);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            System.out.println("Major Malfunction");
        }
    
    }


    private static ProblemStructure readProblemStructure(String filePath) {
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            String sCurrentLine = br.readLine();
            String[] fileFormatArray = sCurrentLine.split(" ");
            ProblemStructure problemStructure = new ProblemStructure(Long.parseLong(fileFormatArray[0]), Long.parseLong(fileFormatArray[1]), Long.parseLong(fileFormatArray[2]),
                    Long.parseLong(fileFormatArray[3]),Long.parseLong(fileFormatArray[4]), Long.parseLong(fileFormatArray[5]));

            for(int i = 0; i < problemStructure.getNumberOfRides(); i++){
                sCurrentLine = br.readLine();
                String[] ridesArray = sCurrentLine.split(" ");
                problemStructure.getRides().add(new Ride(i, Long.parseLong(ridesArray[0]), Long.parseLong(ridesArray[1]), Long.parseLong(ridesArray[2]),
                        Long.parseLong(ridesArray[3]), Long.parseLong(ridesArray[4]), Long.parseLong(ridesArray[5])));
            }
            return problemStructure;
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private static void writeToFile(List<Car> cars, String filePath) throws IOException {
        Writer fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);

            for (int noOfCars = 0; noOfCars < cars.size(); noOfCars++) {
                String carData = "";
                List<Ride> rides = cars.get(noOfCars).getRides();
                carData += rides.size();
                for (int count = 0; count < rides.size(); count++) {
                    carData += " " + rides.get(count).getRideId();
                }
                carData += "\n";
                fileWriter.write(carData);
            }

        }
        finally {
            if (fileWriter!= null){
            fileWriter.close();
            }
            }
    }
}