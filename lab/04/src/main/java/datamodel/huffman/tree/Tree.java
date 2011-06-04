/**
 * 
 */
package datamodel.huffman.tree;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

/**
 * A implementation of your Huffman tree.<br>
 * 
 * @author Kevin Munk, Jakob Karolus
 * @version 1.0
 * 
 */
public class Tree extends AbstractCollection<TreeNode> {


	/**
	 * Ensures that this collection contains the specified element
	 * (optional operation).
	 *
	 * Returns true if this collection changed as a result of the call.
	 * (Returns false if this collection does not permit duplicates and
	 * already contains the specified element.)
	 *
	 * @throws UnsupportedOperationException if the add operation is
	 * not supported by this collection 
	 *
	 * @throws ClassCastException if the class of the specified element
	 * prevents it from being added to this collection 
	 *
	 * @throws NullPointerException if the specified element is null and this
	 * collection does not permit null elements
	 *
	 * @throws IllegalArgumentException if some property of the element
	 * prevents it from being added to this collection 
	 *
	 * @throws IllegalStateException if the element cannot be added at
	 * this time due to insertion restrictions
	 */
	@Override
	public boolean add(TreeNode node) {
		// TODO: Implement Me!
		throw new UnsupportedOperationException("Implement Me!");
	}


	/**
	 * Adds all of the elements in the specified collection to this
	 * collection (optional operation).
	 *
	 * The behavior of this operation is undefined if the specified
	 * collection is modified while the operation is in progress. (This
	 * implies that the behavior of this call is undefined if the
	 * specified collection is this collection, and this collection is
	 * nonempty.) 
	 *
	 * @throws UnsupportedOperationException if the addAll operation is
	 * not supported by this collection 
	 *
	 * @throws ClassCastException if the class of an element of the
	 * specified collection prevents it from being added to this
	 * collection 
	 *
	 * @throws NullPointerException if the specified collection contains
	 * a null element and this collection does not permit null elements,
	 * or if the specified collection is null 
	 *
	 * @throws IllegalArgumentException if some property of an element
	 * of the specified collection prevents it from being added to this
	 * collection 
	 *
	 * @throws IllegalStateException if not all the elements can be
	 * added at this time due to insertion restrictions
	 */
	@Override
	public boolean addAll(Collection<? extends TreeNode> c) {
		// TODO: Implement Me!
		throw new UnsupportedOperationException("Implement Me!");
	}


	/**
	 * Removes a single instance of the specified element from this
	 * collection, if it is present (optional operation).
	 *
	 * More formally, removes an element e such that
	 * <code>(o==null ? e==null : o.equals(e))</code>,
	 * if this collection contains one or more such elements. Returns
	 * true if this collection contained the specified element (or
	 * equivalently, if this collection changed as a result of the
	 * call). 
	 *
	 * @throws UnsupportedOperationException if the remove operation is
	 * not supported by this collection 
	 *
	 * @throws ClassCastException if the type of the specified element
	 * is incompatible with this collection (optional) 
	 *
	 * @throws NullPointerException if the specified element is null and
	 * this collection does not permit null elements (optional)
	 */
	@Override
	public boolean remove(Object o) {
		// TODO: Implement Me!
		throw new UnsupportedOperationException("Implement Me!");
	}


	/**
	 * Removes all of this collection's elements that are also contained
	 * in the specified collection (optional operation).
	 *
	 * After this call returns, this collection will contain no elements
	 * in common with the specified collection. 
	 *
	 * @throws UnsupportedOperationException if the removeAll method is
	 * not supported by this collection 
	 *
	 * @throws ClassCastException if the types of one or more elements
	 * in this collection are incompatible with the specified collection
	 * (optional) 
	 * 
	 * @throws NullPointerException if this collection contains one or
	 * more null elements and the specified collection does not support
	 * null elements (optional), or if the specified collection is null
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO: Implement Me!
		throw new UnsupportedOperationException("Implement Me!");
	}


	/**
	 * Removes all of the elements from this collection (optional
	 * operation). The collection will be empty after this method
	 * returns. 
	 *
	 * @throws UnsupportedOperationException if the clear operation is
	 * not supported by this collection
	 */
	@Override
	public void clear() {
		// TODO: Implement Me!
		throw new UnsupportedOperationException("Implement Me!");
	}


