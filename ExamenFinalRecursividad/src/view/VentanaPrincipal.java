package view;

import model.Predio;
import util.ArchivoUtil;
import util.QuickSort;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JComboBox<String> comboBuscar;
    private JTextField txtBuscar;

    private JLabel lblTiempo;

    private List<Predio> listaPredios;

    public VentanaPrincipal() {

        listaPredios = ArchivoUtil.cargarArchivo("predios.txt");

        setTitle("Sistema Catastral de Antioquia");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iniciarComponentes();

        setVisible(true);
    }

    private void iniciarComponentes() {

        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();

        panelSuperior.add(new JLabel("Buscar por:"));

        comboBuscar = new JComboBox<>();

        comboBuscar.addItem("NPN");
        comboBuscar.addItem("Municipio");
        comboBuscar.addItem("Direccion");
        comboBuscar.addItem("Ficha");

        panelSuperior.add(comboBuscar);

        txtBuscar = new JTextField(15);
        panelSuperior.add(txtBuscar);

        JButton btnBuscar = new JButton("Buscar");
        JButton btnOrdenar = new JButton("Ordenar Municipio");
        JButton btnMostrar = new JButton("Mostrar Todos");

        panelSuperior.add(btnBuscar);
        panelSuperior.add(btnOrdenar);
        panelSuperior.add(btnMostrar);

        add(panelSuperior, BorderLayout.NORTH);

        modelo = new DefaultTableModel();

        modelo.addColumn("NPN");
        modelo.addColumn("Municipio");
        modelo.addColumn("Direccion");
        modelo.addColumn("Ficha");

        tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);

        lblTiempo = new JLabel("Tiempo: 0 ns");

        add(lblTiempo, BorderLayout.SOUTH);

        cargarTabla(listaPredios);

        btnBuscar.addActionListener(e -> buscar());

        btnOrdenar.addActionListener(e -> ordenar());

        btnMostrar.addActionListener(e -> cargarTabla(listaPredios));
    }

    private void cargarTabla(List<Predio> lista) {

        modelo.setRowCount(0);

        for (Predio p : lista) {

            modelo.addRow(new Object[]{
                    p.getNpn(),
                    p.getMunicipio(),
                    p.getDireccion(),
                    p.getFicha()
            });
        }
    }

    private void buscar() {

        long inicio = System.nanoTime();

        String criterio =
                txtBuscar.getText().toLowerCase();

        String campo =
                comboBuscar.getSelectedItem().toString();

        List<Predio> resultado =
                new ArrayList<>();

        for (Predio p : listaPredios) {

            String valor = "";

            switch (campo) {

                case "NPN":
                    valor = p.getNpn();
                    break;

                case "Municipio":
                    valor = p.getMunicipio();
                    break;

                case "Direccion":
                    valor = p.getDireccion();
                    break;

                case "Ficha":
                    valor = p.getFicha();
                    break;
            }

            if (valor.toLowerCase()
                    .contains(criterio)) {

                resultado.add(p);
            }
        }

        long fin = System.nanoTime();

        cargarTabla(resultado);

        lblTiempo.setText(
                "Tiempo: " +
                        (fin - inicio) +
                        " ns");
    }

    private void ordenar() {

        long inicio = System.nanoTime();

        QuickSort.ordenar(
                listaPredios,
                0,
                listaPredios.size() - 1);

        long fin = System.nanoTime();

        cargarTabla(listaPredios);

        lblTiempo.setText(
                "Tiempo: " +
                        (fin - inicio) +
                        " ns");
    }
}