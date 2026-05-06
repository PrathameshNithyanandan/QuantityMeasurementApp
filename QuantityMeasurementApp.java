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

    // UC11: Volume Measurement (Litre, Millilitre, Gallon)
    // Base unit: Millilitre (1 Litre = 1000 ml, 1 Gallon = 3785.41 ml)
    enum VolumeUnit implements Unit {
                MILLILITRE(1.0),
                LITRE(1000.0),
                GALLON(3785.41);
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

            public Quantity<T> subtract(Quantity<T> other) {
                            return new Quantity<>(toBaseUnit() - other.toBaseUnit(), this.unit);
            }

            @Override
                public String toString() { return value + " " + unit.name().toLowerCase(); }
    }

    public static void main(String[] args) {
                // UC10: Length and Weight
            System.out.println("UC10: 1ft == 12in? " + new Quantity<>(1.0, LengthUnit.FEET).isEqualTo(new Quantity<>(12.0, LengthUnit.INCHES)));
                System.out.println("UC10: 1kg == 1000g? " + new Quantity<>(1.0, WeightUnit.KILOGRAM).isEqualTo(new Quantity<>(1000.0, WeightUnit.GRAM)));

            // UC11: Volume Measurement (Litre, Millilitre, Gallon)
            Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
                Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
                System.out.println("UC11: 1 litre == 1000 ml? " + v1.isEqualTo(v2));

            Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.GALLON);
                System.out.println("UC11: 1 gallon in ml = " + v3.convertTo(VolumeUnit.MILLILITRE));
                System.out.println("UC11: 1 gallon in litres = " + v3.convertTo(VolumeUnit.LITRE));

            Quantity<VolumeUnit> sumV = v1.add(v2);
                System.out.println("UC11: 1 litre + 1000 ml = " + sumV);

            // Check equality across units
            Quantity<VolumeUnit> v4 = new Quantity<>(1.0, VolumeUnit.GALLON);
                Quantity<VolumeUnit> v5 = new Quantity<>(3.785, VolumeUnit.LITRE);
                System.out.println("UC11: 1 gallon approx equal to 3.785 litre? " + (Math.abs(v4.toBaseUnit() - v5.toBaseUnit()) < 10));
    }
}
