package com.shooterland.framework;

import java.util.*;

import android.graphics.Canvas;

public class EntityManager 
{
	private ArrayList<AbstractEntity> _entities = new ArrayList<AbstractEntity>();
	
	public EntityManager()
	{
		
	}
	
	public void add(AbstractEntity entity)
	{
		_entities.add(entity);
	}
	
	public void update(float dt)
	{
		for (AbstractEntity e : _entities)
		{
			e.update(dt);
		}
	}
	
	public void draw(Canvas canvas, float dt)
	{
		for (AbstractEntity e : _entities)
		{
			e.draw(canvas, dt);
		}
	}
}
