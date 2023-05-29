# Wolf-sheep predation Agent-Based Model (ABM)

This is an agent-based model simulation of the wolf-sheep predation dynamics. The simulation runs in terminal. Written in Java built using Maven.

## Requirements

- Java Development Kit (JDK) 8 or higher
- Apache Maven

## Getting started

1. Clone the repository:
    ```shell
    git clone https://github.com/rikardoricz/wolf-sheep-predation-sim
    ```
2. Navigate to the project directory:
    ```shell
    cd wolf-sheep-predation-sim
    ```
3. Modify `config.properties` file with your editor of choice
    ```shell
    vim src/main/resources/config.properties
    ```
4. Build the project using Maven:
    ```shell
    mvn clean package
    ```
5. Run the simulation:
    ```shell
    java -jar target/wolf-sheep-predation.jar
    ```

## Configuration

You can modify the simulation parameters by editing the `config.properties` file located in the `src/main/resources` directory. The available configuration options include:

- `board.width`: The width of the simulation board.
- `board.height`: The height of the simulation board.
- `simulation.duration.ticks`: The time in ticks that simulation will run.
- `initial.sheep.count`: The initial number of sheep in the simulation.
- `initial.wolf.count`: The initial number of wolves in the simulation.
- `reproduction.probability`: The probability of animals reproducing when they meet another animal of the same species.
- `sheep.grass.energy`: The amount of energy a sheep gains from eating grass.
- `wolf.sheep.energy`: The amount of energy a wolf gains from eating a sheep.
- `grass.regrowth.ticks`: The time it takes for grass to regrow after being eaten.

Feel free to adjust these parameters to observe different dynamics in the simulation.

## License
This project is licensed under the [MIT License](LICENSE). See the `LICENSE` file for more details.
