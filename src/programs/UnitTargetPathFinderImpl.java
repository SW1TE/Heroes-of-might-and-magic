@Override
public List<Edge> getTargetPath(Unit attackUnit, Unit targetUnit, List<Unit> existingUnitList) {
    Set<Edge> obstacles = existingUnitList.stream()
            .filter(unit -> unit.isAlive())
            .map(unit -> new Edge(unit.getX(), unit.getY()))
            .collect(Collectors.toSet());

    PriorityQueue<EdgeDistance> queue = new PriorityQueue<>(Comparator.comparingInt(EdgeDistance::getDistance));
    Map<Edge, Edge> previousNodes = new HashMap<>();
    Set<Edge> visited = new HashSet<>();

    queue.add(new EdgeDistance(attackUnit.getX(), attackUnit.getY(), 0));

    while (!queue.isEmpty()) {
        EdgeDistance current = queue.poll();
        Edge currentEdge = new Edge(current.getX(), current.getY());

        if (visited.contains(currentEdge)) continue;
        visited.add(currentEdge);

        if (currentEdge.getX() == targetUnit.getX() && currentEdge.getY() == targetUnit.getY()) {
            return reconstructPath(previousNodes, currentEdge);
        }

        for (Edge neighbor : getNeighbors(currentEdge, obstacles)) {
            if (!visited.contains(neighbor)) {
                queue.add(new EdgeDistance(neighbor.getX(), neighbor.getY(), current.getDistance() + 1));
                previousNodes.put(neighbor, currentEdge);
            }
        }
    }
    return Collections.emptyList();
}

private List<Edge> getNeighbors(Edge edge, Set<Edge> obstacles) {
    List<Edge> neighbors = new ArrayList<>();
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    for (int[] dir : directions) {
        int newX = edge.getX() + dir[0];
        int newY = edge.getY() + dir[1];

        if (newX >= 0 && newX < 27 && newY >= 0 && newY < 21) { // Учитываем границы поля
            Edge neighbor = new Edge(newX, newY);
            if (!obstacles.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }
    }
    return neighbors;
}

private List<Edge> reconstructPath(Map<Edge, Edge> previousNodes, Edge target) {
    List<Edge> path = new ArrayList<>();
    Edge current = target;

    while (current != null) {
        path.add(0, current);
        current = previousNodes.get(current);
    }
    return path;
}
