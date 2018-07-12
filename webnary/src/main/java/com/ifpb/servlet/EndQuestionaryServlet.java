/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifpb.model.Developer;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.service.Firebase;

/**
 *
 * @author kuuhaku
 */
@WebServlet("/endAnswer")
public class EndQuestionaryServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        Firebase firebase;
        try {
            firebase = new Firebase("https://webnary-154c2.firebaseio.com/");
        } catch (FirebaseException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        HttpSession session = req.getSession();
        Developer dev = (Developer) session.getAttribute("dev");
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonDev = mapper.writeValueAsString(dev);
            firebase.put(UUID.randomUUID().toString(), jsonDev);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex.getMessage());
        } catch (FirebaseException | UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
