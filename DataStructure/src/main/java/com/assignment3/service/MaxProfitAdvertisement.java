package com.assignment3.service;

import com.assignment3.model.Advertisement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaxProfitAdvertisement {

    /**
     * Method to select advertisements with maximum profit.
     *
     * @param ads       List of advertisements.
     * @param maxHeight Max height available on the page.
     * @return List of selected advertisements with maximum profit.
     *
     * Sorting the advertisements takes O(n log n) and Sorting the list and storing selected ads both take O(n) space
     * n is number of advertisements
     * Time Complexity  Space Complexity
     *  O(n log n)              O(n)
     *
     */
    public List<Advertisement> selectAds(List<Advertisement> ads, int maxHeight) {
        // Sort advertisements by their price-to-height ratio in descending order
        Collections.sort(ads, Comparator.comparingDouble(Advertisement::pricePerHeight).reversed());

        List<Advertisement> selectedAds = new ArrayList<>();
        int remainingHeight = maxHeight;

        // Select ads until there is no more space
        for (Advertisement ad : ads) {
            if (ad.getHeight() <= remainingHeight) {
                selectedAds.add(ad);
                remainingHeight -= ad.getHeight();  // Reduce available height
            }
        }

        return selectedAds;  // Return the selected advertisements
    }
}
