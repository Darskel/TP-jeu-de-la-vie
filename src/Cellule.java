/** 
 * Une classe représentant une Cellule de la grille
 * @param etat l'etat actuel de la cellule
 * @param x l'abscisse de la cellule
 * @param y l'ordonnée de la cellule
 */
public class Cellule{
    private CelluleEtat etat;
    private int x;
    private int y;

    /**
     * Constructeur de la Cellule
     * @param x l'abscisse de la cellule
     * @param y l'ordonnée de la cellule
     * @param etat l'etat de la cellule
     */
    public Cellule(int x, int y, CelluleEtat etat){
        this.x = x;
        this.y = y;
        this.etat = etat;
    }

    /**
     * Méthode qui fait passer l'etat de la cellule à "vivante"
     */
    public void vit(){
        etat = etat.vit();
    }

    /**
     * Méthode qui fait passer l'etat de la cellule à "morte"
     */
    public void meurt(){
        etat = etat.meurt();
    }

    /**
     * Méthode vérifiant l'etat de la cellule
     * @return true si elle est vivante, false si elle est morte
     */
    public boolean estVivante(){
        return etat.estVivante();
    }

    /**
     * Méthode qui calcule le nombre de cellule voisine vivante
     * @param jeu objet contenant la matrice du jeu
     * @return le nombre de cellule voisine vivante
     */
    public int nombreVoisinesVivante(JeuDeLaVie jeu){
        int nbVoisinVivant = 0;
        if(jeu.getGrilleXY(x-1, y-1) != null && jeu.getGrilleXY(x-1, y-1).estVivante())
            nbVoisinVivant++;
        if(jeu.getGrilleXY(x, y-1) != null && jeu.getGrilleXY(x, y-1).estVivante())
            nbVoisinVivant++;
        if(jeu.getGrilleXY(x+1, y-1) != null && jeu.getGrilleXY(x+1, y-1).estVivante())
            nbVoisinVivant++;
        if(jeu.getGrilleXY(x-1, y) != null && jeu.getGrilleXY(x-1, y).estVivante())
            nbVoisinVivant++;
        if(jeu.getGrilleXY(x+1, y) != null && jeu.getGrilleXY(x+1, y).estVivante())
            nbVoisinVivant++;
        if(jeu.getGrilleXY(x-1, y+1) != null && jeu.getGrilleXY(x-1, y+1).estVivante())
            nbVoisinVivant++;
        if(jeu.getGrilleXY(x, y+1) != null && jeu.getGrilleXY(x, y+1).estVivante())
            nbVoisinVivant++;
        if(jeu.getGrilleXY(x+1, y+1) != null && jeu.getGrilleXY(x+1, y+1).estVivante())
            nbVoisinVivant++;

        return nbVoisinVivant;
    }

    /**
     * Méthode qui permet de donner l'abscisse de la cellule
     * @return l'abscisse de la cellule
     */
    public int getX(){
        return x;
    }

    /**
     * Méthode qui permet de donner l'ordonnée de la cellule
     * @return l'ordonnée de la cellule
     */
    public int getY(){
        return y;
    }

    /**
     * Méthode qui permet à la cellule d'accepter un visiteur
     * @param visiteur le visiteur à faire accepter
     */
    public void accepte(Visiteur visiteur){
        etat.accepte(visiteur, this);
    }
}