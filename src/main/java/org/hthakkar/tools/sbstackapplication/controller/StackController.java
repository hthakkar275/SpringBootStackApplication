package org.hthakkar.tools.sbstackapplication.controller;

import org.hthakkar.tools.sbstackapplication.domain.DuplicateStackException;
import org.hthakkar.tools.sbstackapplication.domain.EmptyStackException;
import org.hthakkar.tools.sbstackapplication.domain.FullStackException;
import org.hthakkar.tools.sbstackapplication.domain.StackCreateOperation;
import org.hthakkar.tools.sbstackapplication.domain.StackNotExistsException;
import org.hthakkar.tools.sbstackapplication.domain.StackPushOperation;
import org.hthakkar.tools.sbstackapplication.domain.StackResponse;
import org.hthakkar.tools.sbstackapplication.service.StackCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SimpleApp/stack")
public class StackController {

	@Autowired
	StackCollectionService stackCollectionService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/create")
	public StackResponse create(@RequestBody StackCreateOperation stackCreateOperation) {
		StackResponse stackResponse = new StackResponse();
		try {
			this.stackCollectionService.create(stackCreateOperation.getName(), stackCreateOperation.getMaxSize());
			stackResponse.setResult("Stack created");
		} catch (DuplicateStackException e) {
			stackResponse.setResult("Stack already exists");
		} catch (Throwable t) {
			stackResponse.setResult("Unexpected error");
		}
		return stackResponse;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/push")
	public StackResponse push(@RequestBody StackPushOperation stackPushOperation) {
		StackResponse stackResponse = new StackResponse();
		try {
			stackCollectionService.get(stackPushOperation.getName()).push(stackPushOperation.getNumber());
			stackResponse.setResult("Number pushed on to the stack");
			stackResponse.setNumber(stackPushOperation.getNumber());
		} catch (StackNotExistsException snee) {
			stackResponse.setResult("Stack not found");
		} catch (FullStackException e) {
			stackResponse.setResult("Stack is full");
		} catch (Throwable t) {
			stackResponse.setResult("Unexpected error");
		}
		return stackResponse;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/pop")
	public StackResponse pop(@RequestParam("name") String name, @RequestParam("remove") String remove) {
		StackResponse stackResponse = new StackResponse();
		if (name == null || name.trim().length() == 0) {
			stackResponse.setResult("Invalid request. Stack name is not specified");
		} else {
			try {
				if (remove.equals("yes")) {
					stackResponse.setResult("Number popped from stack");
					stackResponse.setNumber(this.stackCollectionService.get(name).pop());
				} else if (remove.equals("no")) {
					stackResponse.setResult("Number peeked from stack");
					stackResponse.setNumber(this.stackCollectionService.get(name).peek());
				} else {
					stackResponse.setResult("Invalid request");
				}
			} catch (StackNotExistsException snee) {
				stackResponse.setResult("Stack not found");
			} catch (EmptyStackException ese) {
				stackResponse.setResult("Stack is empty");
			} catch (Throwable t) {
				stackResponse.setResult("Unexpected error");
			}
		}
		return stackResponse;
	}

}
