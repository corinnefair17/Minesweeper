import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * test for MineField
 *
 * @author dconner
 * @version 0
 */

/** Tester class */
public class ReflectionMineSweeperTest {

    // 2 seconds max per test
    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    /** redirect console output */
    private PrintStream console;

    /** buffer for output data */
    private ByteArrayOutputStream buffer;

    /** Set up the console re-direct */
    @Before
    public void redirectOutputToStream() {
        // redirect output to a stream
        console = System.out;
        buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
    }

    /** reset the console output */
    @After
    public void redirectOutputToConsole() {
        // return output to its rightful owner
        System.setOut(console);
    }

    /**
     * test default constructor - this test should pass with given code
     */
    @Test
    public void testConstructor() {
        MineSweeper ms = new MineSweeper();
        assertNotNull("created valid instance", ms);

    }

    @Test
    public void testReflection() {
        Class<?> cls = MineSweeper.class;
        Field[] fields = cls.getDeclaredFields();

        Method[] methods = cls.getDeclaredMethods();
        Class<?>[] params = null;
        Class<?> returnType = null;

        boolean containsCreateMineField = false;
        boolean containsPrintUsage = false;

        for (Method m : methods) {
            // System.out.println(" Method: " + m.getName());
            if (!m.isSynthetic()) {
                switch (m.getName()) {
                    case "createMineField":
                        containsCreateMineField = true;
                        params = new Class<?>[] {};
                        returnType = MineField.class;

                        assertFalse(
                                "Method \"" + m.getName()
                                        + "\" can't be private (per spec)",
                                Modifier.isPrivate(m.getModifiers()));
                        assertTrue(
                                "Method \"" + m.getName()
                                        + "\" must be static",
                                Modifier.isStatic(m.getModifiers()));
                        assertEquals(
                                "Method \"" + m.getName()
                                        + "\" should have only 1 parameter",
                                1, m.getParameterTypes().length);
                        // System.out.println("createMineField: "
                        // + m.getParameterTypes()[0]);
                        assertEquals(
                                "Method \"" + m.getName()
                                        + "\"'s parameters should be String[] args",
                                String[].class, m.getParameterTypes()[0]);
                        assertEquals(
                                "Method \"" + m.getName() + "\" should return "
                                        + returnType.toString(),
                                returnType, m.getReturnType());
                        break;
                    case "printUsage":
                        containsPrintUsage = true;
                        params = new Class<?>[] { MineSweeper.class };
                        returnType = void.class;
                        assertFalse(
                                "Method \"" + m.getName()
                                        + "\" can't be private (per spec)",
                                Modifier.isPrivate(m.getModifiers()));
                        assertTrue(
                                "Method \"" + m.getName()
                                        + "\" must be static",
                                Modifier.isStatic(m.getModifiers()));
                        // System.out.println(
                        // "printUsage: " + m.getParameterTypes());
                        assertEquals(
                                "Method \"" + m.getName()
                                        + "\" should have no parameters",
                                0, m.getParameterTypes().length);
                        assertEquals(
                                "Method \"" + m.getName() + "\" should return "
                                        + returnType.toString(),
                                returnType, m.getReturnType());
                        break;
                }
            }
        }
        assertTrue("Your class did not contain a createMineField method",
                containsCreateMineField);
        assertTrue("Your class did not contain a printUsage method",
                containsPrintUsage);
    }

}
