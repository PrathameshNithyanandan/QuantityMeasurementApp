public class QuantityMeasurementApp {

    // UC10: Generic Quantity Class with Unit Interface for Multi-Category Support
    // Unit interface for all measurement types
    interface Unit {
                double getConversionFactor();
                String name();
    }

    // UC8: LengthUnit implementing Unit interface
    enum LengthUnit implements Unit {
                INCHES(1.0), FEET(12.0), YARD(36.0);
                private final double factor;
                LengthUnit(double f) { this.factor = f; }
                public double getConversionFactor() { return factor; }
    }

    // UC9: WeightUnit implementing Unit interface
    enum WeightUnit implements Unit {
                GRAM(1.0), KILOGRAM(1000.0), TONNE(1000000.0);
                private final double factor;
                WeightUnit(double f) { this.factor = f; }
                public double getConversionFactor() { return factor; }
    }

    // UC10: Generic Quantity<T extends Unit> for multi-category support
    static class Quantity<T extends Unit> {
                private double value;
                private T unit;

            public Quantity(double value, T unit) {
                            this.value = value;
                            this.unit = unit;
            }

            public double getValue() { return value; }
                public T getUnit() { return unit; }

            public double toBaseUnit() {
                            return value * unit.getConversionFactor();
            }

            public boolean isEqualTo(Quantity<T> other) {
                            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
            }

            public Quantity<T> convertTo(T targetUnit) {
                            return new Quantity<>(toBaseUnit() / targetUnit.getConversionFactor(), targetUnit);
            }

            public Quantity<T> add(Quantity<T> other) {
                            return new Quantity<>(toBaseUnit() + other.toBaseUnit(), this.unit);
            }

            public Quantity<T> subtract(Quantity<T> other) {
                            return new Quantity<>(toBaseUnit() - other.toBaseUnit(), this.unit);
            }

            @Override
                public String toString() {
                                return value + " " + unit.name().toLowerCase();
                }
    }

    public static void main(String[] args) {
                // UC10: Using generic Quantity class for Length
            Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
                Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);
                System.out.println("UC10 Length: 1 foot == 12 inches? " + l1.isEqualTo(l2));

            Quantity<LengthUnit> l3 = new Quantity<>(1.0, LengthUnit.YARD);
                Quantity<LengthUnit> l4 = new Quantity<>(3.0, LengthUnit.FEET);
                System.out.println("UC10 Length: 1 yard == 3 feet? " + l3.isEqualTo(l4));

            // UC10: Using generic Quantity class for Weight
            Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
                Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);
                System.out.println("UC10 Weight: 1 kg == 1000 g? " + w1.isEqualTo(w2));

            Quantity<WeightUnit> sumW = w1.add(w2);
                System.out.println("UC10 Weight: 1 kg + 1000 g = " + sumW);

            // UC10: Convert
            Quantity<LengthUnit> converted = l1.convertTo(LengthUnit.INCHES);
                System.out.println("UC10 Length: 1 foot in inches = " + converted);
    }
}
