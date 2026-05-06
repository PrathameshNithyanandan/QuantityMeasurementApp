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

    // UC13: Centralized Arithmetic Logic to Enforce DRY in Quantity Operations
    // QuantityArithmetic centralizes all arithmetic operations
    static class QuantityArithmetic {

            // Centralized: convert any value with a unit to its base unit
            public static double toBase(double value, Unit unit) {
                            return value * unit.getConversionFactor();
            }

            // Centralized: compare two quantities of the same unit type
            public static <T extends Unit> boolean isEqual(double v1, T u1, double v2, T u2) {
                            return Double.compare(toBase(v1, u1), toBase(v2, u2)) == 0;
            }

            // Centralized: add two quantities, return result in base unit
            public static <T extends Unit> double add(double v1, T u1, double v2, T u2) {
                            return toBase(v1, u1) + toBase(v2, u2);
            }

            // Centralized: subtract two quantities, return result in base unit
            public static <T extends Unit> double subtract(double v1, T u1, double v2, T u2) {
                            double result = toBase(v1, u1) - toBase(v2, u2);
                            return Math.max(result, 0);
            }

            // Centralized: divide two quantities (returns ratio)
            public static <T extends Unit> double divide(double v1, T u1, double v2, T u2) {
                            double base2 = toBase(v2, u2);
                            if (base2 == 0) throw new ArithmeticException("Cannot divide by zero");
                            return toBase(v1, u1) / base2;
            }

            // Centralized: convert value from one unit to another
            public static <T extends Unit> double convert(double value, T fromUnit, T toUnit) {
                            return toBase(value, fromUnit) / toUnit.getConversionFactor();
            }
    }

    static class Quantity<T extends Unit> {
                private double value;
                private T unit;

            public Quantity(double value, T unit) { this.value = value; this.unit = unit; }
                public double getValue() { return value; }
                public T getUnit() { return unit; }
                public double toBaseUnit() { return QuantityArithmetic.toBase(value, unit); }

            public boolean isEqualTo(Quantity<T> other) {
                            return QuantityArithmetic.isEqual(value, unit, other.value, other.unit);
            }

            public Quantity<T> convertTo(T targetUnit) {
                            return new Quantity<>(QuantityArithmetic.convert(value, unit, targetUnit), targetUnit);
            }

            public Quantity<T> add(Quantity<T> other) {
                            return new Quantity<>(QuantityArithmetic.add(value, unit, other.value, other.unit), this.unit);
            }

            public Quantity<T> subtract(Quantity<T> other) {
                            return new Quantity<>(QuantityArithmetic.subtract(value, unit, other.value, other.unit) / unit.getConversionFactor(), this.unit);
            }

            public double divide(Quantity<T> other) {
                            return QuantityArithmetic.divide(value, unit, other.value, other.unit);
            }

            @Override
                public String toString() { return value + " " + unit.name().toLowerCase(); }
    }

    public static void main(String[] args) {
                System.out.println("=== UC13: Centralized Arithmetic Logic ===");

            // Length
            System.out.println("1ft == 12in: " + QuantityArithmetic.isEqual(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES));
                System.out.println("1yd == 3ft: " + QuantityArithmetic.isEqual(1.0, LengthUnit.YARD, 3.0, LengthUnit.FEET));
                System.out.println("2ft + 24in = " + QuantityArithmetic.add(2.0, LengthUnit.FEET, 24.0, LengthUnit.INCHES) + " inches");
                System.out.println("3ft - 12in = " + QuantityArithmetic.subtract(3.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES) + " inches");
                System.out.println("6ft / 2ft = " + QuantityArithmetic.divide(6.0, LengthUnit.FEET, 2.0, LengthUnit.FEET));
                System.out.println("1ft to inches = " + QuantityArithmetic.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES));

            // Weight
            System.out.println("1kg == 1000g: " + QuantityArithmetic.isEqual(1.0, WeightUnit.KILOGRAM, 1000.0, WeightUnit.GRAM));
                System.out.println("2kg + 500g = " + QuantityArithmetic.add(2.0, WeightUnit.KILOGRAM, 500.0, WeightUnit.GRAM) + " grams");

            // Volume
            System.out.println("1L == 1000ml: " + QuantityArithmetic.isEqual(1.0, VolumeUnit.LITRE, 1000.0, VolumeUnit.MILLILITRE));
                System.out.println("2L - 500ml = " + QuantityArithmetic.subtract(2.0, VolumeUnit.LITRE, 500.0, VolumeUnit.MILLILITRE) + " ml");

            // Using Quantity class with centralized arithmetic
            Quantity<LengthUnit> q1 = new Quantity<>(2.0, LengthUnit.FEET);
                Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.YARD);
                System.out.println("2ft + 1yd = " + q1.add(q2));
    }
}
