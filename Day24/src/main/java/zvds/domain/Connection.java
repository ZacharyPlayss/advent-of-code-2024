package zvds.domain;

import zvds.domain.Wire;
import zvds.domain.binarygate.GateInterface;

public class Connection {
    private Wire input1;
    private Wire input2;
    private Wire output;
    private GateInterface gate;
    private boolean hasComputed = false;

    public Connection(Wire input1, Wire input2,Wire output, GateInterface gate) {
        this.input1 = input1;
        this.input2 = input2;
        this.output = output;
        this.gate = gate;
    }

    public boolean canCompute() {
        return !hasComputed && input1.hasValue() && input2.hasValue();
    }

    public boolean runThroughGate() {
        if (canCompute()) {
            int result = gate.compute(input1.getValue(), input2.getValue());
            output.setValue(result);
            hasComputed = true;
            return true;
        }
        return false;
    }

    public void reset() {
        hasComputed = false;
    }

    public Wire getInput1() {
        return input1;
    }

    public Wire getInput2() {
        return input2;
    }

    public Wire getOutput() {
        return output;
    }

    public void setOutput(Wire output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s -> %s", input1.getName(), gate.getClass().getSimpleName(), input2.getName(), output.getName());
    }
}
