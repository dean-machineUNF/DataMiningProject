import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;

public class winnow {
	
	//10 fold cross-validation parameters
	//Number of folds
	static int numFolds = 10;
	//random number generator for randomization
	static Random random = new Random(1);
	
	//Sets confidence interval to 95%
	static double confidence_interval = 1.96;
	
	static Instances data;
	
	//Location of Data to train/test classifier
	static String file_loc = "C:/Users/Owner/eclipse-workspace/Weka/data/";
	static String file_name = "data.arff";
	
	//Location to save classifier model 
	static String saveModel_Loc = "C:/Users/Owner/eclipse-workspace/Weka/models/";
	static String saveModel_Name = "winnow.model";
	
	static WinnowModel winnowModel = new WinnowModel();
	
	static Classifier current_model;
	
	static boolean run = true;
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Java Implmentation of Balanced Winnow for Twitter Sarcasm Classification");
		System.out.println("Example Training/Test Data consist of 1858 tweets");
		System.out.println("929 are Sarcastic");
		System.out.println("929 are not Sarcastic");
		System.out.println("All Models Trained on 10-fold Cross Validation ");
		System.out.println("Warning need to increase the Java Heap size to atleast 12GB\n");
		
		winnowModel.printParameters();
		
		loadData(file_loc + file_name);
	
