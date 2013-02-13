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

package org.cubeville.hawkeye.search.parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.regions.Region;

import org.cubeville.hawkeye.HawkEye;
import org.cubeville.hawkeye.command.CommandException;
import org.cubeville.hawkeye.command.CommandPlayerException;
import org.cubeville.hawkeye.command.CommandSender;
import org.cubeville.hawkeye.command.CommandUsageException;
import org.cubeville.hawkeye.entity.Player;
import org.cubeville.hawkeye.location.Location;
import org.cubeville.hawkeye.location.Vector;
import org.cubeville.hawkeye.search.ParameterParser;
import org.cubeville.hawkeye.search.SearchQuery;
import org.cubeville.util.Pair;
import org.cubeville.util.StringUtil;

public class RadiusParser extends ParameterParser {

	private static boolean init = false;
	private static WorldEdit worldedit;

	private int world;
	private Vector min;
	private Vector max;

	private static int maxRadius;

	public RadiusParser(List<String> parameters, SearchQuery query) throws CommandException {
		super(parameters, query);

		if (!init) {
			worldedit = null;

			try {
				Class.forName("com.sk89q.worldedit.WorldEdit");
				worldedit = WorldEdit.getInstance();
			} catch (ClassNotFoundException e) {
			}

			init = true;
		}
	}

	private boolean hasWorldEdit() {
		return worldedit instanceof WorldEdit;
	}

	@Override
	public int getParseOrder() {
		return 16;
	}

	@Override
	public void parse() throws CommandException {
		CommandSender sender = query.getSender();
		if (!sender.isPlayer()) throw new CommandPlayerException();
		if (parameters.size() > 1) throw new CommandUsageException("Invalid radius specified: &7" + StringUtil.buildString(parameters, ","));
		String parameter = parameters.get(0);

		if (parameter.equalsIgnoreCase("we") || parameter.equalsIgnoreCase("worldedit")) {
			parseWorldedit(sender);
			return;
		}

		Location center = ((Player) sender).getPosition();
		world = HawkEye.getDataManager().getWorldId(center.getWorld());
		double radius = 0;

		try {
			radius = Double.parseDouble(parameter);
		} catch (NumberFormatException e) {
			throw new CommandUsageException("Invalid radius specified: &7" + parameter);
		}

		if (maxRadius != -1 && radius > maxRadius) throw new CommandUsageException("Radius too large, max radius allowed is: &7" + maxRadius);

		Vector r = new Vector(radius, radius, radius);
		min = center.subtract(r);
		max = center.add(r);
	}

	private void parseWorldedit(CommandSender sender) throws CommandException {
		if (!hasWorldEdit()) throw new CommandException("WorldEdit support is not enabled.");

		LocalSession session = worldedit.getSession(sender.getName());
		Region region;

		try {
			region = session.getSelection(session.getSelectionWorld());

			min = toVector(region.getMinimumPoint());
			max = toVector(region.getMaximumPoint());
		} catch (IncompleteRegionException e) {
			throw new CommandException("You do not have a WorldEdit selection.");
		}
	}

	@Override
	public Pair<String, Map<String, Object>> preProcess() {
		return buildQuery(min, max);
	}

	private Pair<String, Map<String, Object>> buildQuery(Vector min, Vector max) {
		String w;
		String x;
		String y;
		String z;

		Map<String, Object> binds = new HashMap<String, Object>();

		binds.put("xmin", min.getX());
		binds.put("ymin", min.getY());
		binds.put("zmin", min.getZ());

		if (world != -1) {
			w = "`world_id` = :worldrad";
			binds.put("worldrad", world);
		} else {
			// Dummy condition so query doesn't break
			w = "true";
		}

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

		String sql = StringUtil.buildString(" AND ", w, x, y, z);

		return Pair.of(sql, binds);
	}

	private static Vector toVector(com.sk89q.worldedit.Vector vector) {
		return new Vector(vector.getX(), vector.getY(), vector.getZ());
	}

	public static void setMaxRadius(int maxRadius) {
		RadiusParser.maxRadius = maxRadius;
	}

}
