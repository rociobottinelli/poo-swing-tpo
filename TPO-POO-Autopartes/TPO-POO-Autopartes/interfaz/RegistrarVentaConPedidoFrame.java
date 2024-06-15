package interfaz;

import javax.swing.*;
import negocio.Administrador;
import negocio.Cliente;
import negocio.Venta;
import java.awt.event.*;

public class RegistrarVentaConPedidoFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private Administrador admin;

    private JTextField txtCodigoVenta;
    private JTextField txtNumeroPedido;
    private JButton btnRegistrarVenta;

    public RegistrarVentaConPedidoFrame(Administrador admin) {
        this.admin = admin;

        setTitle("Registrar Venta con Pedido");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana en la pantalla

        initComponents();
    }

    private void initComponents() {
        // Crear componentes
        JPanel panel = new JPanel();

        JLabel lblCodigoVenta = new JLabel("Código de Venta:");
        txtCodigoVenta = new JTextField(10);

        JLabel lblNumeroPedido = new JLabel("Número del Pedido:");
        txtNumeroPedido = new JTextField(10);

        btnRegistrarVenta = new JButton("Registrar Venta");

        panel.add(lblCodigoVenta);
        panel.add(txtCodigoVenta);
        panel.add(lblNumeroPedido);
        panel.add(txtNumeroPedido);
        panel.add(btnRegistrarVenta);

        // Acción del botón Registrar Venta
        btnRegistrarVenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarVentaConPedido();
            }
        });

        add(panel);
    }

    private void registrarVentaConPedido() {
        try {
            int codigoVenta = Integer.parseInt(txtCodigoVenta.getText().trim());
            int numeroPedido = Integer.parseInt(txtNumeroPedido.getText().trim());

            if (!admin.existeVentaConId(codigoVenta)) {
                if (admin.existePedido(numeroPedido)) {
                    Venta venta = new Venta();
                    venta.setCodigo(codigoVenta);

                    int idClientePedido = admin.getClienteEnPedidoById(numeroPedido);
                    Cliente cliente = admin.getClienteById(idClientePedido);
                    venta.setCliente(cliente);

                    admin.RegistrarVentaConPedido(numeroPedido, venta);
                    JOptionPane.showMessageDialog(this, "Venta registrada exitosamente.");

                    // Mostrar ventana para registrar el medio de pago
                    RegistrarMedioDePagoFrame medioDePagoFrame = new RegistrarMedioDePagoFrame(admin, venta);
                    medioDePagoFrame.setVisible(true);

                    // Mostrar la factura después de registrar el medio de pago
                    medioDePagoFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            FacturaFrame facturaFrame = new FacturaFrame(venta, admin);
                            facturaFrame.setVisible(true);
                        }
                    });

           
                    txtCodigoVenta.setText("");
                    txtNumeroPedido.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "El pedido con número " + numeroPedido + " no existe.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "El código de venta " + codigoVenta + " ya existe. Introduzca otro número.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ERROR: Ingrese solo números enteros.");
        }
    }
}
