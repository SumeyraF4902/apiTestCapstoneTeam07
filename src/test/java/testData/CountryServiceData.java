package testData;

import utilities.JsonToJava;

import java.util.HashMap;
import java.util.Map;

public class CountryServiceData {


    public  Map<String,String> expectedDataMap;

     /*   {
        "iso2": "DE",
            "name": "Germany",
            "currency": "EUR",
            "currency_name": "Euro",
            "currency_symbol": "���"
    }*/



    public Map<String,String> setupDataIdCountry(){

        expectedDataMap = new HashMap<>();

        expectedDataMap.put("iso2","DE");
        expectedDataMap.put("name","Germany");
        expectedDataMap.put("currency","EUR");
        expectedDataMap.put("currency_name","Euro");
        expectedDataMap.put("currency_symbol","���");

        return expectedDataMap;


    }



}
