package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Modelo;
import vista.Vista;

/**
 *
 * @author alex
 */
public class Controlador implements ActionListener {

    DefaultTableModel modeloDeTabla = new DefaultTableModel();
    private Modelo modelo;
    private Vista vista;

    public Controlador() {

    }

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.btnEdad.addActionListener(this);
        this.vista.btnEnviar.addActionListener(this);
        this.vista.btnSalir.addActionListener(this);
        inicializarVista();
        mostrarDatos();
    }

    public void inicializarVista() {
        this.vista.setVisible(true);
        this.vista.setTitle("Ejemplo de MVC");
        this.vista.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (vista.btnEdad == ae.getSource()) {
            edad();
        }
        if (vista.btnEnviar == ae.getSource()) {
            llenarTabla();
            limparTextFields();
        }
        if (vista.btnSalir == ae.getSource()) {
            salir();
        }
    }

    public void mostrarDatos() {
        modeloDeTabla.addColumn("Nombre");
        modeloDeTabla.addColumn("Apellidos");
        modeloDeTabla.addColumn("edad");
        modeloDeTabla.addColumn("Sexo");
        modeloDeTabla.addColumn("Escuela");
        modeloDeTabla.addColumn("Fecha");
        vista.tabla.setModel(modeloDeTabla);
    }

    public void edad() {
        String edad = JOptionPane.showInputDialog("Ingresa tu edad");
        vista.tfEdad.setText(edad);
        modelo.setEdad(edad);
    }

    public void llenarTabla() {
        String[] datos = new String[6];
        modelo.setNombre(vista.tfNombre.getText());
        modelo.setApellidos(vista.tfApellidos.getText());
        modelo.setEdad(vista.tfEdad.getText());
        if (vista.rbtnHombre.isSelected()) {
            modelo.setSexo("Hombre");
        } else if (vista.rbtnMujer.isSelected()) {
            modelo.setSexo("Mujer");
        }
        modelo.setEscuela(vista.comboEscuelas.getSelectedItem().toString());

        String dia = Integer.toString(vista.calendario.getCalendar().get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(vista.calendario.getCalendar().get(Calendar.MONTH) + 1);
        String anio = Integer.toString(vista.calendario.getCalendar().get(Calendar.YEAR));
        String fecha = dia + "/" + mes + "/" + anio;
        modelo.setFecha(fecha);

        datos[0] = modelo.getNombre();
        datos[1] = modelo.getApellidos();
        datos[2] = modelo.getEdad();
        datos[3] = modelo.getSexo();
        datos[4] = modelo.getEscuela();
        datos[5] = modelo.getFecha();

        modeloDeTabla.addRow(datos);
    }

    public void limparTextFields() {
        vista.tfNombre.setText("");
        vista.tfApellidos.setText("");
        vista.tfEdad.setText("");
        vista.calendario.setCalendar(null);
        vista.grupoSexos.clearSelection();
    }

    public void salir() {
        System.exit(0);
    }
}
