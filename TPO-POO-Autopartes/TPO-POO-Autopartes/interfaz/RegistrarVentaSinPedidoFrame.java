package interfaz;

import javax.swing.*;
import negocio.Administrador;
import negocio.Cliente;
import negocio.Pedido;
import negocio.Venta;

import java.awt.event.*;

public class RegistrarVentaSinPedidoFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private Administrador admin;

    private JTextField txtCodigoVenta;
    private JTextField txtIdCliente;
    private JTextField txtFechaVenta;
    private JTextField txtIdAutoparte;
    private JTextField txtCantidadAutoparte;
    private JButton btnAgregarAutoparte;
    private JButton btnRegistrarVenta;

    private Pedido detalleVenta;

    public RegistrarVentaSinPedidoFrame(Administrador admin) {
        this.admin = admin;
        this.detalleVenta = new Pedido();

        setTitle("Registrar Venta Sin Pedido");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana en la pantalla

        initComponents();
    }

    private void initComponents() {
        // Crear componentes
        JPanel panel = new JPanel();

        JLabel lblCodigoVenta = new JLabel("Código de Venta:");
        txtCodigoVenta = new JTextField(10);

        JLabel lblIdCliente = new JLabel("ID del Cliente:");
        txtIdCliente = new JTextField(10);

        JLabel lblFechaVenta = new JLabel("Fecha de la Venta:");
        txtFechaVenta = new JTextField(10);

        JLabel lblIdAutoparte = new JLabel("ID de la Autoparte:");
        txtIdAutoparte = new JTextField(10);

        JLabel lblCantidadAutoparte = new JLabel("Cantidad:");
        txtCantidadAutoparte = new JTextField(10);

        btnAgregarAutoparte = new JButton("Agregar Autoparte");
        btnRegistrarVenta = new JButton("Registrar Venta");

        panel.add(lblCodigoVenta);
        panel.add(txtCodigoVenta);
        panel.add(lblIdCliente);
        panel.add(txtIdCliente);
        panel.add(lblFechaVenta);
        panel.add(txtFechaVenta);
        panel.add(lblIdAutoparte);
        panel.add(txtIdAutoparte);
        panel.add(lblCantidadAutoparte);
        panel.add(txtCantidadAutoparte);
        panel.add(btnAgregarAutoparte);
        panel.add(btnRegistrarVenta);

        // Acción del botón Agregar Autoparte
        btnAgregarAutoparte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarAutoparte();
            }
        });

        // Acción del botón Registrar Venta
        btnRegistrarVenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarVenta();
            }
        });

        add(panel);
    }

    private void agregarAutoparte() {
        try {
            int idAutoparte = Integer.parseInt(txtIdAutoparte.getText().trim());
            int cantidad = Integer.parseInt(txtCantidadAutoparte.getText().trim());

            if (admin.existeAutoparte(idAutoparte)) {
                int stock = admin.DisponibilidadStock(idAutoparte);
                if (cantidad <= stock) {
                    double precioUnidad = admin.getPrecioAutoparte(idAutoparte);
                    detalleVenta.agregarDetalle(idAutoparte, precioUnidad, cantidad);
                    admin.ModificarStock(idAutoparte, cantidad, 0);
                    JOptionPane.showMessageDialog(this, "Autoparte agregada exitosamente.");
                    txtIdAutoparte.setText("");
                    txtCantidadAutoparte.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Solo hay " + stock + " unidades en stock.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "La autoparte no existe.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ERROR: Ingrese solo números enteros.");
        }
    }

    private void registrarVenta() {
        try {
            int codigoVenta = Integer.parseInt(txtCodigoVenta.getText().trim());
            int idCliente = Integer.parseInt(txtIdCliente.getText().trim());
            String fechaVenta = txtFechaVenta.getText().trim();

            if (!admin.existeVentaConId(codigoVenta)) {
                Cliente cliente;
                if (admin.existeCliente(idCliente)) {
                    cliente = admin.getClienteById(idCliente);
                } else {
                    JOptionPane.showMessageDialog(this, "El cliente no está registrado. Registre el cliente primero.");
                    return;
                }

                Venta venta = new Venta();
                venta.setCodigo(codigoVenta);
                venta.setCliente(cliente);
                detalleVenta.setFecha(fechaVenta);
                venta.setDetalleVenta(detalleVenta);

                admin.RegistrarVentaSinPedido(detalleVenta, venta);
                JOptionPane.showMessageDialog(this, "Venta registrada exitosamente.");
                RegistrarMedioDePagoFrame medioDePagoFrame = new RegistrarMedioDePagoFrame(admin, venta);
                medioDePagoFrame.setVisible(true);
                medioDePagoFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        FacturaFrame facturaFrame = new FacturaFrame(venta,admin);
                        facturaFrame.setVisible(true);
                    }
                });
                // Limpiar campos después de registrar la venta
                txtCodigoVenta.setText("");
                txtIdCliente.setText("");
                txtFechaVenta.setText("");
                detalleVenta = new Pedido(); // Resetear el detalle de la venta
            } else {
                JOptionPane.showMessageDialog(this, "El código de venta ya existe. Introduzca otro número.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ERROR: Ingrese solo números enteros.");
        }
    }

}
