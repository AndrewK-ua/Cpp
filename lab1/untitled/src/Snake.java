import java.time.LocalDateTime;
import java.time.Period;

public class Snake extends Animal {
    private double averageLengthInMeters;

    public Snake(DangerLevel dangerLevel, Period livesWithoutFood, Period maxLifeTime,
                 LocalDateTime lastFeedTime, double averageLengthInMeters,
                 String species) {
        super(dangerLevel, livesWithoutFood, maxLifeTime, lastFeedTime, species);
        this.averageLengthInMeters = averageLengthInMeters;
    }

    // There should be complicated logic to calculate,
    // when animal needs to be fed
    @Override
    protected LocalDateTime CalculateNextFeedTime() {
        return LocalDateTime.now();
    }

    public double getAverageLengthInMeters() {
        return averageLengthInMeters;
    }
}
