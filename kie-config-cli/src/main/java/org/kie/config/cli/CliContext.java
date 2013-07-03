/*
 * Copyright 2013 JBoss by Red Hat.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.config.cli;

import java.util.Scanner;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class CliContext {

	private Weld weld;
	private WeldContainer container;
	private Scanner input;
	
	protected CliContext(Weld weld, WeldContainer container, Scanner input) {
		this.weld = weld;
		this.container = container;
		this.input = input;
	}
	public Weld getWeld() {
		return weld;
	}
	public void setWeld(Weld weld) {
		this.weld = weld;
	}
	public WeldContainer getContainer() {
		return container;
	}
	public void setContainer(WeldContainer container) {
		this.container = container;
	}
	public Scanner getInput() {
		return input;
	}
	public void setInput(Scanner input) {
		this.input = input;
	}
	
	public static CliContext buildContext(Scanner input) {
		
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		
		CliContext context = new CliContext(weld, container, input);
		
		return context;
	}
	
}
