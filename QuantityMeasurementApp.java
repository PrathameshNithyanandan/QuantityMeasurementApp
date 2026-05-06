public class QuantityMeasurementApp {

    interface Unit {
                double getConversionFactor();
                String name();
    }

    enum LengthUnit implements Unit {
                INCHES(1.0), FEET(12.0), YARD(36.0);
                private final double factor;
                LengthUnit(double f) { this.factor = f; }
                public double getConversionFactor() { return factor; }
    }

    enum WeightUnit implements Unit {
                GRAM(1.0), KILOGRAM(1000.0), TONNE(1000000.0);
                private final double factor;
                WeightUnit(double f) { this.factor = f; }
                public double getConversionFactor() { return factor; }
    }

    enum VolumeUnit implements Unit {
                MILLILITRE(1.0), LITRE(1000.0), GALLON(3785.41);
                private final double factor;
                VolumeUnit(double f) { this.factor = f; }
                public double getConversionFactor() { return factor; }
    }

    static class Quantity<T extends Unit> {
                private double value;
                private T unit;

            public Quantity(double value, T unit) {
                            this.value = value;
                            this.unit = unit;
            }

            public double getValue() { return value; }
                public T getUnit() { return unit; }
                public double toBaseUnit() { return value * unit.getConversionFactor(); }

            public boolean isEqualTo(Quantity<T> other) {
                            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
            }

            public Quantity<T> convertTo(T targetUnit) {
                            return new Quantity<>(toBaseUnit() / targetUnit.getConversionFactor(), targetUnit);
            }

            public Quantity<T> add(Quantity<T> other) {
                            return new Quantity<>(toBaseUnit() + other.toBaseUnit(), this.unit);
            }

            // UC12: Subtraction Operation
            public Quantity<T> subtract(Quantity<T> other) {
                            double result = toBaseUnit() - other.toBaseUnit();
                            if (result < 0) result = 0; // No negative measurements
                    return new Quantity<>(result / this.unit.getConversionFactor(), this.unit);
            }

            // UC12: Division Operation
            public double divide(Quantity<T> other) {
                            if (other.toBaseUnit() == 0) throw new ArithmeticException("Cannot divide by zero quantity");
                            return this.toBaseUnit() / other.toBaseUnit();
            }

            public Quantity<T> divideByScalar(double scalar) {
                            if (scalar == 0) throw new ArithmeticException("Cannot divide by zero");
                            return new Quantity<>(value / scalar, this.unit);
            }

            @Override
                public String toString() { return value + " " + unit.name().toLowerCase(); }
    }

    public static void main(String[] args) {
                // UC12: Subtraction and Division Operations on Quantity Measurements
            // Length subtraction
            Quantity<LengthUnit> l1 = new Quantity<>(3.0, LengthUnit.FEET);
                Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);
                System.out.println("UC12 Subtract: 3 feet - 12 inches = " + l1.subtract(l2));

            // Weight subtraction
            Quantity<WeightUnit> w1 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
                Quantity<WeightUnit> w2 = new Quantity<>(500.0, WeightUnit.GRAM);
                System.out.println("UC12 Subtract: 2 kg - 500 g = " + w1.subtract(w2));

            // Volume subtraction
            Quantity<VolumeUnit> v1 = new Quantity<>(2.0, VolumeUnit.LITRE);
                Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
                System.out.println("UC12 Subtract: 2 litres - 500 ml = " + v1.subtract(v2));

            // Division
            Quantity<LengthUnit> l3 = new Quantity<>(6.0, LengthUnit.FEET);
                Quantity<LengthUnit> l4 = new Quantity<>(2.0, LengthUnit.FEET);
                System.out.println("UC12 Divide: 6 feet / 2 feet = " + l3.divide(l4));

            // Division by scalar
            Quantity<WeightUnit> w3 = new Quantity<>(6.0, WeightUnit.KILOGRAM);
                System.out.println("UC12 Divide: 6 kg / 3 = " + w3.divideByScalar(3));
    }
}
