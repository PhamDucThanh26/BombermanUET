package uet.oop.bomberman.path_finding;

import java.util.ArrayList;
import java.util.List;

public class BFS {
    public List<Node> algorithmProcessing(int startX, int startY, int dangerX, int dangerY, int limit) {
        List<Node> result = new ArrayList<>();
        result.add(new Node(startX, startY));

        return result;
    }
}
