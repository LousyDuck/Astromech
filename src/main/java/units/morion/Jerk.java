package units.morion;

import units.distance.DistanceUnit;
import units.exceptions.NotMatchingUnitsException;
import units.generic.Value;
import units.time.TimeUnit;

public class Jerk implements Value {
    private final double value;
    private final DistanceUnit distanceUnit;
    private final TimeUnit firstTimeUnit;
    private final TimeUnit secondTimeUnit;
    private final TimeUnit thirdTimeUnit;

    public Jerk(double value, DistanceUnit distanceUnit, TimeUnit firstTimeUnit, TimeUnit secondTimeUnit, TimeUnit thirdTimeUnit) {
        this.value = value;
        this.distanceUnit = distanceUnit;
        this.firstTimeUnit = firstTimeUnit;
        this.secondTimeUnit = secondTimeUnit;
        this.thirdTimeUnit = thirdTimeUnit;
    }

    public static Jerk milesPerHourCubed(double value) {
        return new Jerk(value, DistanceUnit.MILES, TimeUnit.HOURS, TimeUnit.HOURS, TimeUnit.HOURS);
    }

    public static Jerk kilometersPerHourCubed(double value) {
        return new Jerk(value, DistanceUnit.KILOMETERS, TimeUnit.HOURS, TimeUnit.HOURS, TimeUnit.HOURS);
    }

    public static Jerk metersPerSecondCubed(double value) {
        return new Jerk(value, DistanceUnit.METERS, TimeUnit.SECONDS, TimeUnit.SECONDS, TimeUnit.SECONDS);
    }

    public static Jerk feetPerSecondCubed(double value) {
        return new Jerk(value, DistanceUnit.FEET, TimeUnit.SECONDS, TimeUnit.SECONDS, TimeUnit.SECONDS);
    }

    public static Jerk inchesPerSecondCubed(double value) {
        return new Jerk(value, DistanceUnit.INCHES, TimeUnit.SECONDS, TimeUnit.SECONDS, TimeUnit.SECONDS);
    }

    public static Jerk centimetersPerSecondCubed(double value) {
        return new Jerk(value, DistanceUnit.CENTIMETERS, TimeUnit.SECONDS, TimeUnit.SECONDS, TimeUnit.SECONDS);
    }

    @Override
    public double value() {
        return value;
    }

    public double valueAsMetersPerSecondCubed() {
        return toUnit(DistanceUnit.METERS, TimeUnit.SECONDS, TimeUnit.SECONDS, TimeUnit.SECONDS).value();
    }

    @Override
    public Jerk add(Value other) {
        if (other instanceof Jerk)
            return add((Jerk) other);
        else
            throw new NotMatchingUnitsException();
    }

    public Jerk add(Jerk other) {
        other = other.toUnit(this);
        return new Jerk(value + other.value, distanceUnit, firstTimeUnit, secondTimeUnit, thirdTimeUnit);
    }

    @Override
    public Value sub(Value other) {
        if (other instanceof Jerk)
            return sub((Jerk) other);
        else
            throw new NotMatchingUnitsException();
    }

    public Jerk sub(Jerk other) {
        other = other.toUnit(this);
        return new Jerk(value - other.value, distanceUnit, firstTimeUnit, secondTimeUnit, thirdTimeUnit);
    }

    public double div(Jerk other) {
        other = other.toUnit(this);
        return value()/other.value();
    }

    public Jerk div(double denominator) {
        return new Jerk(value/denominator, distanceUnit, firstTimeUnit, secondTimeUnit, thirdTimeUnit);
    }

    public Jerk mul(double scalar) {
        return new Jerk(value*scalar, distanceUnit, firstTimeUnit, secondTimeUnit, thirdTimeUnit);
    }

    public Jerk toUnit(Jerk other) {
        return toUnit(other.distanceUnit, other.firstTimeUnit, other.secondTimeUnit, other.thirdTimeUnit);
    }

    public Jerk toUnit(DistanceUnit newDistanceUnit, TimeUnit newFirstTimeUnit, TimeUnit newSecondTimeUnit, TimeUnit newThirdTimeUnit) {
        return new Jerk(
                distanceUnit.toUnit(newDistanceUnit,
                        firstTimeUnit.toUnit(newFirstTimeUnit,
                                secondTimeUnit.toUnit(newSecondTimeUnit,
                                        thirdTimeUnit.toUnit(newThirdTimeUnit, value)))), newDistanceUnit, newFirstTimeUnit, newSecondTimeUnit, newThirdTimeUnit);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Jerk && equals((Jerk) o);
    }

    @Override
    public String toString() {
        return String.format("%.4f [%s] per [%s] per [%s] per [%s]", value, distanceUnit.name(), firstTimeUnit.name(), secondTimeUnit.name(), thirdTimeUnit.name());
    }
}
