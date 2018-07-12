/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.stream.Collectors;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 13/08/2017, 09:48:01
 */
public class HttpRequest {

    private final String uri;

    public HttpRequest(String uri) {
        this.uri = uri;
    }

    public String read(String params) throws MalformedURLException, ProtocolException, IOException {
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Length", String.valueOf(params.length()));
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        
        try (OutputStreamWriter outputWriter = new OutputStreamWriter(connection.getOutputStream())) {
            outputWriter.write(params);
            outputWriter.flush();
        }

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        }
    }
    public String readWithAuthorization(String authorization, String params) throws MalformedURLException, ProtocolException, IOException {
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Length", String.valueOf(params.length()));
        connection.setRequestProperty("Authorization", authorization);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        
        try (OutputStreamWriter outputWriter = new OutputStreamWriter(connection.getOutputStream())) {
            outputWriter.write(params);
            outputWriter.flush();
        }

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        }
    }
}
