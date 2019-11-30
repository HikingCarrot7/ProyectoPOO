package com.sw.utilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.text.StyledEditorKit;

public final class Notepad extends JFrame
{

    private Lamina lamina;

    public Notepad()
    {

        lamina = new Lamina();

        setBounds(0, 0, 500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Notepad básico");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(lamina);
        setVisible(true);

        addWindowListener(new WindowAdapter()
        {

            @Override
            public void windowClosing(WindowEvent e)
            {
                new DAO(DAO.RUTA).saveText(lamina.getTexto());
            }

        });

    }

}

final class Lamina extends JPanel
{

    private static final long serialVersionUID = 1L;

    private String[] misFuentes = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    private JMenuBar mibarra;
    private JToolBar herramientas;
    private ButtonGroup tamano, fuentes, herramientascolores, alineacionitems;
    private JTextPane texto;
    private JScrollPane soporteTexto;
    private JPanel soporteHerramientas;
    private JPopupMenu emergente;

    private JMenu[] opciones =
    {

        new JMenu("Font"),
        new JMenu("Style"),
        new JMenu("Size"),
        new JMenu("Text color")

    };

    private JMenuItem[] fuente;

    private JMenuItem[] estilo =
    {

        new JCheckBoxMenuItem("Bold"),
        new JCheckBoxMenuItem("Italic"),
        new JCheckBoxMenuItem("Underline")

    };

    private JMenuItem[] size =
    {

        new JRadioButtonMenuItem("12"),
        new JRadioButtonMenuItem("14"),
        new JRadioButtonMenuItem("16"),
        new JRadioButtonMenuItem("18"),
        new JRadioButtonMenuItem("24"),
        new JRadioButtonMenuItem("36")

    };

    private JMenuItem[] herramientasColor =
    {

        anadirColores(Color.yellow, "/com/src/images/yellow.png"),
        anadirColores(Color.green, "/com/src/images/green.png"),
        anadirColores(Color.pink, "/com/src/images/pink.png"),
        anadirColores(Color.black, "/com/src/images/black.png")

    };

    private JMenuItem[] alineacion =
    {

        anadirAlineacion(0, "/com/src/images/left.png"),
        anadirAlineacion(2, "/com/src/images/right.png"),
        anadirAlineacion(1, "/com/src/images/center.png")

    };

    public Lamina()
    {

        setLayout(new BorderLayout());

        anadirFuentes();

        JMenuItem[][] items =
        {
            fuente, estilo, size, herramientasColor, alineacion
        };

        anadirElementos(items);

        anadirAcciones(items);

        anadirAlGrupo(items, 0, fuentes);
        anadirAlGrupo(items, 2, tamano);
        anadirAlGrupo(items, 3, herramientascolores);
        anadirAlGrupo(items, 4, alineacionitems);

        add(soporteTexto, BorderLayout.CENTER);

        add(soporteHerramientas, BorderLayout.WEST);

        add(mibarra, BorderLayout.NORTH);

        texto.setText(new DAO("data/Texto.txt").getSavedText());

    }

    public void anadirFuentes()
    {

        fuente = new JMenuItem[misFuentes.length - 235];

        for (int i = 0; i < misFuentes.length - 235; i++)
            fuente[i] = new JRadioButtonMenuItem(misFuentes[i]);

    }

    public void anadirElementos(JMenuItem[][] items)
    {

        soporteHerramientas = new JPanel();

        herramientas = new JToolBar(JToolBar.VERTICAL);
        soporteHerramientas.add(herramientas);

        texto = new JTextPane();

        soporteTexto = new JScrollPane(texto);
        soporteTexto.setWheelScrollingEnabled(true);

        mibarra = new JMenuBar();
        emergente = new JPopupMenu();

        anadirMenuEmergente();

        anadirHerramientas(herramientas, herramientasColor);

        herramientas.addSeparator();

        anadirHerramientas(herramientas, alineacion);

        texto.setComponentPopupMenu(emergente);

        for (int i = 0; i < items.length - 2; i++)
        {
            mibarra.add(opciones[i]);
            anadirItems(opciones[i], items[i]);

        }

    }

