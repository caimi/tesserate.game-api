package com.tesserate.game.api.ui;
import java.awt.Color;
import java.awt.Dimension;
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
	private static int height; 
	private static int width; 
    
    private static final Dimension[] AVAILABLE_RESOLUTIONS = new Dimension[]{
    		new Dimension(1280,1024),
    		new Dimension(1280,800),
    		new Dimension(1440,900),
    		new Dimension(800,600)
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
    	final GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	device = env.getDefaultScreenDevice();
        final GraphicsConfiguration gc = device.getDefaultConfiguration();
        mainFrame = new JFrame(gc);
        mainFrame.setUndecorated(true);
        mainFrame.setIgnoreRepaint(true);
        //if(controller.getMouseListener() == null) throw new IllegalArgumentException("MouseListener nï¿½o pode ser null");
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
		final Graphics2D g = this.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(new Color(255,94,55));
		g.fillRect(0, 0, width, height);
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
    
	private static void dumpResolutionInfo(final GraphicsDevice device) {
		System.out.println("Available game resolutions:");
		for (final Dimension resolution : AVAILABLE_RESOLUTIONS) {
			System.out.println("width: "+resolution.width+" height "+resolution.height);
		}
		
		final DisplayMode[] modes = device.getDisplayModes();
		System.out.println("Available resolutions on your system:");
		for (DisplayMode displayMode : modes) {
			System.out.println("Available resolution: [width: "+ displayMode.getWidth() + " height: " + displayMode.getHeight()+ " bitDepth: " + displayMode.getBitDepth() + " refreshRate: "+displayMode.getRefreshRate()+"]");
		}
	}
    
    private static void chooseBestDisplayMode(final GraphicsDevice device) {
		DisplayMode bestDisplayMode = getBestDisplayModeForDeviceOrNull(device);
		
    	if(bestDisplayMode == null){
    		dumpResolutionInfo(device);
    		throw new RuntimeException("Could not set fullscreen");
    	}
    	
		setDisplayModeOnDevice(bestDisplayMode, device);
    }

	private static DisplayMode getBestDisplayModeForDeviceOrNull(final GraphicsDevice device) {
		DisplayMode bestDisplayMode = null;
		final DisplayMode[] modes = device.getDisplayModes();
		for (DisplayMode displayMode : modes) {
			for (final Dimension availableResolution : AVAILABLE_RESOLUTIONS) {
				final Dimension displayModeResolution = new Dimension(displayMode.getWidth(),displayMode.getHeight());
				final boolean displayModeIsCompatible = displayModeResolution.equals(availableResolution);
				if(displayModeIsCompatible){
					bestDisplayMode = getBestDisplayMode(bestDisplayMode,displayMode);
				}
			}
		}
		return bestDisplayMode;
	}

	private static void setDisplayModeOnDevice(DisplayMode bestDisplayMode,
			final GraphicsDevice device) {
		device.setDisplayMode(bestDisplayMode);
		width = bestDisplayMode.getWidth();
		height = bestDisplayMode.getHeight();
	}
    
    private static DisplayMode getBestDisplayMode(DisplayMode oldDisplayMode, DisplayMode newDisplayMode) {
		if(oldDisplayMode == null)
			return newDisplayMode;
		boolean newWidthIsBigger = oldDisplayMode.getWidth() <  newDisplayMode.getWidth();
		if(newWidthIsBigger){
			return newDisplayMode;
		}
		boolean newWidthIsTheSame = oldDisplayMode.getWidth() ==  newDisplayMode.getWidth();
		if(newWidthIsTheSame){
			if(oldDisplayMode.getBitDepth() <  newDisplayMode.getBitDepth())
				return newDisplayMode;
		}
		return oldDisplayMode;
	}

	public void restoreScreen() {
        final Window window = device.getFullScreenWindow();
        if (window != null) {
            window.dispose();
        }
        device.setFullScreenWindow(null);
        System.exit(0);
    }


	public void setController(final ControllerDevice controller) {
		this.controller = controller;
	}

	public ControllerDevice getController() {
		return controller;
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(final JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public static int getHeight() {
		return height;
	}

	public static int getWidth() {
		return width;
	}
}
