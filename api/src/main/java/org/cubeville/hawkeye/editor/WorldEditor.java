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

package org.cubeville.hawkeye.editor;

import java.util.Iterator;
import java.util.List;

import org.cubeville.hawkeye.HawkEye;
import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.model.Entry;
import org.cubeville.hawkeye.model.Modifiable;
import org.cubeville.hawkeye.session.Session;

public abstract class WorldEditor implements Runnable {

	private final Type type;
	private final Iterator<Entry> queue;
	private int taskId;

	protected final Session session;

	protected int count = 0;
	private static int limit;
	private static int perTick;

	public WorldEditor(Type type, Session session, List<Entry> results) {
		if (type.isPreview() && !session.getOwner().isPlayer()) {
			throw new UnsupportedOperationException("A player is required for previews.");
		}

		if (limit != -1 && results.size() > limit) {
			throw new UnsupportedOperationException("Too many entries to rollback.");
		}

		this.type = type;
		this.session = session;
		queue = results.iterator();
		taskId = -1;
	}

	public final boolean isRunning() {
		return taskId != -1;
	}

	public final void start() {
		if (isRunning()) return;

		session.setAttribute(Session.Attribute.WORKING, true);
		taskId = HawkEye.getServerInterface().scheduleSyncRepeatingTask(1L, 1L, this);

		onStart();
	}

	public final void stop() {
		if (!isRunning()) return;
		HawkEye.getServerInterface().cancelTask(taskId);
		session.setAttribute(Session.Attribute.WORKING, false);
		taskId = -1;
	}

	@Override
	public final void run() {
		int i = 0;

		while (i < perTick && queue.hasNext()) {
			Entry e = queue.next();
			if (!(e instanceof Modifiable)) continue;
			Modifiable entry = (Modifiable) e;

			try {
				switch (type) {
				case ROLLBACK:
					if (entry.rollback()) count++;
					break;
				case PREVIEW_ROLLBACK:
					if (entry.localRollback((Player) session.getOwner())) count++;
					break;
				case REBUILD:
					if (entry.rebuild()) count++;
					break;
				case PREVIEW_REBUILD:
					if (entry.localRebuild((Player) session.getOwner())) count++;
					break;
				}
			} catch (UnsupportedOperationException ignore) { }

			i++;
		}

		if (!queue.hasNext()) {
			stop();
			onComplete();
		}
	}

	public abstract void onStart();

	public abstract void onComplete();

	public enum Type {
		/**
		 * Global rollback
		 */
		ROLLBACK,
		/**
		 * Local rollback
		 */
		PREVIEW_ROLLBACK,
		/**
		 * Global rebuild
		 */
		REBUILD,
		/**
		 * Local rebuild
		 */
		PREVIEW_REBUILD;

		private boolean isPreview() {
			return this == PREVIEW_ROLLBACK || this == PREVIEW_REBUILD;
		}
	}

	public static void setLimit(int limit) {
		WorldEditor.limit = limit;
	}

	public static void setTickLimit(int perTick) {
		if (perTick == -1) perTick = Integer.MAX_VALUE;
		WorldEditor.perTick = perTick;
	}

}
