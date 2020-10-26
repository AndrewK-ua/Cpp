import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public abstract class Animal {
    protected String species;

    protected DangerLevel dangerLevel;

    protected Period livesWithoutFood;

    protected Period maxLifeTime;

    protected LocalDateTime lastFeedTime;

    public Animal(DangerLevel dangerLevel, Period livesWithoutFood,
                  Period maxLifeTime, LocalDateTime lastFeedTime,
                  String species) {
        this.dangerLevel = dangerLevel;
        this.livesWithoutFood = livesWithoutFood;
        this.maxLifeTime = maxLifeTime;
        this.lastFeedTime = lastFeedTime;
        this.species = species;
    }

    protected abstract LocalDateTime CalculateNextFeedTime();

    public String getSpecies() {
        return species;
    }

    public DangerLevel getDangerLevel() {
        return dangerLevel;
    }

    public Period getLivesWithoutFood() {
        return livesWithoutFood;
    }

    public Period getMaxLifeTime() {
        return maxLifeTime;
    }

    public LocalDateTime getLastFeedTime() {
        return lastFeedTime;
    }

    public MaxLifeTimeComparator getMaxLifeTimeComparator() {
        return new MaxLifeTimeComparator();
    }

    class NonStaticMaxLifeTimeComparator implements Comparator<Animal> {

        @Override
        public int compare(Animal o1, Animal o2) {
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
        }
    }

    Comparator<Animal> AnonymousMaxLifeTimeComparator = new Comparator<Animal>(){
        @Override
        public int compare(Animal o1, Animal o2) {
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
        }
    };

    public Comparator<Animal> getAnonymousMaxLifeTimeComparator() {
        return AnonymousMaxLifeTimeComparator;
    }

    static class MaxLifeTimeComparator implements Comparator<Animal> {

        @Override
        public int compare(Animal o1, Animal o2) {
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
        }
    }
}
