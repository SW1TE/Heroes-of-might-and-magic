@Override
public List<Unit> getSuitableUnits(List<List<Unit>> unitsByRow, boolean isLeftArmyTarget) {
    List<Unit> suitableUnits = new ArrayList<>();

    for (List<Unit> row : unitsByRow) {
        for (Unit unit : row) {
            if (unit != null && unit.isAlive()) {
                if (isLeftArmyTarget) {
                    if (!isBlockedRight(unit, row)) {
                        suitableUnits.add(unit);
                    }
                } else {
                    if (!isBlockedLeft(unit, row)) {
                        suitableUnits.add(unit);
                    }
                }
            }
        }
    }
    return suitableUnits;
}

private boolean isBlockedRight(Unit unit, List<Unit> row) {
    int unitIndex = row.indexOf(unit);
    for (int i = unitIndex + 1; i < row.size(); i++) {
        if (row.get(i) != null && row.get(i).isAlive()) {
            return true;
        }
    }
    return false;
}

private boolean isBlockedLeft(Unit unit, List<Unit> row) {
    int unitIndex = row.indexOf(unit);
    for (int i = unitIndex - 1; i >= 0; i--) {
        if (row.get(i) != null && row.get(i).isAlive()) {
            return true;
        }
    }
    return false;
}
