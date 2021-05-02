/**
 * Classe qui represente un visiteur avec les regles de jeu HighLife
 * @param visiteur Instance representant le VisiteurSecond unique
 */
public class VisiteurSecond extends Visiteur{
    private static VisiteurSecond visiteur = null;

     /**
     * Constructeur de VisiteurSecond
     * @param jeu le jeu de la vie courant
     */
    private VisiteurSecond(JeuDeLaVie jeu){
        super(jeu);
    }

    @Override
    public void VisiteCelluleMorte(Cellule cellule){
        int nbVoisin = cellule.nombreVoisinesVivante(jeu);
        if(nbVoisin == 3 || nbVoisin == 6)
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
    public static VisiteurSecond getInstance(JeuDeLaVie jeu){
        if(visiteur == null)
            visiteur = new VisiteurSecond(jeu);
        return visiteur;
    }
} 