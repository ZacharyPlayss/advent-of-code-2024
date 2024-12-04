package zvds.counter;

import java.util.List;

public class FormationCounter {
    public int countXFormations(List<List<int[]>> results) {
        int count = 0;
        for (int i = 0; i < results.size(); i++) {
            for (int j = i + 1; j < results.size(); j++) {
                List<int[]> path1 = results.get(i);
                List<int[]> path2 = results.get(j);

                if (isXFormation(path1, path2)) {
                    count++;
                }
            }
        }
        return count;
    }

    private  boolean isXFormation(List<int[]> path1, List<int[]> path2) {
        int[] middle1 = path1.get(1);
        int[] middle2 = path2.get(1);

        if (middle1[0] != middle2[0] || middle1[1] != middle2[1]) {
            return false;
        }

        int[] start1 = path1.get(0);
        int[] start2 = path2.get(0);
        int[] end1 = path1.get(2);
        int[] end2 = path2.get(2);

        System.out.println(start1[0] + " " + start2[0] + " " + middle1[0] + " " + middle2[0]);
        System.out.println(start1[1] + " " + start2[1]+ " " + middle1[0] + " " + middle2[0]);

        boolean diagonal1 = Math.abs(start1[0] - end1[0]) == Math.abs(start1[1] - end1[1]);
        boolean diagonal2 = Math.abs(start2[0] - end2[0]) == Math.abs(start2[1] - end2[1]);

        if (!diagonal1 || !diagonal2) {
            return false;
        }
        System.out.println(diagonal1 + " "  + diagonal2);
        if(diagonal1 && diagonal2){
            System.out.println("THIS IS IN X FORMATION!");
        }

        return true;
    }

}
