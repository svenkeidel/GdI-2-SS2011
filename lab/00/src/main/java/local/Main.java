package local;

import java.util.Arrays;
import java.util.Random;

/*******************************************************************
 * Questions / Fragen
 *
 * The english questions are provided on the exercise sheet
 *
 *******************************************************************
 * a) Wie verhaelt sich die gemessene Laufzeit von den Methoden
 * insertElement und insertElementFast zueinander?
 * Erklaeren Sie das Ergebnis!
 * ----------------------------------------------------------------
 *
 * Die insertElementFast Methode hatte beim Profiling einen Speedup
 * von ~10. Das liegt daran, das schlau eingefuegt wird. Dadurch das
 * durch das Array wie durch einen Baum traversiert wird, muessen nur
 * n/4 Elemente geprueft werden. Bei der langsamen Methode hingegen
 * kommt ein Sortier Algorithmus(Quicksort) zum tragen der bei fast
 * vorsortierten Listen sehr langsam ist O(n^2).
 *
 *
 * b) Warum ist es schneller die Methode System.arraycopy zu
 * verwenden anstatt alle Werte einzeln in einer for-Schleife
 * zuzuweisen?  Beachte hierbei den Java-SourceCode zu dieser
 * Methode!
 * ----------------------------------------------------------------
 *
 * *** Auszug des openjdk sourcecodes ***
 *    FILE: java/lang/System.java
 *    ...
 * 1  public static native void arraycopy(Object src,  int  srcPos,
 * 2                                      Object dest, int destPos,
 * 3                                      int length);
 *    ...
 * *** Auszug Ende ***
 *
 * Das Schluesselwort 'native' in Zeile 1 sagt aus, das die Methode in
 * einer anderen Sprache definiert ist. deswegen befindet sich unter der
 * Methode auch kein Koerper. Da in der JNINativeInterface.c keine solche
 * Methode definiert wurde, ist die Funktion in der JVM implimentiert.
 *
 * *** Auszug von der Hotspot JVM aus dem openjdk ***
 *    FILE: share/vm/c1/c1_Runtime1.cpp
 *    ...
 * 1  JRT_LEAF(int, Runtime1::arraycopy(oopDesc* src, int src_pos, oopDesc* dst, int dst_pos, int length))
 * 2  #ifndef PRODUCT
 * 3    _generic_arraycopy_cnt++;        // Slow-path oop array copy
 * 4  #endif
 * 5
 * 6    if (src == NULL || dst == NULL || src_pos < 0 || dst_pos < 0 || length < 0) return ac_failed;
 * 7    if (!dst->is_array() || !src->is_array()) return ac_failed;
 * 8    if ((unsigned int) arrayOop(src)->length() < (unsigned int)src_pos + (unsigned int)length) return ac_failed;
 * 9    if ((unsigned int) arrayOop(dst)->length() < (unsigned int)dst_pos + (unsigned int)length) return ac_failed;
 * 10
 * 11   if (length == 0) return ac_ok;
 * 12   if (src->is_typeArray()) {
 * 13     const klassOop klass_oop = src->klass();
 * 14     if (klass_oop != dst->klass()) return ac_failed;
 * 15     typeArrayKlass* klass = typeArrayKlass::cast(klass_oop);
 * 16     const int l2es = klass->log2_element_size();
 * 17     const int ihs = klass->array_header_in_bytes() / wordSize;
 * 18     char* src_addr = (char*) ((oopDesc**)src + ihs) + (src_pos << l2es);
 * 19     char* dst_addr = (char*) ((oopDesc**)dst + ihs) + (dst_pos << l2es);
 * 20     // Potential problem: memmove is not guaranteed to be word atomic
 * 21     // Revisit in Merlin
 * 22     memmove(dst_addr, src_addr, length << l2es);
 * 23     return ac_ok;
 * 24   } else if (src->is_objArray() && dst->is_objArray()) {
 * 25     if (UseCompressedOops) {
 * 26       narrowOop *src_addr  = objArrayOop(src)->obj_at_addr<narrowOop>(src_pos);
 * 27       narrowOop *dst_addr  = objArrayOop(dst)->obj_at_addr<narrowOop>(dst_pos);
 * 28       return obj_arraycopy_work(src, src_addr, dst, dst_addr, length);
 * 29     } else {
 * 30       oop *src_addr  = objArrayOop(src)->obj_at_addr<oop>(src_pos);
 * 31       oop *dst_addr  = objArrayOop(dst)->obj_at_addr<oop>(dst_pos);
 * 32       return obj_arraycopy_work(src, src_addr, dst, dst_addr, length);
 * 33     }
 * 34   }
 * 35   return ac_failed;
 * 36 JRT_END
 *    ...
 * *** Auszug Ende ***
 *
 * Wie man in Zeile 22 erkennen kann wird memmove dazu verwendet um den
 * Speicherbereich eines Arrays eines primitiven datentyps byteweise zu
 * kopieren.
 *
 * Resume: Da Java immer noch eine interpretierte Sprache ist, ist es
 * performanter, haufig verwendete und zeitkritische Funktionen in
 * Hadware naehere Sprachen wie C oder C++ auszulagern, anstatt sie in
 * Java selbst zu implimentieren.
 *
 *******************************************************************/


/**
 * provides the main that interacts with the class ArraySort.
 *
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 *
 */
public class Main {

	/**
	 * calls the method insertElement and insertElementFast 1000 times.
	 * Every time it generates a new array with 10000 elements and inserts a random number.
	 *
	 * @param args unused
	 */
	public static void main(String[] args){

		// Random number generator for the element to insert
		Random random = new Random();


		for (int i = 0; i< 1000; i++){

			// Generate a sorted array
			int[] sortedArray = Main.getSortedArray(10000);

			// Generate the element to insert
			int element = random.nextInt();

			// Call both insertElement methods
			ArraySort.insertElement(element, sortedArray);
			ArraySort.insertElementFast(element, sortedArray);

		}
	}

	/**
	 *
	 * @param count the size of the array
	 * @return sorted array with random numbers.
	 */
	public static int[] getSortedArray(int count){

		// Generate random numbers and assign them to the array
		Random random = new Random();
		int[] array = new int[count];
		for (int i = 0; i < count; i++) {
			array[i] = random.nextInt();
		}

		// Sort the array
		Arrays.sort(array);
		return array;
	}

}
