package simulations;

import java.util.List;

import cellsociety_team09.Grid;
import javafx.scene.shape.Rectangle;

public class NeighborFinder {
	
	
	public NeighborFinder(){
		
	}
	public void getNeighbors(List<List<CellModel>> grid, Rectangle rectangle, CellModel model){
		for(int i = 1; i < grid.get(0).size() - 1; i++){
			for (int j = 1; j < grid.get(0).size() - 1; j++){
				for (int k = i - 1; k <= i + 1; k++){
					for (int l = j - 1; l <= j + 1; l++){
						if (!grid.get(i).get(j).equals(grid.get(k).get(l))){
							grid.get(i).get(j).addNeighbor(grid.get(k).get(l));
						}
					}
				}
			}
		}
	}
	public void getNeighbors(List<List<CellModel>> grid, Rectangle rectangle, LifeCell cell){
		this.getNeighbors(grid, rectangle, cell);
	}
	public static void getNeighbors(List<List<CellModel>> grid, Rectangle rectangle, FireCell cell){
		for(int i = 1; i < grid.get(0).size() - 1; i++){
			for (int j = 1; j < grid.get(0).size() - 1; j++){
				System.out.print("boom");
				grid.get(i).get(j).addNeighbor(grid.get(i - 1).get(j));
				grid.get(i).get(j).addNeighbor(grid.get(i).get(j - 1));
				grid.get(i).get(j).addNeighbor(grid.get(i).get(j + 1));
				grid.get(i).get(j).addNeighbor(grid.get(i + 1).get(j));
			}
		}
	}
	public void getNeighbors(List<List<CellModel>> grid, Rectangle rectangle, SegregationCell cell){
		this.getNeighbors(grid, rectangle, cell);
	}
	public void getNeighbors(List<List<CellModel>> grid, Rectangle rectangle, WatorCell cell){
		this.getNeighbors(grid, rectangle, cell);
	}
	
	public static void main(String[] args){
		Grid grid = new Grid(1);
		getNeighbors(grid.getCells(), new Rectangle(), new FireCell());
		
		
		
		
	}
}
