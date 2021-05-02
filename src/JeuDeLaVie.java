import java.util.List;
import java.util.LinkedList;

/**
 * Classe representant le jeu
 * @param grille le tableau de cellule
 * @param xMax l'abscisse maximal de la grille
 * @param yMax l'ordonnée maximal de la grille
 * @param observateurs la liste des observateur du jeu
 * @param commandes la liste des commandes à executer
 * @param visiteur le visiteur appliqué au jeu
 * @param enPause le boolean qui indique si le jeu est en pause
 * @param flag le boolean qui indique si le jeu peut continuer
 * @param vitesse le temps entre 2 generations
 */
public class JeuDeLaVie implements Observable{
    private Cellule[][] grille;
    private int xMax;
    private int yMax;
    private List<Observateur> observateurs;
    private List<Commande> commandes;
    private Visiteur visiteur;
    private boolean enPause;
    private boolean flag;
    private int vitesse = 1000;

    /**
     * Constructeur de JeuDeLaVie
     * @param x l'abscisse de la grille
     * @param y l'ordonne de la grille
     */
    public JeuDeLaVie(int x, int y){
        xMax = x;
        yMax = y;
        grille = new Cellule[xMax][yMax];
        observateurs = new LinkedList<Observateur>();
        commandes = new LinkedList<Commande>();
        enPause = false;
        flag = true;
        initialiseGrille();
    }

    /**
     * Méthode qui va permettre d'initialiser la grille de jeu
     */
    public void initialiseGrille(){
        for(int i = 0; i<xMax; i++){
            for(int j = 0; j<yMax; j++){
                if(Math.random() < 0.5)
                    grille[i][j] = new Cellule(i,j,CelluleEtatMort.getInstance());
                else
                    grille[i][j] = new Cellule(i,j,CelluleEtatVivant.getInstance());
            }
        }
    }

    /**
     * Méthode qui permet de retourner la cellule au coordonnée donnée
     * @param x l'abscisse de la cellule
     * @param y l'ordonnée de la cellule
     * @return le cellule aux coordonnée donnée, renvoie null si les coordonnées sont fausses
     */
    public Cellule getGrilleXY(int x, int y){
        if(x<0 || y<0 || x>=xMax || y>=yMax)
            return null;
        else
            return grille[x][y];
    }

    /**
     * Méthode qui retourne la dimension horizontale de la grille
     */
    public int getXMax(){
        return xMax;
    }

    /**
     * Méthode qui retourne la dimension verticale de la grille
     */
    public int getYMax(){
        return yMax;
    }

    @Override
    public void attacheObservateur(Observateur o){
        observateurs.add(o);
    }

    @Override
    public void detacheObservateur(Observateur o){
        observateurs.remove(o);
    }

    @Override
    public void notifieObservateur(){
        for(Observateur o : observateurs)
            o.actualise();
    }

    /**
     * Méthode qui ajoute une commande à la liste des commandes
     * @param c la commande à ajouter
     */
    public void ajouteCommande(Commande c){
        commandes.add(c);
    }

    /**
     * Méthode qui execute toute les commandes contenues dans la liste des commandes, puis efface la liste
     */
    public void executeCommandes(){
        for(Commande c : commandes)
            c.executer();
        commandes.clear();
    }

    /**
     * Methode qui distribue le visiteur à toutes les cellules de la grille
     */
    public void distribueVisiteur(){
        for(int i = 0; i < xMax; i++){
            for(int j = 0; j < yMax; j++){
                getGrilleXY(i, j).accepte(visiteur);
            }
        }
    }

    /**
     * Methode qui permet de calculer la prochaine génération du jeu
     */
    public void calculerGenerationSuivante(){
        distribueVisiteur();
        executeCommandes();
        notifieObservateur();
    }

    /**
     * Methode qui permet de modifier le visiteur
     * @param visiteur le nouveau visiteur
     */
    public void setVisiteur(Visiteur visiteur){
        this.visiteur = visiteur;
    }

    /**
     * Methode qui permet de connaitre le nombre total de cellule vivante dans la grille
     * @return le nombre total de cellule vivante dans la grille
     */
    public int nombreCelluleVivante(){
        int nbCelluleVivante = 0;
        for(int i = 0; i < xMax; i++){
            for(int j = 0; j < yMax; j++){
                if(grille[i][j].estVivante())
                    nbCelluleVivante++;
            }
        }
        return nbCelluleVivante;
    }

    /* ===== Controle du jeu ===== */

    /**
     * Methode qui met à true la variable qui indique si le jeu est en pause (ici le jeu est en pause)
     */
    public void pause(){
        this.enPause = true;
    }

    /**
     * Methode qui met à false la variable qui indique si le jeu est en pause (ici il ne l'est pas)
     */
    public void reprendre(){
        this.enPause = false;
    }

    /**
     * Méthode qui permet de savoir si le jeu est en pause ou pas
     * @return true si le jeu est en pause, false sinon
     */
    public boolean enPause(){
        return enPause;
    }

    /**
     * Methode qui permet de modifier la vitesse d'apparition de la prochaine génération
     * @param vitesse la nouvelle vitesse
     */
    public void setVitesse(int vitesse){
        this.vitesse = vitesse;
    }

    /**
     * Methode qui permet de recuperer la vitesse actuel
     * @return la vitesse actuel
     */
    public int getVitesse(){
        return vitesse;
    }

    /**
     * Methode qui permet de changer le flag
     * @param f le nouveau flag
     */
    public void changeFlag(boolean f){
		flag = f;
	}
	
	/**
	 * Méthode retournant le flag.
	 * @return Le flag courant.
	 */
	public boolean getFlag(){
		return flag;
	}

    /**
     * Methode du main, permet de lancer le jeu
     * @param args
     */
    public static void main(String[] args){
        JeuDeLaVie jeu = new JeuDeLaVie(400,165);
        
        Visiteur v = VisiteurClassique.getInstance(jeu);
        
        jeu.setVisiteur(v);

        JeuDeLaVieSecond jeuSec = new JeuDeLaVieSecond(jeu);
        JeuDeLaVieUI jeuUI = new JeuDeLaVieUI(jeu, jeuSec);
        jeu.attacheObservateur(jeuUI);
        jeu.attacheObservateur(jeuSec);
    
        while(true){
            try{
            Thread.sleep(jeu.getVitesse());
            }catch(InterruptedException e){
            e.printStackTrace();
            }
            if(!jeu.enPause() && jeu.getFlag())
                jeu.calculerGenerationSuivante();
        }
    }
}
