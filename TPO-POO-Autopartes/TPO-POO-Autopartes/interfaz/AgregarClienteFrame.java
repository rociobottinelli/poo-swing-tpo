package interfaz;

import negocio.Administrador;
import negocio.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class AgregarClienteFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private Administrador admin;
    private JTextField idField;
    private JTextField nombreField;
    private JTextField direccionField;
    private JTextField localidadField;
    private JTextField provinciaField;
    private JTextField correoField;
    private JTextField telefonoField;

    private int idCliente; // Nuevo atributo para almacenar temporalmente el ID del cliente

    public AgregarClienteFrame(Administrador admin) {
        this.admin = admin;

        setTitle("Agregar Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2));

        JLabel idLabel = new JLabel("ID del cliente:");
        idField = new JTextField(10);
        panel.add(idLabel);
        panel.add(idField);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField(10);
        panel.add(nombreLabel);
        panel.add(nombreField);

        JLabel direccionLabel = new JLabel("Dirección:");
        direccionField = new JTextField(10);
        panel.add(direccionLabel);
        panel.add(direccionField);

        JLabel localidadLabel = new JLabel("Localidad:");
        localidadField = new JTextField(10);
        panel.add(localidadLabel);
        panel.add(localidadField);

        JLabel provinciaLabel = new JLabel("Provincia:");
        provinciaField = new JTextField(10);
        panel.add(provinciaLabel);
        panel.add(provinciaField);

        JLabel correoLabel = new JLabel("Correo:");
        correoField = new JTextField(10);
        panel.add(correoLabel);
        panel.add(correoField);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoField = new JTextField(10);
        panel.add(telefonoLabel);
        panel.add(telefonoField);

        JButton agregarButton = new JButton("Agregar Cliente");
        panel.add(new JLabel()); // Espacio vacío para alinear botón
        panel.add(agregarButton);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    agregarCliente();
                    dispose(); // Cierra la ventana después de agregar el cliente
                } else {
                    JOptionPane.showMessageDialog(AgregarClienteFrame.this,
                            "Todos los campos son obligatorios y el ID debe ser numérico. Por favor, complete todos los campos.",
                            "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        add(panel);

        setVisible(true);
    }

    private boolean validarCampos() {
        String id = idField.getText();
        String nombre = nombreField.getText();
        String direccion = direccionField.getText();
        String localidad = localidadField.getText();
        String provincia = provinciaField.getText();
        String correo = correoField.getText();
        String telefono = telefonoField.getText();

        // Verifica que todos los campos estén completos y que el ID sea numérico
        if (id.isEmpty() || !id.matches("\\d+")) {
            return false;
        }

        return !nombre.isEmpty() && !direccion.isEmpty() &&
                !localidad.isEmpty() && !provincia.isEmpty() &&
                !correo.isEmpty() && !telefono.isEmpty();
    }

    private void agregarCliente() {
        int codigo = Integer.parseInt(idField.getText());
        String nombre = nombreField.getText();
        String direccion = direccionField.getText();
        String localidad = localidadField.getText();
        String provincia = provinciaField.getText();
        String correo = correoField.getText();
        String telefono = telefonoField.getText();

        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setCodigo(codigo);
        nuevoCliente.setNombre(nombre);
        nuevoCliente.setDireccion(direccion);
        nuevoCliente.setLocalidad(localidad);
        nuevoCliente.setProvincia(provincia);
        nuevoCliente.setCorreo(correo);
        nuevoCliente.setTelefono(telefono);

        // Intentar agregar el cliente
        if (admin.agregarCliente(nuevoCliente)) {
            JOptionPane.showMessageDialog(this, "Cliente agregado correctamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Ya existe un cliente con el ID: " + codigo,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método público para establecer dinámicamente el ID del cliente desde otra clase
    public void setIdCliente(int id) {
        this.idCliente = id;
        idField.setText(String.valueOf(id)); // Mostrar el ID en el campo de texto
        idField.setEditable(false); // Bloquear la edición del ID después de establecerlo
    }
}
