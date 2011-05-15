package logic;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.BasicConfigurator;

import gui.GridWindow;

/**
 * Questions (see exercise sheet for English version)
 *
 * Frage 1: Warum ist es mögich das Wegproblem mit Moore zu lösen, obwohl das Kantengewicht 4 beträgt?
 * 
 * Der Moore-Algorithmus kommt nicht mit unterschiedlichen Kantengewichten zurecht, da er eine einfach Breitensuche darstellt.
 * Daraus folgt, dass er bei unterschiedlichen Kantengewichten Wege auswählt, die die kleinste Anzahl an "Tiefen" haben, 
 * auch wenn es potenziell Wege gibt deren kumuliertes Kantengewicht niedriger ist. 
 * Besitzt also jede Kante in dem Grid das gleiche Gewicht, ist es nicht relevant ob diese 1, 4 oder zB. 250 ist.
 *
 *
 * Frage 2: Welchen Fehler hat ihr Kommilitone gemacht? Warum findet sein A*- Algorithmus keinen optimalen Weg?
 *
 * Der Kommiltone hat die Gewichtung der heuristischen Komponente bei der Sortierung seiner Open-List zu hoch gewählt.
 *
 * Frage 3: Wie hat ihr Kommilitone versucht das Problem zu lösen? Was ist dabei mit seinem
 * A*- Algorithmus passiert? Was hätte er besser machen können? Diskutieren Sie mögliche Lösungsansätze!
 *
 * Er muss die Breitensuche des A*-Algorithmus verbessern indem er die Gewichtung der heuristische Komponente bei der
 * Sortierung seiner Open-List reduziert. Dadurch werden mehr Felder in Betracht gezogen, da sie in der Open-List aufgrund
 * ihrer angepassten Priorität näher an andere Elemte ranrücken.
 * Eine andere Möglichkeit wäre die Heuristik dynamisch an die Beschaffenheit des Feldes (durchschittliche Kantenlänge im Feld, Luftlinien-
 * abstandsverringerung etc.) anzupassen.
 */

/**
 * provides functionality to interact with the application
 *
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 *
 */
public class Main {
	private static Logger logger = Logger.getRootLogger();

	public static void main(String[] args) {
		BasicConfigurator.configure();
		logger.setLevel(Level.DEBUG);
		@SuppressWarnings("unused")
		GridWindow frame = new GridWindow();
	}

}
