/**
 *  segy Loader based on java
 *
 *           Author: Rong Tao
 *             Time: 2017.03
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BasicStroke;
import java.awt.Image;
import java.awt.image.BufferedImage;


import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JToolBar;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.AttributeSet;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.text.DateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.nio.file.Path;

import javax.imageio.ImageIO;




import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.java.dev.designgridlayout.DesignGridLayout;

class cjbsegy{
/* reference: cjbsegy.h */

        int tracl, tracr, fldr, tracf, ep, cdp, cdpt;/* int (0-6) */
        short trid, nvs, nhs, duse;/* short (7-10) */
        int offset, gelev, selev, sdepth, gdel, sdel, swdep, gwdep;/* int (11-18) */
        short scalel, scalco;/* short (19-20) */
        int  sx, sy, gx, gy;/* int (21-24) */	
        short counit, wevel, swevel, sut, gut;/* short (25-70) */
        short sstat, gstat, tstat, laga, lagb;	
        short delrt, muts, mute, ns, dt;	
        short gain, igc, igi, corr, sfs;	
        short sfe, slen, styp, stas, stae;	
        short tatyp, afilf, afils, nofilf, nofils;
        short lcf, hcf, lcs, hcs, year;
        short day, hour, minute, sec, timbas;
        short trwf, grnors, grnofr, grnlof, gaps;	
        short otrav;
        float d1, f1, d2, f2, ungpow, unscale;/* float (71-76) */	
        int ntr; /* int (77) */
        short mark, shortpad;/* short (78-79) */
        int  line3d, cdp3d;/* int (80-81) */
        short unass0,unass1,unass2,unass3,unass4 ;
        short unass5,unass6,unass7,unass8,unass9 ;

        String[] segy = {"tracl",   "tracr",   "fldr",    "tracf",   "ep",
                         "cdp",     "cdpt",    "trid",    "nvs",     "nhs",
                         "duse",    "offset",  "gelev",   "selev",   "sdepth",
                         "gdel",    "sdel",    "swdep",   "gwdep",   "scalel",
                         "scalco",  "sx",      "sy",      "gx",      "gy",
                         "counit",  "wevel",   "swevel",  "sut",     "gut",
                         "sstat",   "gstat",   "tstat",   "laga",    "lagb",
                         "delrt",   "muts",    "mute",    "ns",      "dt",
                         "gain",    "igc",     "igi",     "corr",    "sfs",
                         "sfe",     "slen",    "styp",    "stas",    "stae",
                         "tatyp",   "afilf",   "afils",   "nofilf",  "nofils",
                         "lcf",     "hcf",     "lcs",     "hcs",     "year",
                         "day",     "hour",    "minute",  "sec",     "timbas",
                         "trwf",    "grnors",  "grnofr",  "grnlof",  "gaps",
                         "otrav",   "d1",      "f1",      "d2",      "f2",
                         "ungpow",  "unscale", "ntr",     "mark",    "shortpad",
                         "line3d",  "cdp3d",
                         "unass0",  "unass1",  "unass2",  "unass3",  "unass4",
                         "unass5",  "unass6",  "unass7",  "unass8",  "unass9"};

}

