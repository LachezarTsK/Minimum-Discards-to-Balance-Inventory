
class Solution {

    private companion object {
        const val REMOVED_ITEM = -1
    }

    fun minArrivalsToDiscard(itemsArrival: IntArray, windowSize: Int, maxNumberSameItemsInWindow: Int): Int {
        var numberOfDiscards = 0
        val maxItemValue = itemsArrival.max()
        val frequencyItems = IntArray(maxItemValue + 1)

        for (i in 0..<windowSize) {
            ++frequencyItems[itemsArrival[i]]
            if (frequencyItems[itemsArrival[i]] > maxNumberSameItemsInWindow) {
                ++numberOfDiscards
                --frequencyItems[itemsArrival[i]]
                itemsArrival[i] = REMOVED_ITEM
            }
        }

        var back = 0
        var front = windowSize

        while (front < itemsArrival.size) {
            if (itemsArrival[back] != REMOVED_ITEM) {
                --frequencyItems[itemsArrival[back]]
            }
            ++frequencyItems[itemsArrival[front]]

            if (frequencyItems[itemsArrival[front]] > maxNumberSameItemsInWindow) {
                ++numberOfDiscards
                --frequencyItems[itemsArrival[front]]
                itemsArrival[front] = REMOVED_ITEM
            }
            ++back
            ++front
        }
        return numberOfDiscards
    }
}
