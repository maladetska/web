package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {
    private static final int CELLS_SIZE = 3;
    private static final int GOAL_SIZE = CELLS_SIZE;

    private State getState(HttpServletRequest request) {
        return (State) request.getSession().getAttribute("state");
    }

    public void action(HttpServletRequest request, Map<String, Object> view) {
        if (getState(request) == null) {
            request.getSession().setAttribute("state", new State(CELLS_SIZE, GOAL_SIZE));
        }
        view.put("state", getState(request));
    }

    public void onMove(HttpServletRequest request, Map<String, Object> view) {
        State currentState = getState(request);
        if (currentState == null) {
            action(request, view);
        } else {
            view.put("state", currentState);
            String key = request
                    .getParameterMap()
                    .keySet()
                    .stream()
                    .filter(x -> x.matches("cell_\\d{2}"))
                    .findFirst()
                    .orElse(null);
            if (key != null) {
                int row = key.charAt(key.length() - 2) - '0';
                int column = key.charAt(key.length() - 1) - '0';
                if (currentState.validCell(row, column)
                        && currentState.phase == Phase.RUNNING
                        && currentState.cells[row][column] == null) {
                    currentState.move(row, column);
                }
            }
        }
    }

    public void newGame(HttpServletRequest request, Map<String, Object> view) {
        State currentState = getState(request);
        if (currentState != null) {
            currentState.clear();
            view.put("state", currentState);
        } else {
            action(request, view);
        }
    }

    public enum Phase {
        RUNNING,
        WON_X,
        WON_O,
        DRAW
    }

    public enum Symbol {
        X,
        O
    }

    public static class State {
        private final int size;
        private final int goal;
        private Phase phase;
        private int moveNumber;
        private boolean crossesMove;
        private final Symbol[][] cells;

        public State(int size, int goal) {
            this.size = size;
            this.goal = goal;
            this.cells = new Symbol[size][size];
            clear();
        }

        private Symbol getSymbol() {
            return crossesMove ? Symbol.X : Symbol.O;
        }

        public int getSize() {
            return size;
        }

        public Phase getPhase() {
            return phase;
        }

        public Symbol[][] getCells() {
            return cells;
        }

        public boolean isCrossesMove() {
            return crossesMove;
        }

        private boolean validCell(int row, int column) {
            return 0 <= row
                    && row < size
                    && 0 <= column
                    && column < size;
        }

        private void move(int row, int column) {
            cells[row][column] = getSymbol();
            if (checkVictory(row, column)) {
                phase = (getSymbol() == Symbol.X ? Phase.WON_X : Phase.WON_O);
            } else if (moveNumber + 1 == size * size) {
                phase = Phase.DRAW;
            }
            crossesMove = !crossesMove;
            moveNumber++;
        }

        private boolean checkVictory(int row, int column) {
            for (int dRow = -1; dRow < 2; ++dRow) {
                for (int dCol = -1; dCol < 2; ++dCol) {
                    if ((dRow != 0 || dCol != 0)
                            && (countDir(row, column, dRow, dCol)
                            + countDir(row, column, -dRow, -dCol) >= goal + 1)) {
                        return true;
                    }
                }
            }

            return false;
        }

        private int countDir(int row, int column, int dRow, int dCol) {
            int counter = 0;
            while (validCell(row, column) && cells[row][column] == getSymbol()) {
                counter++;
                row += dRow;
                column += dCol;
            }

            return counter;
        }

        private void clear() {
            phase = Phase.RUNNING;
            moveNumber = 0;
            crossesMove = true;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    cells[i][j] = null;
                }
            }
        }
    }
}