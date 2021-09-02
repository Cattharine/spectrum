package instances;

import java.util.ArrayList;
import java.util.HashMap;

public class Vertex {
    private final String binary;
    private final int fullD;
    private final HashMap<Vertex, Integer> distances = new HashMap<>();

    public Vertex(int pos, int fullD) {
        this.binary = Integer.toBinaryString(pos);
        this.fullD = fullD;
    }

    public void calculateDistances(ArrayList<Vertex> vertexes) {
        for (var vertex : vertexes) {
            if (vertex != this) {
                var dist = 0;
                if (vertex.distances.isEmpty()) {
                    dist = getDistance(vertex.getBinary());
                }
                else dist = vertex.distances.get(this);
                this.distances.put(vertex, dist);
            }
        }
    }

    public int getMinPhi(int currentDimension, ArrayList<Vertex> vertexes) {
        var min = currentDimension + 1;
        for (var vertex : vertexes) {
            if (vertex == this)
                continue;
            if (this.distances.get(vertex) < min)
                min = this.distances.get(vertex);
        }
        return min - 1;
    }

    private int getDistance(String other) {
        var dist = 0;
        for (var i = 0; i < fullD; i++) {
            var first = (binary.length() - i - 1 < 0) ? '0' : binary.charAt(binary.length() - i - 1);
            var second = (other.length() - i - 1 < 0) ? '0' : other.charAt(other.length() - i - 1);
            if (first != second)
                dist++;
        }
        return dist;
    }

    public String getBinary() {
        return binary;
    }

    public char getCharAt(int pos) {
        return (binary.length() - pos - 1 < 0) ? '0' : binary.charAt(binary.length() - pos - 1);
    }

    @Override
    public String toString() {
        return binary;
    }
}