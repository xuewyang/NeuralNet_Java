package neuralnetwork;

public class OutputNeuron extends Neuron {
    public double ActivationFun(double x)   {
        return x;
    }
    public double DeriActivation(double x)  {
        return 1;
    }
}
