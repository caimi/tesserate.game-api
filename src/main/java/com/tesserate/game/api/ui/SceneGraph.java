package com.tesserate.game.api.ui;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SceneGraph {
	private static SceneGraph instance;
	
	private SceneGraph(){}
	private static List<Renderable> scene;
	
	public static SceneGraph getInstance() {
		if(instance == null){
			instance = new SceneGraph();
			scene = Collections.synchronizedList(new ArrayList<Renderable>());

		}
		return instance;
	}
	
	public void render(Graphics2D g){
		for (Renderable r : scene) {
			r.render(g);
		}
	}
	
	public void add(Renderable r){
		scene.add(r);
	}
	
	public void reset(){
		instance = null;
	}
	
}
