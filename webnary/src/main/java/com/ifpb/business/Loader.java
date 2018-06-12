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
public class Loader {
    public static void main(String[] args) {
        RandomChoiceCreator.getAlternatives(1).forEach(System.out::println);
    }
}
