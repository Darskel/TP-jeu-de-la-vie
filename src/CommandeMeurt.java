/**
 * Classe representant la commande qui fait "mourir" les cellules
 */
public class CommandeMeurt extends Commande{
    /**
     * constructeur de CommandeMeurt
     * @param c la cellule qui va recevoir la commande
    */
    public CommandeMeurt(Cellule c){
        cellule = c;
    }

    @Override
    public void executer(){
        cellule.meurt();
    }
}
