# Pokefight

## About the Project
Pokefight is a Java-based Pokemon battle simulator that allows users to engage in Pokemon battles. The application features a collection of Pokemon with their respective images and abilities.

## Project Structure
- `src/` - Source code
- `fotos/` - Pokemon images (PNG format)
- `gif/` - Pokemon animation files
- `gif-back/` - Back-view Pokemon animation files
- `target/` - Compiled classes and build artifacts

## Requirements
- Java 17 or higher
- Maven 3.6 or higher
- Eclipse IDE (optional, for development)

## Installation

### Clone the repository
```bash
git clone https://github.com/CarlosCamberoR/Pokefight.git
```

### Build with Maven

```bash
mvn clean install
```

### Running the Application
After building the project, you can run it using:

```bash
java -cp target/classes connectNeo
```

Alternatively, if you're using Eclipse:

1. Import the project into Eclipse
2. Right-click on the project
3. Select "Run As" > "Java Application"
4. Select the main class

## Features
- Pokemon battle simulation
- Visual representation of Pokemon using the included image assets
- Battle animations
- Multiple Pokemon to choose from

## License
This project is licensed under CC0 1.0 Universal - see the `LICENSE` file for details.

## Contributing
Contributions are welcome! Please feel free to submit a Pull Request.