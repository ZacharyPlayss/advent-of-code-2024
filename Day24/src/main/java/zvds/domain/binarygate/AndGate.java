package zvds.domain.binarygate;

public class AndGate implements GateInterface{

    @Override
    public int compute(int input1, int input2) {
        return input1 & input2;
    }
}
