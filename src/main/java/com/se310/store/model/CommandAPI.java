package com.se310.store.model;

/**
 * Command interface for managing script commands
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public interface CommandAPI {

    /**
     * Method for processing individual CLI commands
     * @param command
     * @throws CommandException
     * @throws StoreException
     */
    void processCommand(String command) throws CommandException, StoreException;

    /**
     * Method for processing grouped CLI commands in a single file
     * @param fileName
     */
    void processCommandFile(String fileName);

}
