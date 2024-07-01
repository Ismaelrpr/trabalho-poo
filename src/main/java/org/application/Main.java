package org.application;

import entity.Cliente;
import entity.Hotel;
import entity.Voos;
import repository.CSVLoader;
import services.ParallelProcess;
import services.SerialProcess;

import java.util.List;

public class Main {
    public static void main(String[] args) {
    try {
        // Caminho dos arquivos CSV
//        String caminhoClientes = "/home/makima/Ismael/trabalho-poo/src/main/resources/data/formato-clientes.csv";
//        String caminhoHoteis = "/home/makima/Ismael/trabalho-poo/src/main/resources/data/formato-hoteis.csv";
//        String caminhoVoos = "/home/makima/Ismael/trabalho-poo/src/main/resources/data/formato-voos.csv";

        String caminhoClientes = "/home/makima/Ismael/trabalho-poo/geradores/listaclientes.csv";
        String caminhoHoteis = "/home/makima/Ismael/trabalho-poo/geradores/listahoteis.csv";
        String caminhoVoos = "/home/makima/Ismael/trabalho-poo/geradores/listavoos.csv";
        String caminhoOutput = "/home/makima/Ismael/trabalho-poo/src/main/output.csv";

        //Carregar dados dos arquivos CSV
        List<Cliente> clientes = CSVLoader.loadClientes(caminhoClientes);
        List<Hotel> hoteis = CSVLoader.loadHoteis(caminhoHoteis);
        List<Voos> voos = CSVLoader.loadVoos(caminhoVoos);

        //Imprimir dados
        System.out.println("Clientes:");
        for (Cliente cliente : clientes) {
            System.out.println(cliente.getNome() + " " + cliente.getOrigem() + " " + cliente.getDestino() + " " + cliente.getQtd_dias() + " " + cliente.getEstrelas() + " " + cliente.getDinheiro());
        }

        System.out.println();

        long inicio1 = System.currentTimeMillis();

        // processamento serial
        System.out.println("Processamento serial:");
//        SerialProcess.processarUsuarios(clientes, hoteis, voos, caminhoHoteis, caminhoVoos);

        SerialProcess.processarUsuarios(clientes, hoteis, voos, caminhoHoteis, caminhoVoos, caminhoClientes, caminhoOutput);

        System.out.println(" ");

        long fim1 = System.currentTimeMillis();

        long tempo1 = fim1 - inicio1;

        double seg1 = tempo1 / 1000.0;
        double min1 = seg1 / 60.0;

        System.out.println("processamento serial em milissegundos: " + tempo1);
        System.out.println("processamento serial em segundos: " + seg1);
        System.out.println("processamento serial em minutos: " + min1);

        //////////////////////////////////////////////////////////////////////////

        long inicio2 = System.currentTimeMillis();

        ParallelProcess.processarUsuarios(clientes, hoteis, caminhoHoteis, caminhoVoos, caminhoClientes, caminhoOutput);

        long fim2 = System.currentTimeMillis();

        long tempo2 = fim2 - inicio2;

        double seg2 = tempo2 / 1000.0;
        double min2 = seg2 / 60.0;

        System.out.println("Tempo de execução em milissegundos: " + tempo2);
        System.out.println("Tempo de execução em segundos: " + seg2);
        System.out.println("Tempo de execução em minutos: " + min2);


    } catch (Exception e) {
        e.printStackTrace();
    }

    }
}