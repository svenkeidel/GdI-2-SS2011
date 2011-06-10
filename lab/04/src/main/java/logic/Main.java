/**
 * 
 */
package logic;


/**
 * Questions (see exercise sheet for English version)
 * 
 * Frage 1: Vergleichen Sie die Dateigröße ihres mit Huffman codierten Bildes und des Orginalbildes.
 * Codieren Sie dazu jeweils die Bilder in einen BinärString (einmal normal RGB, einmal Huffman) und geben Sie diesen mit BinaryIO aus.
 * Die Methoden dazu sind: writeBinaryString, welche ihren HuffmanString bekommt; writeImageBinaryString bekommt das OrginalBild
 * Erklären Sie den Unterschied. Berechnen Sie aus der Dateigröße des Huffman- Bildes die mittlere
 * Anzahl Bits pro Pixel.
 * 
 * TODO: Answer
 * 
 * Frage 2: 
 * Was fällt Ihnen an den beiden decodierten Bildern auf, wie unterscheiden sich diese?
 * Erklären Sie die auftretenden Unterschiede in den beiden Bildern.
 * Die Method zum Speichern eines BufferedImage findet sich in der IO Klasse: saveImage erstellt aus ihrem decodierten BufferedImage eine png Datei.
 * 
 * TODO: Answer
 *
 * Frage 3:
 * Vergleichen Sie die Dateigröße des Originalbildes mit dem TrieCode- Bild (verlustfrei).
 * Warum sind beide bei unterschiedlicher Codierung identisch? Geben Sie eine Formel zur Berechnung
 * des Speicherbedarfs eines verlustfrei trie- codierten Bildes an.
 * Ziehen Sie nun auch das zweite Bild in ihren Vergleich mit ein. Aktualisieren Sie ihre Formel, sodass
 * diese auch die Stufen des Trees berücksichtigt.
 * Verwenden Sie wieder die Methode writeBinaryString aus IO um ihre codierten Bilder zu speichern.
 * 
 * Betrachten Sie nun die beiden decodierten Bilder. Erklären Sie Ihre Beobachtungen.
 * Worauf sind diese zurückzuführen?
 * 
 * Answer 3:
 * Die drei unterschiedlich kompriemiert codierten Dateien sind gleich groß, da sie lediglich als Bitstring und dadurch ohne jeglich 
 * native Dateikomprimierung (wie zB jpg oder png) abgespeichert werden. Somit ist die Größe der Datei linear zu der Auflösung des ursprünglichen
 * Bildes: 
 * 1 Pixel = 4 Bytes; 800*600 = 480 000; 4Bytes * 480 000 = 1 920 000 Bytes = 1,92 MB;
 * 
 * Der Speicherbedarf der codierten Dateien ändert sich bei unterschiedlich komprimierten Bildern nicht, da fehlende Bits im Code-Trie in dem encrypt-String
 * ersetzt werden. Da die Methode writeBinaryString, die man verwenden soll um die codierten Bilder zu speichern, einfach nur einen Bit-Array als Stream
 * abspeichert, besitzt sie keinerlei Komprimierung und erstellt unabhängig vom Komprimierungsgrad die gleiche Dateigröße.
 * 
 * 
 * Frage 4:
 * Wie erklärt sich der Unterschied im Orginalbild und dem wieder decodierten Bild?
 * 
 * Answer 4:
 * TODO: Check correct exercise understanding
 * Der Code-Trie des 8x8-Pixelbildes funktioniert hier als Filter. Alle Farben im Bild test.png die auch in test2.png vorhanden sind,
 * sind sichtbar, der Rest ist schwarz.
 *
 * Frage 5:
 * Vergleichen Sie die Geschwindigkeit beider Suchmethoden. Geben Sie eine theoretische Erklärung für ihre Beobachtungen an.
 * 
 * TODO: Answer
 */

/**
 * Interaction with the application
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 * 
 */
public class Main {

	public static void main(String[] args) {
		//TODO: You may build your images, etc. using this class
	}

}
