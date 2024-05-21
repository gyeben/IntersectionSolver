package org.IncQuery;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class IntersectionSolverTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void exampleIntersection() {
        String[] args = {"_", "CL", "CS", "CR"};
        IntersectionSolver.main(args);
        assertEquals("LRB\n", outContent.toString());
    }

    @Test
    void noVehicles() {
        String[] args = {"_", "_", "_", "_"};
        IntersectionSolver.main(args);
        assertEquals("\n", outContent.toString());
    }

    @Test
    void carsFromAllDirections() {
        String[] args = {"CS", "CS", "CS", "CS"};
        IntersectionSolver.main(args);
        assertEquals("TRBL\n", outContent.toString());
    }

    @Test
    void ambulanceHasPriority() {
        String[] args = {"AS", "_", "_", "CS"};
        IntersectionSolver.main(args);
        assertEquals("TL\n", outContent.toString());
    }

    @Test
    void carGivesRightOfWayToCarFromItsRight() {
        String[] args = {"CS", "_", "_", "CS"};
        IntersectionSolver.main(args);
        assertEquals("LT\n", outContent.toString());
    }

    @Test
    void leftTurningCarGivesRightOfWayToCarFromItsRight() {
        String[] args = {"CL", "_", "_", "CS"};
        IntersectionSolver.main(args);
        assertEquals("LT\n", outContent.toString());
    }

    @Test
    void leftTurningCarGivesRightOfWay() {
        String[] args = {"CS", "_", "CL", "_"};
        IntersectionSolver.main(args);
        assertEquals("TB\n", outContent.toString());
    }

    @Test
    void leftTurningCarsArrivingFromOppositeDirections() {
        String[] args = {"_", "CL", "_", "CL"};
        IntersectionSolver.main(args);
        assertEquals("RL\n", outContent.toString());
    }

    @Test
    void invalidVehicleString() {
        String[] args = {"AAA", "_", "_", "_"};
        IntersectionSolver.main(args);
        assertEquals("Failed to parse input: invalid vehicle string\n", outContent.toString());
    }

    @Test
    void invalidNumberOfInputArguments() {
        String[] args = {"CS"};
        IntersectionSolver.main(args);
        assertEquals("Failed to parse input: invalid number of input parameters\n", outContent.toString());
    }

    @Test
    void invalidVehicleType() {
        String[] args = {"XS", "_", "_", "_"};
        IntersectionSolver.main(args);
        assertEquals("Failed to parse input: invalid vehicle type\n", outContent.toString());
    }

    @Test
    void invalidTargetDirection() {
        String[] args = {"CX", "_", "_", "_"};
        IntersectionSolver.main(args);
        assertEquals("Failed to parse input: invalid target direction\n", outContent.toString());
    }
}