public class QuantityMeasurementApp {

    // UC1: Feet Measurement Equality
    public boolean areFeetEqual(double feet1, double feet2) {
                return Double.compare(feet1, feet2) == 0;
    }

    // UC2: Feet and Inches Measurement Equality
    // 1 foot = 12 inches; converts all to inches for comparison
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

            // UC1: Feet Measurement Equality
            System.out.println("UC1: Are 3.0 feet and 3.0 feet equal? " + app.areFeetEqual(3.0, 3.0));
                System.out.println("UC1: Are 2.0 feet and 5.0 feet equal? " + app.areFeetEqual(2.0, 5.0));

            // UC2: Feet and Inches Measurement Equality
            System.out.println("UC2: Are 1.0 feet and 12.0 inches equal? " + app.areLengthsEqual(1.0, "feet", 12.0, "inches"));
                System.out.println("UC2: Are 2.0 feet and 24.0 inches equal? " + app.areLengthsEqual(2.0, "feet", 24.0, "inches"));
                System.out.println("UC2: Are 1.0 feet and 1.0 inches equal? " + app.areLengthsEqual(1.0, "feet", 1.0, "inches"));
    }
}
