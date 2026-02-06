package project20280;

import org.junit.Test;
import project20280.stacksqueues.ArrayStack;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    to extend to different bases and bases greater than 9, you would replace %2 with %base and
    dec_num /= 2 to dec_num /= base.
    for bases greater than 9 we can use letters to represent them, and when we pop, we can use
    the letters to see what letters/numbers they should represent, e.g. a string called digits
    with 0 - 9, A - F, and then finding the number/letter that should be at the value when we pop.
 */
public class convertToBinary {
    public static String convertToBinary(long dec_num) {
        if (dec_num == 0) return "0";

        ArrayStack<Integer> s = new ArrayStack<>();

        while (dec_num > 0) {
            long remainder = dec_num % 2;
            s.push((int) remainder);
            dec_num /= 2;
        }

        StringBuilder sb = new StringBuilder();
        while (!s.isEmpty()) {
            sb.append(s.pop());
        }
        return sb.toString();
    }

    @Test
    public void testConvertToBinary() {
        assertEquals("10111", convertToBinary(23));
        assertEquals("111001000000101011000010011101010110110001100010000000000000", convertToBinary(1027010000000000000L));
        }
}
