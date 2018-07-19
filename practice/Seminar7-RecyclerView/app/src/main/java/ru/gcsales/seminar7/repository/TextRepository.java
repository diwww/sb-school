package ru.gcsales.seminar7.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class for getting random text strings
 */
public class TextRepository {

    private static final Random random = new Random();
    private static final List<String> texts = Arrays.asList(
            "Lorem ipsum dolor sit amet.",
            "Aliquam ornare fermentum lacus, sed.",
            "Nunc id neque at purus.",
            "Morbi euismod risus sed libero.",
            "Donec odio velit, dictum sed.",
            "Donec gravida turpis non pulvinar.",
            "Nullam maximus nisl vitae felis."
    );

    private TextRepository() {
    }

    /**
     * Gets random text string
     *
     * @return random text string
     */
    public static String getText() {
        return texts.get(random.nextInt(texts.size()));
    }
}
