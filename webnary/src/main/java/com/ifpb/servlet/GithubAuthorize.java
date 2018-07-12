/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.servlet;

import com.ifpb.business.GithubAuth;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kuuhaku
 */
@WebServlet("/authorize")
public class GithubAuthorize extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String client_id = req.getParameter("client_id");
        String client_secret = req.getParameter("client_secret");
        String redirect_uri = req.getParameter("redirect_uri");
        GithubAuth github = new GithubAuth(client_id, client_secret, redirect_uri);
        req.getSession().setAttribute("github", github);
        res.sendRedirect(github.getAuthorization());
    }
}
