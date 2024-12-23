package zvds.domain;

public class ComputerConnection {
    Computer computerA;
    Computer computerB;

    public ComputerConnection(Computer computerA, Computer computerB) {
        this.computerA = computerA;
        this.computerB = computerB;
    }

    public Computer getComputerA() {
        return computerA;
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

}
