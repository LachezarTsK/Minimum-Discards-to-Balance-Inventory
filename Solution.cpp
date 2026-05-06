
#include <ranges>
#include <vector>
using namespace std;

class Solution {

    static const int REMOVED_ITEM = -1;

public:
    int minArrivalsToDiscard(vector<int>& itemsArrival, int windowSize, int maxNumberSameItemsInWindow) const {
        int numberOfDiscards = 0;
        int maxItemValue = *ranges::max_element(itemsArrival);
        vector<int> frequencyItems(maxItemValue + 1);

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

        while (front < itemsArrival.size()) {
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
};
