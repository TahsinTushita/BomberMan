package com.sust.bomberman.graphics;

public class Sprite {
	
	public final int SIZE;
	private int x,y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16,0,0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite bomb = new Sprite(32,0,0,SpriteSheet.tiles);
	public static Sprite bomb_1 = new Sprite(32,1,0,SpriteSheet.tiles);
	public static Sprite explosion = new Sprite(32,0,2,SpriteSheet.tiles);
	public static Sprite explosion_center = new Sprite(32,1,2,SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16,0x1B87E0);

	public static Sprite player_side = new Sprite(32, 1, 6 , SpriteSheet.tiles);
	public static Sprite player_forward = new Sprite(32, 1, 4 , SpriteSheet.tiles);
	public static Sprite player_back = new Sprite(32, 1, 7, SpriteSheet.tiles);

	public static Sprite enemy_side = new Sprite(32, 4, 6 , SpriteSheet.tiles);
	public static Sprite enemy_forward = new Sprite(32, 4, 4 , SpriteSheet.tiles);
	public static Sprite enemy_back = new Sprite(32, 4, 7, SpriteSheet.tiles);
	
	public static Sprite player_forward_1 = new Sprite(32, 0, 4, SpriteSheet.tiles);
	public static Sprite player_forward_2 = new Sprite(32, 2, 4, SpriteSheet.tiles);
	public static Sprite player_back_1 = new Sprite(32, 0, 7, SpriteSheet.tiles);
	public static Sprite player_back_2 = new Sprite(32, 2, 7, SpriteSheet.tiles);
	public static Sprite player_side_1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_side_2 = new Sprite(32, 2, 6, SpriteSheet.tiles);
	
	public static Sprite enemy_forward_1 = new Sprite(32, 3, 4, SpriteSheet.tiles);
	public static Sprite enemy_forward_2 = new Sprite(32, 5, 4, SpriteSheet.tiles);
	public static Sprite enemy_back_1 = new Sprite(32, 3, 7, SpriteSheet.tiles);
	public static Sprite enemy_back_2 = new Sprite(32, 5, 7, SpriteSheet.tiles);
	public static Sprite enemy_side_1 = new Sprite(32, 3, 6, SpriteSheet.tiles);
	public static Sprite enemy_side_2 = new Sprite(32, 5, 6, SpriteSheet.tiles);
	
	
	public static Sprite spawn_grass=new Sprite(16,0,0,SpriteSheet.spawn);
	public static Sprite spawn_wall1=new Sprite(16,2,0,SpriteSheet.spawn);
	public static Sprite spawn_wall2=new Sprite(16,1,1,SpriteSheet.spawn);
        public static Sprite spawn_wall3=new Sprite(16,1,2,SpriteSheet.spawn);
	public static Sprite spawn_hedge=new Sprite(16,2,1,SpriteSheet.spawn);
	public static Sprite spawn_water=new Sprite(16,0,1,SpriteSheet.spawn);
	public static Sprite spawn_floor=new Sprite(16,1,0,SpriteSheet.spawn);
	
	
	public Sprite(int size,int x,int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		this.x = x*size;
		this.y = y*size;
		this.sheet = sheet;
		load();
	}
	public Sprite(int size,int color) {
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		setColor(color);
	}
	
	private void setColor(int color) {
		for(int i = 0;i < pixels.length;++i) {
			pixels[i] = color;
		}
	}
	
	private void load() {
		for(int y=0;y<SIZE;++y) {
			for(int x=0;x<SIZE;++x) {
				pixels[x+y*SIZE]= sheet.pixels[(x + this.x) + (y + this.y)*sheet.SIZE ];
			}
		}
	}
}
