package engine.utilities;

import engine.main.Engine;
import objects.player.Player;
import objects.tiles.Tile;

public class Camera {
	
	public static int x, y;
	
	public Camera(){
		x = 0;
		y = 0;
	}
	
	public void update(){
		x = (int) Player.x - (Engine.width / 2) + (Tile.GRIDSIZE / 2);
		y = (int) Player.y - (Engine.height / 2) + (Tile.GRIDSIZE / 2);
	}

}
