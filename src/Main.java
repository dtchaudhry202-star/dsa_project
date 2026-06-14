import java.io.FileWriter;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static Queue<Airplane> landingQueue = new LinkedList<>();
    static Queue<Airplane> takeoffQueue = new LinkedList<>();
    static ArrayList<Airplane> history = new ArrayList<>();
    static Stack<Airplane> undoStack = new Stack<>();

    public static void main(String[] args) {


        int choice = 0;

        while (choice != 9) {

            System.out.println("\nAirport Runway Traffic Controller\n");
            System.out.println("1. Add Landing Request");
            System.out.println("2. Add Takeoff Request");
            System.out.println("3. Process Landing");
            System.out.println("4. Process Takeoff");
            System.out.println("5. Display Waiting Airplanes");
            System.out.println("6. Emergency Landing");
            System.out.println("7. Display Flight History");
            System.out.println("8. Undo Last Operation");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addLanding();
                    break;

                case 2:
                    addTakeoff();
                    break;

                case 3:
                    processLanding();
                    break;

                case 4:
                    processTakeoff();
                    break;

                case 5:
                    displayWaiting();
                    break;

                case 6:
                    emergencyLanding();
                    break;

                case 7:
                    displayHistory();
                    break;

                case 8:
                    undoLast();
                    break;

                case 9:
                    System.out.println("Program ended.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static Airplane inputPlane(String type) {
        System.out.print("Enter airplane ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter airplane name: ");
        String name = sc.nextLine();

        return new Airplane(id, name, type);
    }

    static void addLanding() {
        Airplane p = inputPlane("Landing");
        landingQueue.add(p);
        saveLog("Landing request added: " + p);
        System.out.println("Landing request added.");
    }

    static void addTakeoff() {
        Airplane p = inputPlane("Takeoff");
        takeoffQueue.add(p);
        saveLog("take off request added: " + p);
        System.out.println("Takeoff request added.");
    }

    static void processLanding() {
        if (landingQueue.isEmpty()) {
            System.out.println("No airplane waiting for landing.");
        } else {
            Airplane p = landingQueue.poll();
            history.add(p);
            undoStack.push(p);
            saveLog("Landing processed: " + p);
            System.out.println("Landing processed: " + p);
        }
    }

    static void processTakeoff() {
        if (takeoffQueue.isEmpty()) {
            System.out.println("No airplane waiting for takeoff.");
        } else {
            Airplane p = takeoffQueue.poll();
            history.add(p);
            undoStack.push(p);
            saveLog("Takeoff processed: " + p);
            System.out.println("Takeoff processed: " + p);
        }
    }

    static void displayWaiting() {
        System.out.println("\nLanding Queue:");
        if (landingQueue.isEmpty()) {
            System.out.println("No landing requests.");
        } else {
            for (Airplane p : landingQueue) {
                System.out.println(p);
            }
        }

        System.out.println("\nTakeoff Queue:");
        if (takeoffQueue.isEmpty()) {
            System.out.println("No takeoff requests.");
        } else {
            for (Airplane p : takeoffQueue) {
                System.out.println(p);
            }
        }
    }

    static void emergencyLanding() {
        Airplane p = inputPlane("Emergency Landing");
        history.add(p);
        undoStack.push(p);
        saveLog("Emergency landing processed: " + p);
        System.out.println("Emergency landing processed immediately: " + p);
    }

    static void displayHistory() {
        if (history.isEmpty()) {
            System.out.println("No flight history available.");
        } else {
            System.out.println("\nFlight History:");
            for (Airplane p : history) {
                System.out.println(p);
            }
        }
    }

    static void undoLast() {
        if (undoStack.isEmpty()) {
            System.out.println("No operation to undo.");
        } else {
            Airplane p = undoStack.pop();
            history.remove(p);
            saveLog("Undo operation: " + p);
            System.out.println("Last operation removed from history: " + p);
        }
    }
    static void saveLog(String text) {
        try {
            FileWriter fw = new FileWriter("activity.txt", true);
            fw.write(text + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("File error.");
        }
    }
}
