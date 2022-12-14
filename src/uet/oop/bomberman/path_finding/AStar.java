package uet.oop.bomberman.path_finding;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.*;

import static uet.oop.bomberman.Game.bomberman;
import static uet.oop.bomberman.Game.stillObjects;

public class AStar {
    // final cost
    public static final int V_H_COST = 10; // Vertical and horizontal g-cost
    public static final int D_COST = 14;    // Pythagoras theorem

    // graph
    Node[][] nodes;
    PriorityQueue<Node> openNodes = new PriorityQueue<>(Comparator.comparingInt((Node n) -> n.fCost));  //checking nodes
    List<Node> closedNodes = new ArrayList<>(); //visited

    Node start, end, current;

    public AStar(int startCol, int startRow, int endCol, int endRow) {
        // graph init
        nodes = new Node[Sprite.maxWorldCol][Sprite.maxWorldRow];

        // heuristic init
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes.length; j++) {
                nodes[i][j] = new Node(i, j);
                nodes[i][j].gCost = 1;
                nodes[i][j].hCost = Math.abs(i - endCol) + Math.abs(j - endRow);    // h cost compared to end node
                nodes[i][j].solution = false;
            }
        }

        start = nodes[startCol][startRow];
        end = nodes[endCol][endRow];

        nodes[startCol][startRow].fCost = 0;

        try {
            bomberman.getBombs().forEach(bomb -> {
                addBlockOnMap((int) bomb.getX() / Sprite.SCALED_SIZE, (int) bomb.getY() / Sprite.SCALED_SIZE);
            });
            for (Entity e : stillObjects) {
                addBlockOnMap((int) e.getX() / Sprite.SCALED_SIZE, (int) e.getY() / Sprite.SCALED_SIZE);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("out of bound");
            System.exit(1);
        }
    }

    public void addBlockOnMap(int i, int j) {
        nodes[i][j].gCost = -1;
    }

    public void updateCost(Node current, Node connectingNode, int gCost) {
        // if connecting node is a still object, or it is checked
        if (connectingNode.gCost == -1 || closedNodes.contains(connectingNode)) {
            return;
        }

        int calculatedFinalCost = connectingNode.hCost + gCost;
        boolean isInOpenNodes = openNodes.contains(connectingNode);

        // if connecting node isn't in open nodes, or the current node lead to it has lower cost
        // then update connecting node
        if (!isInOpenNodes || calculatedFinalCost < connectingNode.fCost) {
            connectingNode.fCost = calculatedFinalCost;
            connectingNode.parent = current;

            // if connecting node is not in open nodes yet
            if (!isInOpenNodes) {
                openNodes.add(connectingNode);
            }
        }
    }

    /**
     * Pseudocode for A-star.
     * ---
     * 1. Let P = starting point.
     * 2. Assign f, g and h values to P. - preprocessing step
     * 3. Add P to the Open list. At this point, P is the only node on the Open list.
     * 4. Let B = the best node from the Open list (i.e. the node that has the lowest f-value).
     * a. If B is the goal node, then quit ??? a path has been found.
     * b. If the Open list is empty, then quit ??? a path cannot be found
     * 5. Let C = a valid node connected to B.
     * a. Assign f, g, and h values to C. - preprocessing step
     * b. Check whether C is on the Open or Closed list.
     * i. If so, check whether the new path is more efficient (i.e. has a
     * lower f-value).
     * 1. If so update the path.
     * ii. Else, add C to the Open list.
     * c. Repeat step 5 for all valid children of B. Add B to Closed list
     * 6. Repeat from step 4.
     */
    public void algorithmProcessing() {
        // add start to open list
        openNodes.add(start);
        current = start;

        while (true) {
            current = openNodes.poll();

            if (current == null) {
                break;
            }

            current.solution = true;
            closedNodes.add(current);

            if (current.equals(end)) {
                return;
            }

            Node surrounding; // surrounding nodes

            // checking bot
            if (current.row + 1 < nodes.length) {
                surrounding = nodes[current.col][current.row + 1];
                updateCost(current, surrounding, current.fCost + V_H_COST);

//                if (current.col - 1 >= 0) {
//                    surrounding = nodes[current.col - 1][current.row + 1];
//                    updateCost(current, surrounding, current.fCost + D_COST);
//                }
//
//                if (current.col + 1 < nodes[0].length) {
//                    surrounding = nodes[current.col + 1][current.row + 1];
//                    updateCost(current, surrounding, current.fCost + D_COST);
//                }
            }

            // checking side
            if (current.col - 1 >= 0) {
                surrounding = nodes[current.col - 1][current.row];
                updateCost(current, surrounding, current.fCost + V_H_COST);
            }

            if (current.col + 1 < nodes[0].length) {
                surrounding = nodes[current.col + 1][current.row];
                updateCost(current, surrounding, current.fCost + V_H_COST);
            }

            // checking top
            if (current.row - 1 >= 0) {
                surrounding = nodes[current.col][current.row - 1];
                updateCost(current, surrounding, current.fCost + V_H_COST);

//                if (current.col - 1 >= 0) {
//                    surrounding = nodes[current.col - 1][current.row - 1];
//                    updateCost(current, surrounding, current.fCost + D_COST);
//                }
//
//                if (current.col + 1 < nodes[0].length) {
//                    surrounding = nodes[current.col + 1][current.row - 1];
//                    updateCost(current, surrounding, current.fCost + D_COST);
//                }
            }
        }
    }

    public void printPath() {
        if (end.solution) {
            Node current = end;
            Stack<Node> path = new Stack<>();
            while (current.parent != null) {
                path.push(current);
                current = current.parent;
            }
            System.out.print(start);
            while (!path.isEmpty()) {
                System.out.print(" -> " + path.peek());
                path.pop();
            }
            System.out.println();
        } else {
            System.out.println("no possible path");
        }
    }

    public List<Node> nodeList() {
        List<Node> result = new ArrayList<>();
        if (end.solution) {
            Node current = end;
            result.add(0, current);
            while (current.parent != start && current.parent != null) {
                current = current.parent;
                result.add(0, current);
            }
        }
        return result;
    }
}
