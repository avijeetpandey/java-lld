# Java Low-Level Design (LLD) Showcase

This repository contains a collection of low-level design problems implemented in Core Java, focusing on design patterns, data structures, and concurrency.

## Implemented Projects

Below is a list of all the implemented LLD projects.

### Core Concepts & Patterns
*   **SOLID Principles**: [`./src/SOLID`](./src/SOLID)
*   **Design Patterns**: [`./src/design_patterns`](./src/design_patterns)
*   **General Java Concepts**: [`./src/concepts`](./src/concepts)
*   **Java Questions**: [`./src/java_questions`](./src/java_questions)
*   **Producer-Consumer**: [`./src/producer_consumer_multi_threaded`](./src/producer_consumer_multi_threaded)

### System Designs
*   **Book My Show**: [`./src/book_my_show`](./src/book_my_show)
*   **Cache**: [`./src/cache`](./src/cache)
*   **Coupon Engine**: [`./src/coupon_engine`](./src/coupon_engine)
*   **Coupon System**: [`./src/coupon_system`](./src/coupon_system)
*   **Customer Issue Resolution**: [`./src/customer_issue_resolution`](./src/customer_issue_resolution)
*   **Double-Entry Ledger**: [`./src/double_ledger`](./src/double_ledger)
*   **Elevator Management System**: [`./src/elevator`](./src/elevator)
*   **Expense Sharing (Splitwise)**: [`./src/expense_sharing`](./src/expense_sharing)
*   **Expense Policy Rule Engine**: [`./src/expense_policy_rule_engine`](./src/expense_policy_rule_engine)
*   **Flash Sale Order System**: [`./src/flash_sale_order_system`](./src/flash_sale_order_system)
*   **Flight Aggregation System**: [`./src/flight_aggregation_system`](./src/flight_aggregation_system)
*   **In-Memory KV Store**: [`./src/inmemory_kv_store`](./src/inmemory_kv_store)
*   **Inventory System**: [`./src/inventory_system`](./src/inventory_system)
*   **Logger System**: [`./src/logger`](./src/logger)
*   **Parking Lot**: [`./src/parkingLot`](./src/parkingLot)
*   **Product Listing**: [`./src/product_listing`](./src/product_listing)
*   **Rate Limiter**: [`./src/rate_limiter`](./src/rate_limiter)
*   **Splitwise**: [`./src/splitwise`](./src/splitwise)
*   **YouTube-Lite Video Management**: [`./src/youtube_upload`](./src/youtube_upload)

---

## Detailed Explanations

### Elevator Management System

- **Go to Implementation: [`./src/elevator`](./src/elevator)**

This project is a Java-based simulation of an elevator management system. It's designed with scalability and extensibility in mind, leveraging key design patterns to ensure the code is clean, modular, and easy to maintain.

#### Design Patterns Used

- **Strategy Pattern**: The `ElevatorSelectionStrategy` interface allows for interchangeable algorithms for choosing an elevator (e.g., `NearestElevatorStrategy`, `OptimalElevatorStrategy`). This decouples the selection logic from the controller.
- **Command Pattern**: The `Request` class encapsulates all information about a user's request, acting as a command that can be queued and processed.
- **Singleton Pattern (Implicit)**: The `ElevatorController` acts as a central, single point of coordination for all elevator operations, managing the state of all elevators in the system.

#### Folder Structure

```
src/elevator/
├── core/       # The brain of the system
├── model/      # Core data entities
├── strategy/   # Interchangeable algorithms for elevator selection
└── Main.java   # Entry point for the simulation
```

---

### In-Memory "YouTube-Lite" Video Management System

- **Go to Implementation: [`./src/youtube_upload`](./src/youtube_upload)**

This is a high-performance, in-memory video management system built with Core Java (17+). It is designed for high concurrency and thread safety without relying on any external libraries, application servers, or databases.

#### System Architecture

1.  **Core Data Structures**:
    *   **Video Metadata Storage**: A `ConcurrentHashMap<String, Video>` is used as the primary data store for its high-concurrency reads and writes.
    *   **Search Index**: A custom `InvertedIndex` (built on `ConcurrentHashMap`) provides fast, O(1)-like full-text search capabilities on video titles and tags.

2.  **Concurrency Strategy**:
    *   **Read-Heavy Operations**: `getVideo` and `searchVideos` are optimized for speed using non-blocking reads.
    *   **Atomic Write Operations**: A `ReentrantReadWriteLock` is used during deletions to ensure that a video is removed from both the main store and the search index atomically.

3.  **Caching Layer**:
    *   **LRU Cache**: A custom, thread-safe `LRUCache` (built on `LinkedHashMap`) is used to store the results of recent search queries.
    *   **Cache Invalidation**: The entire search cache is cleared whenever a video is uploaded or deleted to prevent stale data.

#### Folder Structure

```
src/youtube_upload/
├── cache/      # Thread-safe, size-limited LRU cache
├── exception/  # Custom exceptions
├── model/      # Immutable record for video metadata
├── search/     # In-memory inverted index for fast search
├── service/    # Core service to manage video operations
└── Main.java   # Entry point and example usage
```