		while(run) {
			
			commandLineInterface();
		}
		
	}
	
	public static void commandLineInterface() throws Exception {
		
		double temp = 0;
		
		BufferedReader user_input = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("\n\n");
		System.out.println("Winnow Classification");
		System.out.println("----------------------------------");
		System.out.println(" 1: Train Winnow Classifier 10-fold Cross validation");
		System.out.println(" 2: Set Default Parameters");
		System.out.println(" 3: Set Alpha Parameter");
		System.out.println(" 4: Set Beta Paramter");
		System.out.println(" 5: Use Balanced Version of Winnow");
		System.out.println(" 6: Display Parameter Settings");
		System.out.println(" 7: Tune Alpha/Beta Paramters (Warning: Runtime could be Hours)");
		System.out.println(" 8: Display 1 Row of Data");
		System.out.println(" 9: Build Winnow Classifier");
		System.out.println("10: Save Winnow Classifier");
		System.out.println("11: Change Training/Test Data");
		System.out.println("12: Classify Instance");
		System.out.println("13: Exit Program");
		System.out.println("----------------------------------");
		System.out.println("Please Enter Selection:");

        String input = user_input.readLine();

		int selection = Integer.parseInt(input);
		
		System.out.println("\n\n");
		
		switch (selection) {
		  case 1:
			  
			  loadData(file_loc + file_name);
				
			  Classifier classifier = winnowModel.createWinnowModel();
			  
		      evaluateClassifier(data, classifier);
				
		      break;
		  case 2: 
			  
			  winnowModel.setDefaultParameters();
			  
		      break;
		  case 3:
			  
			  System.out.println("----------------------------------");
			  System.out.println("Enter a floating point number between 1.0-3.0 \nDefault Value = 2.0");
			  System.out.println("----------------------------------");
			  input = user_input.readLine();
			  temp = Double.parseDouble(input);
			  winnowModel.setAlpha(temp);
			  
			  break;
		  case 4: 
			  
			  System.out.println("----------------------------------");
			  System.out.println("Enter a floating point number between 0.01-1.0 \nDefault Value = .5");
			  System.out.println("----------------------------------");
			  input = user_input.readLine();
			  temp = Double.parseDouble(input);
			  winnowModel.setBeta(temp);
			  
		      break;
		  case 5: 
			  
			  System.out.println("----------------------------------");
			  System.out.println("Enter 1 for True or 0 for False \nDefault Value = True");
			  System.out.println("----------------------------------");
			  input = user_input.readLine();
			  selection = Integer.parseInt(input);
			  if(selection == 1) {
				  winnowModel.setBalanced(true);
			  
			  }else if(selection == 0) {
				  winnowModel.setBalanced(false);
			  }else {
				  System.out.println("Incorrect Selection! No Change was Made! Please Try Again");
			  }
			  
		      break;
		  case 6: 
			  
			 winnowModel.printParameters();

		      break;
		  case 7:  
			  
			  loadData(file_loc + file_name);
								
			  tuneParameters(data);
			  
		      break;
		  case 8:
			  
			  System.out.println("----------------------------------");
			  System.out.println("Enter a Integer Value Between 0-"+ data.numInstances());
			  System.out.println("----------------------------------");
			  input = user_input.readLine();
			  selection = Integer.parseInt(input);
			  printInstance(selection);
			  
			  break;
		  case 9:
			  
			  current_model = buildClassifier();
			  
			  break;
		  case 10:
			  
			  saveModel("winnow.mdoel", current_model);
			  
			  break;
		  case 11:
			  
			  System.out.println("----------------------------------");
			  System.out.println("Enter File Name Including Extension");
			  System.out.println("Default = data.arff");
			  System.out.println("----------------------------------");
			  input = user_input.readLine();
			  file_name = input;
			  
			  loadData(file_loc + file_name);
			  
			  break;
		  case 12:
			  
			  System.out.println("----------------------------------");
			  System.out.println("Enter Sentence for Classification");
			  System.out.println("Please leave out punctuations & special characters");
			  System.out.println("----------------------------------");
			  input = user_input.readLine();
			  String user_sentence = input;
			  
			  System.out.println("----------------------------------");
			  System.out.println("Enter 1 if the sentence is Sarcastic");
			  System.out.println("Enter 0 if the sentence is not Sarcastic");
			  System.out.println("----------------------------------");
			  input = user_input.readLine();
			  int user_label = Integer.parseInt(input);
			  
			  String label = "";
			  
			  if(user_label == 1) {
				  label = "TRUE";
			  }else {
				  label = "FALSE";
			  }
			  
			  Instance instance = createInstance(user_sentence, label);
			  
			  double output = classifyInstance(instance);
			  
			  String pred_label = "";
			  
			  if(output == 0) {
				  
				  pred_label = "FALSE";
			  }else if(output == 1) {
				  
				  pred_label = "TRUE";
			  }else {
				  
				  pred_label = "Unable to Classifiy Instance";
			  }
			  
			  System.out.println("\n\nInstance Classified as " + pred_label);
			  System.out.println("Actual Label is " + label);
			  
			  break;
		  case 13: 
			  
			  System.out.println("Exiting Program");
			  
			  run = false;
			  
		      break;
		  default:
			  
			  System.out.println("Incorrect Selection Please Try Agian");
		      
			  break;
		}
		
	}
	
	public static void tuneParameters(Instances data) throws Exception {
		
		double auc = 0;
		double temp_auc = 0;
		double tuned_alpha = 0;
		double tuned_beta = 0;
		
		winnowModel.setAlpha(0);
		winnowModel.setBeta(0);
		
		for(int x = 0; x < 500; x++){
			
			winnowModel.setAlpha(winnowModel.getAlpha() + .01); 
			
			for(int i = 0; i < 100; i++) {
				
				winnowModel.setBeta(winnowModel.getBeta() + .01);
				
				Classifier classifier = winnowModel.createWinnowModel();
				
				Evaluation eval = new Evaluation(data);
				
				eval.crossValidateModel(classifier, data, numFolds, random);
				
				temp_auc = eval.areaUnderROC(1);
				
				if(temp_auc > auc) {
					
					auc = temp_auc;
					
					tuned_alpha = winnowModel.getAlpha();
					
					tuned_beta = winnowModel.getBeta();
				}
			}
			
			winnowModel.setAlpha(tuned_alpha);
			winnowModel.setBeta(tuned_beta);
		}
		
		System.out.println("Tuned Alpha: "+ tuned_alpha + "\nTuned Beta: "+ tuned_beta);
		
	}
	
	public static Instance createInstance(String user_sentence, String label) {
		
		String[] sentence_Array = user_sentence.split("\\s+");
		
		System.out.print("User input = ");
		for(int i = 0; i < sentence_Array.length; i++) {
			System.out.print(sentence_Array[i] +" ");
		}
		
		Instance instance = (Instance) data.instance(1).copy();
		
		for(int x = 0; x < 141; x++) {
			
			if(x < sentence_Array.length) {
				
				instance.setValue(x, sentence_Array[x]);
			}else if(x == 140) {
				
				instance.setValue(x, label);
			}else {
				
				instance.setMissing(x);
			}
		}
		
		return instance;
	}
	
	public static double classifyInstance(Instance instance) throws Exception {
		
		Classifier winnow_cls = (Classifier) weka.core.SerializationHelper.read("C:/Users/Owner/eclipse-workspace/Weka/models/winnow.model");

		double output = winnow_cls.classifyInstance(instance);
				
		return output;
	}
	
	public static void evaluateClassifier(Instances data, Classifier classifier){

		double absError = 0.0;
		
		System.out.println("Evaluating Model Using 10-Fold Cross Validation Please Wait:");
		
		try {
			
			Evaluation eval = new Evaluation(data);
			
			eval.crossValidateModel(classifier, data, numFolds, random);
			
			absError = eval.meanAbsoluteError();
			
			System.out.println(eval.toSummaryString("\nResults\n=====================\n", false));
			
			System.out.println(eval.toMatrixString());
			
			System.out.printf("\n=====================\nAUROC: %.3f \n=====================\n", eval.areaUnderROC(1));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		double interval = confidence_interval * Math.sqrt(absError * (1 - absError) / (data.numInstances()/numFolds));
		double lowerBound = absError - interval;
		double upperBound = absError + interval;
		
		System.out.println(numFolds + "-fold Cross Validation with 95% confidence interval: ");
		System.out.printf("Lowwer Bound [%.3f - %.3f]Upper Bound\n", lowerBound, upperBound);
		
		
	}
	

	
	public static Instances loadData(String filename) throws IOException {
		
			
			BufferedReader reader = new BufferedReader(new FileReader(filename));

			data = new Instances(reader);

			reader.close();

			//setting class attribute
			data.setClassIndex(data.numAttributes() - 1);
			
			return data;
	}
	
	public static void printInstance(int index) {
		
		Instance row = data.instance(index);
		
		System.out.println(row.toString());
	}
	
	public static void saveModel(String saveModel_Name, Classifier classifier) {
		
		try {
			
			weka.core.SerializationHelper.write(saveModel_Loc + saveModel_Name, classifier);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	public static Classifier buildClassifier() throws Exception{
		
		double auc = 0;
		double temp_auc = 0;
		
		Evaluation final_eval = new Evaluation(data);
		
		Classifier final_classifier = winnowModel.createWinnowModel();
		
		data.stratify(numFolds);
		
		for (int n = 0; n < numFolds; n++) {
		    
			Instances train = data.trainCV(numFolds, n);
		    Instances test = data.testCV(numFolds, n);
			
			Classifier classifier = winnowModel.createWinnowModel();
			
			classifier.buildClassifier(train);
			
			Evaluation eval = new Evaluation(test);
			
			eval.evaluateModel(classifier, test);
			
			temp_auc = eval.areaUnderROC(1);
			
			if(temp_auc > auc) {
				
				auc = temp_auc;
				
				final_classifier = classifier;
				
				final_eval = eval;
			
			}
		}
		
		System.out.println(final_eval.toSummaryString("\nResults\n=====================\n", false));
		
		System.out.println(final_eval.toMatrixString());
		
		System.out.printf("\n=====================\nAUROC: %.3f \n=====================\n", final_eval.areaUnderROC(1));
		
		return final_classifier;
	}

}
