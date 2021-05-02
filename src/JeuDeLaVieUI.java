import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Classe representant l'observateur qui va representer graphique la fentre, la grille du jeu et les boutons
 * @param serialVersionUID la version actuel
 * @param jeu le jeu de la vie courant
 * @param jeuSecond le jeu de la vie Second courant
 * @param vitesse la vitesse entre l'apparition des generations actuel
 * @param regles liste des différentes regles de jeu
 * @param listeRegles la ComboBox des regles
 * @param boutonPlayPause le bouton Play/Pause
 * @param boutonNext le bouton next
 * @param slider le slider pour la vitesse
 */
public class JeuDeLaVieUI extends JFrame implements Observateur{
    private static final long serialVersionUID = 1;
    private JeuDeLaVie jeu;
    private JeuDeLaVieSecond jeuSecond;
    private int vitesse;
    private String[] regles = {"Classique", "HighLife", "Day & Night"};
    private JComboBox listeRegles = new JComboBox(regles);
    private JButton boutonPlayPause;
    private JButton boutonNext;
    private JSlider slider;

    /**
     * Constructeur de JeuDeLaVieUI
     * @param jeu le jeu de la vie courant
     * @param jeuSecond le jeu de la vie Second courant
     */
    public JeuDeLaVieUI(JeuDeLaVie jeu, JeuDeLaVieSecond jeuSecond){
        this.jeu = jeu;
        this.jeuSecond = jeuSecond;
        setTitle("Jeu de la vie - Yannis Allain");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vitesse = jeu.getVitesse();


        BorderLayout bord = new BorderLayout();
		getContentPane().setLayout(bord);
		getContentPane().add(this.pannel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Methode qui va creer un panneau et placer les boutons dessus
     * @return la panneau qui contient les boutons
     */
    private JPanel pannel(){
		GridLayout grid1 = new GridLayout(0,1);
		GridLayout grid2 = new GridLayout(1,3);
		JPanel box1 = new JPanel(grid1);
		JPanel box2 = new JPanel(grid2);
		grid1.setVgap(10);
		grid2.setHgap(10);
		
		boutonPlayPause = new JButton("Pause", new ImageIcon("image/pause.png"));
		boutonPlayPause.addActionListener(new BoutonPlayPauseListener());
		boutonNext = new JButton("Prochaine generation", new ImageIcon("image/next.png"));
		boutonNext.setEnabled(false);
		boutonNext.addActionListener(new BoutonNextListener());
		listeRegles.addActionListener(new ListeReglesListener());
        
        slider = new JSlider(JSlider.HORIZONTAL, 0, 20, 10);
		slider.addChangeListener(new SliderListener());
		
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(false);

		box2.add(boutonPlayPause);
		box2.add(boutonNext);
		box2.add(listeRegles);
		
		box1.add(box2);
        box1.add(slider);

		return box1;
	}

    @Override
    public void actualise(){
        repaint();
    }

    /**
     * Méthode qui va dessiner toutes les cellules
     */
    public void paint(Graphics g){
        super.paint(g);
        for(int x = 0; x < jeu.getXMax(); x++){
            for(int y = 0; y < jeu.getYMax(); y++){
                if(jeu.getGrilleXY(x, y).estVivante()){
                    g.fillOval(x*3, y*3, 3, 3);
                }
            }
        }
    }

    /**
     * Classe qui represente le listener de listeRegles
     * @param visiteurClassique le visiteur avec les regles de base
     * @param visiteurSecond le visiteur avec les regles du HighLife
     * @param visiteurTer le visiteur avec les regles du Day & Night
     */
    class ListeReglesListener implements ActionListener{
		private Visiteur visiteurClassique = VisiteurClassique.getInstance(jeu);
		private Visiteur visiteurSecond =  VisiteurSecond.getInstance(jeu);
        private Visiteur visiteurTer = VisiteurTer.getInstance(jeu);

        @Override
    	public void actionPerformed(ActionEvent e)
		{
			System.out.println("Changement de regles actionne");
			String strVisiteur = (String)listeRegles.getSelectedItem();
			if(strVisiteur.equals("classique")){
                jeuSecond.resetGeneration();
                jeu.initialiseGrille();
				jeu.setVisiteur(visiteurClassique);
	  		}
			if(strVisiteur.equals("HighLife")){
                jeuSecond.resetGeneration();
                jeu.initialiseGrille();
				jeu.setVisiteur(visiteurSecond);
	  		}
            else if(strVisiteur.equals("Day & Night")){
                jeuSecond.resetGeneration();
                jeu.initialiseGrille();
                jeu.setVisiteur(visiteurTer);
            }
		}
	}

    /**
     * Classe qui represente le listener du slider
     */
    class SliderListener implements ChangeListener{
        @Override
	    public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                int time = (int)source.getValue();
                if (time == 0) {
            	    jeu.changeFlag(false);
                } else {
            	    if(boutonPlayPause.getText().equals("Pause"))
            		    jeu.changeFlag(true);
            	    jeu.setVitesse(time*100);
                }
            }
        }
    }

    /**
     * Classe qui represente le listener du boutonPlayPause
     */
    class BoutonPlayPauseListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(boutonPlayPause.getText().equals("Pause")){
                jeu.pause();
                boutonNext.setEnabled(true);
                boutonPlayPause.setText("Play");
            }
            else {
                jeu.reprendre();
                boutonNext.setEnabled(false);
                boutonPlayPause.setText("Pause");
            }
        }
    }

    /**
     * Classe qui represente le listener du boutonNext
     */
    class BoutonNextListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            jeu.calculerGenerationSuivante();
        }
    }
}


