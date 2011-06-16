/**
 * 
 */
package logic;

/**
 * Questions (see exercise sheet for English version)
 * 
 * Frage 1: Vergleichen Sie die Dateigröße ihres mit Huffman codierten
 * Bildes und des Orginalbildes.  Codieren Sie dazu jeweils die Bilder
 * in einen BinärString (einmal normal RGB, einmal Huffman) und geben
 * Sie diesen mit BinaryIO aus.  Die Methoden dazu sind:
 * writeBinaryString, welche ihren HuffmanString bekommt;
 * writeImageBinaryString bekommt das OrginalBild Erklären Sie den
 * Unterschied. Berechnen Sie aus der Dateigröße des Huffman- Bildes die
 * mittlere Anzahl Bits pro Pixel.
 * 
 * Answer1: Das Bild test2.png hat nach dem codieren mit Huffman eine
 * Größe von 19 Bytes.  Der BinärString vom Bild mit RGB hat eine Größe
 * von 256 Bytes.  256 Byte * 8 / 64 (Anzahl der Pixel) = 32 bit/Pixel
 * Beim RGB-Bild wird also jedes Pixel mit 32 bit abgespeichert, jedoch
 * wird beim Huffman-Bild danach abgespeichert welche Farbe am
 * häufigsten vorkommt. So wird für die Farbe Grün der kleinste
 * Huffman-Code verwendet was eine effiziente Speicherung ermöglicht.
 * Außerdem hat dieses spezielle Bild nur 6 verschiedene Farben, so das
 * der Huffman-Code für die am wenigsten vorkommende Farbe legendlich 5
 * bit lang ist.  Was immernoch deutlich weniger ist als die 32 bit beim
 * RGB-Bild und den immensen Speichervorteil begründet.  So kann man aus
 * der Größe von 19 Byte für das Huffman-Bild folgendes errrechen: 19
 * Byte * 8 = 152 bit / 64 (Anzahl der Pixel) = 2,375 bit/Pixel Somit
 * sieht man das beim Huffman-Bild pro Pixel im Durchschnitt nur 2,375
 * bit gespeichert werden, anstatt von 32 bit/Pixel wie beim RGB-Bild.
 *  
 * 
 * Frage 2: Was fällt Ihnen an den beiden decodierten Bildern auf, wie
 * unterscheiden sich diese?  Erklären Sie die auftretenden Unterschiede
 * in den beiden Bildern.  Die Method zum Speichern eines BufferedImage
 * findet sich in der IO Klasse: saveImage erstellt aus ihrem
 * decodierten BufferedImage eine png Datei.
 * 
 * Das erste komprimierte Bild enthält wie erwartet statt den Pixeln mit
 * niedrigster Wahrscheinlichkeit (blau, gelb) Pixel der Farbe weiß. Das
 * zweite Bild, das zusätzlich die weißen Pixel codieren muss, enthält
 * diese nicht. Da die Farbe Weiß im huffman Baum an mehreren Stellen
 * vorkommen kann, kann diese Farbe selber nicht mehr eindeutig kodiert
 * werden.
 *
 * Frage 3: Vergleichen Sie die Dateigröße des Originalbildes mit dem
 * TrieCode- Bild (verlustfrei).  Warum sind beide bei unterschiedlicher
 * Codierung identisch? Geben Sie eine Formel zur Berechnung des
 * Speicherbedarfs eines verlustfrei trie- codierten Bildes an.  Ziehen
 * Sie nun auch das zweite Bild in ihren Vergleich mit ein.
 * Aktualisieren Sie ihre Formel, sodass diese auch die Stufen des Trees
 * berücksichtigt.  Verwenden Sie wieder die Methode writeBinaryString
 * aus IO um ihre codierten Bilder zu speichern.
 * 
 * Betrachten Sie nun die beiden decodierten Bilder. Erklären Sie Ihre
 * Beobachtungen.  Worauf sind diese zurückzuführen?
 * 
 * 
 * Answer 3: Die  unterschiedlich codierten Dateien sind gleich groß, da
 * die Methode in IO.class, welche wir benutzen sollen um aus dem png
 * einen Binärstring zu erstellen, über das Bild iteriert und Pixel für
 * Pixel in voller Größe (32-Bit) nacheinander abspeichert.  Der
 * Ergebnisstring der Verschlüsselung des Bildes mit einem Baum, der
 * vollständig alle Farben des Bildes enthält, ist mit dem obigen
 * identisch.  Jede Farbe wird vollständig (32-Bit) gefunden.
 * 
 * Größe für 8-Stufen Trie:
 * 1 Pixel = 8 * 4 Bits = 4 Bytes; 
 * 800*600 = 480 000; 4Bytes * 480 000 = 1 920 000 Bytes = 1920 KB =  1,92 MB;
 * 
 * Größe für x-Stufen Trie: 
 * 1 Pixel = x * 4 Bits = x * 0.5 Bytes;
 * 800*600 = 480 000; 0.5x Bytes * 480 000 = 240 000x Bytes = 240x KB = 0,24x MB
 * 
 * Wir haben folgende Bilder (siehe /target/-Verzeichnis nach private
* Tests) verglichen: test.png ohne compress ; test.png nach compress(3)
* ; test.png nach compress(6) Alle drei Bilder wurden zunächst codiert
* und zum Schluss decodiert.
 * 
 * Beobachtung:
 * Die resultierenden PNG-Dateien haben folgende Größen:
 * test.png:		767,4 KB
 * NoComp.png:		  1,3 MB
 * ThreeComp.png:	466,6 KB
 * SixComp.png:		 94,4 KB
 * Zusätzlich kann man beobachten, dass je höher der Kompressionsgrad
 * ist, desto schlechtere Farbqualität hat das Bild.
 * 
 * Erklärung: NoComp.png ist größer als test.png, da die Java-Methode
 * höchstwahrscheinlich nicht optimale verlustfreie Komprimierung von
 * png-Dateien bietet. ThreeComp ist kleiner als test.png und NoComp, da
 * hier das Deflate-Komprimierungsverfahren zur Geltung kommt mit
 * welchem unter anderem png verlustfrei komprimiert. Je mehr aufgefüllt
 * wird, desto mehr Bit-Redundanzen hat man. Je mehr Bit-Redundanzen
 * vorhanden sind, desto kleiner die Datei nach der
 * Deflate-Komprimierung.  Selbiges gilt für SixComp.png.  Die schlechte
 * Farbqualität entsteht durch das Beschneiden der Bäume und somit das
 * wegschneiden der hinteren Bits der einzelnen Farbkanäle, was zur
 * Folge hat, dass ähnliche Farben als die gleiche codiert werden. Je
 * kürzer der Trie, desto größer die Toleranz.
 * 
 * 
 * 
 * Frage 4: Wie erklärt sich der Unterschied im Orginalbild und dem
 * wieder decodierten Bild?
 * 
 * Answer 4: Auf den ersten Blick ist kein großer Unterschied zwischen
 * dem original und decodiertem test2.png zu erkennen.  Um einen
 * möglichen Unterschied besser einschätzen zu können, haben wir eine
 * Hilfsmethode für den ImageReader geschrieben, welche analysiert
 * wieviele gemeinsame Pixel ein Bild mit einem anderen hat. Analysiert
 * man nun test.png und test2.png mittels dieser Methode, kommt man auf
 * das Ergebnis, dass 14.29% (1 von 7) der Farben von test.png in
 * test2.png enthalten sind. Das bedeutet in der Praxis, dass durch eine
 * Codierung von test2.png mittels des Tries von test.png 7 von 8 Farben
 * verändert. Sie werden dadurch dunkler, da die fehlenden Farb-Bits mit
 * 0 aufgefüllt werden.
 *
 *
 * Frage 5: Vergleichen Sie die Geschwindigkeit beider Suchmethoden.
 * Geben Sie eine theoretische Erklärung für ihre Beobachtungen an.
 * 
 * TODO: TIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIM
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
		// TODO: You may build your images, etc. using this class
	}

}
