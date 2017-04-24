package engine.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import engine.hud.Crafting;
import engine.hud.Inventory;
import engine.utilities.Camera;
import engine.utilities.Images;
import objects.player.Player;
import objects.tiles.Tile;
import world.World;

public class Engine extends Canvas implements Runnable, KeyListener, MouseListener{
	
	public JFrame frame;
	
	public Thread thread;
	
	public static boolean running = false;
	
	public int fps, frames;
	
	public static int width = 1280;
	public static int height = 720;
	
	public static int mouseX, mouseY;
	public static int worldMouseX, worldMouseY;
	
	public static int STATE = 0;
	
	public static int Game = 0;
	
	public World world;
	public Images imgs;
	public Camera camera;
	public Inventory inv;
	public Crafting craft;
	
	public static Player player;
	
	public static boolean rmb = false;

	public Engine(){
		System.out.println("Initializing");
	
		imgs = new Images();
		world = new World();
		player = new Player(33 * Tile.GRIDSIZE, 49 * Tile.GRIDSIZE);
		camera = new Camera();
		inv = new Inventory();
		craft = new Crafting();
		frame = new JFrame("Game");
		
		System.out.println("Creating window");
		
		Toolkit tk = this.getToolkit();
		
		int width = tk.getScreenSize().width;
		int height = tk.getScreenSize().height;
		
		this.setPreferredSize(new Dimension(1280, 720));
		
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		frame.add(this, BorderLayout.CENTER);
		this.addKeyListener(this);
		this.addMouseListener(this);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		start();
	}
	
	public static void main(String[] args){
		new Engine();
	}
	
	public synchronized void start(){
		System.out.println("Defining thread");
		
		thread = new Thread(this);
		thread.start();
		
		running = true;
	}

	public void run() {
		System.out.println("Starting game loop");
		long timer = System.currentTimeMillis();
		while(running){
			try {
				thread.sleep(7);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			frames++;
			
			update();
			
			if(System.currentTimeMillis() - timer >= 1000){
				timer += 1000;
				fps = frames;
				frames = 0;
				System.out.println("FPS: " + fps);
			}
		}
		stop();
	}
	
	public synchronized void stop(){
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void update(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics graphics = bs.getDrawGraphics();
		Graphics2D g = (Graphics2D) graphics;
		
		g.setColor(new Color(20, 135, 255));
		g.fillRect(0, 0, width, height);
		
		camera.update();
		
		try{
			if(STATE == Game){
				g.translate(-camera.x, -camera.y);
				world.update(g);
				player.update(g);
				g.translate(camera.x, camera.y);
				inv.update(g);
				craft.update(g);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		graphics.dispose();
		bs.show();
	}
	
	

	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		
		if(k == e.VK_A && Player.action != Player.Occupied){
			player.isMovingLeft = true;
			player.isMovingRight = false;
			player.isIdleLeft = false;
			player.isIdleRight = false;
		}
		if(k == e.VK_D && Player.action != Player.Occupied){
			player.isMovingRight = true;
			player.isMovingLeft = false;
			player.isIdleLeft = false;
			player.isIdleRight = false;
		}
		if(k == e.VK_SPACE && player.canJump && Player.action != Player.Occupied){
			player.y -= 6;
			player.isJumping = true;
		}
		if(k == e.VK_E){
			inv.isOpen = true;
		}
		if(k == e.VK_R){
			craft.isOpen = true;
		}
		if(k == e.VK_M){
			inv.isMoving = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int k = e.getKeyCode();
		
		if(k == e.VK_A && Player.action != Player.Occupied){
			player.isMovingLeft = false;
			player.action = player.IdleLeft;
			player.isIdleLeft = true;
			player.isIdleRight = false;
		}
		if(k == e.VK_D && Player.action != Player.Occupied){
			player.isMovingRight = false;
			player.action = player.IdleRight;
			player.isIdleRight = true;
			player.isIdleLeft = false;
		}
		if(k == e.VK_E){
			inv.isOpen = false;
		}
		if(k == e.VK_R){
			craft.isOpen = false;
		}
		if(k == e.VK_M){
			inv.isMoving = false;
		}
	}

	public void keyTyped(KeyEvent e) {}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		mouseX = x;
		mouseY = y;
		
		worldMouseX = x + camera.x;
		worldMouseY = y + camera.y;
		
		if(SwingUtilities.isRightMouseButton(e)){
			rmb = true;
		}
	}

	public void mouseReleased(MouseEvent e) {
		worldMouseX = 0;
		worldMouseY = 0;
		
		craft.canPressOk = true;
		world.sifted = false;
		
		if(SwingUtilities.isRightMouseButton(e)){
			rmb = false;
		}
	}
	
	
}
