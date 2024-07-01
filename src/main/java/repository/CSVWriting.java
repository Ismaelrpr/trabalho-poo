package repository;

import entity.Cliente;
import entity.Hotel;
import entity.Voos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriting {
    public static void atualizarHoteis(String filePath, List<Hotel> hoteis) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Hotel hotel : hoteis) {
                String line = hotel.getLocal() + ";" +
                        hotel.getNome() + ";" +
                        hotel.getVagas() + " vagas;R$ " +
                        hotel.getPreco() + ";" +
                        hotel.getEstrelas() + " estrelas\n";
                writer.write(line);
            }
        }
    }

    public static void atualizarVoos(String filePath, List<Voos> voos) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Voos voo : voos) {
                String line = voo.getOrigem() + ";" +
                        voo.getDestino() + ";" +
                        voo.getData() + ";" +
                        voo.getHorario() + ";" +
                        voo.getQtd_assentos() + " assentos;R$ " +
                        voo.getPreco() + "\n";
                writer.write(line);
            }
        }
    }

    public static void atualizarClientes(String filePath, List<Cliente> clientes) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Cliente cliente : clientes) {
                String line = cliente.getNome() + ";" +
                        cliente.getOrigem() + ";" +
                        cliente.getDestino() + ";" +
                        cliente.getQtd_dias() + " dias;" +
                        cliente.getEstrelas() + " estrelas;R$ " +
                        cliente.getDinheiro() + "\n";
                writer.write(line);
            }
        }
    }
}
