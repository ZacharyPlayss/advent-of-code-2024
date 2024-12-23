package zvds.sorter;

import zvds.domain.Computer;

import java.util.*;

public class ComputerSorter {
    public List<Computer> sortComputersByName(Set<Computer> computers) {
        List<Computer> sortedComputers = new ArrayList<>(computers);
        sortedComputers.sort(Comparator.comparing(Computer::getName));
        return sortedComputers;
    }
}