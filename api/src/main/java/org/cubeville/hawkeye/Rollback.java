/*
 * HawkEye Redux
 * Copyright (C) 2012-2013 Cubeville <http://www.cubeville.org> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.cubeville.hawkeye;

import java.util.Iterator;

import org.cubeville.hawkeye.model.Entry;
import org.cubeville.hawkeye.model.Modifiable;
import org.cubeville.hawkeye.session.Session;

public class Rollback implements Runnable {

	private final Session session;
	private final Iterator<Entry> queue;

	private final int taskId;

	// TODO Make limit configurable
	private final int limit = 200;

	private int count = 0;

	public Rollback(Session session) {
		this.session = session;
		queue = session.getSearchResults().iterator();

		if (!queue.hasNext()) {
			// TODO Message user
			taskId = -1;
			return;
		}

		session.setAttribute(Session.Attribute.WORKING, true);
		taskId = HawkEye.getServerInterface().scheduleRepeatingTask(1L, 1L, this);
	}

	@Override
	public void run() {
		int i = 0;

		while(i < limit && queue.hasNext()) {
			Entry e = queue.next();
			if (!(e instanceof Modifiable)) continue;
			Modifiable entry = (Modifiable) e;

			// TODO Rebuilds and previews
			try {
				if (entry.rollback()) count++;
			} catch (UnsupportedOperationException ignore) { }

			i++;
		}

		if (!queue.hasNext()) {
			HawkEye.getServerInterface().cancelTask(taskId);
			session.setAttribute(Session.Attribute.WORKING, false);
		}
	}

}
