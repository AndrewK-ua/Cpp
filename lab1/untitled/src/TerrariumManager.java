import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TerrariumManager {
    private ArrayList<Animal> animals;

    public TerrariumManager() {
        animals = new ArrayList<Animal>();
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public ArrayList<Animal> findByDangerLevel(DangerLevel dangerLevel) {
        ArrayList<Animal> filteredAnimals = new ArrayList<>();

        for(Animal animal : animals) {
            if(animal.dangerLevel == dangerLevel) {
                filteredAnimals.add(animal);
            }
        }

        return filteredAnimals;
    }

    public ArrayList<Animal> findByLivesWithoutFood(Period period) {
        ArrayList<Animal> filteredAnimals = new ArrayList<>();

        for(Animal animal : animals) {
            if(animal.livesWithoutFood.getDays() == period.getDays()) {
                filteredAnimals.add(animal);
            }
        }

        return filteredAnimals;
    }

    public void sortByMaxLifeTime(boolean descending,
                                  Comparator<Animal> animalComparator) {

        Collections.sort(animals, animalComparator);

        if(descending == true) {
            Collections.reverse(animals);
        }
    }
}
