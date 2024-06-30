package org.application;

import entity.Cliente;
import entity.Hotel;
import entity.Voos;
import repository.CSVLoader;
import services.SerialProcess;

import java.util.List;

public class Main {
    public static void main(String[] args) {
    try {
        // Caminho dos arquivos CSV
        String caminhoClientes = "/home/makima/Ismael/trabalho-poo/src/main/resources/data/formato-clientes.csv";
        String caminhoHoteis = "/home/makima/Ismael/trabalho-poo/src/main/resources/data/formato-hoteis.csv";
        String caminhoVoos = "/home/makima/Ismael/trabalho-poo/src/main/resources/data/formato-voos.csv";

        //Carregar dados dos arquivos CSV
        List<Cliente> clientes = CSVLoader.loadClientes(caminhoClientes);
        List<Hotel> hoteis = CSVLoader.loadHoteis(caminhoHoteis);
        List<Voos> voos = CSVLoader.loadVoos(caminhoVoos);

        //Imprimir dados
        System.out.println("Clientes:");
        for (Cliente cliente : clientes) {
            System.out.println(cliente.getNome() + " " + cliente.getOrigem() + " " + cliente.getDestino() + " " + cliente.getQtd_dias() + " " + cliente.getEstrelas() + " " + cliente.getDinheiro());
        }

        System.out.println("");

        // processamento serial
        System.out.println("Processamento serial:");
        SerialProcess.processarUsuarios(clientes, hoteis, voos, caminhoHoteis, caminhoVoos);


    } catch (Exception e) {
        e.printStackTrace();
    }

    }
}