package com.assignment3.main;

import com.assignment3.model.Advertisement;
import com.assignment3.service.MaxProfitAdvertisement;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MediaCompany {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(MediaCompany.class.getName());

        // Create a list of advertisements
        List<Advertisement> ads = new ArrayList<>();
        ads.add(new Advertisement("advertisement1", 50, 30, "Title 1", "Content 1"));
        ads.add(new Advertisement("advertisement2", 100, 50, "Title 2", "Content 2"));
        ads.add(new Advertisement("advertisement3", 150, 70, "Title 3", "Content 3"));
        ads.add(new Advertisement("advertisement4", 200, 90, "Title 4", "Content 4"));

        // Example: Set maximum height to 100
        var maxHeight = 100;

        MaxProfitAdvertisement maxProfitAdvertisement = new MaxProfitAdvertisement();
        List<Advertisement> selectedAds = maxProfitAdvertisement.selectAds(ads, maxHeight);

        // Output the selected ads
        logger.info("Selected advertisements for maximizing profit");
        double totalProfit = 0;
        int totalHeight = 0;
        for (Advertisement ad : selectedAds) {
            logger.info("ID: " + ad.id + ", Price: " + ad.price + ", Height: " + ad.height + ", Ratio: " + ad.pricePerHeight());
            totalProfit += ad.price;
            totalHeight += ad.height;
        }
        logger.info("Total Profit:" + totalProfit);
        logger.info("Total Height Used: " + totalHeight + "/" + maxHeight);
    }

}
