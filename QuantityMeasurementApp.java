public class QuantityMeasurementApp {

    // UC3 & UC4: Generic Quantity class with Extended Unit Support
    // UC4 adds: Yard (1 yard = 3 feet = 36 inches)
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
                            switch (unit.toLowerCase()) {
                                case "feet":   return value * 12.0;
                                case "inches": return value;
                                case "yard":   return value * 36.0;
                                default:       return value;
                            }
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
    }

    // UC2: Feet and Inches Measurement Equality
    public double feetToInches(double feet) { return feet * 12.0; }

    public boolean areLengthsEqual(double v1, String u1, double v2, String u2) {
                Quantity q1 = new Quantity(v1, u1);
                Quantity q2 = new Quantity(v2, u2);
                return q1.isEqualTo(q2);
    }

    public static void main(String[] args) {
                QuantityMeasurementApp app = new QuantityMeasurementApp();

            // UC1
            System.out.println("UC1: 3.0ft == 3.0ft? " + app.areFeetEqual(3.0, 3.0));
                // UC2
            System.out.println("UC2: 1ft == 12in? " + app.areLengthsEqual(1.0, "feet", 12.0, "inches"));
                // UC3
            Quantity q1 = new Quantity(1.0, "feet");
                Quantity q2 = new Quantity(12.0, "inches");
                System.out.println("UC3: 1ft == 12in? " + q1.isEqualTo(q2));
                // UC4: Extended Unit Support - Yard
            Quantity q3 = new Quantity(1.0, "yard");
                Quantity q4 = new Quantity(36.0, "inches");
                Quantity q5 = new Quantity(3.0, "feet");
                System.out.println("UC4: 1 yard == 36 inches? " + q3.isEqualTo(q4));
                System.out.println("UC4: 1 yard == 3 feet? " + q3.isEqualTo(q5));
    }
}
