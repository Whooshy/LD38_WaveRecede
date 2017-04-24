package engine.hud;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import engine.main.Engine;
import engine.utilities.Images;
import objects.tiles.Tile;

public class Crafting {
	
	public int okPressed = 0;
	
	public static boolean isOpen = false;
	public static boolean canPressOk = true;
	
	public static Slot[] slots = new Slot[3];
	
	public Crafting(){
		slots[0] = new Slot(new Rectangle((Engine.width / 2) - 150, 350, Tile.GRIDSIZE, Tile.GRIDSIZE), Tile.Air, 0);
		slots[1] = new Slot(new Rectangle((Engine.width / 2) + (150 - Tile.GRIDSIZE), 350, Tile.GRIDSIZE, Tile.GRIDSIZE), Tile.Air, 0);
		slots[2] = new Slot(new Rectangle((Engine.width / 2) - (Tile.GRIDSIZE / 2), 425, Tile.GRIDSIZE, Tile.GRIDSIZE), Tile.Air, 0);
	}
	
	public void update(Graphics2D g){
		Rectangle clickPoint = new Rectangle(Engine.mouseX, Engine.mouseY, 1, 1);
		Rectangle okButton = new Rectangle((Engine.width / 2) - 32, 500, 64, 32);
		if(isOpen){
			g.drawImage(Images.okButton[okPressed], (Engine.width / 2) - 32, 500, 64, 32, null);
			
			if(okButton.intersects(clickPoint) && canPressOk){
				okPressed = 1;
			}else{
				okPressed = 0;
			}
			
			for(int i = 0; i < 3; i++){
				slots[i].update(g);
				if(slots[i].intersects(clickPoint)){
					slots[i].isSelected = true;
				}else{
					slots[i].isSelected = false;
				}
			}
			
			if(slots[0].id == Tile.PalmLeaf && slots[1].id == Tile.PalmLog && slots[0].amount >= 3 && slots[1].amount >= 2 && okPressed == 1 && canPressOk){
				slots[2].id = Tile.Sifter;
				slots[2].amount = 1;
				if(okPressed == 1 && canPressOk){
						for(int xx = 0; xx < Inventory.slots.length; xx++){
							for(int yy = 0; yy < Inventory.slots[0].length; yy++){
								if(Inventory.slots[xx][yy].id == Tile.PalmLeaf){
									Inventory.slots[xx][yy].amount -= 3;
									break;
								}
							}
						}
						for(int xx = 0; xx < Inventory.slots.length; xx++){
							for(int yy = 0; yy < Inventory.slots[0].length; yy++){
								if(Inventory.slots[xx][yy].id == Tile.PalmLog){
									Inventory.slots[xx][yy].amount -= 2;
									break;
								}
							}
						}
					for(int xx = 0; xx < Inventory.slots.length; xx++){
						for(int yy = 0; yy < Inventory.slots[0].length; yy++){
							if(okPressed == 1 && canPressOk){
								if(Inventory.slots[xx][yy].id == Tile.Sifter && Inventory.slots[xx][yy].amount <= 99){
									Inventory.slots[xx][yy].amount += 1;
									System.out.println(xx + ", " + yy);
									slots[0].amount = 0;
									slots[1].amount = 0;
									slots[2].amount = 0;
									canPressOk = false;
									okPressed = 0;
								}
								if(Inventory.slots[xx][yy].id == Tile.Air){
									Inventory.slots[xx][yy].amount += 1;
									Inventory.slots[xx][yy].id = Tile.Sifter;
									System.out.println(xx + ", " + yy);
									slots[0].amount = 0;
									slots[1].amount = 0;
									slots[2].amount = 0;
									canPressOk = false;
									okPressed = 0;
								}
							}
						}
					}
				}
			}
			
			if(slots[0].id == Tile.Pebble && slots[1].id == Tile.PalmLog && slots[0].amount >= 2 && slots[1].amount >= 1){
				slots[2].id = Tile.RockShovel;
				slots[2].amount = 1;
				if(okPressed == 1 && canPressOk){
				for(int xx = 0; xx < Inventory.slots.length; xx++){
					for(int yy = 0; yy < Inventory.slots[0].length; yy++){
						if(Inventory.slots[xx][yy].id == Tile.Pebble){
							Inventory.slots[xx][yy].amount -= 2;
							break;
						}
					}
				}
				for(int xx = 0; xx < Inventory.slots.length; xx++){
					for(int yy = 0; yy < Inventory.slots[0].length; yy++){
						if(Inventory.slots[xx][yy].id == Tile.PalmLog){
							Inventory.slots[xx][yy].amount -= 1;
							break;
						}
					}
				}
					for(int xx = 0; xx < Inventory.slots.length; xx++){
						for(int yy = 0; yy < Inventory.slots[0].length; yy++){
								if(Inventory.slots[xx][yy].id == Tile.RockShovel && Inventory.slots[xx][yy].amount <= 99){
									Inventory.slots[xx][yy].amount += 1;
									System.out.println(xx + ", " + yy);
									slots[0].amount = 0;
									slots[1].amount = 0;
									slots[2].amount = 0;
									canPressOk = false;
									okPressed = 0;
									break;
								}else if(Inventory.slots[xx][yy].id == Tile.Air){
									Inventory.slots[xx][yy].amount += 1;
									Inventory.slots[xx][yy].id = Tile.RockShovel;
									System.out.println(xx + ", " + yy);
									slots[0].amount = 0;
									slots[1].amount = 0;
									slots[2].amount = 0;
									canPressOk = false;
									okPressed = 0;
									break;
								}
							}
						}
					}
				
				}
			
			if(slots[0].id == Tile.PalmLog && slots[1].id == Tile.Pebble && slots[0].amount >= 1 && slots[1].amount >= 2 && okPressed == 1 && canPressOk){
				slots[2].id = Tile.RockPickaxe;
				slots[2].amount = 1;
				if(okPressed == 1 && canPressOk){
				for(int xx = 0; xx < Inventory.slots.length; xx++){
					for(int yy = 0; yy < Inventory.slots[0].length; yy++){
						if(Inventory.slots[xx][yy].id == Tile.Pebble){
							Inventory.slots[xx][yy].amount -= 2;
							break;
						}
					}
				}
				for(int xx = 0; xx < Inventory.slots.length; xx++){
					for(int yy = 0; yy < Inventory.slots[0].length; yy++){
						if(Inventory.slots[xx][yy].id == Tile.PalmLog){
							Inventory.slots[xx][yy].amount -= 1;
							break;
						}
					}
				}
					for(int xx = 0; xx < Inventory.slots.length; xx++){
						for(int yy = 0; yy < Inventory.slots[0].length; yy++){
							if(okPressed == 1 && canPressOk){
								if(Inventory.slots[xx][yy].id == Tile.RockPickaxe && Inventory.slots[xx][yy].amount <= 99){
									Inventory.slots[xx][yy].amount += 1;
									System.out.println(xx + ", " + yy);
									slots[0].amount = 0;
									slots[1].amount = 0;
									slots[2].amount = 0;
									canPressOk = false;
									okPressed = 0;
								}
								if(Inventory.slots[xx][yy].id == Tile.Air){
									Inventory.slots[xx][yy].amount += 1;
									Inventory.slots[xx][yy].id = Tile.RockPickaxe;
									System.out.println(xx + ", " + yy);
									slots[0].amount = 0;
									slots[1].amount = 0;
									slots[2].amount = 0;
									canPressOk = false;
									okPressed = 0;
								}
							}
							}
						}
					}
				}
			}
		}
	

}
