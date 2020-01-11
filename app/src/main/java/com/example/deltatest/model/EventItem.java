package com.example.deltatest.model;


public class EventItem extends ListItem {

    private NationModel name ;
    private NationModel capital;


    public NationModel getName() {
        return name;
    }

    public NationModel getCapital(){
        return capital;
    }

    public EventItem(NationModel country){
      name = new NationModel();
      capital = new NationModel();

      name.setName(country.getName());
      capital.setCapital(country.getCapital());

}

    // here getters and setters
    // for title and so on, built
    // using event

    @Override
    public int getType() {
        return TYPE_EVENT;
    }

}
