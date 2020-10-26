import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        TerrariumManager terrariumManager = new TerrariumManager();

        ArrayList<Caretaker> caretakers = new ArrayList<Caretaker>();
        caretakers.add(new Caretaker(1, "Collins",
                "Misha", LocalDate.now()));
        caretakers.add(new Caretaker(2, "Uchiha",
                "Sasuke", LocalDate.now()));

        seedAnimalsToTerrarium(terrariumManager);

        Terrarium terrarium = new Terrarium(caretakers, terrariumManager);

        ArrayList<Animal> animals = terrariumManager.getAnimals();

        printSortingAnimalList("Start array with animals:", animals);

        Animal.MaxLifeTimeComparator animalComparator = new Animal.MaxLifeTimeComparator();
        terrariumManager.sortByMaxLifeTime(true, animalComparator);
        
        printSortingAnimalList("Sort by max life time (static inner class):", animals);

        shuffleAnimalsArray(animals);

        terrariumManager.sortByMaxLifeTime(true, animals.get(0).getMaxLifeTimeComparator());

        printSortingAnimalList("Sort by max life time (inner class):", animals);

        shuffleAnimalsArray(animals);

        terrariumManager.sortByMaxLifeTime(false,
                animals.get(0).getAnonymousMaxLifeTimeComparator());

        printSortingAnimalList("Sort by max life time (anonymous inner class):", animals);

        shuffleAnimalsArray(animals);

        terrariumManager.sortByMaxLifeTime(true, (o1, o2) -> {
            if (o1 == null && o2 == null) {
                return 0;
            }

            if (o1 == null) {
                return -1;
            }

            if (o2 == null) {
                return 1;
            }

            if (o1.maxLifeTime.get(ChronoUnit.DAYS) < o2.maxLifeTime.get(ChronoUnit.DAYS)) {
                return -1;
            }

            if (o1.maxLifeTime.get(ChronoUnit.DAYS) > o2.maxLifeTime.get(ChronoUnit.DAYS)) {
                return 1;
            }

            return 0;
        });

        printSortingAnimalList("Sort by lifetime (lambda):", animals);

        ArrayList<Animal> filteredAnimalsByDangelLevel =
                terrariumManager.findByDangerLevel(DangerLevel.HIGH);

        printAnimalList("Find by high danger level: ", filteredAnimalsByDangelLevel);

        ArrayList<Animal> filteredAnimalsByLivingWithoutFood =
                terrariumManager.findByLivesWithoutFood(Period.ofDays(7));

        printAnimalList("Find by living 7 days without food: ",
                filteredAnimalsByLivingWithoutFood);
    }

    private static void shuffleAnimalsArray(ArrayList<Animal> animals) {
        Collections.shuffle(animals);
        printSortingAnimalList("Array with animals was shuffled:", animals);
    }

    private static void printAnimalList(String message, ArrayList<Animal> animals) {
        System.out.println(message);

        for(Animal animal: animals) {
            System.out.println(animal.species);
        }
    }

    private static void printSortingAnimalList(String message, ArrayList<Animal> animals) {
        System.out.println(message);

        for(Animal animal: animals) {
            System.out.println("Max life time: " + animal.maxLifeTime.get(ChronoUnit.DAYS) +
                    " - " + animal.species);
        }
    }

    private static Spider createSpider(DangerLevel dangerLevel, Period livesWithoutFood,
                                Period maxLifeTime, LocalDateTime lastFeedTime,
                                double legSwingInCentimeters, String species) {
        return new Spider(dangerLevel, livesWithoutFood,
                maxLifeTime, lastFeedTime, legSwingInCentimeters, species);
    }

    private static Snake createSnake(DangerLevel dangerLevel, Period livesWithoutFood,
                                     Period maxLifeTime, LocalDateTime lastFeedTime,
                                     double averageLengthInMeters, String species) {
        return new Snake(dangerLevel, livesWithoutFood,
                maxLifeTime, lastFeedTime, averageLengthInMeters, species);
    }

    private static Insect createInsect(DangerLevel dangerLevel, Period livesWithoutFood,
                                       Period maxLifeTime, LocalDateTime lastFeedTime, boolean hasWings,
                                       String species) {
        return new Insect(dangerLevel, livesWithoutFood,
                maxLifeTime, lastFeedTime, hasWings, species);
    }

    private static void seedAnimalsToTerrarium(TerrariumManager terrariumManager) {
        ArrayList<Animal> terrariumAnimals = terrariumManager
                .getAnimals();

        terrariumAnimals.add(createSpider(DangerLevel.HIGH,
                Period.ofDays(10),
                Period.ofDays(120),
                LocalDateTime.now(),
                15,
                "Karakurt"));

        terrariumAnimals.add(createSpider(DangerLevel.HIGH,
                Period.ofDays(15),
                Period.ofDays(240),
                LocalDateTime.now(),
                25,
                "Tarantula"));

        terrariumAnimals.add(createSpider(DangerLevel.LOW,
                Period.ofDays(7),
                Period.ofDays(360),
                LocalDateTime.now(),
                7,
                "Goliath birdeater"));

        terrariumAnimals.add(createInsect(DangerLevel.VERY_LOW,
                Period.ofDays(18),
                Period.ofDays(720),
                LocalDateTime.now(),
                false,
                "Housefly"));

        terrariumAnimals.add(createInsect(DangerLevel.VERY_LOW,
                Period.ofDays(8),
                Period.ofDays(400),
                LocalDateTime.now(),
                false,
                "Monarch butterfly"));

        terrariumAnimals.add(createInsect(DangerLevel.VERY_LOW,
                Period.ofDays(29),
                Period.ofDays(1080),
                LocalDateTime.now(),
                true,
                "Silverfish"));

        terrariumAnimals.add(createSnake(DangerLevel.VERY_HIGH,
                Period.ofDays(18),
                Period.ofDays(1720),
                LocalDateTime.now(),
                1.9,
                "Copperhead"));

        terrariumAnimals.add(createSnake(DangerLevel.MIDDLE,
                Period.ofDays(8),
                Period.ofDays(2740),
                LocalDateTime.now(),
                10.9,
                "Cottonmouth"));

        terrariumAnimals.add(createSnake(DangerLevel.LOW,
                Period.ofDays(9),
                Period.ofDays(5780),
                LocalDateTime.now(),
                8.4,
                "Cobra"));
    }
}