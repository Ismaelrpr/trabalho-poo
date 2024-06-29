package org.application;

import entity.Cliente;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Cliente primeiro = new Cliente();
        
        primeiro.setDestino("sao paulo");

        System.out.println(primeiro.getDestino());

    }

}