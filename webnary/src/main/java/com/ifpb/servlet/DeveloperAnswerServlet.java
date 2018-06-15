/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.servlet;

import com.ifpb.model.Developer;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kuuhaku
 */
@WebServlet("/sendAnswer")
public class DeveloperAnswerServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        String[] checkeds = req.getParameterValues("check");
        String question = req.getParameter("question");
        HttpSession session = req.getSession();
        Developer dev = (Developer) session.getAttribute("dev");
        dev.addAnswers(question, Arrays.asList(checkeds));
        session.setAttribute("dev", dev);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/questions.jsp");
        try {
            requestDispatcher.forward(req, res);
        } catch (ServletException | IOException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
}
