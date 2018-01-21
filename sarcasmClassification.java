import java.io.BufferedReader;
import java.io.FileReader;

import weka.classifiers.*;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.Winnow;
public class sarcasmClassification {

	public static void main(String[] args) {
		try {
//			// Read the train data file
			BufferedReader trainDataReader = new BufferedReader(new FileReader("src\\data\\twitter_data_latest.arff"));
			Instances twitterTrainingData = new Instances(trainDataReader);
			trainDataReader.close();
			//set the class index
			twitterTrainingData.setClassIndex(twitterTrainingData.numAttributes()-1);
			System.out.println(twitterTrainingData.classIndex());
			//System.out.println(twitterTrainingData.attribute(twitterTrainingData.numAttributes()-1));
			//System.out.println(twitterTrainingData.classAttribute());
			
			//Read the test data file
			BufferedReader testDataReader = new BufferedReader(new FileReader("src\\data\\test_data_2.arff"));
			Instances twitterTestingData = new Instances(testDataReader);
			twitterTestingData.setClassIndex(twitterTrainingData.numAttributes()-1);
				
//			ystem.out.println("File Reading success");
			// Set the options for the classifier
			String[] options = new String[2];
			options[0] = "L";
			options[1] = "3";
			
			Winnow winObj = new Winnow();
			winObj.setOptions(options);
			System.out.println("Options set");
			Object[] a = new Object[10];
			//build the classifier
			winObj.buildClassifier(twitterTrainingData);
			System.out.println("Classifier built");
			//evaluate the test data
			Evaluation eval = new Evaluation(twitterTrainingData);
			eval.evaluateModel(winObj, twitterTestingData, a);
			System.out.println(eval.toSummaryString());
		}
		
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
