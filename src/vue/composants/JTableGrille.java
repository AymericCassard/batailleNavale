/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue.composants;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.Enumeration;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author acassard
 */
public class JTableGrille extends JTable {

    public JTableGrille(DisplayCase[][] data, Dimension prefSize) {
        super(new GrilleTableModel(data));
        initComponents(prefSize);
    }

    private void initComponents(Dimension prefSize) {
        this.setRowSelectionAllowed(false);
        this.setShowGrid(false);
        this.setIntercellSpacing(new Dimension(0, 0));

        Enumeration<TableColumn> columns = this.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn c = columns.nextElement();
            c.setCellRenderer(new CustomCellRenderer());
        }
        int availableHeight = (int) prefSize.getHeight();
        int rowHeight = availableHeight / (this.getRowCount() + 1); // ajoute 1 au rowCount pour tenir compte du header            
        this.setRowHeight(rowHeight);
        this.getTableHeader().setMaximumSize(new Dimension((int) prefSize.getWidth(), rowHeight));
        this.getTableHeader().setReorderingAllowed(false);
        this.getTableHeader().setResizingAllowed(false);
        this.setMaximumSize(new Dimension((int) prefSize.getWidth(), rowHeight * this.getRowCount()));
        this.setOpaque(false);
        this.setBackground(new Color(255, 255, 255, 0));
    }
    
    public GrilleTableModel getGrilleTableModel(){
        GrilleTableModel tableModel = (GrilleTableModel) this.dataModel;
        return tableModel;
    }
    
    public void setGrilleTableModel(DisplayCase[][] data){
        GrilleTableModel tableModel = (GrilleTableModel) this.dataModel;
        tableModel.setData(data);
    }

    public static class GrilleTableModel extends AbstractTableModel {

        private static String[] COLUMN_NAMES = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        //2D array contenant les display case avec pour clé les valeurs [x][y]
        private DisplayCase[][] data;

        public GrilleTableModel(DisplayCase[][] data) {
            this.data = data;
        }

        @Override
        public int getRowCount() {
            return 10;
        }

        @Override
        public int getColumnCount() {
            return 11;
        }

        @Override
        public Object getValueAt(int row, int column) {
            if (column == 0) {
                return row + 1;
            }

            return this.data[row][column - 1];

        }

        @Override
        public String getColumnName(int col) {
            if (col == 0) {
                return null;
            }
            return COLUMN_NAMES[col - 1];
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

        @Override
        public Class<?> getColumnClass(int c) {
            if (c == 0) {
                return int.class;
            }
            return ImageIcon.class;
        }

        public DisplayCase[][] getData() {
            return data;
        }

        public void setData(DisplayCase[][] data) {
            this.data = data;
        }                
        
    }

    public static class CustomCellRenderer extends DefaultTableCellRenderer {        

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JComponent c = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            //empeche la colonne de header(column=0) d'etre selectionnée
            if (column == 0) {
                c = (JComponent) table.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (hasFocus) {
                    c.setBackground(table.getTableHeader().getBackground());
                }
                return c;
            } 
            
            if (hasFocus) {                
                c.setBackground(DisplayCase.backgroundColors.SELECTED.color);               
            } else {
                c.setBackground(table.getBackground());
            }
            
            if (value != null) {
                if (value.getClass() == DisplayCase.class) {
                    this.setText(null);
                    DisplayCase caseConcernee = (DisplayCase) value;
                    ImageIcon img = caseConcernee.getImg();
                    //les cases ou peuvent se placer les bateaux sont affichées en vert
                    if(caseConcernee.getBackgroundColor() != null){
                        c.setBackground(caseConcernee.getBackgroundColor());
                    }
                    c.setBorder(caseConcernee.getBorder());                    
                    if (img.getImage() != null) {                        
                        img = sizeImageIconToCell(img, table, c);                        
                        c.setForeground(table.getForeground());                                                
                    } else {
                        //cache les images icon vides en appliquant la couleur background au component foreground
                        c.setForeground(table.getBackground());
                    }
                    if (caseConcernee.getEdgeCase()){
                        
                        c.setAlignmentX(0.7f);
                        
                    }
                    setIcon(img);                    
                }
            }
                        
            return c;
        }
                

        private static ImageIcon sizeImageIconToCell(ImageIcon img, JTable table, Component c) {
            int rowWidth = table.getColumnModel().getColumn(0).getWidth();            
            int rowHeigth = table.getRowHeight();
            img.setImage(img.getImage().getScaledInstance(rowWidth, rowHeigth, Image.SCALE_DEFAULT));
            return img;
        }
             
         
    }

}
