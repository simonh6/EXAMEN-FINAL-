package view;

import util.ArchivoUtil;
import util.QuickSort;

import model.Predio;

import java.util.List;
import java.util.Scanner;

public class Consola {

    public static void iniciar() {

        List<Predio> lista =
                ArchivoUtil.cargarArchivo("predios.txt");

        QuickSort.ordenar(
                lista,
                0,
                lista.size() - 1);

        Scanner sc = new Scanner(System.in);

        System.out.println("Buscar por:");

        System.out.println("1. NPN");
        System.out.println("2. Municipio");
        System.out.println("3. Direccion");
        System.out.println("4. Ficha");

        int opcion = sc.nextInt();
        sc.nextLine();

        System.out.print("Digite criterio: ");

        String criterio =
                sc.nextLine().toLowerCase();

        long inicioTiempo =
                System.nanoTime();

        boolean encontrado = false;

        for (Predio p : lista) {

            String valor = "";

            switch (opcion) {

                case 1:
                    valor = p.getNpn();
                    break;

                case 2:
                    valor = p.getMunicipio();
                    break;

                case 3:
                    valor = p.getDireccion();
                    break;

                case 4:
                    valor = p.getFicha();
                    break;
            }

            if (valor.toLowerCase()
                    .contains(criterio)) {

                System.out.println(p);

                encontrado = true;
            }
        }

        long finTiempo =
                System.nanoTime();

        if (!encontrado) {

            System.out.println(
                    "No se encontraron registros");
        }

        System.out.println(
                "\nTiempo: "
                + (finTiempo - inicioTiempo)
                + " ns");

        sc.close();
    }
}