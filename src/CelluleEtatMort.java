/**
 * Classe representant une cellule à l'etat "morte"
 * @param uniqueInstance Instance representant le CelluleEtatMort unique
 */
public class CelluleEtatMort implements CelluleEtat{
    
    private static CelluleEtatMort uniqueInstance = null;

    /**
     * Constructeur de CelluleEtatMort
     */
    private CelluleEtatMort(){
        super();
    }

    @Override
    public CelluleEtat vit(){
        return CelluleEtatVivant.getInstance();
    }

    @Override
    public CelluleEtat meurt(){
        return this; 
    }

    @Override
    public boolean estVivante(){
        return false;
    }

    /**
     * Méthode qui permet de recuperer l'unique instance de la classe
     * @return l'unique instance de la classe
     */
    public static CelluleEtatMort getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new CelluleEtatMort();
        }
        return uniqueInstance;
    }

    @Override
    public void accepte(Visiteur visiteur, Cellule cellule){
        visiteur.VisiteCelluleMorte(cellule);
    }
}
