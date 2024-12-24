package zvds.domain;

public class Wire {
    private String name;
    private Integer value;

    public Wire(String name) {
        this.name = name;
        this.value = null;
    }

    public Wire(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean hasValue() {
        return value != null;
    }

    @Override
    public String toString() {
        return name + ": " + (value == null ? "no value" : value);
    }
}
