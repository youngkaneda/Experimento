/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifpb.model.Developer;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kuuhaku
 */
@WebServlet("/endAnswer")
public class EndQuestionaryServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession();
        Developer dev = (Developer) session.getAttribute("dev");
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonDev = mapper.writeValueAsString(dev);
            System.out.println(jsonDev);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
