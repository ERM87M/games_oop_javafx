package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopBlackTest {

    @Test
    void whenCreatedThenPositionCorrectposition() {
        BishopBlack bishopBlack = new BishopBlack(Cell.D4);
        assertThat(bishopBlack.position()).isEqualTo(Cell.D4);
    }

    @Test
    void whenWayFromA1ToC3ThenCorrectPath() {
        BishopBlack bishop = new BishopBlack(Cell.A1);
        Cell[] expected = new Cell[]{Cell.B2, Cell.C3};
        assertThat(bishop.way(Cell.C3)).isEqualTo(expected);
    }

    @Test
    void whenWayNotDiagonalThenException() {
        BishopBlack bishop = new BishopBlack(Cell.A1);
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> bishop.way(Cell.A3)
        );
        assertThat(exception.getMessage()).isEqualTo(String.format(
                "Could not move by diagonal from %s to %s", bishop.position(), Cell.A3)
        );
    }

    @Test
    void whenIsDiagonalTrue() {
        BishopBlack bishop = new BishopBlack(Cell.D4);
        assertThat(bishop.isDiagonal(Cell.D4, Cell.G7)).isTrue();
    }

    @Test
    void whenIsDiagonalFalse() {
        BishopBlack bishop = new BishopBlack(Cell.D4);
        assertThat(bishop.isDiagonal(Cell.D4, Cell.D6)).isFalse();
    }

    @Test
    void whenCopyThenPositionIsNew() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        BishopBlack copied = (BishopBlack) bishop.copy(Cell.G5);
        assertThat(copied.position()).isEqualTo(Cell.G5);
    }
}
