package interfaz;

import javax.swing.*;
import negocio.Administrador;
import java.awt.event.*;

public class CancelarPedidoFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private Administrador admin;
    private JTextField txtNumeroPedido;
    private JButton btnCancelarPedido;

    public CancelarPedidoFrame(Administrador admin) {
        this.admin = admin;

        setTitle("Cancelar Pedido");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana en la pantalla

        initComponents();
    }

    private void initComponents() {
        // Crear componentes
        JPanel panel = new JPanel();

        JLabel lblNumeroPedido = new JLabel("Número de Pedido:");
        txtNumeroPedido = new JTextField(10);
        btnCancelarPedido = new JButton("Cancelar Pedido");

        panel.add(lblNumeroPedido);
        panel.add(txtNumeroPedido);
        panel.add(btnCancelarPedido);

        // Acción del botón Cancelar Pedido
        btnCancelarPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelarPedido();
            }
        });

        add(panel);
    }

    private void cancelarPedido() {
        try {
            int numeroPedido = Integer.parseInt(txtNumeroPedido.getText().trim());

            if (admin.existePedido(numeroPedido)) {
                admin.CancelarPedido(numeroPedido);
                JOptionPane.showMessageDialog(this, "Pedido cancelado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "El pedido con número " + numeroPedido + " no existe.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ERROR: Ingrese solo números enteros.");
        }
    }

 
}
