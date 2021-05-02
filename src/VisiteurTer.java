/**
 * Classe qui represente un visiteur avec les regles de jeu Day & Night
 * @param visiteur Instance representant le VisiteurTer unique
 */
public class VisiteurTer extends Visiteur{
    private static VisiteurTer visiteur = null;

     /**
     * Constructeur de VisiteurTer
     * @param jeu le jeu de la vie courant
     */
    private VisiteurTer(JeuDeLaVie jeu){
        super(jeu);
    }

    @Override
    public void VisiteCelluleMorte(Cellule cellule){
        int nbVoisin = cellule.nombreVoisinesVivante(jeu);
        if(nbVoisin == 3 || nbVoisin >= 6)
            jeu.ajouteCommande(new CommandeVit(cellule));
    }

    @Override
    public void VisiteCelluleVivante(Cellule cellule){
        int nbVoisin = cellule.nombreVoisinesVivante(jeu);
        if(nbVoisin < 3 || nbVoisin == 5)
            jeu.ajouteCommande(new CommandeMeurt(cellule));
    }

    /**
     * Méthode qui permet de recuperer l'unique instance de la classe
     * @param jeu le jeu de la vie courant
     * @return l'unique instance de la classe
     */
    public static VisiteurTer getInstance(JeuDeLaVie jeu){
        if(visiteur == null)
            visiteur = new VisiteurTer(jeu);
        return visiteur;
    }
}
