import java.time.LocalDateTime;
import java.time.Period;

public class Spider extends Animal{
    private double legSwingInCentimeters;

    public Spider(DangerLevel dangerLevel, Period livesWithoutFood, Period maxLifeTime,
                  LocalDateTime lastFeedTime, double legSwingInCentimeters,
                  String species) {
        super(dangerLevel, livesWithoutFood, maxLifeTime, lastFeedTime, species);
        this.legSwingInCentimeters = legSwingInCentimeters;
    }

    // There should be complicated logic to calculate,
    // when animal needs to be fed
    @Override
    protected LocalDateTime CalculateNextFeedTime() {
        return LocalDateTime.now();
    }

    public double getLegSwingInCentimeters() {
        return legSwingInCentimeters;
    }
}
