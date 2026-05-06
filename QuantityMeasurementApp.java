public class QuantityMeasurementApp {

    // UC8: LengthUnit Enum (standalone)
    enum LengthUnit {
                INCHES(1.0), FEET(12.0), YARD(36.0);
                private final double factor;
                LengthUnit(double f) { this.factor = f; }
                public double getFactor() { return factor; }
    }

    // UC9: WeightUnit Enum (new measurement category)
    enum WeightUnit {
                GRAM(1.0), KILOGRAM(1000.0), TONNE(1000000.0);
                private final double factor;
                WeightUnit(double f) { this.factor = f; }
                public double getFactor() { return factor; }
    }

    // Generic Quantity class for length
    static class LengthQuantity {
                private double value;
                private LengthUnit unit;

            public LengthQuantity(double value, LengthUnit unit) {
                            this.value = value;
                            this.unit = unit;
            }

            public double toBaseUnit() { return value * unit.getFactor(); }

            public boolean isEqualTo(LengthQuantity other) {
                            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
            }

            public LengthQuantity convertTo(LengthUnit target) {
                            return new LengthQuantity(toBaseUnit() / target.getFactor(), target);
            }

            public LengthQuantity add(LengthQuantity other) {
                            return new LengthQuantity(toBaseUnit() + other.toBaseUnit(), LengthUnit.INCHES);
            }

            public LengthQuantity addWithTarget(LengthQuantity other, LengthUnit target) {
                            return new LengthQuantity((toBaseUnit() + other.toBaseUnit()) / target.getFactor(), target);
            }

            @Override
                public String toString() { return value + " " + unit.name().toLowerCase(); }
    }

    // UC9: Weight Quantity class
    static class WeightQuantity {
                private double value;
                private WeightUnit unit;

            public WeightQuantity(double value, WeightUnit unit) {
                            this.value = value;
                            this.unit = unit;
            }

            public double toBaseUnit() { return value * unit.getFactor(); }

            public boolean isEqualTo(WeightQuantity other) {
                            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
            }

            public WeightQuantity convertTo(WeightUnit target) {
                            return new WeightQuantity(toBaseUnit() / target.getFactor(), target);
            }

            public WeightQuantity add(WeightQuantity other) {
                            return new WeightQuantity(toBaseUnit() + other.toBaseUnit(), WeightUnit.GRAM);
            }

            @Override
                public String toString() { return value + " " + unit.name().toLowerCase(); }
    }

    public static void main(String[] args) {
                // UC8: Length operations
            LengthQuantity l1 = new LengthQuantity(1.0, LengthUnit.FEET);
                LengthQuantity l2 = new LengthQuantity(12.0, LengthUnit.INCHES);
                System.out.println("UC8: 1 foot == 12 inches? " + l1.isEqualTo(l2));

            // UC9: Weight Measurement
            WeightQuantity w1 = new WeightQuantity(1.0, WeightUnit.KILOGRAM);
                WeightQuantity w2 = new WeightQuantity(1000.0, WeightUnit.GRAM);
                System.out.println("UC9: 1 kg == 1000 grams? " + w1.isEqualTo(w2));

            WeightQuantity w3 = new WeightQuantity(1.0, WeightUnit.TONNE);
                WeightQuantity w4 = new WeightQuantity(1000.0, WeightUnit.KILOGRAM);
                System.out.println("UC9: 1 tonne == 1000 kg? " + w3.isEqualTo(w4));

            WeightQuantity converted = w1.convertTo(WeightUnit.GRAM);
                System.out.println("UC9: 1 kg converted to grams = " + converted);

            WeightQuantity sumWeight = w1.add(w2);
                System.out.println("UC9: 1 kg + 1000 g = " + sumWeight);
    }
}
