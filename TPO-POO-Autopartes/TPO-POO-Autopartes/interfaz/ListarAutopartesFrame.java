package interfaz;

import negocio.Administrador;
import negocio.Autoparte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarAutopartesFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private Administrador admin;

    public ListarAutopartesFrame(Administrador admin) {
        this.admin = admin;

        setTitle("Listar Autopartes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear la tabla y el modelo de la tabla
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);

        // Añadir columnas al modelo de la tabla
        tableModel.addColumn("Código");
        tableModel.addColumn("Denominación");
        tableModel.addColumn("Descripción");
        tableModel.addColumn("Categoría");
        tableModel.addColumn("Marca");
        tableModel.addColumn("Modelo");
        tableModel.addColumn("Precio");
        tableModel.addColumn("Stock");
        tableModel.addColumn("Stock Mínimo");
        tableModel.addColumn("Enlace");

        // Obtener la lista de autopartes y añadir las filas al modelo de la tabla
        List<Autoparte> autopartes = admin.ListarCatalogo();
        for (Autoparte autoparte : autopartes) {
            tableModel.addRow(new Object[]{
                autoparte.getCodigo(),
                autoparte.getDenominacion(),
                autoparte.getDescripcion(),
                autoparte.getCategoria(),
                autoparte.getMarca(),
                autoparte.getModelo(),
                autoparte.getPrecio(),
                autoparte.getCantStock(),
                autoparte.getStockMinimo(),
                autoparte.getEnlace()
            });
        }

        // Añadir la tabla a un JScrollPane para permitir el desplazamiento
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}