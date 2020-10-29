package com.strelkov.test.demo.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Query {
    private String url = null;
    private String word = null;

    public int getCount() throws IOException {
        int count = 0;
        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        String line;
        Pattern p = Pattern.compile(word);

        while ((line = reader.readLine()) != null) {
            Matcher m = p.matcher(line);
            while (m.find())
                count++;
        }
        reader.close();

        return count;
    }

    public String validate() {
        String validateMessage = "Не заполнено поле [";
        if (url == null & word == null)
            validateMessage += "url, word]";
        else if (url == null)
            validateMessage += "url]";
        else if (word == null)
            validateMessage += "word]";

        if (validateMessage.endsWith("]"))
            return validateMessage;
        return null;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
