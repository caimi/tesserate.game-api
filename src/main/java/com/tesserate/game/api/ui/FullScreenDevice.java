package com.tesserate.game.api.ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsConfigTemplate;
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
        //if(controller.getMouseListener() == null) throw new IllegalArgumentException("MouseListener n�o pode ser null");
        //mainFrame.addMouseListener(controller.getMouseListener());
        if(controller.getKeyListener() == null) throw new IllegalArgumentException("KeyListener n�o pode ser null");
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
		final DisplayMode[] modes = device.getDisplayModes();
		System.out.println("Available game resolutions:");
		for (final Dimension resolution : AVAILABLE_RESOLUTIONS) {
			System.out.println("width: "+resolution.width+" height "+resolution.height);
		}
		
		System.out.println("Available resolutions on your system:");
		for (int i = 0; i < modes.length; i++) {
			System.out.println("Available resolution: [width: "+ modes[i].getWidth() + " height: " + modes[i].getHeight()+ " bitDepth: " + modes[i].getBitDepth() + " refreshRate: "+modes[i].getRefreshRate()+"]");
		}
	}
    
    private static void chooseBestDisplayMode(final GraphicsDevice device) {
    	final GraphicsConfiguration bestConfiguration = device.getBestConfiguration(new GraphicsConfigTemplate() {
			@Override
			public boolean isGraphicsConfigSupported(final GraphicsConfiguration gc) {return true;}
			
			@Override
			public GraphicsConfiguration getBestConfiguration(final GraphicsConfiguration[] gc) {
				for (final GraphicsConfiguration graphicsConfiguration : gc) {
					if(isGCValid(graphicsConfiguration)){
						return graphicsConfiguration;
					}
				}
				return null;
			}

			private boolean isGCValid(final GraphicsConfiguration graphicsConfiguration) {
				for (final Dimension resolution : AVAILABLE_RESOLUTIONS) {
					if(graphicsConfiguration.getBounds().getSize().equals(resolution)){
						return true;
					}
		        }
				return false;
			}
		});
    	
    	if(bestConfiguration == null){
    		dumpResolutionInfo(device);
    		throw new RuntimeException("Could not set fullscreen");
    	}
    	
    	final GraphicsDevice bestConfigDevice = bestConfiguration.getDevice();
		final DisplayMode displayMode = bestConfigDevice.getDisplayMode();
    	if (displayMode != null) {
    		device.setDisplayMode(displayMode);
    		width = displayMode.getWidth();
    		height = displayMode.getHeight();
    	}
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
