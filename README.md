# STIB Itinerary Application

This project implements a Java application using JavaFX to simplify route searching within the STIB metro network. It was developed for an advanced Java course at **Haute Ecole Bruxelles Brabant**.

## Table of Contents

- [Introduction](#introduction)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Project Components](#project-components)
- [Testing](#testing)
- [Contact](#contact)

## Introduction

The STIB Itinerary Application helps users find optimal routes within the STIB metro network. It provides a user-friendly interface built with JavaFX and utilizes SQLite for database management.

## Project Structure

The project is organized into the following main directories and components:

- **StibRide**: Main project directory.
  - **src**: Contains all source code.
    - **main**: Main application code.
      - **java/g55994/stib**: Java packages.
        - **MainApp.java**: Entry point of the application.
        - **model**: Contains core application logic.
          - **ConfigManager.java**: Manages configuration settings.
          - **RepositoryException.java**: Custom exception handling.
        - **dao**: Data Access Objects.
          - **Dao.java**: Base interface for all DAOs.
          - **FavoriteDao.java, LineDao.java, StationDao.java, StopDao.java**: Specific DAOs for different entities.
        - **repository**: Data repositories.
          - **FavoriteRepository.java, LineRepository.java, StationRepository.java, StopRepository.java**: Repository classes for data access.
        - **spp**: Shortest Path Problem algorithms.
          - **Graph.java, Node.java, ShortestPath.java**: Implements shortest path calculations.
        - **presenter**: Presentation logic.
          - **Presenter.java**: Manages UI interactions.
        - **utils**: Utility classes.
          - **Observable.java, Observer.java**: Implement observer pattern.
        - **view**: User interface components.
          - **FxmlController.java, FxmlControllerFavorites.java, View.java**: Controllers and views.
    - **resources**: Non-Java resources.
      - **logo.png, metro.gif**: Application images.
    - **config**: Configuration files.
      - **config.properties**: Application settings.
    - **fxml**: FXML files for UI layout.
      - **Favorite.fxml, StibView.fxml**: FXML definitions.
  - **test**: Contains unit tests.
    - **java/g55994/stib/model/repository**: Repository tests.
      - **LineRepositoryTest.java, StationRepositoryTest.java, StopRepositoryTest.java**: Unit tests for repositories.


## Setup Instructions

### Prerequisites

- Java Development Kit (JDK) 8 or later
- Apache Maven
- SQLite

### Steps to Run the Application

1. Clone the repository:

    ```bash
    git clone <repository-url>
    cd StibRide
    ```

2. Setup the database:
   - Ensure `sqlite3.exe` is available in your system's PATH or use the executable provided in the project.
   - Execute the SQL script to set up the database:

     ```bash
     sqlite3 data/sqlite/stib.db < data/sqlite/stib.sql
     ```

3. Build the project:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    mvn javafx:run
    ```

## Project Components

### Main Application

- **MainApp.java**: Entry point of the application.

### Model

- **ConfigManager.java**: Manages configuration settings.
- **Model.java**: Central model class for the application.
- **RepositoryException.java**: Custom exception for repository operations.

### Data Access Objects (DAO)

- **Dao.java**: Base interface for DAO classes.
- **DBManager.java**: Manages database connections.
- **FavoriteDao.java, LineDao.java, StationDao.java, StopDao.java**: DAO classes for different entities.

### Data Transfer Objects (DTO)

- **Dto.java**: Base class for DTOs.
- **FavoriteDto.java, LineDto.java, StationDto.java, StopDto.java**: DTO classes for different entities.

### Repositories

- **FavoriteRepository.java, LineRepository.java, StationRepository.java, StopRepository.java**: Repository classes for data access.

### Shortest Path Algorithm

- **Graph.java, Node.java, ShortestPath.java**: Implements the shortest path algorithm for route searching.

### Presenter

- **Presenter.java**: Manages the presentation logic.

### Utilities

- **Observable.java, Observer.java**: Utility classes for the observer pattern.

### View

- **FxmlController.java, FxmlControllerFavorites.java, View.java**: Handles the user interface components.

### Resources

- **logo.png, metro.gif**: Image resources for the application.
- **config.properties**: Configuration file.
- **Favorite.fxml, StibView.fxml**: FXML files for the user interface.

## Testing

Unit tests for repository classes are located in `src/test/java/g55994/stib/model/repository`.


