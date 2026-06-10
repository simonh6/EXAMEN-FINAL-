package util;

import model.Predio;
import java.util.List;

public class BusquedaBinaria {

    public static int buscar(
            List<Predio> lista,
            String municipio) {

        int inicio = 0;
        int fin = lista.size() - 1;

        while (inicio <= fin) {

            int medio = (inicio + fin) / 2;

            String valor =
                    lista.get(medio)
                         .getMunicipio();

            int comparacion =
                    valor.compareToIgnoreCase(municipio);

            if (comparacion == 0) {
                return medio;
            }

            if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }

        return -1;
    }
}