	/**
	 * Returns true if this collection contains the specified element.
	 * More formally, returns true if and only if this collection
	 * contains at least one element e such that
	 * <code>
	 * (o==null ? e==null : o.equals(e)).
	 * </code>
	 *
	 * This implementation iterates over the elements in the collection,
	 * checking each element in turn for equality with the specified
	 * element. 
	 *
	 * @throws ClassCastException if the type of the specified element
	 * is incompatible with this collection (optional) 
	 *
	 * @throws NullPointerException if the specified element is null
	 * and this collection does not permit null elements (optional)
	 */
	@Override
	public boolean contains(Object o) {
		// TODO: Implement Me!
		throw new UnsupportedOperationException("Implement Me!");
	}


	/**
	 * Returns true if this collection contains all of the elements in
	 * the specified collection.
	 *
	 * This implementation iterates over the specified collection,
	 * checking each element returned by the iterator in turn to see if
	 * it's contained in this collection. If all elements are so
	 * contained true is returned, otherwise false. 
	 *
	 * @throws ClassCastException if the types of one or more elements
	 * in the specified collection are incompatible with this collection
	 * (optional) 
	 *
	 * @throws NullPointerException if the specified collection contains
	 * one or more null elements and this collection does not permit
	 * null elements (optional), or if the specified collection is null
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO: Implement Me!
		throw new UnsupportedOperationException("Implement Me!");
	}


	/**
	 * Returns the number of elements in this collection.
	 *
	 * If this tree contains more than Integer.MAX_VALUE elements,
	 * returns Integer.MAX_VALUE. 
	 */
	@Override
	public int size() {
		// TODO: Implement Me!
		throw new UnsupportedOperationException("Implement Me!");
	}


	/**
	 * Returns true if this collection contains no elements.
	 *
	 * This implementation returns size() == 0. 
	 */
	@Override
	public boolean isEmpty() {
		// TODO: Implement Me!
		throw new UnsupportedOperationException("Implement Me!");
	}


	/**
	 * Retains only the elements in this collection that are contained
	 * in the specified collection (optional operation).
	 * 
	 * In other words, removes from this collection all of its elements
	 * that are not contained in the specified collection. 
	 *
	 * @throws UnsupportedOperationException if the retainAll operation
	 * is not supported by this collection 
	 *
	 * @throws ClassCastException if the types of one or more elements
	 * in this collection are incompatible with the specified collection
	 * (optional) 
	 *
	 * @throws NullPointerException if this collection contains one or
	 * more null elements and the specified collection does not permit
	 * null elements (optional), or if the specified collection is null
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO: Implement Me!
		throw new UnsupportedOperationException("Implement Me!");
	}
	

	/**
	 * Returns an iterator over the elements contained in this
	 * collection. 
	 */
	@Override
	public Iterator<TreeNode> iterator() {
		// TODO: Implement Me!
		throw new UnsupportedOperationException("Implement Me!");
	}


	/**
	 * Returns an array containing all of the elements in this
	 * collection.
	 *
	 * If this collection makes any guarantees as to what order its
	 * elements are returned by its iterator, this method must return
	 * the elements in the same order.
	 */
	@Override
	public Object[] toArray() {
		// TODO: Implement Me!
		throw new UnsupportedOperationException("Implement Me!");
	}


	/**
	 * Returns an array containing all of the elements in this
	 * collection; the runtime type of the returned array is that of the
	 * specified array.
	 *
	 * If the collection fits in the specified array, it is returned
	 * therein. Otherwise, a new array is allocated with the runtime
	 * type of the specified array and the size of this collection.
	 *
	 * @throws ArrayStoreException if the runtime type of the specified
	 * array is not a supertype of the runtime type of every element in
	 * this collection 
	 *
	 * @throws NullPointerException if the specified array is null
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		// TODO: Implement Me!
		throw new UnsupportedOperationException("Implement Me!");
	}


	/**
	 * Returns a string representation of this collection.
	 *
	 * The string representation consists of a list of the collection's
	 * elements in the order they are returned by its iterator, enclosed
	 * in square brackets ("[]"). Adjacent elements are separated by the
	 * characters ", " (comma and space). Elements are converted to
	 * strings as by String.valueOf(Object). 
	 */
	@Override
	public String toString() {
		// TODO: Implement Me!
		throw new UnsupportedOperationException("Implement Me!");
	}
}
