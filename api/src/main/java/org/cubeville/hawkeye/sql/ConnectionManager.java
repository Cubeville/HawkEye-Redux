/*
 * GNU.FREE 2002
 *
 * Copyright (c) 1999, 2000, 2001, 2002
 * The Free Software Foundation (www.fsf.org)
 *
 * GNU.FREE Co-ordinator: Jason Kitcat <jeep@free-project.org>
 *
 * GNU site: http://www.gnu.org/software/free/
 *
 * FREE e-democracy site: http://www.free-project.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program (COPYING); if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */

package org.cubeville.hawkeye.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

/**
 * The class that creates the pool of Connections for use by the driver
 *
 * Code originates from Java Developer Connection.
 */
public class ConnectionManager {

	/**
	 * Database connection info
	 */
	private final String url, user, password;

	/**
	 * Connection pool
	 */
	private final Vector<JDCConnection> connections;

	/**
	 * Reaper to remove old connections
	 */
	private final ConnectionReaper reaper;

	/**
	 * How long to allow a hold on a connection
	 */
	private final long timeout = 60000;

	/**
	 * Maximum number of connections
	 */
	private final int poolsize = 10;

	public ConnectionManager(String url, String user, String password) throws SQLException {
		this.url = url;
		this.user = user;
		this.password = password;
		connections = new Vector<JDCConnection>(poolsize);
		reaper = new ConnectionReaper();
		reaper.start();

		// Try to open a connection to make sure we can connect
		getConnection().close();
	}

	/**
	 * Attempts to get a connection from the pool
	 *
	 * @return Database connection
	 * @throws SQLException
	 */
	public synchronized Connection getConnection() throws SQLException {
		 JDCConnection conn;

		 for(int i = 0; i < connections.size(); i++) {
			  conn = connections.elementAt(i);
			  if (conn.lease()) {
				  return conn;
			  }
		 }

		 // No connections available, establish a new one
		 Connection newConn = DriverManager.getConnection(url, user, password);
		 conn = new JDCConnection(newConn, this);
		 conn.lease();
		 connections.addElement(conn);
		 return conn;
	}

	/**
	 * Releases all connections from the pool
	 */
	protected synchronized void closeConnections() {
		Enumeration<JDCConnection> connlist = connections.elements();

		while (connlist != null && connlist.hasMoreElements()) {
			 JDCConnection conn = connlist.nextElement();
			 removeConnection(conn);
		}
	}

	/**
	 * Removes the lease on a connection
	 *
	 * @param conn Connection to remove the lease from
	 */
	protected synchronized void returnConnection(JDCConnection conn) {
		conn.expireLease();
	}

	/**
	 * Releases expired connections from the pool
	 */
	private synchronized void reapConnections() {
		long stale = System.currentTimeMillis() - timeout;

		Enumeration<JDCConnection> connlist = connections.elements();

		while (connlist != null && connlist.hasMoreElements()) {
			JDCConnection conn = connlist.nextElement();

			if (conn.inUse() && stale > conn.getLastUse() && !conn.validate()) {
				removeConnection(conn);
			}
		}
	}

	/**
	 * Removes a connection from the pool
	 *
	 * @param conn Connection to remove
	 */
	private synchronized void removeConnection(JDCConnection conn) {
		 connections.removeElement(conn);
	}

	/**
	 * Connection reaper to periodically remove old connections
	 */
	private class ConnectionReaper extends Thread {
		/**
		 * How often to reap old connections (300,000 milliseconds = 5 minutes)
		 */
		private final long delay = 300000;

		@Override
		public void run() {
			while (true) {
				try {
					sleep(delay);
				} catch (InterruptedException ignore) { }

				reapConnections();
			}
		}
	}

}
