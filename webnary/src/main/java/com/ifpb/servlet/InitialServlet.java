/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.servlet;

import com.ifpb.business.GithubAuth;
import com.ifpb.business.HttpRequest;
import com.ifpb.business.QuestionsConverter;
import com.ifpb.business.RandomChoiceCreator;
import com.ifpb.model.Developer;
import com.ifpb.model.Question;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet("/callback")
public class InitialServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException, IOException {

//        GithubAuth github = (GithubAuth) req.getSession().getAttribute("github");
//        String code = req.getParameter("code");
//        String url = github.getUrlToken();
//        System.out.println("->"+ github);
//        HttpRequest request = new HttpRequest(url);
//        String retorno = request.read(github.toParams(code));
//        res.setContentType("application/json");
//        res.getWriter().println(retorno);

        if(req.getParameter("code") == null)
            res.sendRedirect("index.html");

        res.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        Developer dev = new Developer("?");

        List<String> questions = QuestionsConverter.getStringQuestions();
        Map<String, List<String>> fullQuestions = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            List<String> alternatives = RandomChoiceCreator.getAlternatives(i);
            fullQuestions.put(questions.get(i), alternatives);
            dev.addQuestion(new Question(questions.get(i), alternatives));
        }

        session.setAttribute("dev", dev);
        int count = 0;
        session.setAttribute("questionCount", count);

        req.setAttribute("question", dev.getQuestion(count));

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/questions.jsp");
        try {

            requestDispatcher.forward(req, res);
        } catch (ServletException | IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
