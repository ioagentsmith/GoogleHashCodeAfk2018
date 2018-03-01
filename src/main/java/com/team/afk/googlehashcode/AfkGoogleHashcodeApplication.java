package com.team.afk.googlehashcode;

import com.team.afk.googlehashcode.models.ProblemStructure;
import com.team.afk.googlehashcode.models.Rides;
import com.team.afk.googlehashcode.simulation.Simulator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@SpringBootApplication
public class AfkGoogleHashcodeApplication {

	public static void main(String[] args) {

		System.out.println("Test");
		
		ProblemStructure problemStructure = readProblemStructure(".\\src\\main\\resources\\a_example.in");
		new Simulator(problemStructure).run();
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
				problemStructure.getRides().add(new Rides(Long.parseLong(ridesArray[0]), Long.parseLong(ridesArray[1]), Long.parseLong(ridesArray[2]),
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

	private static void writeToFile(String filePath) throws IOException {
		Writer fileWriter = null;
		try {
			fileWriter = new FileWriter(filePath);

			fileWriter.write("data 1");
			fileWriter.write("data 2");
			fileWriter.write("data 3");
		}finally {
			fileWriter.close();
		}
	}
}
