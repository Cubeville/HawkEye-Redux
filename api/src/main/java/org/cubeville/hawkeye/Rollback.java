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

import java.util.List;

import org.cubeville.hawkeye.model.Entry;
import org.cubeville.hawkeye.model.Modifiable;
import org.cubeville.hawkeye.session.Session;

public class Rollback implements Runnable {

	private final Session session;
	private final List<Entry> results;

	private final int limit = 200;

	private int count = 0;

	public Rollback(Session session) {
		this.session = session;
		results = session.getSearchResults();
		// TODO Make limit configurable
	}

	@Override
	public void run() {
		int i = 0;

		while(i < limit && count < results.size()) {
			Entry e = results.get(count);
			if (!(e instanceof Modifiable)) continue;
			Modifiable entry = (Modifiable) e;

			// TODO Rebuilds and previews
			entry.rollback();

			count++;
			i++;
		}

		if (count == results.size()) {
			// TODO Finish it
		}
	}

}
