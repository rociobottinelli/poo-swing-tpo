package interfaz;

import javax.swing.*;

import negocio.Administrador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class StockDisponibleFrame extends JFrame {
	 private static final long serialVersionUID = 1L;
    private Administrador admin;
    private JTextField codigoField;
    private JButton consultarButton;

    public StockDisponibleFrame(Administrador admin) {
        this.admin = admin;

        setTitle("Consultar Stock Disponible");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar solo la ventana actual
        setLocationRelativeTo(null); // Centrar en pantalla
        setLayout(null); // Uso de layout absoluto

        JLabel codigoLabel = new JLabel("Código del Autoparte:");
        codigoLabel.setBounds(20, 20, 150, 25);
        add(codigoLabel);

        codigoField = new JTextField();
        codigoField.setBounds(180, 20, 150, 25);
        add(codigoField);

        consultarButton = new JButton("Consultar");
        consultarButton.setBounds(120, 60, 120, 30);
        add(consultarButton);

        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarStockDisponible();
            }
        });
    }

    private void consultarStockDisponible() {
        String codigoText = codigoField.getText();
        try {
            int codigo = Integer.parseInt(codigoText);
            if (admin.existeAutoparte(codigo)) {
                int stock = admin.DisponibilidadStock(codigo);
                JOptionPane.showMessageDialog(this, "Stock disponible: " + stock + " unidades", "Consulta de Stock", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No existe autoparte con el código ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un código válido (número entero).", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
