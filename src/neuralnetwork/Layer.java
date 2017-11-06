package neuralnetwork;

import java.util.Random;

public class Layer {
    int inputDimension;
    int outputDimension;
    int biasIndex;
    Neuron neuron;
    double learningRate;
    double[] inputAct;
    double[] outputAct;
    double[] outputSum;
    double[][] weights;
    Random ran;
    
    public Layer(int inputDimension, int outputDimension, Neuron neuron, double initWeightRange, double learningRate) {
        this.inputDimension = inputDimension;
        this.outputDimension = outputDimension;
        this.neuron = neuron;
        this.learningRate = learningRate;
        biasIndex = inputDimension;
        double factor = 1.0;
        factor = 1.0 / Math.sqrt(inputDimension + 1);
        this.learningRate = factor;
        weights = new double[outputDimension][inputDimension+1];
        ran = new Random();
        for (int i = 0; i < outputDimension; i++)
            for (int j = 0; j < inputDimension+1; j++)
                weights[i][j] = ran.nextGaussian() * initWeightRange * factor; 
        inputAct = new double[inputDimension];
        outputAct = new double[outputDimension];
        outputSum = new double[outputDimension];
    }
    
    int getInputDimension() {
        return inputDimension;
    }
    int getOutputDimension() {
        return outputDimension;
    }
    		
    double[] forwardProp(double input[]) {
    	for (int i = 0; i < outputDimension; i++) {
			outputAct[i] = 0.0;
			outputSum[i] = 0.0;
    		for (int j = 0; j < inputDimension; j++) {
    			outputSum[i] += weights[i][j] * input[j];    			
    		}
    		outputSum[i] += weights[i][inputDimension];  // outputSum += weights * 1.0; The bias
    		outputAct[i] = neuron.ActivationFun(outputSum[i]);
    	}
    	return outputAct;
    }
    
    double[] backProp(double[] delta) {
    	double[] deltaAtInput = new double[inputDimension];
    	double[] outputError = new double[outputDimension];
    	for (int i = 0; i < outputDimension; i++) {
    		outputError[i] = delta[i] * neuron.DeriActivation(outputAct[i]);    // doubt
    		for (int j = 0; j < inputDimension; j++) {
    			weights[i][j] += inputAct[j] * outputError[i] * learningRate;   // doubt
    			deltaAtInput[j] += outputError[i] * weights[i][j];
    		}
    		weights[i][biasIndex] = outputError[i] * learningRate;
    	}
    	return deltaAtInput;
    }
    
}


