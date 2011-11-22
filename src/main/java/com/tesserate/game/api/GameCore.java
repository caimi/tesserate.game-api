package com.tesserate.game.api;

import com.tesserate.game.api.sound.SoundManager;
import com.tesserate.game.api.ui.FullScreenDevice;

public abstract class GameCore implements Runnable {
	private long elapsedTime;
	private long lastTime;
	private static boolean paused = false;
	
	public abstract void update(long elapsedTime);
	public abstract void loadResources();

	public GameCore() {
		Thread t = new Thread(this);
		t.start();
		elapsedTime = 0;
		lastTime = 0;
	}
	
	public void init(){
		initFullScreenMode();
		loadResources();
	}
	
	public void render() {
		FullScreenDevice.getInstance().render();
	}
	
	public static void pause(){
		GameCore.paused = !GameCore.paused;
	}
	
	public static boolean isPaused(){
		return GameCore.paused;
	}
	
	public static void initFullScreenMode(){
		FullScreenDevice.getInstance().setController(ControllerDevice.getInstance());
		FullScreenDevice.getInstance().init();
	}
	
	public void run() {
		try {
			init();
			lastTime = System.currentTimeMillis();
			while(true){
				elapsedTime = System.currentTimeMillis() - lastTime;
				lastTime = System.currentTimeMillis();
				if(!GameCore.paused)
					update(elapsedTime);
				render();
				Thread.sleep(25);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}finally{
			SoundManager.getInstance().close();
		}
		
	}
}
