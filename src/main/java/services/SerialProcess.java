package services;

import entity.Cliente;
import entity.Hotel;
import entity.Voos;
import repository.CSVWriting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SerialProcess {
    public static void processarUsuarios(List<Cliente> clientes, List<Hotel> hoteis, List<Voos> voos, String caminhoHoteis, String caminhoVoos, String caminhoCliente, String caminhoOutput) throws IOException {

        Set<String> clientesDistintos = new HashSet<>();
        int totalPedidos = clientes.size();
        int pedidosAtendidos = 0;
        double valorTotalGasto = 0.0;
        double valorGastoHoteis = 0.0;
        double valorGastoVoos = 0.0;

        // Mapa para armazenar o saldo atualizado dos clientes
        Map<String, Double> saldoAtualizadoClientes = new HashMap<>();

        // Escrever no arquivo de saída
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoOutput))) {
            // Implementação do processamento dos dados
            for (Cliente cliente : clientes) {
                Hotel hotel = ReservaService.buscarHotelMaisBarato(hoteis, cliente.getDestino(), cliente.getEstrelas());
                List<Voos> voosEncontrados = ReservaService.buscarVoosMaisBaratos(voos, cliente.getOrigem(), cliente.getDestino());

                // Adicionar cliente à lista de clientes distintos
                clientesDistintos.add(cliente.getNome());

                // caso não haja hoteis disponiveis, por ex. falta de vagas ou local indisponivel
                if (hotel == null) {
                    String mensagem = "Cliente " + cliente.getNome().split(" ")[1] + " não encontrou hotel disponível";
                    System.out.println(mensagem);
                    bw.write(mensagem);
                    bw.newLine();
                    continue;
                }

                // tratar voos com no maximo 2 conexões
                if (voosEncontrados.isEmpty()) {
                    voosEncontrados = ReservaService.buscarVoosComConexoes(voos, cliente.getOrigem(), cliente.getDestino());
                }

                // calcular o orçamento do cliente
                double custoTotal = hotel.getPreco() + voosEncontrados.stream().mapToDouble(Voos::getPreco).sum();

                // Pega saldo atualizado do cliente
                if(saldoAtualizadoClientes.containsKey(cliente.getNome())){
                    Double saldoAtualizado = saldoAtualizadoClientes.get(cliente.getNome());
                    cliente.setDinheiro(saldoAtualizado);
                }else if(custoTotal > cliente.getDinheiro()){
                    saldoAtualizadoClientes.put(cliente.getNome(), cliente.getDinheiro());
                }else{
                    saldoAtualizadoClientes.put(cliente.getNome(), cliente.getDinheiro() - custoTotal);
                }

                if (custoTotal <= cliente.getDinheiro()) {
                    String mensagem = "Cliente " + cliente.getNome().split(" ")[1] + " aceitou o orçamento de R$ " + custoTotal;
                    System.out.println(mensagem);
                    bw.write(mensagem);
                    bw.newLine();

                    // Valores totais de atendimentos, pedidos e gastos
                    pedidosAtendidos++;
                    valorTotalGasto += custoTotal;
                    valorGastoHoteis += hotel.getPreco();
                    valorGastoVoos += voosEncontrados.stream().mapToDouble(Voos::getPreco).sum();

                    // Atualizar hotel, voos e cliente
                    hotel.setVagas(hotel.getVagas() - 1);
                    CSVWriting.atualizarHoteis(caminhoHoteis, hoteis);

                    cliente.setDinheiro(cliente.getDinheiro() - custoTotal);
                    CSVWriting.atualizarClientes(caminhoCliente, clientes);

                    for (Voos voo : voosEncontrados) {
                        voo.setQtd_assentos(voo.getQtd_assentos() - 1);
                    }
                    CSVWriting.atualizarVoos(caminhoVoos, voos);
                } else {
                    String mensagem = "Cliente " + cliente.getNome().split(" ")[1] + " não aceitou o orçamento de R$ " + custoTotal;
                    System.out.println(mensagem);
                    bw.write(mensagem);
                    bw.newLine();
                }
            }

            bw.newLine();
            // Escrever informações gerais no arquivo de saída
            bw.write("Total de pedidos: "
                    + totalPedidos
                    + ", Total de clientes: "
                    + clientesDistintos.size()
                    + ", Pedidos atendidos: "
                    + pedidosAtendidos
                    + ", Total gasto: "
                    + valorTotalGasto
                    + ", Gasto com hoteis: "
                    + valorGastoHoteis
                    + ", Gasto com voos: "
                    + valorGastoVoos);
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
