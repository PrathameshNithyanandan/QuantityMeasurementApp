public class QuantityMeasurementApp {

    // UC8: Refactoring Unit Enum to Standalone
    // LengthUnit enum with conversion factors (base unit: inches)
    enum LengthUnit {
                INCHES(1.0),
                FEET(12.0),
                YARD(36.0);

                private final double conversionFactor;

                LengthUnit(double conversionFactor) {
                                this.conversionFactor = conversionFactor;
                }

                public double getConversionFactor() {
                                return conversionFactor;
                }
    }

    // Refactored Quantity class using LengthUnit enum
    static class Quantity {
                private double value;
                private LengthUnit unit;

            public Quantity(double value, LengthUnit unit) {
                            this.value = value;
                            this.unit = unit;
            }

            public double getValue() { return value; }
                public LengthUnit getUnit() { return unit; }

            public double toBaseUnit() {
                            return value * unit.getConversionFactor();
            }

            public boolean isEqualTo(Quantity other) {
                            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
            }

            public Quantity convertTo(LengthUnit targetUnit) {
                            double baseValue = this.toBaseUnit();
                            double convertedValue = baseValue / targetUnit.getConversionFactor();
                            return new Quantity(convertedValue, targetUnit);
            }

            public Quantity add(Quantity other) {
                            double totalBase = this.toBaseUnit() + other.toBaseUnit();
                            return new Quantity(totalBase, LengthUnit.INCHES);
            }

            public Quantity addWithTargetUnit(Quantity other, LengthUnit targetUnit) {
                            double totalBase = this.toBaseUnit() + other.toBaseUnit();
                            double converted = totalBase / targetUnit.getConversionFactor();
                            return new Quantity(converted, targetUnit);
            }

            @Override
                public String toString() {
                                return value + " " + unit.name().toLowerCase();
                }
    }

    public static void main(String[] args) {
                // UC8: Using refactored LengthUnit enum
            Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
                Quantity q2 = new Quantity(12.0, LengthUnit.INCHES);
                System.out.println("UC8: 1 foot == 12 inches? " + q1.isEqualTo(q2));

            Quantity q3 = new Quantity(1.0, LengthUnit.YARD);
                Quantity q4 = new Quantity(3.0, LengthUnit.FEET);
                System.out.println("UC8: 1 yard == 3 feet? " + q3.isEqualTo(q4));

            Quantity converted = q1.convertTo(LengthUnit.INCHES);
                System.out.println("UC8: 1 foot converted to inches = " + converted);

            Quantity sum = q1.addWithTargetUnit(q2, LengthUnit.FEET);
                System.out.println("UC8: 1 foot + 12 inches in feet = " + sum);
    }
}
