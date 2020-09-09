package github.dandeduck.units.complex;

public class Position {
    private final Time timestamp;
    private final Distance distance;
    private final Angle angle;

    public Position(Time timestamp, Distance distanceMeters, Angle angleDegrees) {
        this.timestamp = timestamp;
        this.distance = distanceMeters;
        this.angle = angleDegrees;
    }

    public Time timestamp() {
        return timestamp;
    }

    public Distance distance() {
        return distance;
    }

    public Angle getAngle() {
        return angle;
    }
}
