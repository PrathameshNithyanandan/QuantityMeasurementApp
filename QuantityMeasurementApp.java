public class QuantityMeasurementApp {

    // UC1: Feet Measurement Equality
    // Checks equality of two numerical values measured in feet
    public boolean areFeetEqual(double feet1, double feet2) {
                return Double.compare(feet1, feet2) == 0;
    }

    public static void main(String[] args) {
                QuantityMeasurementApp app = new QuantityMeasurementApp();

            // UC1: Feet Measurement Equality
            double feet1 = 3.0;
                double feet2 = 3.0;
                boolean result = app.areFeetEqual(feet1, feet2);
                System.out.println("Are " + feet1 + " feet and " + feet2 + " feet equal? " + result);

            double feet3 = 2.0;
                double feet4 = 5.0;
                boolean result2 = app.areFeetEqual(feet3, feet4);
                System.out.println("Are " + feet3 + " feet and " + feet4 + " feet equal? " + result2);
    }
}
