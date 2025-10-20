package com.se310.store;

import com.se310.store.model.CommandProcessor;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Driver Class for testing Store Service
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class DriverTest {

    @Test
    public void testDriver() throws URISyntaxException {
        // Process script file
        Path path = Path.of(Objects.requireNonNull(getClass().getResource("/store.script")).toURI());

        CommandProcessor processor = new CommandProcessor();
        assertDoesNotThrow(() -> processor.processCommandFile(path.toString()),
                "Script processing should not throw an exception");
    }
}
