
package main

import "slices"

const REMOVED_ITEM = -1

func minArrivalsToDiscard(itemsArrival []int, windowSize int, maxNumberSameItemsInWindow int) int {
    numberOfDiscards := 0
    maxItemValue := slices.Max(itemsArrival)
    frequencyItems := make([]int, maxItemValue+1)

    for i := range windowSize {
        frequencyItems[itemsArrival[i]]++
        if frequencyItems[itemsArrival[i]] > maxNumberSameItemsInWindow {
            numberOfDiscards++
            frequencyItems[itemsArrival[i]]--
            itemsArrival[i] = REMOVED_ITEM
        }
    }

    back := 0
    front := windowSize

    for front < len(itemsArrival) {
        if itemsArrival[back] != REMOVED_ITEM {
            frequencyItems[itemsArrival[back]]--
        }
        frequencyItems[itemsArrival[front]]++

        if frequencyItems[itemsArrival[front]] > maxNumberSameItemsInWindow {
            numberOfDiscards++
            frequencyItems[itemsArrival[front]]--
            itemsArrival[front] = REMOVED_ITEM
        }
        back++
        front++
    }
    return numberOfDiscards
}
