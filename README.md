#  Event-Driven In-Memory Notification System

A **modular** and **extensible** in-memory event-driven system built in **Java** using **Maven** for build and dependency management. This system allows components (users, services, etc.) to:

- **Publish** various types of events  
- **Subscribe** to selected events  
- **Receive notifications** based on custom filters  
- **Schedule** one-time or periodic event dispatching  
- **Log** every notification for future analysis with filtering support  

---

##  System Design

This system serves as a model for real-life notification systems (e.g., Google Calendar).

### Core Components

- **Event Interface**  
  The base interface for all event types (e.g., Task Events, Reminders).

- **Subscriber Interface**  
  Defines how subscribers filter events using `isInterestedIn()` and receive notifications via `notify()`.

- **Publisher**  
  Implemented as a singleton (`EventPublisher`) that manages event dispatching and maintains logs of published events.

- **EventScheduler**  
  Handles both **one-time** and **periodic** event scheduling using background threads.

- **EventFilter Interface**  
  An abstraction for implementing custom event filters using a `matches()` method.
- **HistoryManager**
  Collect history for scheduled events and give access for them

---

## Design Patterns Used

1. **Observer**  
   Used for the Publisher-Subscriber mechanism — subscribers are notified of relevant events.

2. **Factory**  
   Helps construct different event types cleanly by abstracting event creation logic.

3. **Singleton**  
   Ensures a single instance of the `EventPublisher`.

4. **Strategy**  
   Enables dynamic filtering logic for subscribers
5. **SOLID**
   SOLID rules where followed to ensure a clean code Design

---

##  System Flow

1. Events are created with specific types and priorities.  
2. Subscribers register and define filters for events of interest.  
3. Publisher schedules and dispatches events through the `EventScheduler`.  
4. Every dispatched event is logged and saved in event history for later analysis.
5. published events are logged automatically, including Event details and Which subscribers were notified
---

##  How to Run

### Prerequisites

- Java **17+**  
- Maven  

###  Build and Run

```bash
git clone https://github.com/Kareem-izzat/event-notification-system.git
cd event-notification-system
mvn compile
```

## Running Tests

To run the unit tests for the system, use the following Maven command in your terminal:

```bash
mvn test
```

