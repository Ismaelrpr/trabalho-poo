package services;

import entity.Hotel;
import entity.Voos;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class ReservaServiceAsync {

    public static CompletableFuture<Hotel> buscarHotelMaisBaratoAsync(List<Hotel> hoteis, String localizacao, Integer estrelasMinimas) throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> hoteis.stream()
                .filter(h -> h.getLocal().equals(localizacao) && h.getEstrelas() >= estrelasMinimas && h.getVagas() > 0)
                .sorted(Comparator.comparingDouble(Hotel::getPreco))
                .findFirst()
                .orElse(null));
    }


    public static CompletableFuture<List<Voos>> buscarVoosMaisBaratosAsync(List<Voos> voos, String origem, String destino) throws ExecutionException, InterruptedException{
        return CompletableFuture.supplyAsync(() -> {
            List<Voos> voosEncontrados = new ArrayList<>();
            voos.stream()
                    .filter(v -> v.getOrigem().equals(origem) && v.getDestino().equals(destino) && v.getQtd_assentos() > 0)
                    .sorted(Comparator.comparingDouble(Voos::getPreco))
                    .limit(2)
                    .forEach(voosEncontrados::add);
            return voosEncontrados;
        });
    }


    public static CompletableFuture<List<Voos>> buscarVoosComConexoesAsync(List<Voos> voos, String origem, String destino) throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            List<Voos> voosComConexao = new ArrayList<>();
            List<Voos> voosOrigem = voos.stream()
                    .filter(v -> v.getOrigem().equals(origem) && v.getQtd_assentos() > 0)
                    .collect(Collectors.toList());

            for (Voos voo : voosOrigem) {
                voos.stream()
                        .filter(v -> v.getOrigem().equals(voo.getDestino()) && v.getDestino().equals(destino) && v.getQtd_assentos() > 0)
                        .sorted(Comparator.comparingDouble(Voos::getPreco))
                        .limit(1)
                        .forEach(voosComConexao::add);
            }
            return voosComConexao;
        });
    }
}