package com.tesserate.game.api;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

public class ControllerDevice {
	private static ControllerDevice instance;
	private KeyAdapter keyListener;
	private MouseAdapter mouseListener;

	public static ControllerDevice getInstance() {
		if(instance == null){
			instance = new ControllerDevice();
			instance.init();
		}
		return instance;
	}
	
	public void init(){
		this.keyListener = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
				if(e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_SPACE) {
					GameCore.pause();
				}
			}
		};
	}
	
	public KeyAdapter getKeyListener() {
		return keyListener;
	}


	public void setKeyListener(KeyAdapter keyListener) {
		this.keyListener = keyListener;
	}


	public MouseAdapter getMouseListener() {
		return mouseListener;
	}


	public void setMouseListener(MouseAdapter mouseListener) {
		this.mouseListener = mouseListener;
	}
}
