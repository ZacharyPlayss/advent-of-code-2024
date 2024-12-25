package zvds.domain;

import java.util.List;

public class Lock {
    private final ObjectType type;
    private final List<Integer> value;

    public Lock(ObjectType type, List<Integer> value) {
        this.type = type;
        this.value = value;
    }

    public ObjectType getType() {
        return type;
    }

    public List<Integer> getValue() {
        return value;
    }

    public boolean overlaps(Lock other) {
        List<Integer> otherValue = other.getValue();
        for (int i = 0; i < value.size(); i++) {
            if (value.get(i) + otherValue.get(i) > otherValue.size()) {
                return false;
            }
        }
        return true;
    }
}
