# Store Model System Refactoring

This project refactors the classes in the `com.se310.store.model` package to implement several design patterns that enhance the system's flexibility, maintainability, and security.

## Implemented Design Patterns

### 1. Thread-Safe Double-Checked Locking Singleton
The `StoreService` class has been refactored to use the Thread-Safe Double-Checked Locking Singleton pattern. This ensures:
- Only one instance of the service exists throughout the application
- Lazy initialization (instance created only when needed)
- Thread safety with minimal performance impact using double-checking and volatile keyword

**Implementation Details:**
- The `StoreService` class maintains a private static volatile instance
- The `getInstance()` method uses double-checked locking to ensure thread safety
- The constructor is private to prevent direct instantiation

### 2. Factory Pattern
Two factory classes have been implemented:
- `ProductFactory`: Creates different types of products (standard, discounted, premium)
- `CustomerFactory`: Creates different types of customers (guest, registered)

**Implementation Details:**
- Each factory encapsulates the creation logic for its respective object types
- The `ProductFactory` can create standard products, discounted products (with a discount percentage), and premium products (with a markup percentage)
- The `CustomerFactory` can create general customers, guest customers, and registered customers
- This pattern makes it easier to change how objects are created without affecting client code

### 3. Facade Pattern
The `StoreFacade` class provides a simplified interface to the complex subsystem of store operations. It:
- Hides the complexities of the underlying system
- Provides a higher-level interface for clients
- Coordinates interactions between multiple components

**Implementation Details:**
- The facade maintains references to the `StoreService`, `ProductFactory`, and `CustomerFactory`
- It provides simplified methods like `createStore()`, `createAndRegisterProduct()`, and `createCustomerBasket()`
- These methods coordinate the interactions between different components, hiding the complexity from clients

### 4. Proxy Pattern
The `StoreServiceProxy` class controls access to the `StoreService` by:
- Implementing the same interface as the real service (`StoreAPI`)
- Adding access control before delegating to the real service
- Providing a surrogate or placeholder for the real service

**Implementation Details:**
- The proxy maintains a reference to the real `StoreService` and an access token
- Before delegating any operation to the real service, it checks authorization using the `checkAuthorization()` method
- This adds a security layer without modifying the real service or its clients

### 5. Strategy Pattern
The Strategy pattern is implemented for inventory management:
- `InventoryUpdateStrategy`: Interface defining the contract for different update strategies
- `StandardInventoryUpdateStrategy`: Standard implementation with strict capacity checks
- `FlexibleInventoryUpdateStrategy`: Allows temporary over-capacity

**Implementation Details:**
- The `InventoryUpdateStrategy` interface defines a single method `updateInventory()`
- The `StandardInventoryUpdateStrategy` enforces strict capacity limits, throwing an exception if the update would exceed capacity
- The `FlexibleInventoryUpdateStrategy` allows exceeding capacity by a configurable factor, providing more flexibility when needed
- Clients can switch between strategies at runtime based on their requirements

### 6. Command Pattern
The Command pattern is implemented for processing user commands:
- `CommandAPI`: Interface defining the contract for command processing
- `CommandProcessor`: Processes commands and delegates to the appropriate service methods
- `StoreService`: Also implements CommandAPI to provide command processing capabilities

**Implementation Details:**
- The `CommandAPI` interface defines methods for processing individual commands and command files
- The `CommandProcessor` parses command strings, extracts parameters, and calls the appropriate methods on the `StoreService`
- This pattern decouples the command sender from the command receiver, allowing for more flexibility in how commands are processed
- It enables features like command validation, logging, and error handling in a centralized location

### 7. Observer Pattern
The Observer pattern is implemented for device event monitoring and notification:
- `Observer`: Interface defining the contract for observers
- `Observable`: Interface defining the contract for observable subjects
- `Device`: Abstract class implementing Observable, serving as the base for all IoT devices
- `Sensor` and `Appliance`: Concrete device implementations
- Observer implementations: `EventLogger`, `AlertMonitor`, `DeviceStatistics`, `StoreNotifier`

**Implementation Details:**
- The `Observable` interface defines methods for registering, removing, and notifying observers
- The `Observer` interface defines the `update(String deviceId, String eventType, String message)` method that observers must implement
- The `Device` class maintains a list of registered observers and notifies them when state changes occur
- `Sensor` devices notify observers when events are processed (e.g., temperature readings, motion detection)
- `Appliance` devices notify observers for both events and commands (e.g., door open/close, turn on/off)
- **EventLogger**: Singleton observer that logs all device events with timestamps to the console
- **AlertMonitor**: Singleton observer that monitors for critical events containing keywords like "emergency", "critical", "error", "failure", or "alarm"
- **DeviceStatistics**: Singleton observer that tracks event and command counts per device for analytics
- **StoreNotifier**: Observer that sends notifications to store management about device activities
- Observers can be dynamically registered and removed at runtime, allowing flexible monitoring configurations
- Thread-safe implementation with synchronized update methods to handle concurrent notifications

**Benefits:**
- Decouples devices from monitoring systems - devices don't need to know about specific observers
- Easily extensible - new observers can be added without modifying device code
- Supports multiple monitoring strategies simultaneously (logging, alerting, statistics, etc.)
- Event types distinguish between passive observations ("event") and active controls ("command")

## UML Diagrams
Several PlantUML diagrams have been created in the `docs/architecture` directory to visualize the system:
- **Class Diagram**: Shows the relationships between classes and interfaces, highlighting the design patterns
- **Sequence Diagrams**:
    - **Store Model Sequence**: Illustrates the interactions between components during basket operations
    - **Command Sequence**: Shows how commands are processed by the CommandProcessor
- **Activity Diagrams**:
    - **Inventory Update**: Depicts the flow of inventory update operations
    - **Command Processing**: Shows the flow of command processing operations
- **ER Diagram**: Shows the data relationships between entities
- **Component Diagram**: Provides a high-level view of the system architecture

## Benefits of the Refactoring
- **Improved Thread Safety**: The Singleton pattern ensures thread-safe access to the store service
- **Flexibility**: The Factory and Strategy patterns make it easier to extend the system with new types of products, customers, and inventory management strategies
- **Simplified Interface**: The Facade pattern provides a cleaner API for clients
- **Enhanced Security**: The Proxy pattern adds access control to operations
- **Better Maintainability**: Each pattern encapsulates specific concerns, making the code more modular and easier to maintain
- **Reduced Coupling**: The patterns help reduce dependencies between components, making the system more robust to changes
- **Improved Testability**: The separation of concerns makes it easier to test individual components in isolation
