package github.dandeduck.units.morion;

import github.dandeduck.units.distance.DistanceUnit;
import github.dandeduck.units.NotMatchingUnitsException;
import github.dandeduck.units.Value;
import github.dandeduck.units.time.Time;
import github.dandeduck.units.time.TimeUnit;

public class Velocity implements Value {
    private final double value;
    private final DistanceUnit distanceUnit;
    private final TimeUnit timeUnit;

    public Velocity(double value, DistanceUnit distanceUnit, TimeUnit timeUnit) {
        this.value = value;
        this.distanceUnit = distanceUnit;
        this.timeUnit = timeUnit;
    }

    public static Velocity milesPerHour(double value) {
        return new Velocity(value, DistanceUnit.MILES, TimeUnit.HOURS);
    }

    public static Velocity kilometersPerHour(double value) {
        return new Velocity(value, DistanceUnit.KILOMETERS, TimeUnit.HOURS);
    }

    public static Velocity metersPerSecond(double value) {
        return new Velocity(value, DistanceUnit.METERS, TimeUnit.SECONDS);
    }

    public static Velocity feetPerSecond(double value) {
        return new Velocity(value, DistanceUnit.FEET, TimeUnit.SECONDS);
    }

    public static Velocity inchesPerSecond(double value) {
        return new Velocity(value, DistanceUnit.INCHES, TimeUnit.SECONDS);
    }

    public static Velocity centimetersPerSecond(double value) {
        return new Velocity(value, DistanceUnit.CENTIMETERS, TimeUnit.SECONDS);
    }

    @Override
    public double value() {
        return value;
    }

    @Override
    public Velocity add(Value other) {
        if (other instanceof Velocity)
            return add((Velocity) other);
        else
            throw new NotMatchingUnitsException();
    }

    @Override
    public Velocity sub(Value other) {
        if (other instanceof Velocity)
            return sub((Velocity) other);
        else
            throw new NotMatchingUnitsException();
    }

    public Velocity add(Velocity other) {
        other = other.toUnit(this);
        return new Velocity(value + other.value(), distanceUnit, timeUnit);
    }

    public Velocity sub(Velocity other) {
        other = other.toUnit(this);
        return new Velocity(value - other.value(), distanceUnit, timeUnit);
    }

    public double div(Velocity other) {
        other = other.toUnit(this);
        return value()/other.value();
    }

    public double valueAsMetersPerSecond() {
        return toUnit(DistanceUnit.METERS, TimeUnit.SECONDS).value();
    }

    public DistanceUnit distanceUnit() {
        return distanceUnit;
    }

    public TimeUnit timeUnit() {
        return timeUnit;
    }

    public Time div(Acceleration acceleration) {
        return Time.seconds(valueAsMetersPerSecond()/acceleration.valueAsMetersPerSecondSquared());
    }

    public Velocity div(double denominator) {
        return new Velocity(value/denominator, distanceUnit, timeUnit);
    }

    public Acceleration mul(Time time) {
        return Acceleration.metersPerSecondSquared(valueAsMetersPerSecond() * time.valueAsSeconds());
    }

    public Jerk mulSquared(Time time) {
        return mul(time, time);
    }

    public Jerk mul(Time firstTime, Time secondTime) {
        return Jerk.metersPerSecondCubed(valueAsMetersPerSecond() * firstTime.valueAsSeconds() * secondTime.valueAsSeconds());
    }

    public Velocity mul(double scalar) {
        return new Velocity(value*scalar, distanceUnit, timeUnit);
    }

    public Velocity toUnit(Velocity velocity) {
        return toUnit(velocity.distanceUnit(), velocity.timeUnit());
    }

    public Velocity toUnit(DistanceUnit newDistanceUnit, TimeUnit newTimeUnit) {
        return new Velocity(
                distanceUnit.toUnit(newDistanceUnit,
                        timeUnit.toUnit(newTimeUnit, value)), newDistanceUnit, newTimeUnit);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Velocity && equals((Velocity)o);
    }

    @Override
    public String toString() {
        return String.format("%.4f [%s] per [%s]", value, distanceUnit.name(), timeUnit.name());
    }
}
