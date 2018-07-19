package ru.gcsales.seminar7.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class for getting random image urls
 */
public class ImageUrlRepository {

    private static final Random random = new Random();
    private static final List<String> imageUrls = Arrays.asList(
            "https://i.redditmedia.com/jCX0D_Plwy4HsQmA3pJ0PgtnqHkkY5X6QAtLD-vujhY.jpg?s=1242fed12ecff81ef95d4ceda1e10099",
            "https://i.redditmedia.com/vvQwiYUv9tqsivJE5AoyvvfwakVWER5usisqam5WZA4.jpg?fit=crop&crop=faces%2Centropy&arh=2&w=640&s=b8e33c978ecd19bbc99a339cdbf54eb0",
            "https://i.redditmedia.com/w4uy0WdBRuZh8ALZLmawFZLORoxYzQopI32USmtZRso.jpg?fit=crop&crop=faces%2Centropy&arh=2&w=640&s=67ac22b667c1715ff4c02ffadc109b3b",
            "https://i.redditmedia.com/_T_VEnB20qGS0kR3q5Lsr-fMJYIQT8VLWEQZ5kP3psg.jpg?fit=crop&crop=faces%2Centropy&arh=2&w=640&s=a0a3592c56e17f603c617b34beb8cb84",
            "https://i.redditmedia.com/riYPbLWke_Kfxy9ieWQXmSBSQLn81pOy5KgEQj_fW5Y.jpg?fit=crop&crop=faces%2Centropy&arh=2&w=640&s=0d8ce5288ad52234f87556915e340508",
            "https://i.redditmedia.com/QgDVm8YEwkD6gUFnhg72G9RdR9a7ynguG7blny0YSlY.jpg?fit=crop&crop=faces%2Centropy&arh=2&w=640&s=3235a678089a3883b650bb3e65ec535e",
            "https://i.redditmedia.com/O9ITqnBxzbf-rS2-RLIOH-4zXWAH49pkyAnCZXtEcHw.jpg?fit=crop&crop=faces%2Centropy&arh=2&w=640&s=9aa2400f86e2cca2278bb2825ab528ec",
            "https://i.redditmedia.com/kgyJzhkr0J2umteZsqQvj0i8RJhLWg0cNX1gG4tcGNE.jpg?fit=crop&crop=faces%2Centropy&arh=2&w=640&s=7a4bf5c554006b5151b4d24c9c60f07a"
    );

    private ImageUrlRepository() {
    }

    /**
     * Gets random image url
     *
     * @return random image url
     */
    public static String getImageUrl() {
        return imageUrls.get(random.nextInt(imageUrls.size()));
    }
}
