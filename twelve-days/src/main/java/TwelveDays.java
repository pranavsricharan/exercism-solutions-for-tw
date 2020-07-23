import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class TwelveDays {
    private final String TEMPLATE = "On the %s day of Christmas my true love gave to me: %s.\n";
    private final String[] DAYS = {
        "first", "second", "third", "fourth",
        "fifth", "sixth", "seventh", "eighth",
        "ninth", "tenth","eleventh", "twelfth"
    };
    private final String[] GIFTS = {
        "twelve Drummers Drumming",
        "eleven Pipers Piping",
        "ten Lords-a-Leaping",
        "nine Ladies Dancing",
        "eight Maids-a-Milking",
        "seven Swans-a-Swimming",
        "six Geese-a-Laying",
        "five Gold Rings",
        "four Calling Birds",
        "three French Hens",
        "two Turtle Doves",
        "a Partridge in a Pear Tree"
    };

    String verse(int verseNumber) {
        int length = GIFTS.length;
        IntFunction<String> giftMapper = this.getGiftMapper(verseNumber);
        
        String giftsString = IntStream.range(length - verseNumber, length)
            .mapToObj(giftMapper)
            .collect(Collectors.joining(", "));

        return String.format(TEMPLATE, DAYS[verseNumber -1], giftsString);
    }

    String verses(int startVerse, int endVerse) {
        return IntStream.rangeClosed(startVerse, endVerse)
            .mapToObj(this::verse)
            .collect(Collectors.joining("\n"));
    }
    
    String sing() {
        return verses(1, 12);
    }

    private IntFunction<String> getGiftMapper(int totalGifts) {
        String prefix = (totalGifts > 1) ? "and ": "";

        return (giftIndex) -> {
            String gift = GIFTS[giftIndex];
            return (giftIndex == GIFTS.length - 1) ? prefix + gift: gift;
        };
    }
}
