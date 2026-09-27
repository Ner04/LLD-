import java.util.*;

enum Direction {
    UP,
    DOWN,
    IDLE
}



class Request {

    private int floor;
    private Direction direction;

    public Request(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
    }

    public int getFloor() {
        return floor;
    }

    public Direction getDirection() {
        return direction;
    }
}


class Elevator {

    private int id;
    private int currentFloor;
    private Direction direction;

    public Elevator(int id, int currentFloor) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.direction = Direction.IDLE;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getId() {
        return id;
    }

    public Direction getDirection() {
        return direction;
    }

    public void moveTo(int destinationFloor) {

        System.out.println(
            "Elevator " + id +
            " moving from floor " +
            currentFloor +
            " to floor " +
            destinationFloor
        );

        while (currentFloor != destinationFloor) {

            if (destinationFloor > currentFloor) {

                currentFloor++;
                direction = Direction.UP;

            } else {

                currentFloor--;
                direction = Direction.DOWN;
            }

            System.out.println(
                "Elevator " + id +
                " reached floor " +
                currentFloor
            );
        }

        direction = Direction.IDLE;

        System.out.println(
            "Elevator " + id +
            " is now IDLE at floor " +
            currentFloor
        );
    }
}



interface ElevatorSelectionStrategy {

    Elevator selectElevator(
        List<Elevator> elevators,
        Request request
    );
}



class NearestElevatorStrategy
        implements ElevatorSelectionStrategy {

    @Override
    public Elevator selectElevator(
            List<Elevator> elevators,
            Request request) {

        Elevator bestElevator = null;

        int minimumDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {

            int distance =
                Math.abs(
                    elevator.getCurrentFloor()
                    - request.getFloor()
                );

            if (distance < minimumDistance) {

                minimumDistance = distance;
                bestElevator = elevator;
            }
        }

        return bestElevator;
    }
}



class ElevatorSystem {

    private List<Elevator> elevators;
    private ElevatorSelectionStrategy strategy;

    public ElevatorSystem(
            List<Elevator> elevators,
            ElevatorSelectionStrategy strategy) {

        this.elevators = elevators;
        this.strategy = strategy;
    }

    public void requestElevator(Request request) {

        Elevator elevator =
            strategy.selectElevator(
                elevators,
                request
            );

        System.out.println(
            "Selected Elevator: " +
            elevator.getId()
        );

        elevator.moveTo(request.getFloor());
    }
}



public class ElevatorSystemMainLLD {

    public static void main(String[] args) {

        Elevator elevator1 =
            new Elevator(1, 1);

        Elevator elevator2 =
            new Elevator(2, 5);

        Elevator elevator3 =
            new Elevator(3, 10);


        List<Elevator> elevators =
            Arrays.asList(
                elevator1,
                elevator2,
                elevator3
            );


        ElevatorSelectionStrategy strategy =
            new NearestElevatorStrategy();


        ElevatorSystem system =
            new ElevatorSystem(
                elevators,
                strategy
            );


        Request request =
            new Request(
                9 ,
                Direction.UP
            );


        system.requestElevator(request);
    }
}