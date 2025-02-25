public class HW04 {
    public static void main(String[] args) {
        System.out.println(getPolynomialDegree(0x3CF0));
        System.out.println(getPolynomialDegree(0x10000));
        System.out.println(getPolynomialDegree(0x00));
        System.out.println(getPolynomialDegree(0x01));

        System.out.println(decToHex(divide_galois(0x1000, 0x11B)));
        System.out.println(decToHex(divide_galois(0xE1, 0x11B)));
        System.out.println(decToHex(divide_galois(0x32CFE1, 0x11B)));
        System.out.println(decToHex(divide_galois(0xE1, 0x11B)));

        System.out.println(decToHex(multiply_galois(0xD5, 0x61, 0x11B)));
        System.out.println(decToHex(multiply_galois(0x1E3C, 0x1E3C, 0x11B)));
    }
    public static int getPolynomialDegree(int a) {
        int degree = -1;
        if(a == 0) {
            return degree;
        }
        while(a != 0) {
            ++degree;
            a >>= 1;
        }
        return degree;
    }

    public static int divide_galois(int dividend, int divisor) {
        int result = dividend;

        while (getPolynomialDegree(result) >= getPolynomialDegree(divisor)) {
            int shift = getPolynomialDegree(result) - getPolynomialDegree(divisor);
            result ^= (divisor << shift);
        }

        return result;
    }

    public static int multiply_galois(int a, int b, int m) {
        int result = 0;

        while (b > 0) {
            if ((b & 1) != 0) {
                result ^= a;
            }
            b >>= 1;
            a <<= 1;

            // If an overflows 8 bits, reduce modulo the irreducible polynomial
            if ((a & 0x100) != 0) {
                a ^= m;
            }
        }
        return result & 0xff;
    }
    public static String decToHex(int dec)
    {
        return Integer.toHexString(dec);
    }
}
