import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;


public class KMeans {

	public static BufferedReader readDataFile(String filename) {
		BufferedReader inputReader = null;
 
		try {
			inputReader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException ex) {
			System.err.println("File not found: " + filename);
		}
 
		return inputReader;
	}
	
	public static void writeClusterPercentDistributionToFile(int[] clusterNumericalDistribution,
															 float[] clusterPercentDistribution,
														     String outputFile) throws IOException {
		
		PrintWriter pw = new PrintWriter(outputFile);
		
		for(int i = 0; i < clusterNumericalDistribution.length; ++i) {
			pw.println("Cluster " + i + " : " + clusterNumericalDistribution[i] + "-" + clusterPercentDistribution[i] + "%");
		}
				
		pw.close();
	}
	
	public static void calculateClusterDistribution(int[] assignments, int numClusters, String outputFile) throws IOException {
		
		int[] clusterNumericalDistribution = new int[numClusters];
		float[] clusterPercentDistribution = new float[numClusters];
		
		int totalFlows=0;
		
		for(int clusterNum : assignments) {
		    clusterNumericalDistribution[clusterNum]++;
		    ++totalFlows;
		}
		
		for(int i = 0; i < clusterNumericalDistribution.length; ++i) {
			clusterPercentDistribution[i] = ((float)clusterNumericalDistribution[i] / totalFlows) * 100;
		}
		
		writeClusterPercentDistributionToFile(clusterNumericalDistribution, clusterPercentDistribution, outputFile);
	}
	

	public static void writeToFileSupervisedInput(String fileName, ArrayList<String> results) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(fileName);
					
		for(String line : results) {
			pw.println(line);
		}
		
		pw.close();
	}
	
	public static void rebuildArffFileWithClusterNumber(String inputFile, String outputFile, 
														int[] assignments, 
														int skipCounter) throws IOException {
		
		HashMap myMap = new HashMap<Integer, String>();
        myMap.put(0, "zero");
        myMap.put(1, "one");
        myMap.put(2, "two");
        myMap.put(3, "three");
        myMap.put(4, "four");
        myMap.put(5, "five");
        myMap.put(6, "six");
        myMap.put(7, "seven");
        myMap.put(8, "eight");
        myMap.put(9, "nine");
        myMap.put(10, "ten");
        myMap.put(11, "eleven");
        myMap.put(12, "twelve");
        myMap.put(13, "thirteen");
        myMap.put(14, "fourteen");
		
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		for(int i = 1; i <= skipCounter; ++i) {
			br.readLine();
		}
		
		ArrayList<String> resultsForSupervised = new ArrayList<String>();
		String line = "";
		int i = 0;
		while((line = br.readLine()) != null) {
			resultsForSupervised.add(myMap.get(assignments[i]) + "," + line); 
			i++;
		}
		
		br.close();
		
		writeToFileSupervisedInput(outputFile, resultsForSupervised);
		
	}
	
	public static void run(String inputFile, String outputFileDistribution, String outputFileSupervised,
						  int numClusters, int skipLines) throws Exception {
		
		SimpleKMeans kmeans = new SimpleKMeans();
		 
		kmeans.setPreserveInstancesOrder(true);
		kmeans.setNumClusters(numClusters);
 
		BufferedReader datafile = readDataFile(inputFile); 
		Instances data = new Instances(datafile);
 
		kmeans.buildClusterer(data);
 
		int[] assignments = kmeans.getAssignments();
		
		calculateClusterDistribution(assignments, numClusters, outputFileDistribution);
		rebuildArffFileWithClusterNumber(inputFile, outputFileSupervised, assignments, skipLines);
		
	}

}