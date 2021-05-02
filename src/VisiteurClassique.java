/**
 * Classe qui represente un visiteur avec les regles de jeu classique
 * @param visiteur Instance representant le VisiteruClassique unique
 */
public class VisiteurClassique extends Visiteur{
    private static VisiteurClassique visiteur = null;

    /**
     * Constructeur de VisiteurClassique
     * @param jeu le jeu de la vie courant
     */
    private VisiteurClassique(JeuDeLaVie jeu){
        super(jeu);
    }

    @Override
    public void VisiteCelluleMorte(Cellule cellule){
        int nbVoisin = cellule.nombreVoisinesVivante(jeu);
        if(nbVoisin == 3)
            jeu.ajouteCommande(new CommandeVit(cellule));
    }

    @Override
    public void VisiteCelluleVivante(Cellule cellule){
        int nbVoisin = cellule.nombreVoisinesVivante(jeu);
        if(nbVoisin < 2 || nbVoisin > 3)
            jeu.ajouteCommande(new CommandeMeurt(cellule));
    }

    /**
     * Méthode qui permet de recuperer l'unique instance de la classe
     * @param jeu le jeu de la vie courant
     * @return l'unique instance de la classe
     */
    public static VisiteurClassique getInstance(JeuDeLaVie jeu){
        if(visiteur == null)
            visiteur = new VisiteurClassique(jeu);
        return visiteur;
    }
}
