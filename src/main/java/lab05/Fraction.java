/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2023
 *
 * Name: LINH NGUYEN
 * Date: 2/5/23
 * Time: 3:51 PM
 *
 * Project: csci205_labs
 * Package: lab05
 * Class: Fraction
 * Description:
 * A class that represent a Fraction of int values.
 * It also provides a collection of basic math operations
 * ****************************************
 */

package lab05;


/**
 * This class encapsulates a single fraction in the form of
 * x/y, where x and y are restricted to integers
 */
class Fraction {
    /** The numerator of the fraction */
    private final int numerator;

    /** The denominator of the fraction */
    private final int denominator;

    /** Is this fraction valid? A valid fraction must have both numbers set properly and
     * can not have a 0 in the denominator */
    private final boolean isValid;

    /**
     * Construct a new Fraction from two integer values. If the denominator
     * is zero, the fraction is not considered valid (@code isValid} is
     * {@code false}
     *
     * @param numerator an {@code int} numerator of the fraction
     * @param denominator an {@code int} denominator of the fraction
     */
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        if(denominator == 0) {
            this.isValid = false;
        }
        else {
            this.isValid = true;
        }
    }

    /**
     * Construct a new Fraction from a string in the form "a / b". There may or
     * may not be whitespace in between tokens in the string. If the string
     * could not be parsed, then set isValid to false and print an error message.
     * If it could be parsed, but the denominator is 0, just set isValid to false.
     * Otherwise isValid is true
     *
     * @param strFraction a String that should be of the form a/b. If there is
     *                    any problem parsing the fraction, then the fraction
     *                    defaults to 1/1 and a  message idisplayed
     */
    public Fraction(String strFraction) {
        // Temporary variables to store numerator, denominator and isValid value
        boolean temp_isValid = true;
        String temp_num = "";
        String temp_denom = "";

        // Clean whitespaces in string
        String cleaned_strFraction = strFraction.replaceAll("\\s", "");

        // A valid fraction will have at least 3 characters
        if (cleaned_strFraction.length() <3) {
            temp_isValid = false;
        }

        int i = 0; //Starting index = 0

        // Separate numerator and denominator
        while (temp_isValid) {
            // Check if the character in the string is digit.
            // The only case it's not a digit is when the character is '/
            if ((!Character.isDigit(cleaned_strFraction.charAt(i))) && (cleaned_strFraction.charAt(i) != '/')) {
                temp_isValid = false;
            } else if (cleaned_strFraction.charAt(i) == '/') {
                break;
            } else {
                temp_num += cleaned_strFraction.charAt(i);
                i++;
            }
        }

        // If the fraction does not contain '/', then i equals to the string length
        if (i == cleaned_strFraction.length()-1) {
            temp_isValid = false;
        }
        else {
            temp_denom = cleaned_strFraction.substring(i + 1);
        }


        // Set instance variables
        if (temp_isValid) {
            this.numerator = Integer.valueOf(temp_num);
            this.denominator = Integer.valueOf(temp_denom);
        } else {
            System.out.println("Incorrect format for fraction: " + strFraction);
            this.numerator = 1;
            this.denominator = 1;
        }
        this.isValid = temp_isValid;
    }


    /** @return the numerator in the fraction */
    public int getNumerator() { return numerator; }

    /** @return the denominator in the fraction */
    public int getDenominator() { return denominator; }

    /** @return the state of the {@link #isValid} flag */
    public boolean isValid() { return this.isValid; }

    /**
     * Return a new copy of this fraction but in most simplified terms
     *
     * @return a new Fraction that represents this Fraction but in simplest terms.
     * For example, 3/6 would return a new Fraction 1/2. 1/2 would also return 1/2.
     */
    public Fraction getSimplifiedFraction() {
        //TODO - FINISH ME!
        int gcd = getGcd(this.numerator, this.denominator);
        int num = this.numerator / gcd;
        int denom = this.denominator / gcd;
        return new Fraction(num,denom);
    }

    /**
     * Find greatest common divisor of numerator and denominator using Euclid algorithms
     * @return greatest common divisor
     */
    private int getGcd(int n1, int n2) {
        if (n2 == 0){
            return n1;
        }
        return getGcd(n2, n1%n2);
    }

    /**
     * @return a new Fraction representing the reciprocal of this fraction
     */
    public Fraction reciprocal() {
        return new Fraction(this.denominator,this.numerator);
    }

    /**
     * @return a new Fraction representing the negative of this fraction
     * (multiply the numerator or denominator by -1)
     */
    public Fraction negate() {
        return new Fraction(-1*this.numerator,this.denominator);
    }

    /**
     * @return this fraction as a single floating point number (e.g. 1/2 ==> 0.5)
     */
    public double getDecimal() {
        double decimal = (double) this.numerator / this.denominator;
        return decimal;
    }

    /**
     * Perform simple Fraction addition, returning a new Fraction that represents
     * this added to {@code otherFrac} in simplified form
     *
     * @param otherFrac the other Fraction that will be added to this one
     * @return a new Fraction that represents the addition of this Fraction to
     *         otherFrac, simplified
     */
    public Fraction add(Fraction otherFrac) {
        //TODO - FINISH ME!
        int num = this.numerator * otherFrac.denominator + this.denominator * otherFrac.numerator;
        int denom = this.denominator * otherFrac.denominator;

        return new Fraction(num, denom) ;
    }

    /**
     * @return the product of this fraction multiplied to {@code otherFrac}
     * as a new Fraction, simplified
     */
    public Fraction multiply(Fraction otherFrac) {
        int num = this.numerator * otherFrac.numerator;
        int denom = this.denominator * otherFrac.denominator;
        Fraction new_fraction = new Fraction(num,denom);
        return new_fraction.getSimplifiedFraction();
    }

    /**
     * Multiply this fraction with a specified numerator and denominator
     *
     * @param numerator the numerator to multiply to this numerator
     * @param denominator the denominator to multiple to this denominator
     *
     * @return a new Fraction that is this fraction multiplied by a provided
     * numerator and denominator, in simplified form
     */
    public Fraction multiply(int numerator, int denominator) {
        return new Fraction(this.numerator * numerator,this.denominator*denominator);
    }

    /**
     * Is this fraction greater than {@code otherFrac}?
     *
     * @param otherFrac the other Fraction to compare to
     * @return true if this fraction is greater than {@code otherFrac}
     */
    public boolean isGreaterThan(Fraction otherFrac) {
        if (this.denominator == otherFrac.getDenominator()) {
            return (this.numerator > otherFrac.getNumerator());
        }
        else {
            return (this.numerator * otherFrac.getDenominator() > otherFrac.getNumerator()*this.denominator);
            }
    }

    /**
     * Is this Fraction equal to {@code otherFrac}?
     * NOTE: 1/2 == 3/6 (i.e numerators do not need
     * to be the same)
     *
     * @return true if this fraction is equal to otherFrac,
     * false otherwise.
     */
    public boolean isEqualTo(Fraction otherFrac) {
        Fraction simplified = otherFrac.getSimplifiedFraction();
        return (simplified.getNumerator() == this.numerator & simplified.getDenominator() == this.denominator);
    }

    /**
     * @return a String in the form of x/y, where x is the numerator and
     *         y is the denominator.
     *         However:
     *         - if the numerator is 0, then return "0".
     *         - if the denominator is 0, then return "ERROR - divide by 0"
     *         - if the numerator == denominator, then return "1"
     *         - otherwise, return numerator/denominator
     */
    @Override
    public String toString() {
        if (this.numerator == 0) {
            return "0";
        }
        if (this.denominator == 0) {
            return "ERROR - divide by 0";
        }
        else {
            String fractionstr = Integer.toString(this.numerator) + "/" + Integer.toString(this.denominator);
            return fractionstr;
        }
    }
}
