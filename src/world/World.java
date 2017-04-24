package world;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import engine.hud.Crafting;
import engine.hud.Inventory;
import engine.main.Engine;
import objects.player.Player;
import objects.tiles.Tile;

public class World {
	
	public static Tile[][] tiles = new Tile[75][75];
	
	public Random random = new Random();
	
	public float breakTime;
	public float breakSpeed;
	
	public int day, time;
	
	public boolean sifted = false;
	
	public World(){
		day = 1;
		
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[0].length; y++){
				tiles[x][y] = new Tile(new Rectangle(x * Tile.GRIDSIZE, y * Tile.GRIDSIZE, Tile.GRIDSIZE, Tile.GRIDSIZE), Tile.Air);
				Rectangle island = new Rectangle(25 * Tile.GRIDSIZE, 51 * Tile.GRIDSIZE, 25 * Tile.GRIDSIZE, 9 * Tile.GRIDSIZE);
				Rectangle islandTop = new Rectangle(25 * Tile.GRIDSIZE, 50 * Tile.GRIDSIZE, 25 * Tile.GRIDSIZE, 1 * Tile.GRIDSIZE);
				Rectangle islandTreeZone = new Rectangle(25 * Tile.GRIDSIZE, 49 * Tile.GRIDSIZE, 25 * Tile.GRIDSIZE, 1 * Tile.GRIDSIZE);
				
				Rectangle borderLeft = new Rectangle(new Rectangle(24 * Tile.GRIDSIZE, 0, Tile.GRIDSIZE, 75 * Tile.GRIDSIZE));
				Rectangle borderRight = new Rectangle(new Rectangle(50 * Tile.GRIDSIZE, 0, Tile.GRIDSIZE, 75 * Tile.GRIDSIZE));
				
				Rectangle sand1 = new Rectangle(24 * Tile.GRIDSIZE, 51 * Tile.GRIDSIZE, Tile.GRIDSIZE, Tile.GRIDSIZE);
				Rectangle sand2 = new Rectangle(23 * Tile.GRIDSIZE, 52 * Tile.GRIDSIZE, Tile.GRIDSIZE * 2, Tile.GRIDSIZE);
				Rectangle sand3 = new Rectangle(22 * Tile.GRIDSIZE, 53 * Tile.GRIDSIZE, Tile.GRIDSIZE * 3, Tile.GRIDSIZE);
				Rectangle sand4 = new Rectangle(21 * Tile.GRIDSIZE, 54 * Tile.GRIDSIZE, Tile.GRIDSIZE * 4, Tile.GRIDSIZE);
				Rectangle sand5 = new Rectangle(20 * Tile.GRIDSIZE, 55 * Tile.GRIDSIZE, Tile.GRIDSIZE * 5, Tile.GRIDSIZE);
				
				Rectangle sand6 = new Rectangle(50 * Tile.GRIDSIZE, 51 * Tile.GRIDSIZE, Tile.GRIDSIZE, Tile.GRIDSIZE);
				Rectangle sand7 = new Rectangle(50 * Tile.GRIDSIZE, 52 * Tile.GRIDSIZE, Tile.GRIDSIZE * 2, Tile.GRIDSIZE);
				Rectangle sand8 = new Rectangle(50 * Tile.GRIDSIZE, 53 * Tile.GRIDSIZE, Tile.GRIDSIZE * 3, Tile.GRIDSIZE);
				Rectangle sand9 = new Rectangle(50 * Tile.GRIDSIZE, 54 * Tile.GRIDSIZE, Tile.GRIDSIZE * 4, Tile.GRIDSIZE);
				Rectangle sand10 = new Rectangle(50 * Tile.GRIDSIZE, 55 * Tile.GRIDSIZE, Tile.GRIDSIZE * 5, Tile.GRIDSIZE);
				
				if(tiles[x][y].intersects(island)){
					tiles[x][y].id = Tile.Dirt;
				}
				if(tiles[x][y].intersects(islandTop)){
					tiles[x][y].id = Tile.Grass;
				}
				
				if(tiles[x][y].intersects(sand1)){
					tiles[x][y].id = Tile.Sand;
				}
				if(tiles[x][y].intersects(sand2)){
					tiles[x][y].id = Tile.Sand;
				}
				if(tiles[x][y].intersects(sand3)){
					tiles[x][y].id = Tile.Sand;
				}
				if(tiles[x][y].intersects(sand4)){
					tiles[x][y].id = Tile.Sand;
				}
				if(tiles[x][y].intersects(sand5)){
					tiles[x][y].id = Tile.Sand;
				}
				if(tiles[x][y].intersects(sand6)){
					tiles[x][y].id = Tile.Sand;
				}
				if(tiles[x][y].intersects(sand7)){
					tiles[x][y].id = Tile.Sand;
				}
				if(tiles[x][y].intersects(sand8)){
					tiles[x][y].id = Tile.Sand;
				}
				if(tiles[x][y].intersects(sand9)){
					tiles[x][y].id = Tile.Sand;
				}
				if(tiles[x][y].intersects(sand10)){
					tiles[x][y].id = Tile.Sand;
				}
				
				if(tiles[x][y].intersects(borderLeft)){
					tiles[x][y].id = Tile.Border;
				}
				
				if(tiles[x][y].intersects(borderRight)){
					tiles[x][y].id = Tile.Border;
				}
				
				if(tiles[x][y].intersects(islandTreeZone)){
					if(random.nextInt(3) == 0){
						tiles[x][y].id = Tile.PalmTreeBottom;
					}
				}
				
				if(tiles[x][y].id == Tile.PalmTreeBottom){
					tiles[x][y - 1].id = Tile.PalmTreeTop;
				}
			}
		}
	}
	
	public void update(Graphics2D g){
		Rectangle clickPoint = new Rectangle(Engine.worldMouseX, Engine.worldMouseY, 1, 1);
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[0].length; y++){
				try{
					tiles[x][y].update(g);
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		time++;
		
		g.setColor(Color.BLACK);
		g.fillRect((int) Player.x - 400, (int) Player.y - 300, 800, 20);
		
		g.setColor(Color.YELLOW);
		g.fillRect((int) Player.x - 400, (int) Player.y - 300, (time / 8), 20);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.drawString("Day " + day, Player.x - 400, Player.y - 240);
		
		if(time >= 6400){
			time = 0;
			day += 1;
		}
		
		g.setColor(Color.BLUE);
		g.fillRect(0, (int) (50.5 * Tile.GRIDSIZE), 25 * Tile.GRIDSIZE, 10 * Tile.GRIDSIZE);
		g.fillRect(50 * Tile.GRIDSIZE, (int) (50.5 * Tile.GRIDSIZE), 25 * Tile.GRIDSIZE, 10 * Tile.GRIDSIZE);
		
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[0].length; y++){
				if(tiles[x][y].intersects(clickPoint) && !Inventory.isOpen && !Crafting.isOpen){
					for(int xx = 0; xx < Inventory.slots.length; xx++){
						for(int yy = 0; yy < Inventory.slots[0].length; yy++){
							if(tiles[x][y].id == Tile.Grass && !Engine.rmb){
								g.setColor(Color.BLACK);
								g.fillRect((int) Player.x - 100, (int) Player.y + 250, 200 + Tile.GRIDSIZE, 5);
								
								g.setColor(Color.GREEN);
								g.fillRect((int) Player.x - 100, (int) Player.y + 250, (int) breakTime, 5);
								
								if(Inventory.slots[xx][yy].isSelected){
									if(Inventory.slots[xx][yy].id == Tile.RockShovel){
										breakTime += 0.2f;
									}
								}else{
									breakTime += 0.1f;
								}
								
								if(breakTime >= 200 + Tile.GRIDSIZE){
									if(Inventory.slots[xx][yy].id == Tile.Grass){
										Inventory.slots[xx][yy].amount += 1;
										tiles[x][y].id = Tile.Air;
									}
									if(Inventory.slots[xx][yy].id == Tile.Air){
										Inventory.slots[xx][yy].id = Tile.Grass;
										Inventory.slots[xx][yy].amount += 1;
										tiles[x][y].id = Tile.Air;
									}
								}
							}
						}
					}
					for(int xx = 0; xx < Inventory.slots.length; xx++){
						for(int yy = 0; yy < Inventory.slots[0].length; yy++){
							if(tiles[x][y].id == Tile.Dirt && !Engine.rmb){
								g.setColor(Color.BLACK);
								g.fillRect((int) Player.x - 100, (int) Player.y + 250, 200 + Tile.GRIDSIZE, 5);
								
								g.setColor(Color.GREEN);
								g.fillRect((int) Player.x - 100, (int) Player.y + 250, (int) breakTime, 5);
								
								if(Inventory.slots[xx][yy].isSelected){
									if(Inventory.slots[xx][yy].id == Tile.RockShovel){
										breakTime += 0.3f;
									}else{
										breakTime += 0.15f;
									}
								}else{
									breakTime += 0.15f;
								}
								
								if(breakTime >= 200 + Tile.GRIDSIZE){
									int r = random.nextInt(3);
									if(r == 0){
										if(Inventory.slots[xx][yy].id == Tile.Dirt){
											Inventory.slots[xx][yy].amount += 1;
											tiles[x][y].id = Tile.Air;
										}
										if(Inventory.slots[xx][yy].id == Tile.Air){
											Inventory.slots[xx][yy].id = Tile.Dirt;
											Inventory.slots[xx][yy].amount += 1;
											tiles[x][y].id = Tile.Air;
										}
									}
									if(r == 1){
										if(Inventory.slots[xx][yy].id == Tile.Sand){
											Inventory.slots[xx][yy].amount += 1;
											tiles[x][y].id = Tile.Air;
										}
										if(Inventory.slots[xx][yy].id == Tile.Air){
											Inventory.slots[xx][yy].id = Tile.Sand;
											Inventory.slots[xx][yy].amount += 1;
											tiles[x][y].id = Tile.Air;
										}
									}
									if(r == 2){
										if(Inventory.slots[xx][yy].id == Tile.Pebble){
											Inventory.slots[xx][yy].amount += 1;
											tiles[x][y].id = Tile.Air;
										}
										if(Inventory.slots[xx][yy].id == Tile.Air){
											Inventory.slots[xx][yy].id = Tile.Pebble;
											Inventory.slots[xx][yy].amount += 1;
											tiles[x][y].id = Tile.Air;
										}
									}
								}
							}
						}
					}
					for(int xx = 0; xx < Inventory.slots.length; xx++){
						for(int yy = 0; yy < Inventory.slots[0].length; yy++){
							if(tiles[x][y].id == Tile.PalmTreeBottom && !Engine.rmb){
								g.setColor(Color.BLACK);
								g.fillRect((int) Player.x - 100, (int) Player.y + 250, 200 + Tile.GRIDSIZE, 5);
								
								g.setColor(Color.GREEN);
								g.fillRect((int) Player.x - 100, (int) Player.y + 250, (int) breakTime, 5);
								
								breakTime += 0.03f;
								
								if(breakTime >= 200 + Tile.GRIDSIZE){
									if(Inventory.slots[xx][yy].id == Tile.PalmLog){
										Inventory.slots[xx][yy].amount += 2;
										tiles[x][y - 1].id = Tile.Air;
										tiles[x][y].id = Tile.Air;
									}
									if(Inventory.slots[xx][yy].id == Tile.Air){
										Inventory.slots[xx][yy].id = Tile.PalmLog;
										Inventory.slots[xx][yy].amount += 2;
										tiles[x][y - 1].id = Tile.Air;
										tiles[x][y].id = Tile.Air;
									}
								}
							}
						}
					}
					for(int xx = 0; xx < Inventory.slots.length; xx++){
						for(int yy = 0; yy < Inventory.slots[0].length; yy++){
							if(tiles[x][y].id == Tile.PalmTreeTop && !Engine.rmb){
								g.setColor(Color.BLACK);
								g.fillRect((int) Player.x - 100, (int) Player.y + 250, 200 + Tile.GRIDSIZE, 5);
								
								g.setColor(Color.GREEN);
								g.fillRect((int) Player.x - 100, (int) Player.y + 250, (int) breakTime, 5);
								
								breakTime += 0.3f;
								
								if(breakTime >= 200 + Tile.GRIDSIZE){
									int r = random.nextInt(2);
									if(r == 0){
										if(Inventory.slots[xx][yy].id == Tile.PalmSeeds){
											Inventory.slots[xx][yy].amount += 2;
											tiles[x][y].id = Tile.Air;
											tiles[x][y].id = Tile.Air;
										}
										if(Inventory.slots[xx][yy].id == Tile.Air){
											Inventory.slots[xx][yy].id = Tile.PalmSeeds;
											Inventory.slots[xx][yy].amount += 2;
											tiles[x][y].id = Tile.Air;
											tiles[x][y].id = Tile.Air;
										}
									}else{
										if(Inventory.slots[xx][yy].id == Tile.PalmLeaf){
											Inventory.slots[xx][yy].amount += 2;
											tiles[x][y].id = Tile.Air;
											tiles[x][y].id = Tile.Air;
										}
										if(Inventory.slots[xx][yy].id == Tile.Air){
											Inventory.slots[xx][yy].id = Tile.PalmLeaf;
											Inventory.slots[xx][yy].amount += 2;
											tiles[x][y].id = Tile.Air;
											tiles[x][y].id = Tile.Air;
										}
									}
								}
							}
						}
					}
					for(int xx = 0; xx < Inventory.slots.length; xx++){
						for(int yy = 0; yy < Inventory.slots[0].length; yy++){
							if(tiles[x][y].id == Tile.Sand && !Engine.rmb){
								g.setColor(Color.BLACK);
								g.fillRect((int) Player.x - 100, (int) Player.y + 250, 200 + Tile.GRIDSIZE, 5);
								
								g.setColor(Color.GREEN);
								g.fillRect((int) Player.x - 100, (int) Player.y + 250, (int) breakTime, 5);
								
								breakTime += 0.2f;
								
								if(breakTime >= 200 + Tile.GRIDSIZE){
									if(Inventory.slots[xx][yy].id == Tile.Sand){
										Inventory.slots[xx][yy].amount += 2;
										tiles[x][y].id = Tile.Air;
									}
									if(Inventory.slots[xx][yy].id == Tile.Air){
										Inventory.slots[xx][yy].id = Tile.Sand;
										Inventory.slots[xx][yy].amount += 2;
										tiles[x][y].id = Tile.Air;
									}
								}
							}
						}
					}
					for(int xx = 0; xx < Inventory.slots.length; xx++){
						for(int yy = 0; yy < Inventory.slots[0].length; yy++){
							if(tiles[x][y].id == Tile.Sifter && Engine.rmb && Inventory.slots[xx][yy].isSelected && !sifted){
								if(Inventory.slots[xx][yy].id == Tile.Dirt){
									g.setColor(Color.BLACK);
									g.fillRect((int) Player.x - 100, (int) Player.y + 250, 200 + Tile.GRIDSIZE, 5);
									
									g.setColor(Color.GREEN);
									g.fillRect((int) Player.x - 100, (int) Player.y + 250, (int) breakTime, 5);
									
									breakTime += 2f;
									
									if(breakTime >= 200 + Tile.GRIDSIZE){
										int r = random.nextInt(3);
										System.out.println(r);
										breakTime = 0;
									}
								}
							}
						}
					}
					for(int xx = 0; xx < Inventory.slots.length; xx++){
						for(int yy = 0; yy < Inventory.slots[0].length; yy++){
							if(tiles[x][y].id == Tile.Air){
								breakSpeed = 0;
								breakTime = 0;
							}
						}
					}
					for(int xx = 0; xx < Inventory.slots.length; xx++){
						for(int yy = 0; yy < Inventory.slots[0].length; yy++){
							if(Engine.rmb && Inventory.slots[xx][yy].isSelected){
								if(tiles[x][y].id == Tile.Air && Inventory.slots[xx][yy].id != Tile.PalmLeaf && Inventory.slots[xx][yy].id != Tile.PalmLog && Inventory.slots[xx][yy].id != Tile.Pebble && Inventory.slots[xx][yy].id != Tile.RedGemstone && Inventory.slots[xx][yy].id != Tile.RockPickaxe && Inventory.slots[xx][yy].id != Tile.RockShovel && Inventory.slots[xx][yy].id != Tile.PalmSapling){
									tiles[x][y].id = Inventory.slots[xx][yy].id;
									System.out.println(tiles[x][y].id);
								}
							}
						}
					}
				}
			}
		}
	}

}
