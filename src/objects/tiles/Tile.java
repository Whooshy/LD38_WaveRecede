package objects.tiles;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import engine.utilities.Images;
import world.World;

public class Tile extends Rectangle{
	
	public int id;
	
	public static int TILESIZE = 16;
	public static int GRIDSIZE = 64;
	
	public static final int Air = 0;
	public static final int Dirt = 1;
	public static final int Grass = 2;
	public static final int Sand = 3;
	public static final int PalmTreeTop = 4;
	public static final int PalmTreeBottom = 5;
	public static final int PalmLog = 6;
	public static final int PalmSeeds = 7;
	public static final int PalmLeaf = 8;
	public static final int Sifter = 9;
	public static final int Pebble = 10;
	public static final int RedGemstone = 11;
	public static final int RockShovel = 12;
	public static final int RockPickaxe = 13;
	public static final int Border = 14;
	public static final int PalmSapling = 15;
	public static final int Water = 16;
	
	public boolean isVoxel = true;

	public Tile(Rectangle bounds, int id){
		setBounds(bounds);
		this.id = id;
	}
	
	public void update(Graphics2D g){
		g.drawImage(Images.tiles[id], x, y, width, height, null);
	}
}
