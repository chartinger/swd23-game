package at.campus02.swd.game.board;

@FunctionalInterface
public interface BudgetObserver {
    void updateBudget(int budget);
}
