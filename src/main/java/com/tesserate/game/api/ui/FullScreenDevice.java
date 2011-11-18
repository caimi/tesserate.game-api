package com.tesserate.game.api.ui;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.tesserate.game.api.ControllerDevice;

public class FullScreenDevice {
	private static FullScreenDevice instance;
	private JFrame mainFrame;
	private BufferStrategy bufferStrategy;
	private GraphicsDevice device;
	private ControllerDevice controller;
	
    private static DisplayMode[] BEST_DISPLAY_MODES = new DisplayMode[] {
    	new DisplayMode(800, 600, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
        new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN),
        new DisplayMode(1440, 900, 32, DisplayMode.REFRESH_RATE_UNKNOWN),
        new DisplayMode(800, 600, 8, DisplayMode.REFRESH_RATE_UNKNOWN)
    };
    
	public FullScreenDevice(){
		
	}

	public static FullScreenDevice getInstance() {
		if(instance == null){
			instance = new FullScreenDevice();
		}
		return instance;
	}
	
    
    public void init(){
    	GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	device = env.getDefaultScreenDevice();
        GraphicsConfiguration gc = device.getDefaultConfiguration();
        mainFrame = new JFrame(gc);
        mainFrame.setUndecorated(true);
        mainFrame.setIgnoreRepaint(true);
        //if(controller.getMouseListener() == null) throw new IllegalArgumentException("MouseListener não pode ser null");
        //mainFrame.addMouseListener(controller.getMouseListener());
        if(controller.getKeyListener() == null) throw new IllegalArgumentException("KeyListener não pode ser null");
        mainFrame.addKeyListener(controller.getKeyListener());
        device.setFullScreenWindow(mainFrame);
        if (device.isDisplayChangeSupported()) {
            chooseBestDisplayMode(device);
        }
		mainFrame.createBufferStrategy(2);
        bufferStrategy = mainFrame.getBufferStrategy();
    }
    
    public void render(){
		Graphics2D g = this.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(new Color(255,94,55));
		g.fillRect(0, 0, 800, 600);
		SceneGraph.getInstance().render(g);
		g.dispose();
		bufferStrategy.show();
    }

    public Graphics2D getGraphics(){
    	if(bufferStrategy.contentsLost()){
    		 bufferStrategy = mainFrame.getBufferStrategy();
    	}
    	return (Graphics2D)bufferStrategy.getDrawGraphics();
    }
    
    private static void chooseBestDisplayMode(GraphicsDevice device) {
    	DisplayMode best = getBestDisplayMode(device);
    	if (best != null) {
    		device.setDisplayMode(best);
    	}
    }

    private static DisplayMode getBestDisplayMode(GraphicsDevice device) {
        for (int x = 0; x < BEST_DISPLAY_MODES.length; x++) {
            DisplayMode[] modes = device.getDisplayModes();
            for (int i = 0; i < modes.length; i++) {
                if (modes[i].getWidth() == BEST_DISPLAY_MODES[x].getWidth()
                   && modes[i].getHeight() == BEST_DISPLAY_MODES[x].getHeight()
                   && modes[i].getBitDepth() == BEST_DISPLAY_MODES[x].getBitDepth()
                   ) {
                    return BEST_DISPLAY_MODES[x];
                }
            }
        }
        return null;
    }
    
    public void restoreScreen() {
        Window window = device.getFullScreenWindow();
        if (window != null) {
            window.dispose();
        }
        device.setFullScreenWindow(null);
        System.exit(0);
    }


	public void setController(ControllerDevice controller) {
		this.controller = controller;
	}

	public ControllerDevice getController() {
		return controller;
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
}
