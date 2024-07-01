package services;

import entity.Cliente;
import entity.Hotel;
import entity.Voos;
import repository.CSVWriting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SerialProcess {
    public static void processarUsuarios(List<Cliente> clientes, List<Hotel> hoteis, List<Voos> voos, String caminhoHoteis, String caminhoVoos, String caminhoCliente) throws IOException {

        // Implementação do processamento dos dados
        for(Cliente cliente : clientes) {
            Hotel hotel = ReservaService.buscarHotelMaisBarato(hoteis, cliente.getDestino(), cliente.getEstrelas());
            List<Voos> voosEncontrados = ReservaService.buscarVoosMaisBaratos(voos, cliente.getOrigem(), cliente.getDestino());

            // caso não haja hoteis disponiveis, por ex. falta de vagas ou local indisponivel
            if(hotel == null){
                System.out.println("Cliente " + cliente.getNome().split(" ")[1] + " não encontrou hotel disponível");
                continue;
            }

            // tratar voos com no maximo 2 conexões
            if(voosEncontrados.isEmpty()){
                voosEncontrados = ReservaService.buscarVoosComConexoes(voos, cliente.getOrigem(), cliente.getDestino());
            }

            // calcular o orçamento do cliente
            double custoTotal = hotel.getPreco() + voosEncontrados.stream().mapToDouble(Voos::getPreco).sum();
            if (custoTotal <= cliente.getDinheiro()) {
                System.out.println("Cliente " + cliente.getNome().split(" ")[1] + " aceitou o orçamento de R$ " + custoTotal);

                // Atualizar  hotel, voos e cliente
                hotel.setVagas(hotel.getVagas() - 1);
                CSVWriting.atualizarHoteis(caminhoHoteis, hoteis);

                cliente.setDinheiro((cliente.getDinheiro() - custoTotal));
                CSVWriting.atualizarClientes(caminhoCliente, clientes);

                for (Voos voo : voosEncontrados) {
                    voo.setQtd_assentos(voo.getQtd_assentos() - 1);
                }
                CSVWriting.atualizarVoos(caminhoVoos, voos);
            } else {
                System.out.println("Cliente " + cliente.getNome().split(" ")[1] + " não aceitou o orçamento de R$ " + custoTotal);
            }
        }
    }
}
