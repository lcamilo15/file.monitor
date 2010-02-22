/*******************************************************************************
 * Copyright (c) 2010 Philipp Kursawe.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Philipp Kursawe (phil.kursawe@gmail.com) - initial API and implementation
 ******************************************************************************/
package file.monitor.osgi.example.log.internal;

import java.util.concurrent.atomic.AtomicReference;

import org.osgi.service.log.LogService;

import file.monitor.core.FileEvent;
import file.monitor.core.FileListener;

public class FileListenerImpl implements FileListener {
	AtomicReference<LogService> logRef = new AtomicReference<LogService>();
	
	protected void bind(LogService log) {
		logRef.set(log);
	}
	
	protected void unbind(LogService log) {
		logRef.compareAndSet(log, null);
	}
	
	public void fileChanged(final FileEvent e) {
		LogService log = logRef.get();
		if (log != null) {
			log.log(LogService.LOG_DEBUG, e.toString());
		}
	}
}
