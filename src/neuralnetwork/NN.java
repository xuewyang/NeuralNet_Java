package neuralnetwork;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import util.Util;

public class NN {
	private List<Layer> layers = new ArrayList<Layer>();
	private Random ran;
	private Layer outputLayer;
	private int inputDimension;
	private int outputDimension;
	private int unitsPerHiddenLayer;
	private Neuron neuron;
	private double initWeightRange;
	private double learningRate;
	
	public NN(int inputDimension, int outputDimension, int unitsPerHiddenLayer, Neuron neuron, double initWeightRange, double learningRate) {
		this.inputDimension = inputDimension;
		this.outputDimension = outputDimension;
		this.unitsPerHiddenLayer = unitsPerHiddenLayer;
		this.neuron = neuron;
		this.initWeightRange = initWeightRange;
		this.learningRate = learningRate;
	}
	
	private int getRealInputDimension() {
		int inputDim = inputDimension;
		for (Layer l : layers) 
			inputDim += l.inputDimension;
		return inputDim;
	}
	
	private void addHiddenLayer() {
		int inputDim = getRealInputDimension();
		Layer layer = new Layer(inputDim, unitsPerHiddenLayer, neuron, initWeightRange, learningRate);
		layers.add(layer);
	}
	
	private void addOutputLayer() {
		int inputDim = getRealInputDimension();
		outputLayer = new Layer(inputDim, outputDimension, new OutputNeuron(), initWeightRange, learningRate);
		layers.add(outputLayer);
	}
	
	private double[] Next(double[] input, double[] targetOutput) {
		double[] extendedInput = Util.Copy(input);
		for (Layer layer : layers)
			extendedInput = Util.ConcatArr(layer.forwardProp(extendedInput), extendedInput);
		double[] output = outputLayer.forwardProp(extendedInput);
		if (targetOutput != null) {
			double[] delta = Util.Delta(targetOutput, output);
			delta = outputLayer.backProp(delta);   // backpropagate outputLayer
			double[] delta2 = Util.ShortenArr(delta, unitsPerHiddenLayer);
			layers.get(layers.size()-1).backProp(delta2);
		}
		return output;
	}
	
	private double[] Next(double[] input) {
		return Next(input, null);
	}
			    
}
