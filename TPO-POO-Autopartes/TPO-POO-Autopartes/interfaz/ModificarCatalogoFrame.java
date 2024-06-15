package interfaz;
import javax.swing.*;

import negocio.Administrador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class ModificarCatalogoFrame extends JFrame {
	 private static final long serialVersionUID = 1L;
private Administrador admin;

    private JTextField codigoField;
    private JComboBox<String> opcionComboBox;
    private JTextField valorField;
    private JButton modificarButton;

    public ModificarCatalogoFrame(Administrador admin) {
    	this.admin = admin;
        setTitle("Modificar Catálogo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel codigoLabel = new JLabel("Código de Autoparte:");
        codigoField = new JTextField();
        panel.add(codigoLabel);
        panel.add(codigoField);

        JLabel opcionLabel = new JLabel("Opción a modificar:");
        opcionComboBox = new JComboBox<>(new String[]{"Código", "Denominación", "Descripción", "Categoría",
                                                      "Marca", "Modelo", "Precio", "Stock Mínimo", "Enlace", "Stock"});
        panel.add(opcionLabel);
        panel.add(opcionComboBox);

        JLabel valorLabel = new JLabel("Nuevo valor:");
        valorField = new JTextField();
        panel.add(valorLabel);
        panel.add(valorField);

        modificarButton = new JButton("Modificar");
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(codigoField.getText());
                    int opcionIndex = opcionComboBox.getSelectedIndex() + 1; // Sumar 1 porque las opciones inician en 1
                    String valor = valorField.getText();

                    // Verificar si existe la autoparte con el código ingresado
                    if (!admin.existeAutoparte(codigo)) {
                        JOptionPane.showMessageDialog(ModificarCatalogoFrame.this,
                                "No existe autoparte con el código ingresado.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Llamar al método correspondiente en Administrador para modificar el catálogo
                    Object nuevoValor = parseValor(opcionIndex, valor);
                    if (nuevoValor != null) {
                        admin.ModificarCatalogo(codigo, opcionIndex, nuevoValor);
                        JOptionPane.showMessageDialog(ModificarCatalogoFrame.this,
                                "Se ha modificado exitosamente el artículo del catálogo.",
                                "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(ModificarCatalogoFrame.this,
                                "Opción inválida o tipo de dato incorrecto.",
                                "Error de modificación", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ModificarCatalogoFrame.this,
                            "Ingrese un código válido.",
                            "Error de entrada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(modificarButton);
        

        add(panel);
        setVisible(true);
    }

    // Método para convertir el valor ingresado a su tipo correspondiente
    private Object parseValor(int opcion, String valor) {
        switch (opcion) {
            case 1: // Código (entero)
            case 8: // Stock Mínimo (entero)
            case 10: // Stock (entero)
                try {
                    return Integer.parseInt(valor);
                } catch (NumberFormatException e) {
                    return null;
                }
            case 7: // Precio (double)
                try {
                    return Double.parseDouble(valor);
                } catch (NumberFormatException e) {
                    return null;
                }
            default: // String para los demás campos
                return valor;
        }
    }

}