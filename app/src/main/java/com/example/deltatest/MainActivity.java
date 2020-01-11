package com.example.deltatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.deltatest.model.EventItem;
import com.example.deltatest.model.HeaderItem;
import com.example.deltatest.model.ListItem;
import com.example.deltatest.model.NationModel;
import com.example.deltatest.model.NationalRecyclerViewAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
/**
 * Country
 * {
 *   name: "United States of America",
 *   capital: "Washington, D.C.",
 *   region: "Americas"
 * }
 *
 * Task: Given a list of countries, do:
 * 1. Display the list of countries grouped by their regions in a RecyclerView
 *    a. Each region BOLDED in its own view
 *    b. Each country and its capital in its own view
 * 2. Write unit tests on your logic that parses the countries into their corresponding regions
 *
 * Example:
 * [
 *   {
 *     name: "United States of America",
 *     capital: "Washington, D.C.",
 *     region: "Americas"
 *   },
 *   {
 *     name: "Peru",
 *     capital: "Lima",
 *     region: "Americas"
 *   },
 *   {
 *     name: "France",
 *     capital: "Paris",
 *     region: "Europe"
 *   }
 *
 *     *
 *          * Should look something like
 *          *
 *          * ____________________________
 *          * Americas //bolded
 *          * ____________________________
 *          * United States of America
 *          * Washington, D.C.
 *          *____________________________
 *          * Peru
 *          * Lima
 *          * ____________________________
 *          * Europe //bolded
 *          * ____________________________
 *          * France
 *          * Paris
 *          * ____________________________
 *          */



    String countries = "[{\"name\":\"Afghanistan\",\"capital\":\"Kabul\",\"region\":\"Asia\"},{\"name\":\"Åland Islands\",\"capital\":\"Mariehamn\",\"region\":\"Europe\"},{\"name\":\"Albania\",\"capital\":\"Tirana\",\"region\":\"Europe\"},{\"name\":\"Algeria\",\"capital\":\"Algiers\",\"region\":\"Africa\"},{\"name\":\"American Samoa\",\"capital\":\"Pago Pago\",\"region\":\"Oceania\"},{\"name\":\"Andorra\",\"capital\":\"Andorra la Vella\",\"region\":\"Europe\"},{\"name\":\"Angola\",\"capital\":\"Luanda\",\"region\":\"Africa\"},{\"name\":\"Anguilla\",\"capital\":\"The Valley\",\"region\":\"Americas\"},{\"name\":\"Antarctica\",\"capital\":\"\",\"region\":\"Polar\"},{\"name\":\"Antigua and Barbuda\",\"capital\":\"Saint John's\",\"region\":\"Americas\"},{\"name\":\"Argentina\",\"capital\":\"Buenos Aires\",\"region\":\"Americas\"},{\"name\":\"Armenia\",\"capital\":\"Yerevan\",\"region\":\"Asia\"},{\"name\":\"Aruba\",\"capital\":\"Oranjestad\",\"region\":\"Americas\"},{\"name\":\"Australia\",\"capital\":\"Canberra\",\"region\":\"Oceania\"},{\"name\":\"Austria\",\"capital\":\"Vienna\",\"region\":\"Europe\"},{\"name\":\"Azerbaijan\",\"capital\":\"Baku\",\"region\":\"Asia\"},{\"name\":\"Bahamas\",\"capital\":\"Nassau\",\"region\":\"Americas\"},{\"name\":\"Bahrain\",\"capital\":\"Manama\",\"region\":\"Asia\"},{\"name\":\"Bangladesh\",\"capital\":\"Dhaka\",\"region\":\"Asia\"},{\"name\":\"Barbados\",\"capital\":\"Bridgetown\",\"region\":\"Americas\"},{\"name\":\"Belarus\",\"capital\":\"Minsk\",\"region\":\"Europe\"},{\"name\":\"Belgium\",\"capital\":\"Brussels\",\"region\":\"Europe\"},{\"name\":\"Belize\",\"capital\":\"Belmopan\",\"region\":\"Americas\"},{\"name\":\"Benin\",\"capital\":\"Porto-Novo\",\"region\":\"Africa\"},{\"name\":\"Bermuda\",\"capital\":\"Hamilton\",\"region\":\"Americas\"},{\"name\":\"Bhutan\",\"capital\":\"Thimphu\",\"region\":\"Asia\"},{\"name\":\"Bolivia (Plurinational State of)\",\"capital\":\"Sucre\",\"region\":\"Americas\"},{\"name\":\"Bonaire, Sint Eustatius and Saba\",\"capital\":\"Kralendijk\",\"region\":\"Americas\"},{\"name\":\"Bosnia and Herzegovina\",\"capital\":\"Sarajevo\",\"region\":\"Europe\"},{\"name\":\"Botswana\",\"capital\":\"Gaborone\",\"region\":\"Africa\"},{\"name\":\"Bouvet Island\",\"capital\":\"\",\"region\":\"\"},{\"name\":\"Brazil\",\"capital\":\"Brasília\",\"region\":\"Americas\"},{\"name\":\"British Indian Ocean Territory\",\"capital\":\"Diego Garcia\",\"region\":\"Africa\"},{\"name\":\"United States Minor Outlying Islands\",\"capital\":\"\",\"region\":\"Americas\"},{\"name\":\"Virgin Islands (British)\",\"capital\":\"Road Town\",\"region\":\"Americas\"},{\"name\":\"Virgin Islands (U.S.)\",\"capital\":\"Charlotte Amalie\",\"region\":\"Americas\"},{\"name\":\"Brunei Darussalam\",\"capital\":\"Bandar Seri Begawan\",\"region\":\"Asia\"},{\"name\":\"Bulgaria\",\"capital\":\"Sofia\",\"region\":\"Europe\"},{\"name\":\"Burkina Faso\",\"capital\":\"Ouagadougou\",\"region\":\"Africa\"},{\"name\":\"Burundi\",\"capital\":\"Bujumbura\",\"region\":\"Africa\"},{\"name\":\"Cambodia\",\"capital\":\"Phnom Penh\",\"region\":\"Asia\"},{\"name\":\"Cameroon\",\"capital\":\"Yaoundé\",\"region\":\"Africa\"},{\"name\":\"Canada\",\"capital\":\"Ottawa\",\"region\":\"Americas\"},{\"name\":\"Cabo Verde\",\"capital\":\"Praia\",\"region\":\"Africa\"},{\"name\":\"Cayman Islands\",\"capital\":\"George Town\",\"region\":\"Americas\"},{\"name\":\"Central African Republic\",\"capital\":\"Bangui\",\"region\":\"Africa\"},{\"name\":\"Chad\",\"capital\":\"N'Djamena\",\"region\":\"Africa\"},{\"name\":\"Chile\",\"capital\":\"Santiago\",\"region\":\"Americas\"},{\"name\":\"China\",\"capital\":\"Beijing\",\"region\":\"Asia\"},{\"name\":\"Christmas Island\",\"capital\":\"Flying Fish Cove\",\"region\":\"Oceania\"},{\"name\":\"Cocos (Keeling) Islands\",\"capital\":\"West Island\",\"region\":\"Oceania\"},{\"name\":\"Colombia\",\"capital\":\"Bogotá\",\"region\":\"Americas\"},{\"name\":\"Comoros\",\"capital\":\"Moroni\",\"region\":\"Africa\"},{\"name\":\"Congo\",\"capital\":\"Brazzaville\",\"region\":\"Africa\"},{\"name\":\"Congo (Democratic Republic of the)\",\"capital\":\"Kinshasa\",\"region\":\"Africa\"},{\"name\":\"Cook Islands\",\"capital\":\"Avarua\",\"region\":\"Oceania\"},{\"name\":\"Costa Rica\",\"capital\":\"San José\",\"region\":\"Americas\"},{\"name\":\"Croatia\",\"capital\":\"Zagreb\",\"region\":\"Europe\"},{\"name\":\"Cuba\",\"capital\":\"Havana\",\"region\":\"Americas\"},{\"name\":\"Curaçao\",\"capital\":\"Willemstad\",\"region\":\"Americas\"},{\"name\":\"Cyprus\",\"capital\":\"Nicosia\",\"region\":\"Europe\"},{\"name\":\"Czech Republic\",\"capital\":\"Prague\",\"region\":\"Europe\"},{\"name\":\"Denmark\",\"capital\":\"Copenhagen\",\"region\":\"Europe\"},{\"name\":\"Djibouti\",\"capital\":\"Djibouti\",\"region\":\"Africa\"},{\"name\":\"Dominica\",\"capital\":\"Roseau\",\"region\":\"Americas\"},{\"name\":\"Dominican Republic\",\"capital\":\"Santo Domingo\",\"region\":\"Americas\"},{\"name\":\"Ecuador\",\"capital\":\"Quito\",\"region\":\"Americas\"},{\"name\":\"Egypt\",\"capital\":\"Cairo\",\"region\":\"Africa\"},{\"name\":\"El Salvador\",\"capital\":\"San Salvador\",\"region\":\"Americas\"},{\"name\":\"Equatorial Guinea\",\"capital\":\"Malabo\",\"region\":\"Africa\"},{\"name\":\"Eritrea\",\"capital\":\"Asmara\",\"region\":\"Africa\"},{\"name\":\"Estonia\",\"capital\":\"Tallinn\",\"region\":\"Europe\"},{\"name\":\"Ethiopia\",\"capital\":\"Addis Ababa\",\"region\":\"Africa\"},{\"name\":\"Falkland Islands (Malvinas)\",\"capital\":\"Stanley\",\"region\":\"Americas\"},{\"name\":\"Faroe Islands\",\"capital\":\"Tórshavn\",\"region\":\"Europe\"},{\"name\":\"Fiji\",\"capital\":\"Suva\",\"region\":\"Oceania\"},{\"name\":\"Finland\",\"capital\":\"Helsinki\",\"region\":\"Europe\"},{\"name\":\"France\",\"capital\":\"Paris\",\"region\":\"Europe\"},{\"name\":\"French Guiana\",\"capital\":\"Cayenne\",\"region\":\"Americas\"},{\"name\":\"French Polynesia\",\"capital\":\"Papeetē\",\"region\":\"Oceania\"},{\"name\":\"French Southern Territories\",\"capital\":\"Port-aux-Français\",\"region\":\"Africa\"},{\"name\":\"Gabon\",\"capital\":\"Libreville\",\"region\":\"Africa\"},{\"name\":\"Gambia\",\"capital\":\"Banjul\",\"region\":\"Africa\"},{\"name\":\"Georgia\",\"capital\":\"Tbilisi\",\"region\":\"Asia\"},{\"name\":\"Germany\",\"capital\":\"Berlin\",\"region\":\"Europe\"},{\"name\":\"Ghana\",\"capital\":\"Accra\",\"region\":\"Africa\"},{\"name\":\"Gibraltar\",\"capital\":\"Gibraltar\",\"region\":\"Europe\"},{\"name\":\"Greece\",\"capital\":\"Athens\",\"region\":\"Europe\"},{\"name\":\"Greenland\",\"capital\":\"Nuuk\",\"region\":\"Americas\"},{\"name\":\"Grenada\",\"capital\":\"St. George's\",\"region\":\"Americas\"},{\"name\":\"Guadeloupe\",\"capital\":\"Basse-Terre\",\"region\":\"Americas\"},{\"name\":\"Guam\",\"capital\":\"Hagåtña\",\"region\":\"Oceania\"},{\"name\":\"Guatemala\",\"capital\":\"Guatemala City\",\"region\":\"Americas\"},{\"name\":\"Guernsey\",\"capital\":\"St. Peter Port\",\"region\":\"Europe\"},{\"name\":\"Guinea\",\"capital\":\"Conakry\",\"region\":\"Africa\"},{\"name\":\"Guinea-Bissau\",\"capital\":\"Bissau\",\"region\":\"Africa\"},{\"name\":\"Guyana\",\"capital\":\"Georgetown\",\"region\":\"Americas\"},{\"name\":\"Haiti\",\"capital\":\"Port-au-Prince\",\"region\":\"Americas\"},{\"name\":\"Heard Island and McDonald Islands\",\"capital\":\"\",\"region\":\"\"},{\"name\":\"Holy See\",\"capital\":\"Rome\",\"region\":\"Europe\"},{\"name\":\"Honduras\",\"capital\":\"Tegucigalpa\",\"region\":\"Americas\"},{\"name\":\"Hong Kong\",\"capital\":\"City of Victoria\",\"region\":\"Asia\"},{\"name\":\"Hungary\",\"capital\":\"Budapest\",\"region\":\"Europe\"},{\"name\":\"Iceland\",\"capital\":\"Reykjavík\",\"region\":\"Europe\"},{\"name\":\"India\",\"capital\":\"New Delhi\",\"region\":\"Asia\"},{\"name\":\"Indonesia\",\"capital\":\"Jakarta\",\"region\":\"Asia\"},{\"name\":\"Côte d'Ivoire\",\"capital\":\"Yamoussoukro\",\"region\":\"Africa\"},{\"name\":\"Iran (Islamic Republic of)\",\"capital\":\"Tehran\",\"region\":\"Asia\"},{\"name\":\"Iraq\",\"capital\":\"Baghdad\",\"region\":\"Asia\"},{\"name\":\"Ireland\",\"capital\":\"Dublin\",\"region\":\"Europe\"},{\"name\":\"Isle of Man\",\"capital\":\"Douglas\",\"region\":\"Europe\"},{\"name\":\"Israel\",\"capital\":\"Jerusalem\",\"region\":\"Asia\"},{\"name\":\"Italy\",\"capital\":\"Rome\",\"region\":\"Europe\"},{\"name\":\"Jamaica\",\"capital\":\"Kingston\",\"region\":\"Americas\"},{\"name\":\"Japan\",\"capital\":\"Tokyo\",\"region\":\"Asia\"},{\"name\":\"Jersey\",\"capital\":\"Saint Helier\",\"region\":\"Europe\"},{\"name\":\"Jordan\",\"capital\":\"Amman\",\"region\":\"Asia\"},{\"name\":\"Kazakhstan\",\"capital\":\"Astana\",\"region\":\"Asia\"},{\"name\":\"Kenya\",\"capital\":\"Nairobi\",\"region\":\"Africa\"},{\"name\":\"Kiribati\",\"capital\":\"South Tarawa\",\"region\":\"Oceania\"},{\"name\":\"Kuwait\",\"capital\":\"Kuwait City\",\"region\":\"Asia\"},{\"name\":\"Kyrgyzstan\",\"capital\":\"Bishkek\",\"region\":\"Asia\"},{\"name\":\"Lao People's Democratic Republic\",\"capital\":\"Vientiane\",\"region\":\"Asia\"},{\"name\":\"Latvia\",\"capital\":\"Riga\",\"region\":\"Europe\"},{\"name\":\"Lebanon\",\"capital\":\"Beirut\",\"region\":\"Asia\"},{\"name\":\"Lesotho\",\"capital\":\"Maseru\",\"region\":\"Africa\"},{\"name\":\"Liberia\",\"capital\":\"Monrovia\",\"region\":\"Africa\"},{\"name\":\"Libya\",\"capital\":\"Tripoli\",\"region\":\"Africa\"},{\"name\":\"Liechtenstein\",\"capital\":\"Vaduz\",\"region\":\"Europe\"},{\"name\":\"Lithuania\",\"capital\":\"Vilnius\",\"region\":\"Europe\"},{\"name\":\"Luxembourg\",\"capital\":\"Luxembourg\",\"region\":\"Europe\"},{\"name\":\"Macao\",\"capital\":\"\",\"region\":\"Asia\"},{\"name\":\"Macedonia (the former Yugoslav Republic of)\",\"capital\":\"Skopje\",\"region\":\"Europe\"},{\"name\":\"Madagascar\",\"capital\":\"Antananarivo\",\"region\":\"Africa\"},{\"name\":\"Malawi\",\"capital\":\"Lilongwe\",\"region\":\"Africa\"},{\"name\":\"Malaysia\",\"capital\":\"Kuala Lumpur\",\"region\":\"Asia\"},{\"name\":\"Maldives\",\"capital\":\"Malé\",\"region\":\"Asia\"},{\"name\":\"Mali\",\"capital\":\"Bamako\",\"region\":\"Africa\"},{\"name\":\"Malta\",\"capital\":\"Valletta\",\"region\":\"Europe\"},{\"name\":\"Marshall Islands\",\"capital\":\"Majuro\",\"region\":\"Oceania\"},{\"name\":\"Martinique\",\"capital\":\"Fort-de-France\",\"region\":\"Americas\"},{\"name\":\"Mauritania\",\"capital\":\"Nouakchott\",\"region\":\"Africa\"},{\"name\":\"Mauritius\",\"capital\":\"Port Louis\",\"region\":\"Africa\"},{\"name\":\"Mayotte\",\"capital\":\"Mamoudzou\",\"region\":\"Africa\"},{\"name\":\"Mexico\",\"capital\":\"Mexico City\",\"region\":\"Americas\"},{\"name\":\"Micronesia (Federated States of)\",\"capital\":\"Palikir\",\"region\":\"Oceania\"},{\"name\":\"Moldova (Republic of)\",\"capital\":\"Chișinău\",\"region\":\"Europe\"},{\"name\":\"Monaco\",\"capital\":\"Monaco\",\"region\":\"Europe\"},{\"name\":\"Mongolia\",\"capital\":\"Ulan Bator\",\"region\":\"Asia\"},{\"name\":\"Montenegro\",\"capital\":\"Podgorica\",\"region\":\"Europe\"},{\"name\":\"Montserrat\",\"capital\":\"Plymouth\",\"region\":\"Americas\"},{\"name\":\"Morocco\",\"capital\":\"Rabat\",\"region\":\"Africa\"},{\"name\":\"Mozambique\",\"capital\":\"Maputo\",\"region\":\"Africa\"},{\"name\":\"Myanmar\",\"capital\":\"Naypyidaw\",\"region\":\"Asia\"},{\"name\":\"Namibia\",\"capital\":\"Windhoek\",\"region\":\"Africa\"},{\"name\":\"Nauru\",\"capital\":\"Yaren\",\"region\":\"Oceania\"},{\"name\":\"Nepal\",\"capital\":\"Kathmandu\",\"region\":\"Asia\"},{\"name\":\"Netherlands\",\"capital\":\"Amsterdam\",\"region\":\"Europe\"},{\"name\":\"New Caledonia\",\"capital\":\"Nouméa\",\"region\":\"Oceania\"},{\"name\":\"New Zealand\",\"capital\":\"Wellington\",\"region\":\"Oceania\"},{\"name\":\"Nicaragua\",\"capital\":\"Managua\",\"region\":\"Americas\"},{\"name\":\"Niger\",\"capital\":\"Niamey\",\"region\":\"Africa\"},{\"name\":\"Nigeria\",\"capital\":\"Abuja\",\"region\":\"Africa\"},{\"name\":\"Niue\",\"capital\":\"Alofi\",\"region\":\"Oceania\"},{\"name\":\"Norfolk Island\",\"capital\":\"Kingston\",\"region\":\"Oceania\"},{\"name\":\"Korea (Democratic People's Republic of)\",\"capital\":\"Pyongyang\",\"region\":\"Asia\"},{\"name\":\"Northern Mariana Islands\",\"capital\":\"Saipan\",\"region\":\"Oceania\"},{\"name\":\"Norway\",\"capital\":\"Oslo\",\"region\":\"Europe\"},{\"name\":\"Oman\",\"capital\":\"Muscat\",\"region\":\"Asia\"},{\"name\":\"Pakistan\",\"capital\":\"Islamabad\",\"region\":\"Asia\"},{\"name\":\"Palau\",\"capital\":\"Ngerulmud\",\"region\":\"Oceania\"},{\"name\":\"Palestine, State of\",\"capital\":\"Ramallah\",\"region\":\"Asia\"},{\"name\":\"Panama\",\"capital\":\"Panama City\",\"region\":\"Americas\"},{\"name\":\"Papua New Guinea\",\"capital\":\"Port Moresby\",\"region\":\"Oceania\"},{\"name\":\"Paraguay\",\"capital\":\"Asunción\",\"region\":\"Americas\"},{\"name\":\"Peru\",\"capital\":\"Lima\",\"region\":\"Americas\"},{\"name\":\"Philippines\",\"capital\":\"Manila\",\"region\":\"Asia\"},{\"name\":\"Pitcairn\",\"capital\":\"Adamstown\",\"region\":\"Oceania\"},{\"name\":\"Poland\",\"capital\":\"Warsaw\",\"region\":\"Europe\"},{\"name\":\"Portugal\",\"capital\":\"Lisbon\",\"region\":\"Europe\"},{\"name\":\"Puerto Rico\",\"capital\":\"San Juan\",\"region\":\"Americas\"},{\"name\":\"Qatar\",\"capital\":\"Doha\",\"region\":\"Asia\"},{\"name\":\"Republic of Kosovo\",\"capital\":\"Pristina\",\"region\":\"Europe\"},{\"name\":\"Réunion\",\"capital\":\"Saint-Denis\",\"region\":\"Africa\"},{\"name\":\"Romania\",\"capital\":\"Bucharest\",\"region\":\"Europe\"},{\"name\":\"Russian Federation\",\"capital\":\"Moscow\",\"region\":\"Europe\"},{\"name\":\"Rwanda\",\"capital\":\"Kigali\",\"region\":\"Africa\"},{\"name\":\"Saint Barthélemy\",\"capital\":\"Gustavia\",\"region\":\"Americas\"},{\"name\":\"Saint Helena, Ascension and Tristan da Cunha\",\"capital\":\"Jamestown\",\"region\":\"Africa\"},{\"name\":\"Saint Kitts and Nevis\",\"capital\":\"Basseterre\",\"region\":\"Americas\"},{\"name\":\"Saint Lucia\",\"capital\":\"Castries\",\"region\":\"Americas\"},{\"name\":\"Saint Martin (French part)\",\"capital\":\"Marigot\",\"region\":\"Americas\"},{\"name\":\"Saint Pierre and Miquelon\",\"capital\":\"Saint-Pierre\",\"region\":\"Americas\"},{\"name\":\"Saint Vincent and the Grenadines\",\"capital\":\"Kingstown\",\"region\":\"Americas\"},{\"name\":\"Samoa\",\"capital\":\"Apia\",\"region\":\"Oceania\"},{\"name\":\"San Marino\",\"capital\":\"City of San Marino\",\"region\":\"Europe\"},{\"name\":\"Sao Tome and Principe\",\"capital\":\"São Tomé\",\"region\":\"Africa\"},{\"name\":\"Saudi Arabia\",\"capital\":\"Riyadh\",\"region\":\"Asia\"},{\"name\":\"Senegal\",\"capital\":\"Dakar\",\"region\":\"Africa\"},{\"name\":\"Serbia\",\"capital\":\"Belgrade\",\"region\":\"Europe\"},{\"name\":\"Seychelles\",\"capital\":\"Victoria\",\"region\":\"Africa\"},{\"name\":\"Sierra Leone\",\"capital\":\"Freetown\",\"region\":\"Africa\"},{\"name\":\"Singapore\",\"capital\":\"Singapore\",\"region\":\"Asia\"},{\"name\":\"Sint Maarten (Dutch part)\",\"capital\":\"Philipsburg\",\"region\":\"Americas\"},{\"name\":\"Slovakia\",\"capital\":\"Bratislava\",\"region\":\"Europe\"},{\"name\":\"Slovenia\",\"capital\":\"Ljubljana\",\"region\":\"Europe\"},{\"name\":\"Solomon Islands\",\"capital\":\"Honiara\",\"region\":\"Oceania\"},{\"name\":\"Somalia\",\"capital\":\"Mogadishu\",\"region\":\"Africa\"},{\"name\":\"South Africa\",\"capital\":\"Pretoria\",\"region\":\"Africa\"},{\"name\":\"South Georgia and the South Sandwich Islands\",\"capital\":\"King Edward Point\",\"region\":\"Americas\"},{\"name\":\"Korea (Republic of)\",\"capital\":\"Seoul\",\"region\":\"Asia\"},{\"name\":\"South Sudan\",\"capital\":\"Juba\",\"region\":\"Africa\"},{\"name\":\"Spain\",\"capital\":\"Madrid\",\"region\":\"Europe\"},{\"name\":\"Sri Lanka\",\"capital\":\"Colombo\",\"region\":\"Asia\"},{\"name\":\"Sudan\",\"capital\":\"Khartoum\",\"region\":\"Africa\"},{\"name\":\"Suriname\",\"capital\":\"Paramaribo\",\"region\":\"Americas\"},{\"name\":\"Svalbard and Jan Mayen\",\"capital\":\"Longyearbyen\",\"region\":\"Europe\"},{\"name\":\"Swaziland\",\"capital\":\"Lobamba\",\"region\":\"Africa\"},{\"name\":\"Sweden\",\"capital\":\"Stockholm\",\"region\":\"Europe\"},{\"name\":\"Switzerland\",\"capital\":\"Bern\",\"region\":\"Europe\"},{\"name\":\"Syrian Arab Republic\",\"capital\":\"Damascus\",\"region\":\"Asia\"},{\"name\":\"Taiwan\",\"capital\":\"Taipei\",\"region\":\"Asia\"},{\"name\":\"Tajikistan\",\"capital\":\"Dushanbe\",\"region\":\"Asia\"},{\"name\":\"Tanzania, United Republic of\",\"capital\":\"Dodoma\",\"region\":\"Africa\"},{\"name\":\"Thailand\",\"capital\":\"Bangkok\",\"region\":\"Asia\"},{\"name\":\"Timor-Leste\",\"capital\":\"Dili\",\"region\":\"Asia\"},{\"name\":\"Togo\",\"capital\":\"Lomé\",\"region\":\"Africa\"},{\"name\":\"Tokelau\",\"capital\":\"Fakaofo\",\"region\":\"Oceania\"},{\"name\":\"Tonga\",\"capital\":\"Nuku'alofa\",\"region\":\"Oceania\"},{\"name\":\"Trinidad and Tobago\",\"capital\":\"Port of Spain\",\"region\":\"Americas\"},{\"name\":\"Tunisia\",\"capital\":\"Tunis\",\"region\":\"Africa\"},{\"name\":\"Turkey\",\"capital\":\"Ankara\",\"region\":\"Asia\"},{\"name\":\"Turkmenistan\",\"capital\":\"Ashgabat\",\"region\":\"Asia\"},{\"name\":\"Turks and Caicos Islands\",\"capital\":\"Cockburn Town\",\"region\":\"Americas\"},{\"name\":\"Tuvalu\",\"capital\":\"Funafuti\",\"region\":\"Oceania\"},{\"name\":\"Uganda\",\"capital\":\"Kampala\",\"region\":\"Africa\"},{\"name\":\"Ukraine\",\"capital\":\"Kiev\",\"region\":\"Europe\"},{\"name\":\"United Arab Emirates\",\"capital\":\"Abu Dhabi\",\"region\":\"Asia\"},{\"name\":\"United Kingdom of Great Britain and Northern Ireland\",\"capital\":\"London\",\"region\":\"Europe\"},{\"name\":\"United States of America\",\"capital\":\"Washington, D.C.\",\"region\":\"Americas\"},{\"name\":\"Uruguay\",\"capital\":\"Montevideo\",\"region\":\"Americas\"},{\"name\":\"Uzbekistan\",\"capital\":\"Tashkent\",\"region\":\"Asia\"},{\"name\":\"Vanuatu\",\"capital\":\"Port Vila\",\"region\":\"Oceania\"},{\"name\":\"Venezuela (Bolivarian Republic of)\",\"capital\":\"Caracas\",\"region\":\"Americas\"},{\"name\":\"Viet Nam\",\"capital\":\"Hanoi\",\"region\":\"Asia\"},{\"name\":\"Wallis and Futuna\",\"capital\":\"Mata-Utu\",\"region\":\"Oceania\"},{\"name\":\"Western Sahara\",\"capital\":\"El Aaiún\",\"region\":\"Africa\"},{\"name\":\"Yemen\",\"capital\":\"Sana'a\",\"region\":\"Asia\"},{\"name\":\"Zambia\",\"capital\":\"Lusaka\",\"region\":\"Africa\"},{\"name\":\"Zimbabwe\",\"capital\":\"Harare\",\"region\":\"Africa\"},{\"name\":\"\",\"capital\":\"\",\"region\":\"Polar\"}]";
    NationalRecyclerViewAdapter adapter;
    RecyclerView rvNationalList;
    private List<ListItem> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<NationModel> listToDisplay = convertStringToList();

        Map<String, ArrayList<NationModel>> mappedList = toMap(listToDisplay);

        for (String listOfRegions: mappedList.keySet()) {
            HeaderItem header = new HeaderItem(listOfRegions);

            items.add(header);

            for ( NationModel country : mappedList.get(listOfRegions)) {
                EventItem item =  new EventItem(country);
                items.add(item);

            }
        }

        rvNationalList = findViewById(R.id.rvNationalList);
        adapter = new NationalRecyclerViewAdapter(items);
        rvNationalList.setLayoutManager(new LinearLayoutManager(this));
        rvNationalList.setAdapter(adapter);

    }

    private ArrayList<NationModel> convertStringToList() {
        Type returnType = new TypeToken<ArrayList<NationModel>>(){}.getType();
        return new Gson().fromJson(countries,returnType);
    }


    private Map<String, ArrayList<NationModel>> toMap(ArrayList<NationModel> items) {
        Map<String, ArrayList<NationModel>> map = new TreeMap<>();
        for ( int i = 0; i < items.size(); i++) {
            ArrayList<NationModel> value;
            value = map.get(items.get(i).getRegion());
            if (value == null) {
                value = new ArrayList<>();
                map.put(items.get(i).getRegion(), value);
            }
            value.add(items.get(i));


        }
        return map;
    }

}