/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.business;

/**
 *
 * @author kuuhaku
 */
public class GithubAuth {

    private final String client_authorize = "http://github.com/login/oauth/authorize";
    private final String client_token = "http://github.com/login/oauth/access_token";
    private final String client_id, client_secret, redirect_uri;

    public GithubAuth(String client_id, String client_secret, String redirect_uri) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.redirect_uri = redirect_uri;
    }

    public String getAuthorization() {
        String params = String.format("client_id=%s&redirect_uri=%s", client_id, redirect_uri);
        return client_authorize.concat("?").concat(params);
    }

    public String toParams(String code) {
        return String.format("client_id=%s&client_secret=%s&redirect_uri=%s&code=%s",
                client_id, client_secret, redirect_uri, code);
    }

    public String getUrlToken() {
        return client_token;
    }

    @Override
    public String toString() {
        return "GithubAuth{" + "client_authorize=" + client_authorize + ", client_token=" + client_token + ", client_id=" + client_id + ", client_secret=" + client_secret + ", redirect_uri=" + redirect_uri + '}';
    }
}
