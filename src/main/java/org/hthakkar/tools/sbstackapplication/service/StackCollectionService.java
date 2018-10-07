package org.hthakkar.tools.sbstackapplication.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hthakkar.tools.sbstackapplication.domain.DuplicateStackException;
import org.hthakkar.tools.sbstackapplication.domain.StackNotExistsException;
import org.springframework.stereotype.Service;

@Service("stackCollectionService")
public class StackCollectionService {

	private Map<String, StackService> stacks = new ConcurrentHashMap<>();
	
	public void create(String name, int maxSize) throws DuplicateStackException {
		if (stacks.containsKey(name)) {
			throw new DuplicateStackException("Stack already exists");
		}
		stacks.put(name, new StackService(maxSize));
	}
	
	public StackService get(String name) throws StackNotExistsException {
		if (!stacks.containsKey(name)) {
			throw new StackNotExistsException("Stack not found");
		}
		return stacks.get(name);
	}
}
