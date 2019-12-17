package com.ivashchenko.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    public void call_me() throws Exception {
        String url = "https://sandbox.iexapis.com/stable/ref-data/symbols?token=Tpk_ee567917a6b640bb8602834c9d30e571";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String s = response.toString();
        List<CompanyData> myPojos = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(s);
        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject e = jsonArray.getJSONObject(i);
            CompanyData myPojo = new CompanyData();

            if (e.getBoolean("isEnabled")) {
                myPojo.setSymbol("Symbol: " + e.getString("symbol"));
                myPojo.setExchange("Exchange: " + e.getString("exchange"));
                myPojo.setName("Name: " + e.getString("name"));
                myPojo.setDate("Date: " + e.getString("date"));
                myPojo.setType("Type: " + e.getString("type"));
                myPojo.setIexId("IexId: " + e.getString("iexId"));
                myPojo.setRegion("Region: " + e.getString("region"));
                myPojo.setCurrency("Currency: " + e.getString("currency"));
                myPojo.setEnabled(e.getBoolean("isEnabled"));
                myPojos.add(myPojo);
            } else {
                return;
            }

            System.out.println(myPojos.get(i).getSymbol());
            System.out.println(myPojos.get(i).getExchange());
            System.out.println(myPojos.get(i).getName());
            System.out.println(myPojos.get(i).getDate());
            System.out.println(myPojos.get(i).getType());
            System.out.println(myPojos.get(i).getIexId());
            System.out.println(myPojos.get(i).getRegion());
            System.out.println(myPojos.get(i).getCurrency());
            System.out.print("Enabled: ");
            System.out.println(myPojos.get(i).getEnabled() + "\n");
        }
        System.out.println(Thread.currentThread());
    }


}

