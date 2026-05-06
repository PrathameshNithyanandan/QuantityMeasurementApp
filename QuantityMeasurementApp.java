public class QuantityMeasurementApp {

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

            // UC6: Addition returning in inches
            public Quantity add(Quantity other) {
                            double totalInches = this.toBaseUnit() + other.toBaseUnit();
                            return new Quantity(totalInches, "inches");
            }

            // UC7: Addition with Target Unit Specification
            public Quantity addWithTargetUnit(Quantity other, String targetUnit) {
                            double totalInches = this.toBaseUnit() + other.toBaseUnit();
                            Quantity result = new Quantity(totalInches, "inches");
                            return result.convertTo(targetUnit);
            }

            @Override
                public String toString() {
                                return value + " " + unit;
                }
    }

    public static void main(String[] args) {
                // UC6: Addition
            Quantity q1 = new Quantity(1.0, "feet");
                Quantity q2 = new Quantity(12.0, "inches");
                System.out.println("UC6: 1 foot + 12 inches = " + q1.add(q2));

            // UC7: Addition with Target Unit Specification
            Quantity qa = new Quantity(1.0, "feet");
                Quantity qb = new Quantity(12.0, "inches");
                System.out.println("UC7: 1 foot + 12 inches in feet = " + qa.addWithTargetUnit(qb, "feet"));
                System.out.println("UC7: 1 foot + 12 inches in inches = " + qa.addWithTargetUnit(qb, "inches"));
                System.out.println("UC7: 1 foot + 12 inches in yards = " + qa.addWithTargetUnit(qb, "yard"));

            Quantity qc = new Quantity(2.0, "feet");
                Quantity qd = new Quantity(1.0, "yard");
                System.out.println("UC7: 2 feet + 1 yard in feet = " + qc.addWithTargetUnit(qd, "feet"));
    }
}
