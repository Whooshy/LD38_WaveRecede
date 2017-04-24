package engine.hud;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import engine.main.Engine;
import objects.tiles.Tile;

public class Inventory {
	
	public static boolean isOpen;
	
	public static Slot[][] slots = new Slot[5][4];
	
	public static boolean isMoving = false;
	
	int tempID, tempAmount;
	
	public Inventory(){
		isOpen = false;
		
		for(int x = 0; x < slots.length; x++){
			for(int y = 0; y < slots[0].length; y++){
				slots[x][y] = new Slot(new Rectangle((x * Tile.GRIDSIZE) + (Engine.width / 2) - (int) (2.5 * Tile.GRIDSIZE), (y * Tile.GRIDSIZE) + (Engine.height / 2) - (int) (2 * Tile.GRIDSIZE), Tile.GRIDSIZE, Tile.GRIDSIZE), Tile.Air, 0);
			}
		}
	}
	
	public void update(Graphics2D g){
		Rectangle clickPoint = new Rectangle(Engine.mouseX, Engine.mouseY, 1, 1);
		if(isOpen){
			for(int x = 0; x < slots.length; x++){
				for(int y = 0; y < slots[0].length; y++){
					slots[x][y].update(g);
					if(slots[x][y].intersects(clickPoint)){ 
						slots[x][y].isSelected = true;
					}else{
						slots[x][y].isSelected = false;
					}
					if(isMoving){
						if(slots[x][y].isSelected){
							tempID = slots[x][y].id;
							tempAmount = slots[x][y].amount;
						}
						
						for(int i = 0; i < 3; i++){
							if(Crafting.slots[i].isSelected){
								Crafting.slots[i].id = tempID;
								Crafting.slots[i].amount = tempAmount;
							}
						}
					}
				}
			}
		}
	}

}
