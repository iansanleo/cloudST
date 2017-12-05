package com.cloudST.utiles;

import com.cloudST.model.Raspberry;
import com.cloudST.repository.RaspberryRepository;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

@Component
public class NetworkDevicesScanner {

    @Autowired
    RaspberryRepository raspberryRepository;

    @Async
    public void scanNetwork(){
        String ip_network = identifyNetwork();
        for(int host=1;host<254;host++){
            try {
                String response = sendGET(ip_network + "." + host + ":8080/identify");
                if(response==null){continue;}

                Raspberry newDevice=createRaspJson(response);
                Raspberry oldDevice=raspberryRepository.findRaspberryMac(newDevice.getMac());

                if(oldDevice==null || !oldDevice.getStatus()){
                    raspberryRepository.save(newDevice);
                }else if(!oldDevice.getIp().equals(newDevice.getIp())){
                    raspberryRepository.delete(oldDevice.getIdRaspberry());
                    raspberryRepository.save(newDevice);
                }

            } catch (IOException e) {
                continue;
            }
        }
    }

    private String identifyNetwork() {
        String ip = getIp();
        int position_last_number = ip.lastIndexOf(".");
        String network = ip.substring(0, position_last_number);
        return network;
    }

    public String sendGET(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        }
        System.out.println("GET request not worked");
        return null;
    }

    private Raspberry createRaspJson(String response){
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        Raspberry raspberry = new Raspberry();

        try {
            jsonObject = (JSONObject) parser.parse(response);
        } catch (ParseException e1) {

            e1.printStackTrace();
        }
        try {
            raspberry.setIp((String) jsonObject.get("ip"));
            raspberry.setMac((String) jsonObject.get("mac"));
            raspberry.setTotalSize((double)jsonObject.get("totalSize"));
            raspberry.setUseSize((double) jsonObject.get("useSize"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return raspberry;
    }

    private InetAddress getInetIp(){
        try {
            DatagramSocket socket = new DatagramSocket();
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            InetAddress ip = socket.getLocalAddress();
            return ip;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();

        }
        return null;
    }
    private String getIp(){
        return getInetIp().getHostAddress();
    }
}
