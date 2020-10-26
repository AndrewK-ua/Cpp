import java.util.ArrayList;

public class Terrarium {
    private ArrayList<Caretaker> caretakers;

    private TerrariumManager terrariumManager;

    public Terrarium(ArrayList<Caretaker> caretakers, TerrariumManager terrariumManager) {
        this.caretakers = caretakers;
        this.terrariumManager = terrariumManager;
    }

    public void setCaretakers(ArrayList<Caretaker> caretakers) {
        this.caretakers = caretakers;
    }

    public ArrayList<Caretaker> getCaretakers() {
        return caretakers;
    }

    public TerrariumManager getTerrariumManager() {
        return terrariumManager;
    }
}
