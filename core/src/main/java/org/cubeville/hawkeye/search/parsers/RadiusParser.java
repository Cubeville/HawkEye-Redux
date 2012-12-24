/*
 * HawkEye Redux
 * Copyright (C) 2012 Cubeville <http://www.cubeville.org> and contributors
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

package org.cubeville.hawkeye.search.parsers;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.regions.Region;

import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.command.CommandPlayerException;
import org.cubeville.hawkeye.command.CommandSender;
import org.cubeville.hawkeye.command.CommandUsageException;
import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.Vector;
import org.cubeville.hawkeye.search.ParameterParser;
import org.cubeville.util.StringUtil;

public class RadiusParser implements ParameterParser {

	private WorldEdit worldedit;

	public RadiusParser() {
		worldedit = null;

		try {
			Class.forName("com.sk89q.worldedit.WorldEdit");
			worldedit = WorldEdit.getInstance();
		} catch (ClassNotFoundException e) {
		}
	}

	private boolean hasWorldEdit() {
		return worldedit instanceof WorldEdit;
	}

	@Override
	public String process(String parameter, CommandSender sender) throws CommandException {
		if (!sender.isPlayer()) throw new CommandPlayerException();

		if ((parameter.equalsIgnoreCase("we") || parameter.equalsIgnoreCase("worldedit")) && hasWorldEdit()) {
			return processWorldedit(sender);
		}

		Vector center = ((Player) sender).getPosition();
		double radius;

		try {
			radius = Double.parseDouble(parameter);
		} catch (NumberFormatException ex) {
			throw new CommandUsageException("Invalid radius specified: " + parameter);
		}

		Vector r = new Vector(radius, radius, radius);
		Vector min = center.subtract(r);
		Vector max = center.add(r);

		return buildQuery(min, max);
	}

	private String processWorldedit(CommandSender sender) throws CommandException {
		LocalSession session = worldedit.getSession(sender.getName());
		Region region;

		try {
			region = session.getSelection(session.getSelectionWorld());
		} catch (IncompleteRegionException e) {
			throw new CommandException("You do not have a WorldEdit selection.");
		}

		Vector min = toVector(region.getMinimumPoint());
		Vector max = toVector(region.getMaximumPoint());

		return buildQuery(min, max);
	}

	private String buildQuery(Vector min, Vector max) {
		String x;
		String y;
		String z;

		if (min.equals(max)) {
			x = "x = " + min.getBlockX();
			y = "y = " + min.getBlockY();
			z = "z = " + min.getBlockZ();
		} else {
			x = "(x BETWEEN " + min.getX() + " AND " + max.getX() + ")";
			y = "(y BETWEEN " + min.getY() + " AND " + max.getY() + ")";
			z = "(z BETWEEN " + min.getZ() + " AND " + max.getZ() + ")";
		}

		return StringUtil.buildString(" AND ", x, y, z);
	}

	private static Vector toVector(com.sk89q.worldedit.Vector vector) {
		return new Vector(vector.getX(), vector.getY(), vector.getZ());
	}

}
