package test_private;



import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import datamodel.rucksack.Rucksack;
import datamodel.rucksack.RucksackObject;

public class RucksackTest {

	private RucksackObject o1 = new RucksackObject(10, 15);
	private RucksackObject o2 = new RucksackObject(15, 5);
	private RucksackObject o3 = new RucksackObject(23, 45);
	private RucksackObject o4 = new RucksackObject(300, 200);
	private RucksackObject o5 = new RucksackObject(34, 65);
	private RucksackObject o6 = new RucksackObject(5, 1);

	private Rucksack rucksack = new Rucksack(150);

	@Before
	public void init(){
		rucksack.removeAll();
	}
	
	@Test
	public void insertTest(){
		Assert.assertFalse(rucksack.insert(o4));
		Assert.assertTrue(rucksack.insert(o1));
		Assert.assertTrue(rucksack.insert(o1));
		Assert.assertTrue(rucksack.insert(o1));
		Assert.assertTrue(rucksack.insert(o1));
		Assert.assertTrue(rucksack.insert(o1));
		Assert.assertTrue(rucksack.insert(o1));
		Assert.assertTrue(rucksack.insert(o1));
		Assert.assertTrue(rucksack.insert(o1));
		Assert.assertTrue(rucksack.insert(o1));
		Assert.assertTrue(rucksack.insert(o1));
		// full
		Assert.assertFalse(rucksack.insert(o1));
		
		Assert.assertEquals(150, rucksack.getWeightOfRucksack());
		rucksack.removeAll();
		Assert.assertEquals(0, rucksack.getWeightOfRucksack());
	}
	
	@Test
	public void getsetTest(){
		Assert.assertFalse(rucksack.insert(o4));
		rucksack.setCapacity(200);
		Assert.assertTrue(rucksack.insert(o4));
		Assert.assertEquals(o4, rucksack.getObject(0));
		Assert.assertEquals(200, rucksack.getCapacity());
	}
	
	@Test
	public void rucksackTest(){
		Assert.assertEquals(0, rucksack.getWeightOfRucksack());
		rucksack.insert(o1);
		rucksack.insert(o2);
		rucksack.insert(o3);
		rucksack.insert(o4);
		rucksack.insert(o5);
		rucksack.insert(o6);
		Assert.assertEquals(131, rucksack.getWeightOfRucksack());
		Assert.assertEquals(87, rucksack.getValueOfRucksack());
		rucksack.removeAt(1);
		Assert.assertEquals(126, rucksack.getWeightOfRucksack());
		Assert.assertEquals(72, rucksack.getValueOfRucksack());
		rucksack.removeAll();
		Assert.assertEquals(0, rucksack.getWeightOfRucksack());
		Assert.assertEquals(0, rucksack.getValueOfRucksack());
	}
	
	@Test
	public void equalsTest(){
		rucksack.insert(o1);
		Assert.assertTrue(rucksack.equals(rucksack));
		Rucksack otherRucksack = new Rucksack(150);
		otherRucksack.insert(o2);
		Assert.assertFalse(rucksack.equals(otherRucksack));
		otherRucksack.removeAll();
		otherRucksack.insert(o1);
		Assert.assertTrue(rucksack.equals(otherRucksack));
		Assert.assertTrue(otherRucksack.equals(rucksack));
		rucksack.removeAll();
		otherRucksack.removeAll();
		rucksack.insert(o1);
		rucksack.insert(o2);
		otherRucksack.insert(o2);
		otherRucksack.insert(o1);
		Assert.assertTrue(rucksack.equals(otherRucksack));
		Assert.assertTrue(otherRucksack.equals(rucksack));
	}
	
	@Test
	public void cloneTest(){
		rucksack.insert(o1);
		rucksack.insert(o2);
		Rucksack otherRucksack = rucksack.clone();
		Assert.assertTrue(rucksack.equals(otherRucksack));
		Assert.assertTrue(otherRucksack.equals(rucksack));
	}
}
