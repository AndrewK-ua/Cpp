import java.time.LocalDate;

public class Caretaker {
    private int id;

    private String lastName;

    private String firstName;

    private LocalDate dateHired;

    public Caretaker(int id, String lastName, String firstName, LocalDate dateHired) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateHired = dateHired;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getDateHired() {
        return dateHired;
    }
}
