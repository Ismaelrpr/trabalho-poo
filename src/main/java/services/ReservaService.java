package services;

import entity.Hotel;
import entity.Voos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReservaService {

    // Busca o hotel mais barato que atenda ao número mínimo de estrelas e tenha vagas disponíveis
    public static Hotel buscarHotelMaisBarato(List<Hotel> hoteis, String localizacao, Integer estrelasMinimas) {
        return hoteis.stream()
                .filter(h -> h.getLocal().equals(localizacao) && h.getEstrelas() >= estrelasMinimas && h.getVagas() > 0)
                .sorted(Comparator.comparingDouble(Hotel::getPreco))
                .findFirst()
                .orElse(null);
    }

    // Busca os dois voos mais baratos que atendam à origem e destino informados
    public static List<Voos> buscarVoosMaisBaratos(List<Voos> voos, String origem, String destino) {
        List<Voos> voosEncontrados = new ArrayList<>();
        voos.stream()
                .filter(v -> v.getOrigem().equals(origem) && v.getDestino().equals(destino) && v.getQtd_assentos() > 0)
                .sorted(Comparator.comparingDouble(Voos::getPreco))
                .limit(2)
                .forEach(voosEncontrados::add);
        return voosEncontrados;
    }

    // Busca voos com conexões que atendam à origem e destino informados
    public static List<Voos> buscarVoosComConexoes(List<Voos> voos, String origem, String destino) {
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
    }
}
