/**
 * 
 */
package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import datamodel.Grid;
import datamodel.UpdateEvent;

/**
 * displays the GridPanel
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 *
 */
public class GridPanel extends JPanel implements Observer, ActionListener{
	
	private GridElementGUI[][] grid;
	private GridWindow parent;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6639618040013880313L;
	
	/**
	 * constructor
	 */
	public GridPanel(GridWindow parent){
		super();
		this.parent = parent;
		this.setOpaque(true);
		
	}
	
	/**
	 * initializes the grid field
	 */
	private void buildField(int width, int heigth, Grid grid){
		//remove old field
		this.removeAll();
		//set the layout
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth=1;
		c.gridheight=1;
		
		//get a new field
		this.grid = new GridElementGUI[heigth][width];	
		
		//fill it
		for(int i=0; i < heigth; i++){
			for(int j=0; j < width; j++){
				//init each element
				c.gridx = j;
				c.gridy = i;
				this.grid[i][j] = new GridElementGUI(grid.getElementAt(i, j));
				this.grid[i][j].addActionListener(this);
				this.grid[i][j].setActionCommand(i + "," + j);
				this.grid[i][j].setOpaque(true);
				
				this.add(this.grid[i][j], c);
				this.grid[i][j].paint();
			}
		}
		this.validate();
	}
	

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		//get the update event
		UpdateEvent e = (UpdateEvent) arg;
		if(e.getOccuredEvent().equalsIgnoreCase(UpdateEvent.GRID_READY)){
			//build a new field
			this.buildField(e.getxPosition(), e.getyPosition(), (Grid) o);
		}
		else if(e.getOccuredEvent().equalsIgnoreCase(UpdateEvent.FOUND_SOLUTION)){
			//enable generate and disable solve
			this.parent.finishedSolving(true);
		}
		else if(e.getOccuredEvent().equalsIgnoreCase(UpdateEvent.NO_SOLUTION)){
			//display message, enable generate and disable solve
			this.parent.finishedSolving(false);
		}
		else if(e.getOccuredEvent().equalsIgnoreCase(UpdateEvent.FALSE_PRECONDITIONS)){
			//no start or end were set
			this.parent.falsePreConditions();
			this.parent.finishedSolving(true);
		}
		else{
			//update element, could be algo or element state
			if(e.getChangedState() == null){
				//switch algo state
				this.grid[e.getxPosition()][e.getyPosition()].mask();
			}
			else{
				//switch element state
				this.grid[e.getxPosition()][e.getyPosition()].paint();
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//get values
		String[] values = e.getActionCommand().split(",");
		int column,row;
		try{
			row = Integer.valueOf(values[0]);
			column = Integer.valueOf(values[1]);
			//set the new state
			this.grid[row][column].getGridElement().setState(this.parent.getToggleState());
			//no paint necessary, observer does the work
			
		} catch(NumberFormatException ex){
			//nothing to do
		}

	}
	
	/**
	 * disables or enable all the buttons of the gridField
	 * @param flag false disables all buttons, true enables them
	 */
	protected void changeButtons(boolean flag){
		for(int i=0; i < this.grid.length; i++){
			for(int j=0; j < this.grid[0].length; j++){
				this.grid[i][j].setEnabled(flag);
			}
		}
	}

}
