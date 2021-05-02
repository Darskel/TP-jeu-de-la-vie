/**
 * Classe representant l'observateur qui va indiquer le numéro de la génération et le nombre de cellules encore vivantes
 * @param generation le numero de la génération actuel
 * @param jeu le jeu de la vie courant
 */
public class JeuDeLaVieSecond implements Observateur{
    private int generation;
    private JeuDeLaVie jeu;

    /**
     * Constructeur de JeuDeLaVieSecond
     * @param jeu le jeu de la vie courant
     */
    public JeuDeLaVieSecond(JeuDeLaVie jeu){
        this.jeu = jeu;
        generation = 0;
    }

    @Override
    public void actualise(){
        generation++;
        System.out.println("Generation numero " + generation + "\nIl reste : " + jeu.nombreCelluleVivante() + " cellules vivantes !\n");
    }

    /**
     * Methode qui permet de remettre la génération à 0
     */
    public void resetGeneration(){
        generation = 0;
    }
}
