package zvds.finder;

import java.util.List;

public interface FinderInterface<T> {
    List<T> find(Object... args);
}
