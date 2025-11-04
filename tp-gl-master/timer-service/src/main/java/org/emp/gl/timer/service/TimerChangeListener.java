package org.emp.gl.timer.service;

import java.beans.PropertyChangeListener;

/**
 * Hérite maintenant de PropertyChangeListener
 */
public interface TimerChangeListener extends PropertyChangeListener {

    final static String DIXEME_DE_SECONDE_PROP = "dixième";
    final static String SECONDE_PROP = "seconde";
    final static String MINUTE_PROP = "minute";
    final static String HEURE_PROP = "heure";

    // Garder notre signature personnalisée pour la compatibilité
    void propertyChange(String prop, Object oldValue, Object newValue);

    // Implémentation par défaut de la méthode de PropertyChangeListener
    @Override
    default void propertyChange(java.beans.PropertyChangeEvent evt) {
        propertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
    }
}