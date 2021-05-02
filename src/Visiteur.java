/**
 * Classe qui represente les Visiteur du jeu
 * @param jeu le jeu de la vie courant
 */
public abstract class Visiteur {
    protected JeuDeLaVie jeu;

    /**
     * Constructeur de Visiteur
     * @param jeu le jeu de la vie courant
     */
    public Visiteur(JeuDeLaVie jeu){
        this.jeu = jeu;
    }

    /**
     * Methode qui permet d'assigner une commande à une cellule vivante
     * @param cellule la cellule concernée
     */
    public abstract void VisiteCelluleVivante(Cellule cellule);

    /**
     * Methode qui permet d'assigner une commande à une cellule morte
     * @param cellule la cellule concernée
     */
    public abstract void VisiteCelluleMorte(Cellule cellule);
}
