/**
 * Interface representant l'etat d'une cellule
 */
public interface CelluleEtat {
    /**
     * Méthode qui retourne l'etat vivant
     * @return un CelluleEtatVivant
     */
    CelluleEtat vit();
    
    /**
     * Méthode qui retourne l'etat mort
     * @return un CelluleEtatMort
     */
    CelluleEtat meurt();
   
    /**
     * Méthode qui vérifie l'etat vivant
     * @return true si la cellule est vivante, false sinon
     */
    boolean estVivante();
    
    /**
     * Méthode qui permet à l'etat d'accepter un visiteur
     * @param visiteur le visiteur à accepter
     * @param cellule la cellule concernée
     */
    void accepte(Visiteur visiteur, Cellule cellule);
}
