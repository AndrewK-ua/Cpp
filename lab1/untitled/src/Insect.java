import java.time.LocalDateTime;
import java.time.Period;

public class Insect extends Animal {
    private boolean hasWings;

    public Insect(DangerLevel dangerLevel, Period livesWithoutFood,
                  Period maxLifeTime, LocalDateTime lastFeedTime, boolean hasWings,
                  String species) {
        super(dangerLevel, livesWithoutFood, maxLifeTime, lastFeedTime, species);
        this.hasWings = hasWings;
    }

    // There should be complicated logic to calculate,
    // when animal needs to be fed
    @Override
    protected LocalDateTime CalculateNextFeedTime() {
        return LocalDateTime.now();
    }

    public boolean isHasWings() {
        return hasWings;
    }
}
