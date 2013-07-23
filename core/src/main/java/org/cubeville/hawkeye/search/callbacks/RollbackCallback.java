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

package org.cubeville.hawkeye.search.callbacks;

import java.util.List;

import org.cubeville.hawkeye.editor.Rollback;
import org.cubeville.hawkeye.model.Entry;
import org.cubeville.hawkeye.session.Session;
import org.cubeville.util.Chat;

public class RollbackCallback extends AbstractCallback {

	public RollbackCallback(Session session) {
		super(session);
	}

	@Override
	public void execute(List<Entry> results) {
		super.execute(results);

		if (results.size() == 0) {
			session.sendMessage(Chat.RED + "No records found to rollback.");
			return;
		}

		new Rollback(session).start();
	}

}
