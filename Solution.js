
/**
 * @param {number[]} itemsArrival
 * @param {number} windowSize
 * @param {number} maxNumberSameItemsInWindow
 * @return {number}
 */
var minArrivalsToDiscard = function (itemsArrival, windowSize, maxNumberSameItemsInWindow) {
    const REMOVED_ITEM = -1;
    const maxItemValue = Math.max(...itemsArrival);
    let numberOfDiscards = 0;
    const frequencyItems = new Array(maxItemValue + 1).fill(0);

    for (let i = 0; i < windowSize; ++i) {
        ++frequencyItems[itemsArrival[i]];
        if (frequencyItems[itemsArrival[i]] > maxNumberSameItemsInWindow) {
            ++numberOfDiscards;
            --frequencyItems[itemsArrival[i]];
            itemsArrival[i] = REMOVED_ITEM;
        }
    }

    let back = 0;
    let front = windowSize;

    while (front < itemsArrival.length) {
        if (itemsArrival[back] !== REMOVED_ITEM) {
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
};