class Swap{
        /* float */
        public static float swap (float value){
                int intValue = Float.floatToRawIntBits (value);
                intValue = swap (intValue);
                return Float.intBitsToFloat (intValue);
          }
        /* int */
        public static int swap (int value){
                int b1 = (value >>  0) & 0xff;
                int b2 = (value >>  8) & 0xff;
                int b3 = (value >> 16) & 0xff;
                int b4 = (value >> 24) & 0xff;
                return b1 << 24 | b2 << 16 | b3 << 8 | b4 << 0;
          }
        /* short */
        public static short swap (short value){
                int b1 = value & 0xff;
                int b2 = (value >> 8) & 0xff;
                return (short) (b1 << 8 | b2 << 0);
          }
        /* long */
        public static long swap (long value){
                long b1 = (value >>  0) & 0xff;
                long b2 = (value >>  8) & 0xff;
                long b3 = (value >> 16) & 0xff;
                long b4 = (value >> 24) & 0xff;
                long b5 = (value >> 32) & 0xff;
                long b6 = (value >> 40) & 0xff;
                long b7 = (value >> 48) & 0xff;
                long b8 = (value >> 56) & 0xff;
                return b1 << 56 | b2 << 48 | b3 << 40 | b4 << 32 |
                       b5 << 24 | b6 << 16 | b7 <<  8 | b8 <<  0;
          }
        /* double */
        public static double swap (double value){
                long longValue = Double.doubleToLongBits (value);
                longValue = swap (longValue);
                return Double.longBitsToDouble (longValue);
          }
        /* short array */
        public static void swap (short[] array){
                for (int i = 0; i < array.length; i++)
                        array[i] = swap (array[i]);
          }
        /* int array */
        public static void swap (int[] array){
                for (int i = 0; i < array.length; i++)
                        array[i] = swap (array[i]);
          }
        /* long array */
        public static void swap (long[] array){
                for (int i = 0; i < array.length; i++)
                        array[i] = swap (array[i]);
          }
        /* float array */
        public static void swap (float[] array){
                for (int i = 0; i < array.length; i++)
                        array[i] = swap (array[i]);
          }
        /* double array */
        public static void swap (double[] array){
                for (int i = 0; i < array.length; i++)
                        array[i] = swap (array[i]);
          }
}

class mySEGYJFrame extends JFrame {


        private String filename;

        private static JLabel countlabel = new JLabel();

        private Object[] columnNames = new Object[]{"Trace # "};

        private DefaultTableModel model;

        private ArrayList<cjbsegy> hdrlist;

        private JTable hdrtable;

        private JScrollPane hdrscrollpane;

        private String[] segy = {"tracl",   "tracr",   "fldr",    "tracf",   "ep",
                         "cdp",     "cdpt",    "trid",    "nvs",     "nhs",
                         "duse",    "offset",  "gelev",   "selev",   "sdepth",
                         "gdel",    "sdel",    "swdep",   "gwdep",   "scalel",
                         "scalco",  "sx",      "sy",      "gx",      "gy",
                         "counit",  "wevel",   "swevel",  "sut",     "gut",
                         "sstat",   "gstat",   "tstat",   "laga",    "lagb",
                         "delrt",   "muts",    "mute",    "ns",      "dt",
                         "gain",    "igc",     "igi",     "corr",    "sfs",
                         "sfe",     "slen",    "styp",    "stas",    "stae",
                         "tatyp",   "afilf",   "afils",   "nofilf",  "nofils",
                         "lcf",     "hcf",     "lcs",     "hcs",     "year",
                         "day",     "hour",    "minute",  "sec",     "timbas",
                         "trwf",    "grnors",  "grnofr",  "grnlof",  "gaps",
                         "otrav",   "d1",      "f1",      "d2",      "f2",
                         "ungpow",  "unscale", "ntr",     "mark",    "shortpad",
                         "line3d",  "cdp3d",
                         "unass0",  "unass1",  "unass2",  "unass3",  "unass4",
                         "unass5",  "unass6",  "unass7",  "unass8",  "unass9"};


        mySEGYJFrame(String filename)throws IOException,
                             NoSuchFieldException,
                             IllegalAccessException{

                this.filename = filename;


                setTitle("rongtao's segy header show");
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Toolkit toolkit = getToolkit();
                Dimension size = toolkit.getScreenSize();
                setLocation(size.width/10 , size.height/7 );


                hdrlist = ReadSEGY.readSegy(this.filename);



                model = new DefaultTableModel(columnNames, hdrlist.size());
                hdrtable = new JTable(model){
                        @Override
                        public boolean isCellEditable(int row, int column){
                                return false;
                              }
                    };
                hdrtable.getColumnModel().getColumn(0).setWidth(10);
                hdrtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                for(int itrace = 0;itrace<hdrlist.size();itrace++){
                        hdrtable.setValueAt(Integer.toString(itrace+1),itrace,0);
                    }
                hdrscrollpane = new JScrollPane(hdrtable);


                JComboBox info = new JComboBox<String>(segy);
                info.setSelectedIndex(0);
                info.addItemListener(new itemlistener());



                JPanel panel1 = new JPanel();
                JLabel titlelabel = new JLabel("SEGY header info");
                DesignGridLayout layout1 = new DesignGridLayout(panel1);
                layout1.row().left().add(new JSeparator()).fill().withOwnRowWidth();
                layout1.row().center().add(titlelabel);
                layout1.row().left().add(new JSeparator()).fill().withOwnRowWidth();
                layout1.row().left().add(info);
                layout1.row().left().add(new JSeparator()).fill().withOwnRowWidth();
                layout1.row().left().add(hdrscrollpane);
                layout1.row().left().add(new JSeparator()).fill().withOwnRowWidth();



                JInternalFrame internalFrame = new JInternalFrame("SEGY header",true,true, true, true);  
                DesignGridLayout mainLayout = new DesignGridLayout(internalFrame.getContentPane());
                mainLayout.row().grid().add(panel1);

                internalFrame.pack();
                internalFrame.setVisible(true);


                getContentPane().add(internalFrame);
                setVisible(true);
                pack();

          }
        class itemlistener implements ItemListener{

