package com.shooterland.framework;

import java.util.ArrayList;

import android.graphics.Canvas;

public class EntityManager 
{
	private ArrayList<AbstractEntity> _entities = new ArrayList<AbstractEntity>();
	
	public void add(AbstractEntity entity)
	{
		_entities.add(entity);
	}
	
	public void update(float dt)
	{
		ArrayList<AbstractEntity> entitiesToRemove = new ArrayList<AbstractEntity>();
		
		for (AbstractEntity e : _entities)
		{
			e.update(dt);
			if (e.isDead())
				entitiesToRemove.add(e);
		}
		
		for (AbstractEntity e : entitiesToRemove)
		{
			_entities.remove(e);
		}
	}
	
	public void draw(Canvas canvas)
	{
		for (AbstractEntity e : _entities)
		{
			e.draw(canvas);
		}
	}
	
	public void clear()
	{
		_entities.clear();
	}
}
