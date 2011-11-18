package com.tesserate.game.api.sound;

import com.tesserate.game.api.fs.Resource;
import com.tesserate.game.api.fs.ResourceManager;

/**
    The Sound class is a container for sound samples. The sound
    samples are format-agnostic and are stored as a byte array.
*/
public class Sound extends Resource{
	public static final int WAITING = 0;
	public static final int PLAYING = 1;
    private byte[] samples;
    private int state;

    /**
        Create a new Sound object with the specified byte array.
        The array is not copied.
    */
    public Sound(String id, byte[] samples) {
    	this.id = id;
        this.samples = samples;
        this.state = WAITING;
        ResourceManager.addResource(this);
    }


    /**
        Returns this Sound's objects samples as a byte array.
    */
    public byte[] getSamples() {
        return samples;
    }


	public void setState(int state) {
		this.state = state;
	}


	public int getState() {
		return state;
	}

}
