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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.cubeville.util.Pair;
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
	public Pair<String, Map<String, Object>> process(List<String> parameters, CommandSender sender) throws CommandException {
		if (!sender.isPlayer()) throw new CommandPlayerException();
		if (parameters.size() > 1) throw new CommandUsageException("Invalid radius specified: &7" + StringUtil.buildString(parameters, ","));

		String parameter = parameters.get(0);

		if ((parameter.equalsIgnoreCase("we") || parameter.equalsIgnoreCase("worldedit")) && hasWorldEdit()) {
			return processWorldedit(sender);
		}

		Vector center = ((Player) sender).getPosition();
		double radius;

		try {
			radius = Double.parseDouble(parameter);
		} catch (NumberFormatException ex) {
			throw new CommandUsageException("Invalid radius specified: &7" + parameter);
		}

		Vector r = new Vector(radius, radius, radius);
		Vector min = center.subtract(r);
		Vector max = center.add(r);

		return buildQuery(min, max);
	}

	private Pair<String, Map<String, Object>> processWorldedit(CommandSender sender) throws CommandException {
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

	private Pair<String, Map<String, Object>> buildQuery(Vector min, Vector max) {
		String x;
		String y;
		String z;

		Map<String, Object> binds = new HashMap<String, Object>();

		binds.put("xmin", min.getX());
		binds.put("ymin", min.getY());
		binds.put("zmin", min.getZ());

		if (min.equals(max)) {
			x = "`x` = :xmin";
			y = "`y` = :ymin";
			z = "`z` = :zmin";
		} else {
			binds.put("xmax", max.getX());
			binds.put("ymax", max.getY());
			binds.put("zmax", max.getZ());
			x = "(`x` BETWEEN :xmin AND :xmax)";
			y = "(`y` BETWEEN :ymin AND :ymax)";
			z = "(`z` BETWEEN :zmin AND :zmax)";
		}

		String sql = StringUtil.buildString(" AND ", x, y, z);

		return Pair.of(sql, binds);
	}

	private static Vector toVector(com.sk89q.worldedit.Vector vector) {
		return new Vector(vector.getX(), vector.getY(), vector.getZ());
	}

}
