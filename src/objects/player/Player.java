package objects.player;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import engine.utilities.Images;
import objects.tiles.Tile;
import world.World;

public class Player {
	
	public static float x, y, action;
	
	public static float velX, velY, velocity;
	
	public static float speed = 3.5f;
	
	public static int IdleRight = 0;
	public static int IdleLeft = 1;
	public static int WalkRight = 2;
	public static int WalkLeft = 3;
	public static int JumpRight = 4;
	public static int JumpLeft = 5;
	public static int Occupied = 6;
	
	public static boolean isMovingLeft, isMovingRight, isJumping, isFalling, canJump, isCollidingLeft, isCollidingRight, isIdleLeft, isIdleRight;
	
	public int frames = 0;
	
	public Player(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void update(Graphics2D g){
		if(action == IdleRight){
			g.drawImage(Images.player[0], (int) x, (int) y, Tile.GRIDSIZE, Tile.GRIDSIZE, null);
		}
		if(action == IdleLeft){
			g.drawImage(Images.player[3], (int) x, (int) y, Tile.GRIDSIZE, Tile.GRIDSIZE, null);
		}
		if(action == WalkRight){
			frames++;
			if(frames <= 20) g.drawImage(Images.player[0], (int) x, (int) y, Tile.GRIDSIZE, Tile.GRIDSIZE, null);
			if(frames >= 20 && frames <= 40) g.drawImage(Images.player[1], (int) x, (int) y, Tile.GRIDSIZE, Tile.GRIDSIZE, null);
			if(frames >= 40) frames = 0;
		}
		if(action == WalkLeft){
			frames++;
			if(frames <= 20) g.drawImage(Images.player[3], (int) x, (int) y, Tile.GRIDSIZE, Tile.GRIDSIZE, null);
			if(frames >= 20 && frames <= 40) g.drawImage(Images.player[2], (int) x, (int) y, Tile.GRIDSIZE, Tile.GRIDSIZE, null);
			if(frames >= 40) frames = 0;
		}
		if(action == JumpRight){
			g.drawImage(Images.player[1], (int) x, (int) y, Tile.GRIDSIZE, Tile.GRIDSIZE, null);
		}
		if(action == JumpLeft){
			g.drawImage(Images.player[2], (int) x, (int) y, Tile.GRIDSIZE, Tile.GRIDSIZE, null);
		}
		
		if(isMovingLeft && !isCollidingLeft){
			if(!isJumping && !isFalling) action = WalkLeft;
			velX = -speed;
		}else if(isMovingRight && !isCollidingRight){
			if(!isJumping && !isFalling) action = WalkRight;
			velX = speed;
		}else{
			velX = 0;
		}
		
		if(isJumping){
			velocity = -3;
			if(isMovingLeft || isIdleLeft){
				action = JumpLeft;
			}
			if(isMovingRight || isIdleRight){
				action = JumpRight;
			}
		}
		
		if(isFalling){
			isJumping = false;
			velocity += 0.05f;
			if(isMovingLeft || isIdleLeft){
				action = JumpLeft;
			}
			if(isMovingRight || isIdleRight){
				action = JumpRight;
			}
		}
		
		x += velX;
		y += velY;
		
		velY = velocity;
		
		collision();
	}
	
	public void collision(){
		for(int x = 0; x < World.tiles.length; x++){
			for(int y = 0; y < World.tiles[0].length; y++){
				if(World.tiles[x][y].intersects(bottomBox()) && World.tiles[x][y].id != Tile.Air && World.tiles[x][y].id != Tile.PalmTreeBottom && World.tiles[x][y].id != Tile.PalmTreeTop){
					isFalling = false;
					isJumping = false;
					canJump = true;
					velY = 0;
				}
				if(World.tiles[x][y].intersects(bottomBox()) && World.tiles[x][y].id == Tile.Air){
					isFalling = true;
					canJump = false;
				}
				if(World.tiles[x][y].intersects(leftBox()) && World.tiles[x][y].id != Tile.Air && World.tiles[x][y].id != Tile.PalmTreeBottom && World.tiles[x][y].id != Tile.PalmTreeTop){
					isCollidingLeft = true;
				}
				if(World.tiles[x][y].intersects(leftBox()) && (World.tiles[x][y].id == Tile.Air || World.tiles[x][y].id == Tile.PalmTreeBottom || World.tiles[x][y].id == Tile.PalmTreeTop)){
					isCollidingLeft = false;
				}
				if(World.tiles[x][y].intersects(rightBox()) && World.tiles[x][y].id != Tile.Air && World.tiles[x][y].id != Tile.PalmTreeBottom && World.tiles[x][y].id != Tile.PalmTreeTop){
					isCollidingRight = true;
				}
				if(World.tiles[x][y].intersects(rightBox()) && (World.tiles[x][y].id == Tile.Air || World.tiles[x][y].id == Tile.PalmTreeBottom || World.tiles[x][y].id == Tile.PalmTreeTop)){
					isCollidingRight = false;
				}
				if(World.tiles[x][y].intersects(topBox()) && World.tiles[x][y].id != Tile.Air && World.tiles[x][y].id != Tile.PalmTreeBottom && World.tiles[x][y].id != Tile.PalmTreeTop){
					velocity = 1;
					isFalling = true;
				}
				if(World.tiles[x][y].intersects(topBox()) && (World.tiles[x][y].id == Tile.Air || World.tiles[x][y].id == Tile.PalmTreeBottom || World.tiles[x][y].id == Tile.PalmTreeTop)){

				}
			}
		}
	}
	
	public Rectangle bottomBox(){
		return new Rectangle((int) x + 10, (int) (y + Tile.GRIDSIZE) - 10, Tile.GRIDSIZE - 20, 10);
	}
	
	public Rectangle topBox(){
		return new Rectangle((int) x + 10, (int) y, Tile.GRIDSIZE - 20, 10);
	}
	
	public Rectangle leftBox(){
		return new Rectangle((int) x - 5, (int) y + 5, 10, Tile.GRIDSIZE - 10);
	}
	
	public Rectangle rightBox(){
		return new Rectangle((int) x + (Tile.GRIDSIZE - 10), (int) y + 5, 10, Tile.GRIDSIZE - 10);
	}
}
