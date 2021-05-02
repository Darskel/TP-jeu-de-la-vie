/**
 * Classe representant la commande qui fait "vivre" les cellules
 */
public class CommandeVit extends Commande{
    /**
     * constructeur de CommandeVit
     * @param c la cellule qui va recevoir la commande
    */
    public CommandeVit(Cellule c){
        cellule = c;
    }

    @Override
    public void executer(){
        cellule.vit();
    }
}
