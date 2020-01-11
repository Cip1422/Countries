package com.example.deltatest.model;

public class HeaderItem extends ListItem {
    private NationModel region;

    public HeaderItem(String listOfRegions) {
        super();
        region = new NationModel();
        region.setRegion(listOfRegions);
    }

    public NationModel getRegion() {
        return region;
    }


    // here getters and setters
    // for title and so on, built
    // using date

    @Override
    public int getType() {
        return TYPE_HEADER;
    }

}
