
public class Test {

	public static String INPUT_FILE = "arff/Enterprise_Application_Profile2.arff";
	public static String OUTPUT_FILE_PERCENTAGE = "output/EnterPrise_Application_Profile2_distribution15.txt";
	public static String OUTPUT_FILE_SUPERVISED_INPUT = "output/Enterprise_Application_Profile2_supervised15.txt";
	public static int NUM_CLUSTERS = 15;
	public static int SKIP_LINES = 49;
		
	public static void main(String[] args) throws Exception {
		
		KMeans.run(INPUT_FILE, OUTPUT_FILE_PERCENTAGE, OUTPUT_FILE_SUPERVISED_INPUT, NUM_CLUSTERS, SKIP_LINES);
		
	}

}