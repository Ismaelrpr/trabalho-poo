package repository;

import com.opencsv.CSVReader;
import entity.Cliente;
import entity.Hotel;
import entity.Voos;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    public static List<Cliente> loadClientes(String filePath) throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                String[] data = line[0].split(";");
                Cliente cliente = new Cliente(data[0], data[1], data[2], data[3], Integer.parseInt(data[4].split(" ")[0]), Double.parseDouble(data[5].split(" ")[1]));
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public static List<Hotel> loadHoteis(String filePath) throws Exception {
        List<Hotel> hoteis = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                String[] data = line[0].split(";");
                Hotel hotel = new Hotel(data[0], data[1], Integer.parseInt(data[2].split(" ")[0]), Double.parseDouble(data[3].split(" ")[1]), Integer.parseInt(data[4].split(" ")[0]));
                hoteis.add(hotel);
            }
        }
        return hoteis;
    }

    public static List<Voos> loadVoos(String filePath) throws Exception {
        List<Voos> voos = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                String[] data = line[0].split(";");
                Voos voo = new Voos(data[0], data[1], data[2], data[3], Integer.parseInt(data[4].split(" ")[0]), Double.parseDouble(data[5].split(" ")[1]));
                voos.add(voo);
            }
        }
        return voos;
    }
}

