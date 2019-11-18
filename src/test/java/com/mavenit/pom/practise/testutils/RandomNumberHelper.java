package com.mavenit.pom.practise.testutils;

import java.util.Random;

public class RandomNumberHelper {

    public int generateRandomNumber(int size)
    {
       return new Random().nextInt(size-1);
    }

}
