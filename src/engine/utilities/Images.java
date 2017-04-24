package engine.utilities;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
	
	public static BufferedImage tilesheet1;
	public static BufferedImage playersheet;
	
	public static BufferedImage slot;
	public static BufferedImage selection;
	public static BufferedImage move_selection;
	
	public static BufferedImage[] tiles = new BufferedImage[18];
	public static BufferedImage[] player = new BufferedImage[4];
	public static BufferedImage[] okButton = new BufferedImage[2];
	
	public Images(){
		try {
			tilesheet1 = ImageIO.read(getClass().getResource("/tiles/tile_sheet.png"));
			playersheet = ImageIO.read(getClass().getResource("/player/player_basic.png"));
			slot = ImageIO.read(getClass().getResource("/hud/empty_slot.png"));
			selection = ImageIO.read(getClass().getResource("/hud/select_box.png"));
			move_selection = ImageIO.read(getClass().getResource("/hud/move_box.png"));
			
			tiles[0] = null;
			tiles[1] = getSprite(tilesheet1, 0, 0, 16);
			tiles[2] = getSprite(tilesheet1, 1, 0, 16);
			tiles[3] = getSprite(tilesheet1, 2, 0, 16);
			tiles[4] = getSprite(tilesheet1, 0, 1, 16);
			tiles[5] = getSprite(tilesheet1, 0, 2, 16);
			tiles[6] = getSprite(tilesheet1, 3, 0, 16);
			tiles[7] = getSprite(tilesheet1, 1, 1, 16);
			tiles[8] = getSprite(tilesheet1, 2, 1, 16);
			tiles[9] = getSprite(tilesheet1, 3, 1, 16);
			tiles[10] = getSprite(tilesheet1, 1, 2, 16);
			tiles[11] = getSprite(tilesheet1, 2, 2, 16);
			tiles[12] = getSprite(tilesheet1, 3, 2, 16);
			tiles[13] = getSprite(tilesheet1, 0, 3, 16);
			tiles[14] = null;
			tiles[15] = getSprite(tilesheet1, 2, 3, 16);
			tiles[16] = getSprite(tilesheet1, 1, 3, 16);
			tiles[16] = getSprite(tilesheet1, 3, 3, 16);
			
			player[0] = getSprite(playersheet, 0, 0, 32);
			player[1] = getSprite(playersheet, 1, 0, 32);
			player[2] = getSprite(playersheet, 2, 0, 32);
			player[3] = getSprite(playersheet, 3, 0, 32);
			
			okButton[0] = ImageIO.read(getClass().getResource("/hud/ok_button.png"));
			okButton[1] = ImageIO.read(getClass().getResource("/hud/ok_button_pressed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(BufferedImage img, int x, int y, int size){
		BufferedImage image = img.getSubimage(x * size, y * size, size, size);
		return image;
	}

}
