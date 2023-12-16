
public class Day10 {
    static char[][] maze;

    public static void solution() {
        maze = Main.transformInputTo2dArray(Main.getInput("resources/day10_input.txt"));
        part1();
    }

    private static void part1() {
        int[] startCoordinates = findStartCoordinates();
        var crawler = new Crawler(maze, startCoordinates[0], startCoordinates[1]);
        crawler.goLeft();
        while (crawler.getOccupiedPipe() != 'S') {
            crawler.nextStep();
        }
        System.out.println("Day 10 part 1 solution: " + crawler.steps / 2);
    }


    private static int[] findStartCoordinates() {
        var coords = new int[2];
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                if (maze[r][c] == 'S') {
                    coords[0] = r;
                    coords[1] = c;
                    return coords;
                }
            }
        }
        return coords;
    }

    private static class Crawler {
        int row, column, steps;
        char[][] maze;
        Direction direction;

        public Crawler(char[][] maze, int startingRow, int startingColumn) {
            this.maze = maze;
            this.row = startingRow;
            this.column = startingColumn;
            direction = Direction.LEFT;
            steps = 0;
        }

        char getOccupiedPipe() {
            return maze[row][column];
        }

        void nextStep() {
            switch (maze[row][column]) {
                case '-' -> {
                    if (direction == Direction.LEFT) {
                        goLeft();
                    } else {
                        goRight();
                    }
                }
                case '|' -> {
                    if (direction == Direction.UP) {
                        goUp();
                    } else {
                        goDown();
                    }
                }
                case 'L' -> {
                    rotate('L');
                    if (direction == Direction.RIGHT) {
                        goRight();
                    } else {
                        goUp();
                    }
                }
                case 'J' -> {
                    rotate('J');
                    if (direction == Direction.UP) {
                        goUp();
                    } else {
                        goLeft();
                    }
                }
                case '7' -> {
                    rotate('7');
                    if (direction == Direction.DOWN) {
                        goDown();
                    } else {
                        goLeft();
                    }
                }
                case 'F' -> {
                    rotate('F');
                    if (direction == Direction.RIGHT) {
                        goRight();
                    } else {
                        goDown();
                    }
                }
            }
        }

        private void rotate(char rotation) {
            switch (rotation) {
                case 'L' -> direction = direction == Direction.LEFT ? Direction.UP : Direction.RIGHT;
                case 'J' -> direction = direction == Direction.DOWN ? Direction.LEFT : Direction.UP;
                case '7' -> direction = direction == Direction.RIGHT ? Direction.DOWN : Direction.LEFT;
                case 'F' -> direction = direction == Direction.UP ? Direction.RIGHT : Direction.DOWN;
            }
        }

        void goLeft() {
            if (column == 0) {
                return;
            }
            column--;
            steps++;
        }

        void goRight() {
            if (column == maze[row].length - 1) {
                return;
            }
            column++;
            steps++;
        }

        void goUp() {
            if (row == 0) {
                return;
            }
            row--;
            steps++;
        }

        void goDown() {
            if (row == maze.length - 1) {
                return;
            }
            row++;
            steps++;
        }

        enum Direction {
            LEFT, RIGHT, UP, DOWN
        }
    }
}











