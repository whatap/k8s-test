package io.whatap.common.util;

import java.util.Random;

public class RandomUtils {
    /*
    더 높은 숫자의 랜덤 수가 확률적으로 더 많이나오는 함수
     */
    public static int randomManipulateBigNum(int min, int max) {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        double skewedDouble = Math.pow(randomDouble, 0.5);  // You can experiment with this value
        int skewedRandomNumber = min + (int)((max - min + 1) * skewedDouble);
        if (skewedRandomNumber > max) {
            skewedRandomNumber = max;
        } else if (skewedRandomNumber < min) {
            skewedRandomNumber = min;
        }
        return skewedRandomNumber;
    }
}
