@Override
public void simulate(Army playerArmy, Army computerArmy) throws InterruptedException {
    List<Unit> allUnits = new ArrayList<>();
    allUnits.addAll(playerArmy.getUnits());
    allUnits.addAll(computerArmy.getUnits());

    while (playerArmy.hasAliveUnits() && computerArmy.hasAliveUnits()) {
        allUnits.sort(Comparator.comparingInt(Unit::getBaseAttack).reversed());

        for (Unit unit : allUnits) {
            if (unit.isAlive()) {
                Unit target = unit.getProgram().attack();
                if (target != null) {
                    printBattleLog.printBattleLog(unit, target);
                }
            }
        }
    }
}
