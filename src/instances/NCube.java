package instances;

import java.util.ArrayList;
import java.util.HashMap;

public class NCube {
    private final int fullD;
    private final ArrayList<Vertex> vertexes = new ArrayList<>();
    private HashMap<HashMap<Integer, Boolean>, ArrayList<Vertex>> kFaces = new HashMap<>();

    public NCube(String table) {
        fullD = (int) (Math.log(table.length()) / Math.log(2));
        for (var i = 0; i < table.length(); i++) {
            if (table.charAt(i) == '1')
                vertexes.add(new Vertex(i, fullD));
        }
        kFaces.put(null, vertexes);
        calculateDistances();
    }

    public void calculateDistances() {
        for (var vertex: vertexes)
            vertex.calculateDistances(vertexes);
    }

    public int getPhi(int dimension) {
        if (dimension == fullD)
            return getMaxPhiInkFace(0, vertexes, dimension);
        else {
            System.out.println("\tНачинается разделение граней куба большей размерности на грани куба текущей размерности");
            setKFaces();
            System.out.println("\tРазделение граней куба большей размерности произведено успешно");
            var max = 0;
            for (var set: kFaces.values())
                max = getMaxPhiInkFace(max, set, dimension);
            System.out.println("\tГрани куба текущей рамерности успешно обработаны");
            return max;
        }
    }

    public int getMaxPhiInkFace(int currentMax, ArrayList<Vertex> set, int dimension) {
        for (var vertex: set) {
            var phi = vertex.getMinPhi(dimension, set);
            if (phi > currentMax)
                currentMax = phi;
        }
        return currentMax;
    }

    private void setKFaces() {
        var iFaces = new HashMap<HashMap<Integer, Boolean>, ArrayList<Vertex>>();
        for (var key: kFaces.keySet()) {
            for (var i = 0; i < fullD; i++) {
                HashMap<Integer, Boolean> first = (key == null) ? new HashMap<>() : new HashMap<>(key);
                HashMap<Integer, Boolean> second = (key == null) ? new HashMap<>() : new HashMap<>(key);
                if (key == null || !key.containsKey(i)) {
                    first.put(i, true);
                    second.put(i, false);
                    if (iFaces.containsKey(first) && iFaces.containsKey(second))
                        continue;
                    var division = divide(i, kFaces.get(key));
                    if (!iFaces.containsKey(first) && !division.get(0).isEmpty())
                        iFaces.put(first, division.get(0));
                    if (!iFaces.containsKey(second) && !division.get(1).isEmpty())
                        iFaces.put(second, division.get(1));
                }
            }
        }
        kFaces = iFaces;
    }

    private ArrayList<ArrayList<Vertex>> divide(int pos, ArrayList<Vertex> current) {
        var t = new ArrayList<Vertex>();
        var f = new ArrayList<Vertex>();
        for (var vertex: current) {
            if (vertex.getCharAt(pos) == '1')
                t.add(vertex);
            else f.add(vertex);
        }
        var res = new ArrayList<ArrayList<Vertex>>();
        res.add(t);
        res.add(f);
        return res;
    }

    public int getFullD() {
        return fullD;
    }
}
