package ru.job4j.chess;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.QueenBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void whenFigureNotOnBoardThenFigureNotFoundException() {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(
                FigureNotFoundException.class,
                () -> logic.move(Cell.E1, Cell.E5)
        );
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveThenImpossibleMoveException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.F8));
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            logic.move(Cell.F8, Cell.C6);
        });
        assertThat(exception.getMessage()).isEqualTo(
                String.format("Could not move by diagonal from %s to %s", Cell.F8, Cell.C6)
        );
    }

    @Test
    public void whenDestinationOccupiedThenOccupiedCellException() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.F1));
        logic.add(new QueenBlack(Cell.C4));
        OccupiedCellException exception = assertThrows(
                OccupiedCellException.class,
                () -> logic.move(Cell.F1, Cell.A6)
        );
        assertThat(exception.getMessage()).isEqualTo("Cell is occupied.");
    }
}
