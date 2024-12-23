package zvds.domain;

public class ComputerInterconnection {
    Computer computerA;
    Computer computerB;
    Computer computerC;

    public ComputerInterconnection(Computer computerA, Computer computerB, Computer computerC) {
        this.computerA = computerA;
        this.computerB = computerB;
        this.computerC = computerC;
    }

    public Computer getComputerA() {
        return computerA;
    }

    public Computer getComputerC() {
        return computerC;
    }

    public void setComputerC(Computer computerC) {
        this.computerC = computerC;
    }

    public void setComputerA(Computer computerA) {
        this.computerA = computerA;
    }

    public Computer getComputerB() {
        return computerB;
    }

    public void setComputerB(Computer computerB) {
        this.computerB = computerB;
    }
    @Override
    public String toString() {
        return computerA + " - " + computerB + " - " + computerC;
    }
}
