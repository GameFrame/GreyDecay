package org.gameframe.greydecay.engine;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class Main {

  private static Game game;
	
	public static void main(String[] args){
		initdisplay();
		initGL();
		initGame();
		
		gameloop();
		
		cleanUp();
	}
	
	private static void gameloop(){
		while(!Display.isCloseRequested()){
			getInput();
			update();
			render();
		}
	}
	
	private static void initGame(){
		game = new Game();
	}
	
	private static void getInput(){
		game.getInput();
	}
	
	private static void update(){
		game.update();
	}
	
	private static void render(){
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
		
		game.render();
		
		Display.update();
		Display.sync(60);
	}
	
	private static void cleanUp(){
		Display.destroy();
		Keyboard.destroy();
	}
	
	private static void initGL(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,Display.getWidth(),0,Display.getHeight(),-1,1);
		glMatrixMode(GL_MODELVIEW);
		
		glDisable(GL_DEPTH_TEST);
		glClearColor(0,0,0,0);
	}
	
	private static void initdisplay(){
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
			Keyboard.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
	}
}
