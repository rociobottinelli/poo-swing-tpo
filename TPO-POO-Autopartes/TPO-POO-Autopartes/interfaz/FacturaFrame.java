package interfaz;

import javax.swing.*;
import java.awt.*;

import negocio.Administrador;
import negocio.Cliente;
import negocio.Pedido;
import negocio.Venta;

public class FacturaFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    Administrador admin;

    public FacturaFrame(Venta venta, Administrador admin) {
    	this.admin = admin;
        setTitle("Factura de Venta");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents(venta);
    }

    private void initComponents(Venta venta) {
        Cliente cliente = venta.getCliente();
        Pedido pedido = venta.getDetalleVenta();
        double totalVenta = venta.getMontoFinal();
        String medioPago = venta.getMedioDePago();
        int cantCuotas = venta.getCantCuotas();

        // Crear componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(new JLabel("**********************************************************************************"));
        panel.add(new JLabel("                         FACTURA                          "));
        panel.add(new JLabel("**********************************************************************************"));
        panel.add(new JLabel("Fecha de venta: " + pedido.getFecha()));
        panel.add(new JLabel("\nDATOS DEL NEGOCIO                       	DATOS DEL CLIENTE"));
        panel.add(new JLabel("Nombre: TUTTA LA MACCHINA               	ID: " + cliente.getCodigo()));
        panel.add(new JLabel("Dirección: Avenida Corrientes 123       	Nombre: " + cliente.getNombre()));
        panel.add(new JLabel("Localidad: Monserrat                    	Dirección: " + cliente.getDireccion()));
        panel.add(new JLabel("Provincia: Buenos Aires                 	Localidad: " + cliente.getLocalidad()));
        panel.add(new JLabel("Teléfono: 1122334455                    	Provincia: " + cliente.getProvincia()));
        panel.add(new JLabel("                                        	Correo: " + cliente.getCorreo()));
        panel.add(new JLabel("                                        	Teléfono: " + cliente.getTelefono()));
        panel.add(new JLabel("**********************************************************************************"));
        panel.add(new JLabel("Detalles de la Venta:"));
        
        JPanel detallesPanel = new JPanel();
        detallesPanel.setLayout(new GridLayout(0, 5));
        
        detallesPanel.add(new JLabel("ID"));
        detallesPanel.add(new JLabel("Autoparte"));
        detallesPanel.add(new JLabel("Cantidad"));
        detallesPanel.add(new JLabel("Precio Unitario"));
        detallesPanel.add(new JLabel("Subtotal"));

        for (Pedido.Detalle detalle : pedido.getDetalles()) {
            String nombreAutoparte = admin.getNombreAutoparte(detalle.getArticulo());
            detallesPanel.add(new JLabel(String.valueOf(detalle.getArticulo())));
            detallesPanel.add(new JLabel(nombreAutoparte));
            detallesPanel.add(new JLabel(String.valueOf(detalle.getCantidad())));
            detallesPanel.add(new JLabel(String.format("%.2f", detalle.getPrecio())));
            detallesPanel.add(new JLabel(String.format("%.2f", detalle.getCantidad() * detalle.getPrecio())));
        }
        
        panel.add(detallesPanel);
        panel.add(new JLabel("----------------------------------------------------------------------------------"));
        panel.add(new JLabel("Total Venta: " + String.format("%.2f", pedido.getMontoTotal())));
        panel.add(new JLabel("**********************************************************************************"));
        panel.add(new JLabel("Medio de pago: " + medioPago));
        panel.add(new JLabel("Cantidad de Cuotas: " + cantCuotas));
        panel.add(new JLabel("Monto Total a Pagar: " + String.format("%.2f", totalVenta)));
        panel.add(new JLabel("**********************************************************************************"));
        panel.add(new JLabel("Gracias por su compra!"));

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);
    }
}
