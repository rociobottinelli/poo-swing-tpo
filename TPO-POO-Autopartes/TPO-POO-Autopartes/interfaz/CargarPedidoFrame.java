package interfaz;

import javax.swing.*;
import negocio.Administrador;
import negocio.Pedido;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CargarPedidoFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private Administrador admin;
    private JTextField txtNumeroPedido, txtFecha, txtIdCliente, txtIdAutoparte, txtCantidad;
    private JButton btnAgregarAutoparte, btnFinalizarPedido;
    private DefaultListModel<String> detallesListModel;
    private JList<String> detallesList;
    private Pedido pedido;

    public CargarPedidoFrame(Administrador admin) {
        this.admin = admin;
        this.pedido = new Pedido();

        setTitle("Cargar Pedido");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana en la pantalla

        initComponents();
    }

    private void initComponents() {
        // Crear componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblNumeroPedido = new JLabel("Número de Pedido:");
        txtNumeroPedido = new JTextField(10);

        JLabel lblFecha = new JLabel("Fecha del Pedido:");
        txtFecha = new JTextField(10);

        JLabel lblIdCliente = new JLabel("ID del Cliente:");
        txtIdCliente = new JTextField(10);

        JLabel lblIdAutoparte = new JLabel("ID de la Autoparte:");
        txtIdAutoparte = new JTextField(10);

        JLabel lblCantidad = new JLabel("Cantidad:");
        txtCantidad = new JTextField(10);

        btnAgregarAutoparte = new JButton("Agregar Autoparte");
        btnFinalizarPedido = new JButton("Finalizar y Cargar Pedido");

        panel.add(lblNumeroPedido);
        panel.add(txtNumeroPedido);
        panel.add(lblFecha);
        panel.add(txtFecha);
        panel.add(lblIdCliente);
        panel.add(txtIdCliente);
        panel.add(lblIdAutoparte);
        panel.add(txtIdAutoparte);
        panel.add(lblCantidad);
        panel.add(txtCantidad);
        panel.add(btnAgregarAutoparte);

        // Lista de detalles
        detallesListModel = new DefaultListModel<>();
        detallesList = new JList<>(detallesListModel);
        JScrollPane detallesScrollPane = new JScrollPane(detallesList);
        detallesScrollPane.setPreferredSize(new Dimension(580, 150));
        panel.add(detallesScrollPane);
        
        panel.add(btnFinalizarPedido);

        // Acciones de botones
        btnAgregarAutoparte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarAutoparte();
            }
        });

        btnFinalizarPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                finalizarPedido();
            }
        });

        add(panel);
    }

    private void agregarAutoparte() {
        try {
            int numeroPedido = Integer.parseInt(txtNumeroPedido.getText().trim());
            String fecha = txtFecha.getText().trim();
            int idCliente = Integer.parseInt(txtIdCliente.getText().trim());

            // Validar si el cliente existe
            if (!admin.existeCliente(idCliente)) {
                int opcion = JOptionPane.showConfirmDialog(this, "El cliente no existe. ¿Desea agregarlo?", "Cliente no encontrado", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    AgregarClienteDialog agregarClienteDialog = new AgregarClienteDialog(this, admin);
                    agregarClienteDialog.setVisible(true);

                    if (agregarClienteDialog.isClienteAgregado()) {
                        idCliente = agregarClienteDialog.getIdCliente();
                        txtIdCliente.setText(String.valueOf(idCliente));
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }

            pedido.setIdPedido(numeroPedido);
            pedido.setFecha(fecha);
            pedido.setCliente(idCliente);

            int idAutoparte = Integer.parseInt(txtIdAutoparte.getText().trim());
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());

            // Validar si la autoparte existe
            if (!admin.existeAutoparte(idAutoparte)) {
                JOptionPane.showMessageDialog(this, "La autoparte con ID " + idAutoparte + " no existe.");
                return;
            }

            // Validar cantidad disponible en stock
            int stock = admin.DisponibilidadStock(idAutoparte);
            if (cantidad > stock) {
                JOptionPane.showMessageDialog(this, "No hay suficiente stock disponible para la autoparte con ID " + idAutoparte + ".");
                return;
            }

            // Agregar detalle al pedido
            double precioUnitario = admin.getPrecioAutoparte(idAutoparte);
            double precioTotal = precioUnitario * cantidad;
            pedido.agregarDetalle(idAutoparte, precioUnitario, cantidad);
            admin.ModificarStock(idAutoparte, cantidad, 0); // Reducir stock de la autoparte

            // Agregar detalle a la lista de la interfaz
            detallesListModel.addElement("ID Autoparte: " + idAutoparte + ", Cantidad: " + cantidad + ", Precio Total: $" + precioTotal);

            // Limpiar campos de autoparte y cantidad para nuevas entradas
            txtIdAutoparte.setText("");
            txtCantidad.setText("");

            int opcion = JOptionPane.showConfirmDialog(this, "¿Desea agregar más autopartes?", "Agregar más autopartes", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.NO_OPTION) {
                finalizarPedido();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese números válidos en los campos correspondientes.");
        }
    }

    private void finalizarPedido() {
        if (pedido.getDetalles().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay detalles de autopartes agregados al pedido.");
            return;
        }

        // Agregar el pedido al administrador
        if (admin.CargarPedido(pedido)) {
            JOptionPane.showMessageDialog(this, "Pedido cargado exitosamente.");
            dispose(); // Cerrar la ventana después de finalizar el pedido
        } else {
            JOptionPane.showMessageDialog(this, "Error al cargar el pedido. Intente nuevamente.");
        }
    }

   
}
