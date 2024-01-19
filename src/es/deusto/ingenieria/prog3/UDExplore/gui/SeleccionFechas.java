package es.deusto.ingenieria.prog3.UDExplore.gui;

import org.jdatepicker.JDatePanel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import es.deusto.ingenieria.prog3.UDExplore.io.Logica;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class SeleccionFechas {

    private UtilDateModel startDateModel;
    private UtilDateModel endDateModel;
    private JDatePickerImpl startDatePicker;
    private JDatePickerImpl endDatePicker;

    public SeleccionFechas() {
        JFrame frame = new JFrame("Seleccion de fechas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300); // Tamaño adecuado a mostrar
        frame.setLocationRelativeTo(null); // Centrar en la pantalla

        JPanel panel = new JPanel(new GridLayout(1, 2));
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {

        startDateModel = new UtilDateModel();
        endDateModel = new UtilDateModel();

        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");

        JDatePanel startDatePanel = new JDatePanelImpl(startDateModel, properties);
        JDatePanel endDatePanel = new JDatePanelImpl(endDateModel, properties);


        startDatePicker = new JDatePickerImpl((JDatePanelImpl) startDatePanel, new DateLabelFormatter());
        endDatePicker = new JDatePickerImpl((JDatePanelImpl) endDatePanel, new DateLabelFormatter());


        panel.add(createDatePickerPanel(startDatePicker, "Fecha de Inicio:"));
        panel.add(createDatePickerPanel(endDatePicker, "Fecha de Fin:"));
    }

    private JPanel createDatePickerPanel(JDatePickerImpl datePicker, String label) {
        JPanel datePickerPanel = new JPanel(new BorderLayout());

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.add(new JLabel(label));
        datePickerPanel.add(labelPanel, BorderLayout.NORTH);

        JPanel pickerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pickerPanel.add(datePicker);
        datePickerPanel.add(pickerPanel, BorderLayout.CENTER);

        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selectedDate = (Date) datePicker.getModel().getValue();
                Date fechaHoy = new Date();

                if (selectedDate != null && !selectedDate.before(fechaHoy)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    System.out.println(label + " " + dateFormat.format(selectedDate));

                  
                    if (label.equals("Fecha de Inicio:")) {
                        Logica.fechaIni = selectedDate;
                    } else if (label.equals("Fecha de Fin:")) {
                        if (!selectedDate.before(Logica.fechaIni)) {
                            Logica.fechaFin = selectedDate;

                           
                            JOptionPane.showMessageDialog(null,
                                    "Fechas seleccionadas:\nFecha de Inicio: " + dateFormat.format(Logica.fechaIni)
                                            + "\nFecha de Fin: " + dateFormat.format(Logica.fechaFin),
                                    "Fechas Seleccionadas", JOptionPane.INFORMATION_MESSAGE);
                           
                            JFrame seleccionFechasFrame = (JFrame) SwingUtilities.getWindowAncestor(aceptarButton);
                            seleccionFechasFrame.dispose();
                            
                          
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "La fecha de fin debe ser posterior a la fecha de inicio.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Por favor, selecciona una fecha válida a partir de hoy.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(aceptarButton);
        datePickerPanel.add(buttonPanel, BorderLayout.SOUTH);

        return datePickerPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SeleccionFechas::new);
    }

    private static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private static final long serialVersionUID = 1L;
		private final String datePattern = "yyyy-MM-dd";
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) {
            try {
                return dateFormatter.parseObject(text);
            } catch (java.text.ParseException e) {
                return null;
            }
        }

        @Override
        public String valueToString(Object value) throws ParseException {
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            if (value != null && value instanceof Date) {
                return dateFormat.format((Date) value);
            } else {
                return "";
            }
        }
    }
}



