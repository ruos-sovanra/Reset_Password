package com.example.user.features.calendarapi;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Service
public class CalendarService {
    private static final String APPLICATION_NAME = "Alumni CSTAD ";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    public List<Event> getEvents() throws IOException, GeneralSecurityException {

        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        System.out.println("HTTP_TRANSPORT: " + HTTP_TRANSPORT);

        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        System.out.println("Service: " + service);

        DateTime now = new DateTime(System.currentTimeMillis());

        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        if (events == null || events.getItems() == null) {
            throw new IllegalStateException("No events found or received a null response from API.");
        }
        return events.getItems();
    }

    private Credential getCredentials(NetHttpTransport HTTP_TRANSPORT) throws IOException {

        if (HTTP_TRANSPORT == null) {
            throw new IllegalStateException("HTTP_TRANSPORT is null");
        }

        String CREDENTIALS_FILE_PATH = "D:\\ITE_CSTAD\\finalProject\\google\\calendar_api\\src\\main\\resources\\json1.json";

        try {

            FileInputStream fileInputStream = new FileInputStream(CREDENTIALS_FILE_PATH);

            GoogleCredential credential = GoogleCredential.fromStream(fileInputStream)
                    .createScoped(Collections.singleton(CalendarScopes.CALENDAR_EVENTS));

            fileInputStream.close();

            return credential;

        } catch (FileNotFoundException e) {

            System.err.println("Error: Credentials file not found at " + CREDENTIALS_FILE_PATH);

            e.printStackTrace();

            throw e;

        } catch (IOException e) {

            System.err.println("Error reading credentials file: " + e.getMessage());

            e.printStackTrace();

            throw e;
        }
    }


    public void createEvent(Event eventDetails) throws IOException, GeneralSecurityException {

        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

       var event = service.events().insert("4eab7b46912c3ce8c1c88f2d1807ae0f3af2d517556e741bab29c54616a4b2c5@group.calendar.google.com", eventDetails).execute();

        System.out.println("Event created: " + event);

        //will send a message to the telegram bot
        sendTelegramMessage("Event created: " + event.getHtmlLink());
    }

    private void sendTelegramMessage(String message) throws IOException {

        String chatId = "-4133149806"; // Replace YOUR_CHAT_ID_HERE with your actual chat ID

        HttpURLConnection conn = getHttpURLConnection(message, chatId);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {

            StringBuilder response = new StringBuilder();

            String responseLine = null;

            while ((responseLine = br.readLine()) != null) {

                response.append(responseLine.trim());

            }
            System.out.println("Telegram API response: " + response.toString());
        }
    }

    private static HttpURLConnection getHttpURLConnection(String message, String chatId) throws IOException {

        URL url = new URL("https://api.telegram.org/bot7160877407:AAFDrPyVjFIyC9rXWnPO5QRcEJsry20Oqq4/sendMessage");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        String jsonInputString = "{\"chat_id\": \"" + chatId + "\", \"text\": \"" + message + "\"}";

        try (OutputStream os = conn.getOutputStream()) {

            byte[] input = jsonInputString.getBytes("utf-8");

            os.write(input, 0, input.length);
        }

        return conn;
    }

}

