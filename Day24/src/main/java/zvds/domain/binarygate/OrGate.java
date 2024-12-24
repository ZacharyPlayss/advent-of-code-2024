package zvds.domain.binarygate;

public class OrGate implements  GateInterface{

    @Override
    public int compute(int input1, int input2) {
        return input1 | input2;
    }
}
