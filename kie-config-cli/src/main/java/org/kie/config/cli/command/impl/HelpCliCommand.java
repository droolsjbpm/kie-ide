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
package org.kie.config.cli.command.impl;

import org.kie.config.cli.CliContext;
import org.kie.config.cli.command.CliCommand;

public class HelpCliCommand implements CliCommand {

	@Override
	public String getName() {
		return "help";
	}

	@Override
	public String execute(CliContext context) {
		StringBuffer helpMessage = new StringBuffer();
		helpMessage.append("****************** KIE Cli Help ************************\n");
		helpMessage.append("********************************************************\n");
		helpMessage.append("Available commands:\n");
		helpMessage.append("\t exit - quits this command line tool\n");
		helpMessage.append("\t help - prints this message\n");
		helpMessage.append("\t list-repo - list available repositories\n");
		helpMessage.append("\t list-group - list available groups\n");
		helpMessage.append("\t list-deployment - list available deployments\n");
		helpMessage.append("\t create-group - creates new group\n");
		helpMessage.append("\t remove-group - remove existing group\n");
		helpMessage.append("\t add-deployment - add new deployment unit\n");
		helpMessage.append("\t remove-deployment - remove existing deployment\n");
		helpMessage.append("\t create-repo - creates new git repository\n");
		helpMessage.append("\t remove-repo - remove existing repository from config only\n");
		helpMessage.append("\t add-repo-group - add repository to the group\n");
		helpMessage.append("\t remove-repo-group - remove repository from the group\n");
		return helpMessage.toString();
	}

}