                        public void itemStateChanged(ItemEvent e) {

                                if (e.getStateChange() == ItemEvent.SELECTED) {
                                        JComboBox combo = (JComboBox) e.getSource();
                                        String item = (combo.getSelectedItem()).toString();

                                        DefaultTableModel temp =(DefaultTableModel)hdrtable.getModel();
                                        int row = hdrtable.getRowCount();  
                                        int col = hdrtable.getColumnCount();  
                                        Vector<Object> newCol = new Vector<Object>();
                                        newCol.add(item);
                                        temp.addColumn(newCol);
                                        try{
                                                Field h = cjbsegy.class.getDeclaredField(item);
                                                h.setAccessible(true);
                                                for(int itrace = 0;itrace<hdrlist.size();itrace++){
                                                        hdrtable.setValueAt(h.get(hdrlist.get(itrace)),itrace,col);
                                                          }
                                        }catch(Exception ee){ee.printStackTrace();}
                                        }

                              }
          }
        static class ReadSEGY{

                static int counttrace = 0;

                public static ArrayList<cjbsegy> readSegy(String filename)
                         throws java.io.IOException,
                                NoSuchFieldException,
                                IllegalAccessException{

                        DataInputStream fp = null;
                        if(!new File(filename).exists()){  
                                System.out.println("The "+filename +" file dont't exists");
                                return null;  
                              } 
                        fp = new DataInputStream(new FileInputStream(new File(filename)));

                        
                        countlabel.setText(Integer.toString(++counttrace));
                        cjbsegy hdr = readCjbsegy(fp);
                        fp.skip(hdr.ns*4);

                        ArrayList<cjbsegy> list = new ArrayList<cjbsegy>();
                        list.add(hdr);
                        do{   
                                countlabel.setText(Integer.toString(++counttrace));
                                if(fp.available()<=0)break;
                                list.add(readCjbsegy(fp));
                                fp.skip(hdr.ns*4);
                        } while(fp.available()>0);

                        return list;
                }//read file


                private static cjbsegy readCjbsegy(DataInputStream fp)
                         throws java.io.IOException,
                                NoSuchFieldException,
                                IllegalAccessException{

                        cjbsegy hdr = new cjbsegy();

                        for(int i = 0; i<92; i++){
                                Field strs = cjbsegy.class.getDeclaredField(hdr.segy[i]);
                                strs.setAccessible(true);

                                if( ( i>=0  && i<=6  ) 
                                 || ( i>=11 && i<=18 ) 
                                 || ( i>=21 && i<=24 ) 
                                 ||   i==77 
                                 || ( i>=80 && i<=81 ) )
                                        strs.set(hdr,fp.readInt());
                                else if( ( i>=7  && i<=10 ) 
                                      || ( i>=19 && i<=20 ) 
                                      || ( i>=25 && i<=70 ) 
                                      || ( i>=78 && i<=79 )
                                      || ( i>=82 && i<=91 )  )
                                        strs.set(hdr,fp.readShort());
                                else 
                                        strs.set(hdr,fp.readFloat());
                              }
                        return hdr;
                    }
          }

        public static void main(String[]args)                 
                throws java.io.IOException,
                        NoSuchFieldException,
                        IllegalAccessException{


                new mySEGYJFrame("v.su");

          }

}
class mySEGYParasJFrame extends JFrame{


        mySEGYParasJFrame(){



          }




}












