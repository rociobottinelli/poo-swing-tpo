package interfaz;

import javax.swing.*;
import negocio.Administrador;
import negocio.Venta;

import java.awt.event.*;

public class RegistrarMedioDePagoFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private Administrador admin;
    private Venta venta;

    private JButton btnDebito;
    private JButton btnCredito;
    private JButton btnEfectivo;

    public RegistrarMedioDePagoFrame(Administrador admin, Venta venta) {
        this.admin = admin;
        this.venta = venta;

        setTitle("Registrar Medio de Pago");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana en la pantalla

        initComponents();
    }

    private void initComponents() {
        // Crear componentes
        JPanel panel = new JPanel();

        JLabel lblSeleccioneMedio = new JLabel("Seleccione el medio de pago:");

        btnDebito = new JButton("Tarjeta de débito");
        btnCredito = new JButton("Tarjeta de crédito");
        btnEfectivo = new JButton("Efectivo");

        panel.add(lblSeleccioneMedio);
        panel.add(btnDebito);
        panel.add(btnCredito);
        panel.add(btnEfectivo);

        // Acciones de los botones
        btnDebito.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarMedioDePago("Tarjeta de débito");
            }
        });

        btnCredito.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarMedioDePago("Tarjeta de crédito");
            }
        });

        btnEfectivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarMedioDePago("Efectivo");
            }
        });

        add(panel);
    }

    private void registrarMedioDePago(String medioPago) {
        double totalVenta = venta.getDetalleVenta().getMontoTotal();
        double montoFinal = 0.0;

        switch (medioPago) {
            case "Tarjeta de débito":
                montoFinal = totalVenta;
                venta.setMedioDePago("Tarjeta de débito");
                venta.setCantCuotas(0);
                break;

            case "Tarjeta de crédito":
                String[] opciones = {"2", "3", "6"};
                String seleccion = (String) JOptionPane.showInputDialog(this, "Seleccione la cantidad de cuotas:", 
                        "Cuotas", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if (seleccion != null) {
                    int cantidadCuotas = Integer.parseInt(seleccion);
                    venta.setMedioDePago("Tarjeta de crédito");
                    venta.setCantCuotas(cantidadCuotas);

                    if (cantidadCuotas == 2) {
                        montoFinal = totalVenta * 1.06;
                    } else if (cantidadCuotas == 3) {
                        montoFinal = totalVenta * 1.12;
                    } else if (cantidadCuotas == 6) {
                        montoFinal = totalVenta * 1.20;
                    }
                }
                break;

            case "Efectivo":
                montoFinal = totalVenta * 0.9;
                venta.setMedioDePago("Efectivo");
                venta.setCantCuotas(0);
                break;

            default:
                JOptionPane.showMessageDialog(this, "Medio de pago no válido.");
                return;
        }

        venta.setMontoFinal(montoFinal);
        JOptionPane.showMessageDialog(this, "Monto a abonar: $" + montoFinal);
        admin.GeneradorDeFacturas(venta);
        dispose(); // Cerrar la ventana actual después de registrar el medio de pago
    }
}
