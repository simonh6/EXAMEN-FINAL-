package util;

import model.Predio;
import java.io.*;
import java.util.*;

public class ArchivoUtil {

    public static List<Predio> cargarArchivo(String ruta) {

        List<Predio> lista = new ArrayList<>();

        try {

            BufferedReader br =
                    new BufferedReader(
                    new FileReader(ruta));

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                Predio p = new Predio(
                        datos[0],
                        datos[1],
                        datos[2],
                        datos[3]);

                lista.add(p);
            }

            br.close();

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage());
        }

        return lista;
    }
}