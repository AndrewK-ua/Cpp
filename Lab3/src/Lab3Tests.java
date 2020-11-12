import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class Lab3Tests {
    @Test
    void task2() {
        var app = new Lab3();
        assertEquals("HellO WorlD", app.task2("Hello World"));
        assertEquals("MY lifE iS goinG oN", app.task2("My life is going on"));
        assertEquals("WelL, welL, welL, iT iS finE!", app.task2("Well, well, well, it is fine!"));
        assertEquals("I WILL FIND YOU", app.task2("I WILL FIND YOU"));
        assertEquals("HerE iS JohnnY!", app.task2("Here is Johnny!"));
        assertEquals("SO, hoW arE yoU doiN`?)", app.task2("So, how are you doin`?)"));
        assertEquals("I I I I I", app.task2("I I I I I"));
    }
}