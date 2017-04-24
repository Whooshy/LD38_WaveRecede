package engine.hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import engine.utilities.Images;
import objects.tiles.Tile;

public class Slot extends Rectangle{
	
	public int id;
	public int amount;
	
	public boolean isSelected = false;
	
	public Slot(Rectangle bounds, int id, int amount){
		setBounds(bounds);
		this.id = id;
		this.amount = amount;
	}
	
	public void update(Graphics2D g){
		g.drawImage(Images.slot, x, y, width, height, null);
		g.drawImage(Images.tiles[id], x, y, width, height, null);
		
		if(isSelected) g.drawImage(Images.selection, x, y, width, height, null);
		if(Inventory.isMoving && isSelected) g.drawImage(Images.move_selection, x, y, width, height, null);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.drawString("" + amount, x, y + Tile.GRIDSIZE);
		
		if(amount <= 0){
			id = Tile.Air;
			amount = 0;
		}
	}

}
