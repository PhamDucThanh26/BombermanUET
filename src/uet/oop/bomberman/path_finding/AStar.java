package uet.oop.bomberman.path_finding;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.PriorityQueue;

import static uet.oop.bomberman.BombermanGame.stillObjects;

public class AStar {
    // final cost
    public static final int V_H_COST = 10;
    public static final int D_COST = 14;    // Pythagoras theorem

    // graph
    Node[][]  nodes;
    PriorityQueue<Node> openNodes = new PriorityQueue<Node>( (Node n1, Node n2) -> {
        return Integer.compare(n1.fCost, n2.fCost);
    });
    ArrayList<Node> pathList = new ArrayList<>();
    boolean[][] closedNodes;

    Node start, end, current;
    boolean reached = false;
    int step = 0;

    public AStar(int width, int height, int startCol, int startRow, int endCol, int endRow, boolean[][] blocks) {
        // graph init
        nodes = new Node[width][height];
        closedNodes = new boolean[width][height];

        start = new Node(startCol, startRow);
        end = new Node(endCol, endRow);

        // heuristic init
        for(int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes.length; j++) {
                nodes[i][j] = new Node(i, j);
                nodes[i][j].hCost = Math.abs(i - endCol) + Math.abs(j - endRow);
                nodes[i][j].solution = false;
            }
        }
        nodes[startCol][startRow].fCost = 0;

        try {
            for(Entity e: stillObjects) {
                addBlockOnMap( (int) e.getX() / Sprite.SCALED_SIZE, (int) e.getY() / Sprite.SCALED_SIZE);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("out of bound");
            System.exit(1);
        } catch (NullPointerException e) {
            System.out.println("???");
            System.exit(1);
        }
    }

    public void addBlockOnMap(int i, int j) {
        nodes[i][j] = null;
    }

    public void setStart(int i, int j) {
        start.col = i;
        start.row = j;
    }

    public void setEnd(int i, int j) {
        start.col = i;
        start.row = j;
    }

    public void updateCost(Node current, Node connectingNode, int gCost) {
        // if connecting nodde is a still object, or it is checked
        if( connectingNode == null || closedNodes[connectingNode.col][connectingNode.col]) {
            return;
        }

        int calculatedFinalCost = connectingNode.hCost + gCost;
        boolean isInOpenNodes = openNodes.contains(connectingNode);

        // if connecting node isn't in open nodes, or the current node lead to it has lower cost
        // then update connecting node
        if(!isInOpenNodes || calculatedFinalCost < connectingNode.fCost) {
            connectingNode.fCost = calculatedFinalCost;
            connectingNode.parent = current;

            // if connecting node is not in open nodes yet
            if(!isInOpenNodes) {
                openNodes.add(connectingNode);
            }
        }
    }

    /**
     * Pseudocode for A-star
     *
     * 1. Let P = starting point.
     * 2. Assign f, g and h values to P. - preprocessing step
     * 3. Add P to the Open list. At this point, P is the only node on the Open list.
     * 4. Let B = the best node from the Open list (i.e. the node that has the lowest fvalue).
     *     a. If B is the goal node, then quit – a path has been found.
     *     b. If the Open list is empty, then quit – a path cannot be found
     * 5. Let C = a valid node connected to B.
     *     a. Assign f, g, and h values to C. - preprocessing step
     *     b. Check whether C is on the Open or Closed list.
     *     i. If so, check whether the new path is more efficient (i.e. has a
     * lower f-value).
     *             1. If so update the path.
     *             ii. Else, add C to the Open list.
     *     c. Repeat step 5 for all valid children of B.
     * 6. Repeat from step 4.
     */
    public void algorithmProcessing() {
        // add start to open list
        openNodes.add(start);
        current = start;

        while(true) {
            current = openNodes.poll();

            if(current == null) {
                break;
            }

            if(current.equals(end)) {
                return;
            }

            Node surrounding; // surrounding nodes

            // checking bot
            if(current.row + 1 < nodes.length) {
                surrounding = nodes[current.col][current.row + 1];
                updateCost(current, surrounding, current.fCost + V_H_COST);

                if(current.col - 1 >= 0) {
                    surrounding = nodes[current.col - 1][current.row + 1];
                    updateCost(current, surrounding, current.fCost + D_COST);
                }

                if(current.col + 1 < nodes[0].length) {
                    surrounding = nodes[current.col - 1][current.row + 1];
                    updateCost(current, surrounding, current.fCost + D_COST);
                }
            }

            // checking side
            if(current.col - 1 >= 0) {
                surrounding = nodes[current.col - 1][current.row];
                updateCost(current, surrounding, current.fCost + V_H_COST);
            }

            if(current.col + 1 < nodes[0].length) {
                surrounding = nodes[current.col + 1][current.row];
                updateCost(current, surrounding, current.fCost + V_H_COST);
            }

            // checking top
            if(current.row - 1 >= 0) {
                surrounding = nodes[current.col][current.row - 1];
                updateCost(current, surrounding, current.fCost + V_H_COST);

                if(current.col - 1 >= 0) {
                    surrounding = nodes[current.col - 1][current.row - 1];
                    updateCost(current, surrounding, current.fCost + D_COST);
                }

                if(current.col + 1 < nodes[0].length) {
                    surrounding = nodes[current.col - 1][current.row - 1];
                    updateCost(current, surrounding, current.fCost + D_COST);
                }
            }
        }
    }

    public void display() {

    }
}
