package services;

import entity.Cliente;
import entity.Hotel;
import entity.Voos;
import repository.CSVWriting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParallelProcess {

    public static void processarUsuarios(List<Cliente> clientes, List<Hotel> hoteis, List<Voos> voos, String caminhoHoteis, String caminhoVoos) {

        List<Thread> threads = new ArrayList<>();


        for (Cliente cliente : clientes) {
            Thread thread = new Thread(new Tarefa(cliente, hoteis, voos));
            threads.add(thread);
            thread.start();
        }


        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        try {
            CSVWriting.atualizarHoteis(caminhoHoteis, hoteis);
            CSVWriting.atualizarVoos(caminhoVoos, voos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Tarefa implements Runnable {
        private final Cliente cliente;
        private final List<Hotel> hoteis;
        private final List<Voos> voos;

        public Tarefa(Cliente cliente, List<Hotel> hoteis, List<Voos> voos) {
            this.cliente = cliente;
            this.hoteis = hoteis;
            this.voos = voos;
        }

        @Override
        public void run() {
            Hotel hotel = ReservaService.buscarHotelMaisBarato(hoteis, cliente.getDestino(), cliente.getEstrelas());
            List<Voos> voosEncontrados = ReservaService.buscarVoosMaisBaratos(voos, cliente.getOrigem(), cliente.getDestino());


            if (hotel == null) {
                System.out.println("Cliente " + cliente.getNome().split(" ")[1] + " não encontrou hotel disponível");
                return;
            }


            if (voosEncontrados.isEmpty()) {
                voosEncontrados = ReservaService.buscarVoosComConexoes(voos, cliente.getOrigem(), cliente.getDestino());
            }


            double custoTotal = hotel.getPreco() + voosEncontrados.stream().mapToDouble(Voos::getPreco).sum();
            if (custoTotal <= cliente.getDinheiro()) {
                System.out.println("Cliente " + cliente.getNome().split(" ")[1] + " aceitou o orçamento de R$ " + custoTotal);
                synchronized (hoteis) {
                    hotel.setVagas(hotel.getVagas() - 1);
                }
                synchronized (voos) {
                    for (Voos voo : voosEncontrados) {
                        voo.setQtd_assentos(voo.getQtd_assentos() - 1);
                    }
                }
            } else {
                System.out.println("Cliente " + cliente.getNome().split(" ")[1] + " não aceitou o orçamento de R$ " + custoTotal);
            }
        }
    }
}