    public void anadirMenuEmergente()
    {

        JMenuItem cabecera = new JMenuItem("Set a background color...");
        cabecera.setEnabled(false);

        emergente.add(cabecera);
        emergente.add(new JMenuItem(new AccionEmergente("Yellow", Color.yellow)));
        emergente.add(new JMenuItem(new AccionEmergente("Green", Color.green)));
        emergente.add(new JMenuItem(new AccionEmergente("Blue", Color.blue)));
        emergente.add(new JMenuItem(new AccionEmergente("Pink", Color.pink)));
        emergente.add(new JMenuItem(new AccionEmergente("White", Color.white)));

    }

    public void anadirHerramientas(JToolBar herramientas, JMenuItem items[])
    {

        for (JMenuItem H : items)
            herramientas.add(H);

    }

    private final class AccionEmergente extends AbstractAction
    {

        private Color color;

        public AccionEmergente(String titulo, Color color)
        {
            putValue(Action.NAME, titulo);
            this.color = color;

        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            texto.setBackground(color);
        }

    }

    public void anadirItems(JMenu opcion, JMenuItem[] items)
    {

        for (JMenuItem I : items)
            opcion.add(I);

    }

    public void anadirAcciones(JMenuItem[][] items)
    {

        for (JMenuItem F : fuente)
            F.addActionListener(new StyledEditorKit.FontFamilyAction("", F.getText()));

        for (JMenuItem S : size)
            S.addActionListener(new StyledEditorKit.FontSizeAction("", Integer.parseInt(S.getText())));

        anadirAtajos(items);

    }

    public void anadirAtajos(JMenuItem[][] items)
    {

        items[1][0].addActionListener(new StyledEditorKit.BoldAction());
        items[1][0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        items[1][1].addActionListener(new StyledEditorKit.ItalicAction());
        items[1][1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
        items[1][2].addActionListener(new StyledEditorKit.UnderlineAction());
        items[1][2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));

    }

    public void anadirAlGrupo(JMenuItem[][] items, int indice, ButtonGroup grupo)
    {

        grupo = new ButtonGroup();

        for (JMenuItem M : items[indice])
            grupo.add(M);

    }

    public JRadioButtonMenuItem anadirColores(Color color, String ruta)
    {

        JRadioButtonMenuItem etiqueta = new JRadioButtonMenuItem(getImageIcon(ruta));

        etiqueta.addActionListener(new StyledEditorKit.ForegroundAction("", color));

        return etiqueta;

    }

    public JRadioButtonMenuItem anadirAlineacion(int i, String ruta)
    {

        JRadioButtonMenuItem etiqueta = new JRadioButtonMenuItem(getImageIcon(ruta));

        etiqueta.addActionListener(new StyledEditorKit.AlignmentAction("", i));

        return etiqueta;

    }

    private ImageIcon getImageIcon(String path)
    {
        return new ImageIcon(Lamina.class.getClass().getResource(path));
    }

    public JTextPane getTexto()
    {
        return texto;
    }

}

class DAO
{

    public static final String RUTA = "res/com/sw/data/NotepadData.txt";
    private File file;

    public DAO(String path)
    {

        file = new File(RUTA);

        if (!file.exists())
            try
            {
                file.createNewFile();

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

    }

    public String getSavedText()
    {

        String text = "";
        int c = 0;

        try
        {

            try (BufferedReader mibuffer = new BufferedReader(new FileReader(RUTA)))
            {

                while (c != -1)
                {

                    c = mibuffer.read();

                    if (c != -1)
                        if ((char) c == '\n')
                            text += String.valueOf('\n');
                        else
                            text += String.valueOf((char) c);

                }

            }

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        return text;

    }

    public void saveText(JTextPane panel)
    {

        try (Formatter out = new Formatter(new FileWriter(file, false)))
        {

            out.format("%s", panel.getText());

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

}
