package com.tesserate.game.api.fs;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.tesserate.game.api.sound.Sound;

public class ResourceManager {

	private static Map<String, Resource> resourcesLoaded = new TreeMap<String, Resource>();
	
	public static Resource getResource(String id){
		return resourcesLoaded.get(id);
	}
	
	public static void addResource(Resource resource){
		resourcesLoaded.put(resource.getId(), resource);
	}
	
	public static ImageResource getImageResource(String id){
		return (ImageResource)resourcesLoaded.get(id);
	}
	
	public static Sound getSound(String id){
		return (Sound)resourcesLoaded.get(id);
	}
	
	public static List<String> getTextFiles(String id){
		return ((TextFileResource) resourcesLoaded.get(id)).getLineFile();
	}

	public static void removeResource(String id){
		resourcesLoaded.remove(id);
	}
	
	
}
