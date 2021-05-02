/**
 * Interface representant les commandes du jeu
 * @param cellule la cellule concernée par la commande
 */
public abstract class Commande {
    protected Cellule cellule;

    /**
     * Méthode qui va executer la commande
     */
    public abstract void executer();
}
