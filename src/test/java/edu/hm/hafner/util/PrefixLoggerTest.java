package edu.hm.hafner.util;

import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.*;
import static java.util.Collections.*;
import static org.mockito.Mockito.*;

/**
 * Tests the class {@link PrefixLogger}.
 *
 * @author Ullrich Hafner
 */
class PrefixLoggerTest {
    private static final String LOG_MESSAGE = "Hello PrefixLogger!";
    private static final String TOOL_NAME = "test";
    private static final String EXPECTED_TOOL_PREFIX = "[test]";
    private static final String FIRST_MESSAGE = "One";
    private static final String SECOND_MESSAGE = "Two";

}
