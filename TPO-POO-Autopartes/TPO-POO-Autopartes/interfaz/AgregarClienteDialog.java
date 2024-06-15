package interfaz;

import javax.swing.*;
import negocio.Administrador;
import negocio.Cliente;
import java.awt.*;
import java.awt.event.*;

public class AgregarClienteDialog extends JDialog {
    private Administrador admin;
    private JTextField txtIdCliente, txtNombre, txtDireccion, txtLocalidad, txtProvincia, txtCorreo, txtTelefono;
    private boolean clienteAgregado = false;

    public AgregarClienteDialog(Frame parent, Administrador admin) {
        super(parent, "Agregar Cliente", true);
        this.admin = admin;

        setSize(400, 300);
        setLocationRelativeTo(parent);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(8, 2));

        JLabel lblIdCliente = new JLabel("ID del Cliente:");
        txtIdCliente = new JTextField();

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();

        JLabel lblDireccion = new JLabel("Dirección:");
        txtDireccion = new JTextField();

        JLabel lblLocalidad = new JLabel("Localidad:");
        txtLocalidad = new JTextField();

        JLabel lblProvincia = new JLabel("Provincia:");
        txtProvincia = new JTextField();

        JLabel lblCorreo = new JLabel("Correo:");
        txtCorreo = new JTextField();

        JLabel lblTelefono = new JLabel("Teléfono:");
        txtTelefono = new JTextField();

        JButton btnAgregar = new JButton("Agregar");

        panel.add(lblIdCliente);
        panel.add(txtIdCliente);
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblDireccion);
        panel.add(txtDireccion);
        panel.add(lblLocalidad);
        panel.add(txtLocalidad);
        panel.add(lblProvincia);
        panel.add(txtProvincia);
        panel.add(lblCorreo);
        panel.add(txtCorreo);
        panel.add(lblTelefono);
        panel.add(txtTelefono);
        panel.add(new JLabel());
        panel.add(btnAgregar);

        add(panel);

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarCliente();
            }
        });
    }

    private void agregarCliente() {
        try {
            int idCliente = Integer.parseInt(txtIdCliente.getText().trim());
            String nombre = txtNombre.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String localidad = txtLocalidad.getText().trim();
            String provincia = txtProvincia.getText().trim();
            String correo = txtCorreo.getText().trim();
            String telefono = txtTelefono.getText().trim();

            if (!admin.existeCliente(idCliente)) {
                Cliente cliente = new Cliente();
                cliente.setCodigo(idCliente);
                cliente.setNombre(nombre);
                cliente.setDireccion(direccion);
                cliente.setLocalidad(localidad);
                cliente.setProvincia(provincia);
                cliente.setCorreo(correo);
                cliente.setTelefono(telefono);

                admin.agregarCliente(cliente);
                clienteAgregado = true;
                JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente.");
                dispose(); // Cerrar el diálogo después de agregar el cliente
            } else {
                JOptionPane.showMessageDialog(this, "El cliente con ID " + idCliente + " ya existe.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ERROR: Ingrese solo números enteros en el ID del cliente.");
        }
    }

    public boolean isClienteAgregado() {
        return clienteAgregado;
    }

    public int getIdCliente() {
        return Integer.parseInt(txtIdCliente.getText().trim());
    }
}
