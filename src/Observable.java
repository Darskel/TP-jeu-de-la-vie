/**
 * Interface representant les Observables
 */
public interface Observable {
    /**
     * Methode qui permet d'attacher un observateur
     * @param o l'observateur à attacher
     */
    void attacheObservateur(Observateur o);

    /**
     * Methode qui permet de detacher un observateur
     * @param o l'observateur à detacher
     */
    void detacheObservateur(Observateur o);

    /**
     * Methode qui permet d'actualiser l'observateur
     */
    void notifieObservateur();
}
