public class QuantityMeasurementApp {

    // UC3: Generic Quantity Class for DRY Principle
    // Using a generic Quantity class to avoid code repetition
    static class Quantity {
                private double value;
                private String unit;

            public Quantity(double value, String unit) {
                            this.value = value;
                            this.unit = unit;
            }

            public double getValue() { return value; }
                public String getUnit() { return unit; }

            // Convert value to base unit (inches for length)
            public double toBaseUnit() {
                            if (unit.equalsIgnoreCase("feet")) return value * 12.0;
                            if (unit.equalsIgnoreCase("inches")) return value;
                            return value;
            }

            public boolean isEqualTo(Quantity other) {
                            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
            }

            @Override
                public String toString() {
                                return value + " " + unit;
                }
    }

    // UC1: Feet Measurement Equality
    public boolean areFeetEqual(double feet1, double feet2) {
                return Double.compare(feet1, feet2) == 0;

    // UC2: Feet and Inches Measurement Equality
    public double feetToInches(double feet) {
                return feet * 12.0;
    }

    public boolean areLengthsEqual(double value1, String unit1, double value2, String unit2) {
                double inches1 = unit1.equalsIgnoreCase("feet") ? feetToInches(value1) : value1;
                double inches2 = unit2.equalsIgnoreCase("feet") ? feetToInches(value2) : value2;
                return Double.compare(inches1, inches2) == 0;
    }

    public static void main(String[] args) {
                QuantityMeasurementApp app = new QuantityMeasurementApp();

            // UC1
            System.out.println("UC1: Are 3.0 feet and 3.0 feet equal? " + app.areFeetEqual(3.0, 3.0));

            // UC2
            System.out.println("UC2: Are 1.0 feet and 12.0 inches equal? " + app.areLengthsEqual(1.0, "feet", 12.0, "inches"));

            // UC3: Generic Quantity Class
            Quantity q1 = new Quantity(1.0, "feet");
                Quantity q2 = new Quantity(12.0, "inches");
                Quantity q3 = new Quantity(2.0, "feet");
                System.out.println("UC3: " + q1 + " equals " + q2 + "? " + q1.isEqualTo(q2));
                System.out.println("UC3: " + q1 + " equals " + q3 + "? " + q1.isEqualTo(q3));
    }
}
