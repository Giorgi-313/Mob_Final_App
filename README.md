# CarSpotters 

**CarSpotters** is a high-performance Car Specifications Android application designed for automotive enthusiasts. Built with a modern tech stack and a sleek, high-contrast dark theme, it allows users to explore a curated catalog of 121 performance vehicles across different powertrain categories.

## Features

- **121 Car Catalog:** A comprehensive database featuring Gas, Hybrid, and Electric performance vehicles.
- **High-Contrast Dark Theme:** A premium visual experience utilizing Dark/Medium Grey backgrounds with vibrant **Yellow (#FFD369)** accents.
- **Interactive HP Filtering:** A custom-designed Horsepower filter using a unique `Slider` with a tiny car-shaped thumb (`8.dp x 4.dp`) for precise performance filtering.
- **Categorical Browsing:** Efficiently navigate through "Gas", "Hybrid", and "Electric" categories from the main menu.
- **Detailed Specifications:** Deep dives into every car, including engine type, fuel efficiency, transmission, and horsepower.
- **Dynamic Asset Loading:** Uses intelligent resource mapping to load car images dynamically based on the database model.

## Technical Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose (Declarative UI)
- **Architecture:** MVVM (Model-View-ViewModel)
- **Local Database:** Room Database for persistent storage and efficient querying.
- **Asynchronous Flow:** Kotlin Coroutines & StateFlow for reactive UI updates and smooth data handling.
- **Navigation:** Jetpack Compose Navigation for seamless transitions between screens.
- **Theming:** Custom Material 3 integration with Gradient backgrounds.

##  Architecture

The app follows clean architecture principles to ensure scalability and maintainability:

1.  **Data Layer:**
    *   `Car`: Room Entity representing the car data model.
    *   `CarDao`: Interface defining database operations.
    *   `AppDatabase`: The main database entry point (version 2) with destructive migration support.
2.  **Repository Layer:** 
    *   `CarRepository`: Acts as a single source of truth, mediating between the DAO and the ViewModel.
3.  **UI Layer (MVVM):**
    *   `CarViewModel`: Handles state management, horsepower filtering logic, and database pre-population.
    *   **Compose Screens:** Modularized screens (`MainMenu`, `CarList`, `CarDetail`) that react to StateFlow changes.

