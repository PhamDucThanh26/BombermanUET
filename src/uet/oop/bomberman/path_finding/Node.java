package uet.oop.bomberman.path_finding;

public class Node {
    // position
    public int col;
    public int row;

    // path finding
    Node parent;

    // cost
    int gCost; // graph cost
    int hCost; // heuristic cost, calculated by Euclidean distance
    int fCost; // final cost = graph + heuristic

    // calculation
    boolean solution;

    public Node(int col, int row) {
        this.col = col;
        this.row = row;
    }

    @Override
    public String toString() {
        return "(" + col + "," + row + ")";
    }
}
