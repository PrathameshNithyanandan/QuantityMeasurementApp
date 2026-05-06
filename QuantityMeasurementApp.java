public class QuantityMeasurementApp {

    // Quantity class with UC3/UC4/UC5/UC6 features
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

            // UC6: Addition of Two Length Units (Same Category)
            public Quantity add(Quantity other) {
                            double totalInches = this.toBaseUnit() + other.toBaseUnit();
                            return new Quantity(totalInches, "inches");
            }

            @Override
                public String toString() {
                                return value + " " + unit;
                }
    }

    public static void main(String[] args) {
                // UC5: Conversion
            System.out.println("UC5: 12 inches to feet = " + new Quantity(12.0, "inches").convertTo("feet"));
                System.out.println("UC5: 1 yard to feet = " + new Quantity(1.0, "yard").convertTo("feet"));

            // UC6: Addition of Two Length Units
            Quantity q1 = new Quantity(2.0, "feet");
                Quantity q2 = new Quantity(2.0, "feet");
                Quantity sum = q1.add(q2);
                System.out.println("UC6: 2 feet + 2 feet = " + sum);

            Quantity q3 = new Quantity(1.0, "feet");
                Quantity q4 = new Quantity(12.0, "inches");
                Quantity sum2 = q3.add(q4);
                System.out.println("UC6: 1 foot + 12 inches = " + sum2);

            Quantity q5 = new Quantity(2.0, "inches");
                Quantity q6 = new Quantity(2.0, "inches");
                Quantity sum3 = q5.add(q6);
                System.out.println("UC6: 2 inches + 2 inches = " + sum3);
    }
}
