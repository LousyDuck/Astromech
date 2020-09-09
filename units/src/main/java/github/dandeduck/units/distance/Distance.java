package github.dandeduck.units.distance;

import github.dandeduck.units.NotMatchingUnitsException;
import github.dandeduck.units.Value;
import github.dandeduck.units.morion.Velocity;
import github.dandeduck.units.time.Time;

public class Distance implements Value {
    private final double value;
    private final DistanceUnit unit;

    public Distance(double value, DistanceUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public static Distance miles(double value) {
        return new Distance(value, DistanceUnit.MILES);
    }

    public static Distance kilometers(double value) {
        return new Distance(value, DistanceUnit.KILOMETERS);
    }

    public static Distance meters(double value) {
        return new Distance(value, DistanceUnit.METERS);
    }

    public static Distance yards(double value) {
        return new Distance(value, DistanceUnit.YARDS);
    }

    public static Distance feet(double value) {
        return new Distance(value, DistanceUnit.FEET);
    }

    public static Distance inches(double value) {
        return new Distance(value, DistanceUnit.INCHES);
    }

    public static Distance centimeters(double value) {
        return new Distance(value, DistanceUnit.CENTIMETERS);
    }

    public static Distance millimeters(double value) {
        return new Distance(value, DistanceUnit.MILLIMETERS);
    }

    @Override
    public double value() {
        return value;
    }

    @Override
    public Distance add(Value other) {
        if (other instanceof Distance)
            return add((Distance)other);
        else
            throw new NotMatchingUnitsException();
    }

    @Override
    public Distance sub(Value other) {
        if (other instanceof Distance)
            return sub((Distance)other);
        else
            throw new NotMatchingUnitsException();
    }

    public Distance add(Distance other) {
        other = other.toUnit(unit);

        return new Distance(value + other.value(), unit);
    }

    public Distance sub(Distance other) {
        other = other.toUnit(unit);

        return new Distance(value - other.value(), unit);
    }

    public double valueAsMeters() {
        return unit.toUnit(DistanceUnit.METERS, value);
    }

    public DistanceUnit unit() {
        return unit;
    }

    public double div(Distance denominator) {
        return valueAsMeters()/denominator.valueAsMeters();
    }

    public Time timeToReach(Velocity velocity) {
        return Time.seconds(valueAsMeters()/velocity.valueAsMetersPerSecond());
    }

    public Distance div(double denominator) {
        return new Distance(value/denominator, unit);
    }

    public Distance mul(double scalar) {
        return new Distance(value*scalar, unit);
    }

    public Distance toUnit(DistanceUnit newUnit) {
        return new Distance(unit.toUnit(newUnit, value), newUnit);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Distance && equals((Distance)o);
    }

    @Override
    public String toString() {
        return String.format("%.4f [%s]", value, unit.name());
    }
}
