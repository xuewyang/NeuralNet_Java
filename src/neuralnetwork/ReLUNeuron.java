package neuralnetwork;

public class ReLUNeuron extends Neuron {
    private double slope;
    public ReLUNeuron(double slope)     {
            this.slope = slope;
    }
    public double ActivationFun(double x)   {
        if (x >= 0) return x;
        return x*slope;
    }
    public double DeriActivation(double x)  {
        if (x >= 0) return 1;
        return slope;
    }
}
