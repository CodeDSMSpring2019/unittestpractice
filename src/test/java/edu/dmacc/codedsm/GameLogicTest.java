package edu.dmacc.codedsm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameLogicTest {

    private GameLogic logic;
    private boolean expectedVictorious = false;
    private boolean expectedDefeated = false;

    private GameRunner runnerStub = new GameRunner() {
        public boolean isVictorious() {
            return expectedVictorious;
        }

        public boolean isDefeated() {
            return expectedDefeated;
        }
    };

    @Before
    public void setUp() throws Exception {
        logic = new GameLogic(runnerStub);
        expectedDefeated = false;
        expectedVictorious = false;
    }

    @Test
    public void shouldCreateResult() {
        GameResult result = logic.buildResult("player", 1000);

        assertEquals("player", result.getPlayer());
        assertEquals((Integer) 1000, result.getScore());
    }

    @Test
    public void shouldScoreZeroIfDidNotPlay() {
        GameResult result = logic.runGame("player");

        assertEquals((Integer) 0, result.getScore());
    }

    @Test
    public void shouldScoreTenThousandIfVictorious() {
        expectedVictorious = true;

        GameResult result = logic.runGame("player");

        assertEquals((Integer) 10000, result.getScore());
    }

    @Test
    public void shouldScoreOneHundredIfDefeated() {
        expectedDefeated = true;

        GameResult result = logic.runGame("player");

        assertEquals((Integer) 100, result.getScore());
    }


}