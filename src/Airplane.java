public class Airplane {
    int id;
    String name;
    String type;

    public Airplane(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Type: " + type;
    }
}