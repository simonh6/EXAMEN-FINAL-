package util;

import model.Predio;
import java.util.List;

public class QuickSort {

    public static void ordenar(
            List<Predio> lista,
            int inicio,
            int fin) {

        if (inicio < fin) {

            int pivote = particion(
                    lista,
                    inicio,
                    fin);

            ordenar(lista, inicio, pivote - 1);
            ordenar(lista, pivote + 1, fin);
        }
    }

    private static int particion(
            List<Predio> lista,
            int inicio,
            int fin) {

        String pivote =
                lista.get(fin)
                     .getMunicipio();

        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {

            if (lista.get(j)
                    .getMunicipio()
                    .compareToIgnoreCase(pivote) <= 0) {

                i++;

                Predio temp = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, temp);
            }
        }

        Predio temp = lista.get(i + 1);
        lista.set(i + 1, lista.get(fin));
        lista.set(fin, temp);

        return i + 1;
    }
}