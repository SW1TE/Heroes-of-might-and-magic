@Override
public Army generate(List<Unit> unitList, int maxPoints) {
    unitList.sort(Comparator.comparingDouble(unit ->
            (unit.getBaseAttack() / (double) unit.getCost()) +
                    (unit.getHealth() / (double) unit.getCost())).reversed());

    List<Unit> armyUnits = new ArrayList<>();
    Map<String, Integer> unitCount = new HashMap<>();
    int currentPoints = 0;

    for (Unit unit : unitList) {
        if (currentPoints + unit.getCost() <= maxPoints &&
                unitCount.getOrDefault(unit.getUnitType(), 0) < 11) {
            armyUnits.add(new Unit(unit)); // Создаем копию юнита
            unitCount.put(unit.getUnitType(), unitCount.getOrDefault(unit.getUnitType(), 0) + 1);
            currentPoints += unit.getCost();
        }
    }

    return new Army(armyUnits, currentPoints);
}
