import weka.classifiers.Classifier;
import weka.classifiers.functions.Winnow;

public class WinnowModel {

	//Winnow Parameters
	//Implements balanced version if true
	private static boolean balanced = true;
	//Promotion coefficient alpha
	private static double alpha = 2.00;
	//Demotion coefficient beta
	private static double beta = .5;
	//Prediction threshold (-1 means: set to number of attributes)
	private static double threshold = -1.0;
	//Initial value of weights/coefficients 
	private static double weight = 2.0;
	//The preferred number of instances to process
	private static String batch_size = "100";
	//The number of iterations to be performed
	private static int num_iterations = 1;
	//Random number seed used for data shuffling (-1 means no randomization)
	private static int seed = 1;
	//The number of decimal places to be used for the output of numbers in the model.
	private static int num_decimal = 2;
	//If set to true, classifier may output additional info to the console.
	private static boolean debug = false;
	
	
	public Classifier createWinnowModel() {
		
		Winnow model = new Winnow();
		
		model.setBalanced(balanced);
		model.setAlpha(alpha);
		model.setBeta(beta);
		model.setBatchSize(batch_size);
		model.setDefaultWeight(weight);
		model.setNumIterations(num_iterations);
		model.setSeed(seed);
		model.setNumDecimalPlaces(num_decimal);
		model.setDebug(debug);
		model.setThreshold(threshold);
		
		return model;
		
	}
	
	
	public boolean getDebug() {
		return debug;
	}
	
	public void setDebug(boolean debug) {
		WinnowModel.debug = debug;
	}


	public int getNum_decimal() {
		return num_decimal;
	}

	
	public void setNum_decimal(int num_decimal) {
		WinnowModel.num_decimal = num_decimal;
	}

	public int getNum_iterations() {
		return num_iterations;
	}

	public void setNum_iterations(int num_iterations) {
		WinnowModel.num_iterations = num_iterations;
	}

	public String getBatch_size() {
		return batch_size;
	}

	public void setBatch_size(String batch_size) {
		WinnowModel.batch_size = batch_size;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		WinnowModel.weight = weight;
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		WinnowModel.threshold = threshold;
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		WinnowModel.beta = beta;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		WinnowModel.alpha = alpha;
	}

	public boolean getBalanced() {
		return balanced;
	}

	public void setBalanced(boolean balanced) {
		WinnowModel.balanced = balanced;
	}

	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		WinnowModel.seed = seed;
	}
	
	public void printParameters() {
		
		  System.out.println("Current Winnow Parameters");
		  System.out.println("----------------------------------");
		  System.out.println("Alpha: "+ getAlpha());
		  System.out.println("Beta: "+ getBeta());
		  System.out.println("Balanced: "+ getBalanced());
		  System.out.println("Threshold: "+ getThreshold());
		  System.out.println("Weight: "+ getWeight());
		  System.out.println("Batch Size: "+ getBatch_size());
		  System.out.println("Num Iterations: "+ getNum_iterations());
		  System.out.println("Seed: "+ getSeed());
		  System.out.println("Num Decimal: "+ getNum_decimal());
		  System.out.println("----------------------------------");
	}
	
	public void setDefaultParameters() {
		
		 setAlpha(2.00);
		 setBeta(.5);
		 setBalanced(true);
		 setThreshold(-1.0);
		 setWeight(2.0);
		 setBatch_size("100");
		 setNum_iterations(1);
		 setSeed(1);
		 setNum_decimal(2);		
	}
	
}
