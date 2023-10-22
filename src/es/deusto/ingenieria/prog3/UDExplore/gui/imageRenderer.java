package es.deusto.ingenieria.prog3.UDExplore.gui;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class imageRenderer extends DefaultTableCellRenderer {
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof String) {
        	
        	String imageRuta = (String) value;
        	JLabel label = new JLabel();
        	  try {
                  ImageIcon icon = new ImageIcon(imageRuta);
                  label.setIcon(icon);
                  label.setHorizontalAlignment(SwingConstants.CENTER);
                  return label;
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
          return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      }
        	

}
