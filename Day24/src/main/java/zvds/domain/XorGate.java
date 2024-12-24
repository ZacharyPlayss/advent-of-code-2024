package zvds.domain;

import zvds.domain.binarygate.GateInterface;

public class XorGate implements GateInterface {
    @Override
    public int compute(int input1, int input2) {
        return input1 ^ input2;
    }
}
