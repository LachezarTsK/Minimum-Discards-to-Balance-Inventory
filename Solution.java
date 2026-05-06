
import java.util.Arrays;

public class Solution {

    private static final int REMOVED_ITEM = -1;

    public int minArrivalsToDiscard(int[] itemsArrival, int windowSize, int maxNumberSameItemsInWindow) {
        int numberOfDiscards = 0;
        int maxItemValue = Arrays.stream(itemsArrival).max().getAsInt();
        int[] frequencyItems = new int[maxItemValue + 1];

        for (int i = 0; i < windowSize; ++i) {
            ++frequencyItems[itemsArrival[i]];
            if (frequencyItems[itemsArrival[i]] > maxNumberSameItemsInWindow) {
                ++numberOfDiscards;
                --frequencyItems[itemsArrival[i]];
                itemsArrival[i] = REMOVED_ITEM;
            }
        }

        int back = 0;
        int front = windowSize;

        while (front < itemsArrival.length) {
            if (itemsArrival[back] != REMOVED_ITEM) {
                --frequencyItems[itemsArrival[back]];
            }
            ++frequencyItems[itemsArrival[front]];

            if (frequencyItems[itemsArrival[front]] > maxNumberSameItemsInWindow) {
                ++numberOfDiscards;
                --frequencyItems[itemsArrival[front]];
                itemsArrival[front] = REMOVED_ITEM;
            }
            ++back;
            ++front;
        }
        return numberOfDiscards;
    }
}
