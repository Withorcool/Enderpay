package com.withorcc.enderpay.requests;

import com.withorcc.enderpay.Enderpay;
import com.withorcc.enderpay.lib.DiscordWebhook;

import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class webhook {
    public static void hook(String op, String p, int pa, int a) throws IOException {
        String webhookURL = Enderpay.getPlugin(Enderpay.class).config.getString("webhook");
        final HttpsURLConnection connection = (HttpsURLConnection) new URL(webhookURL).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11");
        connection.setDoOutput(true);
        try (final OutputStream outputStream = connection.getOutputStream()) {
            String content = "Операция " + op + " Игрок " + p + " Старый баланс " + pa +" Новый баланс " + a;
            outputStream.write(("{\"content\":\"" + content + "\"}").getBytes(StandardCharsets.UTF_8));
        }
        connection.getInputStream();
    }
}
