package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.Dao;
import modelo.Album;
import modelo.Cancion;
import modelo.Playlist;
import modelo.Usuario;

import java.awt.Toolkit;

public class VPrincipal extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JButton btnLogo, btnFotoAlbum, btnFotoPlay;
    private JLabel lblPlayList, lblAlbumes;
    private Dao dao;
    private final JPanel contentPanel = new JPanel();
    private int numPlay, numAlbum, num = 1, numListPlay = 0, numListAlbum, pos;
    private JTextField txtBuscar;
    private JButton btnSecreto;
    private JList<String> list;
    private ArrayList<Playlist> plays;
    private JScrollPane scrollPane;
    private ArrayList<Cancion> canciones = new ArrayList<Cancion>();
    private ArrayList<Album> albums;
    private JButton btnSiguientePlay;
    private JButton btnSiguienteAlbum;
    private JPanel panelAlbum = new JPanel();
    private Cancion cancion;
    private Clip clip;
    private JButton btnStop;
    private JButton btnPlay;
    private boolean play = false;
    private JPanel panelPlay;
    private Playlist playlist;
    private Album album;
    private int numeroA = 0;
    private int numeroP = 0;
    private JButton btnAnteriorAlbum;
    private static Usuario usu;
    private JButton btnAnteriorPlaylist;
    private JButton btnCrearPlaylist;

    public VPrincipal(Inicio_Sesion inicio_Sesion, boolean modal, Dao dao, Usuario usuario) {
        super(inicio_Sesion);
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\1dam\\Desktop\\PGR\\3ª Eva\\RetoFinal\\Img\\logoPequeña.png"));
        setModal(modal);
        pantalla(dao, usuario);
    }

    public VPrincipal(VentanaPlay ventanaPlay, boolean modal, Dao dao, boolean play, Cancion cancion, int pos2) {
        super(ventanaPlay);
        setModal(modal);
        this.play = play;
        this.pos = pos2;
        this.cancion = cancion;
        pantalla(dao, null);
    }

    public VPrincipal(VListaCanciones VlistaCanciones, boolean modal, Dao dao, Usuario usuario) {
        super(VlistaCanciones);
        setModal(modal);
        pantalla(dao, usuario);
    }

    public VPrincipal(VAñadirPlaylist playlist, boolean modal, Dao dao, Usuario usuario) {
        super(playlist);
        setModal(modal);
        pantalla(dao, usuario);
    }

    public VPrincipal(VCerrar_Sesion vCerrar_Sesion, boolean modal, Dao dao) {
        super(vCerrar_Sesion);
        setModal(modal);
        pantalla(dao, null);
    }

    public void pantalla(Dao dao, Usuario usuario) {
        this.dao = dao;
        
        if(usuario != null) {
            this.usu = usuario;
        }
        setBackground(new Color(214, 214, 214));
        setBounds(100, 100, 1259, 749);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnSecreto.requestFocus();
            }
        });
        contentPanel.setBackground(new Color(214, 214, 214));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        btnSecreto = new JButton("");
        btnSecreto.setForeground(new Color(75, 75, 75));
        btnSecreto.setBackground(new Color(75, 75, 75));
        btnSecreto.setBounds(0, 0, 1, 1);
        contentPanel.add(btnSecreto);

        panelPlay = new JPanel();
        panelPlay.setOpaque(false);
        panelPlay.setBounds(126, 181, 942, 143);
        contentPanel.add(panelPlay);
        panelPlay.setLayout(null);

        list = new JList<>();
        list.setFont(new Font("Felix Titling", Font.PLAIN, 32));
        list.setVisible(false);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                escucharCancion();
            }
        });
        contentPanel.add(list);

        btnSiguientePlay = new JButton(">");
        btnSiguientePlay.addActionListener(this);
        btnSiguientePlay.setBounds(1089, 181, 100, 143);
        contentPanel.add(btnSiguientePlay);

        btnSiguienteAlbum = new JButton(">");
        btnSiguienteAlbum.setBounds(1089, 430, 100, 143);
        btnSiguienteAlbum.addActionListener(this);
        contentPanel.add(btnSiguienteAlbum);

        scrollPane = new JScrollPane(list);
        scrollPane.setBounds(208, 99, 846, 368);
        scrollPane.setVisible(false);
        contentPanel.add(scrollPane);

        txtBuscar = new JTextField();
        txtBuscar.setText("Buscar");
        txtBuscar.setForeground(new Color(192, 192, 192));
        txtBuscar.setFont(new Font("Tahoma", Font.PLAIN, 38));
        txtBuscar.setBounds(208, 29, 846, 69);
        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                cargarLista();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cargarLista();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        contentPanel.add(txtBuscar);
        txtBuscar.setColumns(10);
        txtBuscar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtBuscar.getText().equals("Buscar")) {
                    txtBuscar.setText("");
                    scrollPane.setVisible(true);
                    list.setVisible(true);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {

                txtBuscar.setText("Buscar");
                scrollPane.setVisible(false);
                list.setVisible(false);
            }
        });

        numPlay = dao.sacarNumeroDePlayList();
        numAlbum = dao.sacarNumeroDeAlbum();
        
        
        btnCrearPlaylist = new JButton("+");
        btnCrearPlaylist.setBounds(320, 127, 85, 44);
        btnCrearPlaylist.addActionListener(this);
        contentPanel.add(btnCrearPlaylist);
        
        lblPlayList = new JLabel("PlayLists");
        lblPlayList.setForeground(new Color(75, 75, 75));
        lblPlayList.setFont(new Font("Stencil", Font.PLAIN, 44));
        lblPlayList.setBounds(70, 127, 242, 50);
        contentPanel.add(lblPlayList);

        lblAlbumes = new JLabel("Albumes");
        lblAlbumes.setForeground(new Color(75, 75, 75));
        lblAlbumes.setFont(new Font("Stencil", Font.PLAIN, 44));
        lblAlbumes.setBounds(70, 376, 242, 50);
        contentPanel.add(lblAlbumes);
        
        
        btnLogo = new JButton("");
        btnLogo.setBackground(new Color(64, 128, 128));
        btnLogo.setBounds(1181, 10, 56, 75);
        btnLogo.setOpaque(false);
        btnLogo.setBorderPainted(false);
        btnLogo.setFocusable(false);
        ImageIcon icono = new ImageIcon("..\\RetoFinal\\Img\\logoPequeña.png");
        Image imagen = icono.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(btnLogo.getWidth(),btnLogo.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
        btnLogo.setIcon(iconoRedimensionado);
        contentPanel.add(btnLogo);
        btnLogo.addActionListener(this);

        btnStop = new JButton("");
        btnStop.setBounds(969, 613, 80, 80);
        contentPanel.add(btnStop);
        
        btnPlay = new JButton("");
        btnPlay.setBounds(969, 613, 80, 80);
        contentPanel.add(btnPlay);

        panelAlbum = new JPanel();
        panelAlbum.setBounds(126, 430, 942, 143);
        panelAlbum.setOpaque(false);
        contentPanel.add(panelAlbum);
        panelAlbum.setLayout(null);
        
        
        btnAnteriorAlbum = new JButton("<");
        btnAnteriorAlbum.setBounds(10, 430, 100, 143);
        btnAnteriorAlbum.addActionListener(this);
        contentPanel.add(btnAnteriorAlbum);
        
        btnAnteriorPlaylist = new JButton("<");
        btnAnteriorPlaylist.setBounds(16, 181, 100, 143);
        btnAnteriorPlaylist.addActionListener(this);
        contentPanel.add(btnAnteriorPlaylist);
        
        
        BotonesAlbum();
        BotonesPlaylist();
        
        JLabel fondo = new JLabel("") {
            /*
             * 
             */
            private static final long serialVersionUID = 1L;

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setPaint(new GradientPaint(0, 0, new Color(73, 197, 250), 0, getHeight(), new Color(78, 78, 78)));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };
        
        fondo.setBounds(0, 0, 1275, 724);
        contentPanel.add(fondo);
        

        
        if(play) {
             File archivoSonido = new File(cancion.getAudio());
               AudioInputStream audio;
                try {
                    audio = AudioSystem.getAudioInputStream(archivoSonido);
                    clip  = AudioSystem.getClip();
                    clip.open(audio);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            btnPlay.setVisible(false);
            btnStop.setVisible(true);
            play();
        } else { 
            btnPlay.setVisible(true);
            btnStop.setVisible(false);
            stop();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnLogo)) {
            irCerrar_Sesion();
        }
        if(e.getSource().equals(btnPlay)) {
            play(); 
        }
        if(e.getSource().equals(btnStop)) {
            stop(); 
        }
        if(e.getSource().equals(btnSiguientePlay)) {
            numListAlbum++;
            LimpiarPlaylist();
            BotonesPlaylist();
        }
        if(e.getSource().equals(btnSiguienteAlbum)) {
            numeroA++;
            LimpiarAlbumes();
            BotonesAlbum();    
        }
        if(e.getSource().equals(btnAnteriorAlbum)) {
            numeroA--;
            LimpiarAlbumes();
            BotonesAlbum();
        }
    }

    private void irAñadirPlaylist() {
        this.dispose();
        VAñadirPlaylist ven = new VAñadirPlaylist(this, play, dao, usu);
        ven.setVisible(true);
    }

    private void BotonesPlaylist() {
        int[] playListButtonX = { 20, 160, 300, 440, 580, 720, 860, 1000 };
        
        if(this.plays == null) {
            num = dao.sacarNumeroDePlayList();
            plays = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                Playlist play = dao.sacarPlaylist(i+numeroP,usu.getDni());
                if(play!=null) {
                    plays.add(play);
                    txtBuscar.setText(play.getDni());
                }
                
            }
        }
        
        if(!plays.isEmpty()) {
             for (int i = 0; i < Math.min(numPlay, 8); i++) {
                 JLabel lblLista;
                 if (i < plays.size()) {
                     btnFotoPlay = CrearBoton("", plays.get(i).getFotoPlaylist(), playListButtonX[i], 0);
                     panelPlay.add(btnFotoPlay);
                     Playlist play = plays.get(i);
                     btnFotoPlay.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             menuPlay(play); // Acción específica para este botón
                         }
                     });
                     lblLista = new JLabel(plays.get(i).getNombrePlaylist());
                 } else {
                     btnFotoPlay = CrearBoton("", "", playListButtonX[i], 200);
                     lblLista = new JLabel("Nombre Playlist");
                 }
                 lblLista.setFont(new Font("Calibri", Font.PLAIN, 17));
                 lblLista.setHorizontalAlignment(SwingConstants.CENTER);
                 lblLista.setBounds(playListButtonX[i]-25, 103, 150, 32);
                 lblLista.setForeground(Color.WHITE);
                 panelPlay.add(lblLista);
             }
        }
       
    }

    private void BotonesAlbum() {
        //crearPanelAlbum();
        if(numeroA==1) {
            btnAnteriorAlbum.setEnabled(false);
        }else {
            btnAnteriorAlbum.setEnabled(true);
        }
        int[] albumButtonX = { 20, 180, 340, 500, 660, 820 };        
        if(this.albums == null) {
            num = dao.sacarNumeroDeAlbum();
            albums = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                Album album = dao.sacarAlbum(i+numeroA);
                albums.add(album);
            }    
        }
        
        for (int i = 0; i <  6; i++) {
            JLabel lblAlbum;
            if (i < albums.size()) {
                btnFotoAlbum = CrearBoton("", albums.get(i).getFotoAlbum(), albumButtonX[i], 0);
                panelAlbum.add(btnFotoAlbum);
                Album album = albums.get(i);
                btnFotoAlbum.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        menuAlbum(album); // Acción específica para este botón
                    }
                });
                lblAlbum = new JLabel(albums.get(i).getNombreAlbum());
            } else {
                btnFotoAlbum = CrearBoton("", "", albumButtonX[i], 430);
                lblAlbum = new JLabel("Nombre Album");
            }
            lblAlbum.setFont(new Font("Calibri", Font.PLAIN, 17));
            lblAlbum.setHorizontalAlignment(SwingConstants.CENTER);
            lblAlbum.setBounds(albumButtonX[i]-25, 103, 150, 32);
            lblAlbum.setForeground(Color.WHITE);
            panelAlbum.add(lblAlbum);
        }
    }
       
      
    private void LimpiarAlbumes () {
        panelAlbum.removeAll();
        this.albums=null;
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void LimpiarPlaylist() {
    	panelPlay.removeAll();
    	this.plays=null;
    	contentPanel.revalidate();
    	contentPanel.repaint();
    }

    protected void escucharCancion() {
        Cancion can = new Cancion();
        String nombre = list.getSelectedValue().toString();
        int pos = nombre.indexOf("  ");
        int posicion = nombre.indexOf("   |");
        nombre = nombre.substring(pos + 2, posicion);
        for (Cancion c : canciones) {
            if (c.getNombreCancion().equalsIgnoreCase(nombre)) {
                can = c;
            }
        }
        this.dispose();
        VentanaPlay play = new VentanaPlay(this, can,true, dao);
        play.setVisible(true);
    }

    protected void stop() {
        // TODO Auto-generated method stub
        if (clip != null && clip.isRunning()) {
            clip.stop();
            num = 0;
            btnPlay.setVisible(true);
            btnStop.setVisible(false);
            pos = clip.getFramePosition();
            play = false;
        }
    }
    public void play() {
        // TODO Auto-generated method stub
        if (num == 0) {
            clip.setFramePosition(pos);
            clip.start();
            num++;
            btnPlay.setVisible(false);
            btnStop.setVisible(true);    
            play = true;
        }
    }

    private JButton CrearBoton(String text, String imagePath, int x, int y) {
        JButton button = new JButton("");
        button.setBackground(new Color(64, 128, 128));
        button.setBounds(x, y, 100, 100);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusable(false);
        ImageIcon icono = new ImageIcon(imagePath);
        Image imagen = icono.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(button.getWidth(),button.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
        button.setIcon(iconoRedimensionado);
        contentPanel.revalidate();
        return button;
        
    }

    private void cargarLista() {
        // TODO Auto-generated method stub
        canciones = dao.sacarCanciones();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        
        //introducir primero los que empiezen igual
        for (Cancion can : canciones) {
            if (can.getNombreCancion().toLowerCase().startsWith(txtBuscar.getText().toLowerCase()) ) {
                listModel.addElement("<html><font size=7>"+"  "+can.getNombreCancion() + "   |     " + "<html><font size=5>"+dao.funcionArtistas(can.getCodCancion()));
                list.setModel(listModel);
            }
        }
        //introducir las que tengan letras en comun
        for (Cancion can : canciones) {
            if(can.getNombreCancion().toLowerCase().contains(txtBuscar.getText().toLowerCase()) && !can.getNombreCancion().toLowerCase().startsWith(txtBuscar.getText().toLowerCase()) ) {
                listModel.addElement("<html><font size=7>"+"  "+can.getNombreCancion() + "   |     " + "<html><font size=5>"+dao.funcionArtistas(can.getCodCancion()));
                list.setModel(listModel);
            }
        }
    }



    private void menuPlay(Playlist play) {
        this.dispose();
        VListaCanciones ven = new VListaCanciones(play, this, true, dao, usu);
        ven.setVisible(true);        
    }

    private void menuAlbum(Album album) {
        this.dispose();
        VListaCanciones ven = new VListaCanciones(album, this,  true, dao, usu);
        ven.setVisible(true);        
    }

    private void irCerrar_Sesion() {
        this.setVisible(false);
        VCerrar_Sesion ven = new VCerrar_Sesion(this, true, dao);
        ven.setVisible(true);
    }
}