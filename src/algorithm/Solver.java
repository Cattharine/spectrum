package algorithm;

import instances.NCube;

public class Solver {
    private final NCube cube;

    public Solver(String table) {
        cube = new NCube(table);
    }

    public String solve() {
        var res = new StringBuilder();
        for (var i = cube.getFullD(); i > 0; i--) {
            System.out.printf("Начинается обработка граней куба %d размерности%n", i);
            res.insert(0, cube.getPhi(i));
            res.insert(0,", ");
        }
        res.insert(0, '0');
        return res.toString();
    }
}
