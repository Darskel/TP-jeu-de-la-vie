/**
 * Classe representant une cellule à l'etat "vivante"
 * @param uniqueInstance Instance representant le CelluleEtatVivante unique
 */
public class CelluleEtatVivant implements CelluleEtat{

    private static CelluleEtatVivant uniqueInstance = null;

    /**
     * Constructeur de CelluleEtatMort
     */
    private CelluleEtatVivant(){
        super();
    }

    @Override
    public CelluleEtat vit(){
        return this;
    }

    @Override
    public CelluleEtat meurt(){
        return CelluleEtatMort.getInstance(); 
    }

    @Override
    public boolean estVivante(){
        return true;
    }

    /**
     * Méthode qui permet de recuperer l'unique instance de la classe
     * @return l'unique instance de la classe
     */
    public static CelluleEtatVivant getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new CelluleEtatVivant();
        }
        return uniqueInstance;
    }

    @Override
    public void accepte(Visiteur visiteur, Cellule cellule){
        visiteur.VisiteCelluleVivante(cellule);
    }
    
}
