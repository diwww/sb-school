package ru.production.ssobolevsky.espressotest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pro on 16.07.2018.
 */

public class SingletonData {

    private static List<String> NAMES;


    public static List<String> getInstance() {
        if (NAMES == null) {
            NAMES = new ArrayList<>();
        }
        return NAMES;
    }
}
