public class QuantityMeasurementApp {

    // Quantity class - UC3/UC4
    static class Quantity {
                private double value;
                private String unit;

            public Quantity(double value, String unit) {
                            this.value = value;
                            this.unit = unit;
            }

            public double getValue() { return value; }
                public String getUnit() { return unit; }

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

            // UC5: Unit-to-Unit Conversion (Same Measurement Type)
            public Quantity convertTo(String targetUnit) {
                            double baseValue = this.toBaseUnit();
                            double convertedValue;
                            switch (targetUnit.toLowerCase()) {
                                case "feet":   convertedValue = baseValue / 12.0; break;
                                case "inches": convertedValue = baseValue; break;
                                case "yard":   convertedValue = baseValue / 36.0; break;
                                default:       convertedValue = baseValue;
                            }
                            return new Quantity(convertedValue, targetUnit);
            }

            @Override
                public String toString() {
                                return value + " " + unit;
                }
    }

    public static void main(String[] args) {
                // UC1
            System.out.println("UC1: 3.0ft == 3.0ft? " + (Double.compare(3.0, 3.0) == 0));
                // UC2
            Quantity q1 = new Quantity(1.0, "feet");
                Quantity q2 = new Quantity(12.0, "inches");
                System.out.println("UC2: 1ft == 12in? " + q1.isEqualTo(q2));
                // UC3
            System.out.println("UC3: 1ft == 12in using Quantity class? " + q1.isEqualTo(q2));
                // UC4
            Quantity yard = new Quantity(1.0, "yard");
                System.out.println("UC4: 1 yard == 36 inches? " + yard.isEqualTo(new Quantity(36.0, "inches")));
                // UC5: Unit-to-Unit Conversion
            Quantity converted1 = new Quantity(12.0, "inches").convertTo("feet");
                System.out.println("UC5: 12 inches converted to feet = " + converted1);
                Quantity converted2 = new Quantity(3.0, "feet").convertTo("inches");
                System.out.println("UC5: 3 feet converted to inches = " + converted2);
                Quantity converted3 = new Quantity(1.0, "yard").convertTo("feet");
                System.out.println("UC5: 1 yard converted to feet = " + converted3);
    }
}
