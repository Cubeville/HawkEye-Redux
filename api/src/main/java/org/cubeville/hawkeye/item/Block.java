package org.cubeville.hawkeye.item;

import java.util.HashMap;
import java.util.Map;

import org.cubeville.hawkeye.item.data.BasicItemData;
import org.cubeville.hawkeye.item.data.ItemData;
import org.cubeville.hawkeye.item.data.MobHeadItemData;
import org.cubeville.hawkeye.item.data.SignItemData;

public enum Block {

	AIR(0),
	STONE(1),
	GRASS(2),
	DIRT(3),
	COBBLESTONE(4),
	WOOD(5, BasicItemData.class),
	SAPLING(6, BasicItemData.class),
	BEDROCK(7),
	WATER(8, BasicItemData.class),
	STATIONARY_WATER(9, BasicItemData.class),
	LAVA(10, BasicItemData.class),
	STATIONARY_LAVA(11, BasicItemData.class),
	SAND(12),
	GRAVEL(13),
	GOLD_ORE(14),
	IRON_ORE(15),
	COAL_ORE(16),
	LOG(17, BasicItemData.class),
	LEAVES(18, BasicItemData.class),
	SPONGE(19),
	GLASS(20),
	LAPIS_ORE(21),
	LAPIS_BLOCK(22),
	DISPENSER(23),
	SANDSTONE(24, BasicItemData.class),
	NOTE_BLOCK(25),
	BED_BLOCK(26, BasicItemData.class),
	POWERED_RAIL(27, BasicItemData.class),
	DETECTOR_RAIL(28, BasicItemData.class),
	PISTON_STICKY_BASE(29, BasicItemData.class),
	WEB(30),
	LONG_GRASS(31, BasicItemData.class),
	DEAD_BUSH(32),
	PISTON_BASE(33, BasicItemData.class),
	PISTON_EXTENSION(34, BasicItemData.class),
	WOOL(35, BasicItemData.class),
	PISTON_MOVING_PIECE(36),
	YELLOW_FLOWER(37),
	RED_ROSE(38),
	BROWN_MUSHROOM(39),
	RED_MUSHROOM(40),
	GOLD_BLOCK(41),
	IRON_BLOCK(42),
	DOUBLE_STEP(43, BasicItemData.class),
	STEP(44),
	BRICK(45),
	TNT(46),
	BOOKSHELF(47),
	MOSSY_COBBLESTONE(48),
	OBSIDIAN(49),
	TORCH(50, BasicItemData.class),
	FIRE(51),
	MOB_SPAWNER(52),
	WOOD_STAIRS(53, BasicItemData.class),
	CHEST(54),
	REDSTONE_WIRE(55, BasicItemData.class),
	DIAMOND_ORE(56),
	DIAMOND_BLOCK(57),
	WORKBENCH(58),
	CROPS(59, BasicItemData.class),
	SOIL(60, BasicItemData.class),
	FURNACE(61),
	BURNING_FURNACE(62),
	SIGN_POST(63, SignItemData.class),
	WOODEN_DOOR(64, BasicItemData.class),
	LADDER(65, BasicItemData.class),
	RAILS(66, BasicItemData.class),
	COBBLESTONE_STAIRS(67, BasicItemData.class),
	WALL_SIGN(68, SignItemData.class),
	LEVER(69, BasicItemData.class),
	STONE_PLATE(70, BasicItemData.class),
	IRON_DOOR_BLOCK(71, BasicItemData.class),
	WOOD_PLATE(72, BasicItemData.class),
	REDSTONE_ORE(73),
	GLOWING_REDSTONE_ORE(74),
	REDSTONE_TORCH_OFF(75, BasicItemData.class),
	REDSTONE_TORCH_ON(76, BasicItemData.class),
	STONE_BUTTON(77, BasicItemData.class),
	SNOW(78),
	ICE(79),
	SNOW_BLOCK(80),
	CACTUS(81, BasicItemData.class),
	CLAY(82),
	SUGAR_CANE_BLOCK(83, BasicItemData.class),
	JUKEBOX(84),
	FENCE(85),
	PUMPKIN(86, BasicItemData.class),
	NETHERRACK(87),
	SOUL_SAND(88),
	GLOWSTONE(89),
	PORTAL(90),
	JACK_O_LANTERN(91, BasicItemData.class),
	CAKE_BLOCK(92, BasicItemData.class),
	DIODE_BLOCK_OFF(93, BasicItemData.class),
	DIODE_BLOCK_ON(94, BasicItemData.class),
	LOCKED_CHEST(95),
	TRAP_DOOR(96, BasicItemData.class),
	MONSTER_EGGS(97, BasicItemData.class),
	SMOOTH_BRICK(98, BasicItemData.class),
	HUGE_MUSHROOM_1(99, BasicItemData.class),
	HUGE_MUSHROOM_2(100, BasicItemData.class),
	IRON_FENCE(101),
	THIN_GLASS(102),
	MELON_BLOCK(103),
	PUMPKIN_STEM(104, BasicItemData.class),
	MELON_STEM(105, BasicItemData.class),
	VINE(106, BasicItemData.class),
	FENCE_GATE(107, BasicItemData.class),
	BRICK_STAIRS(108, BasicItemData.class),
	SMOOTH_STAIRS(109, BasicItemData.class),
	MYCEL(110),
	WATER_LILY(111),
	NETHER_BRICK(112),
	NETHER_FENCE(113),
	NETHER_BRICK_STAIRS(114, BasicItemData.class),
	NETHER_WARTS(115, BasicItemData.class),
	ENCHANTMENT_TABLE(116),
	BREWING_STAND(117, BasicItemData.class),
	CAULDRON(118, BasicItemData.class),
	ENDER_PORTAL(119),
	ENDER_PORTAL_FRAME(120),
	ENDER_STONE(121),
	DRAGON_EGG(122),
	REDSTONE_LAMP_OFF(123),
	REDSTONE_LAMP_ON(124),
	WOOD_DOUBLE_STEP(125, BasicItemData.class),
	WOOD_STEP(126, BasicItemData.class),
	COCOA(127, BasicItemData.class),
	STANDSTONE_STAIRS(128, BasicItemData.class),
	EMERALD_ORE(129),
	ENDER_CHEST(130, BasicItemData.class),
	TRIPWIRE_HOOK(131, BasicItemData.class),
	TRIPWIRE(132, BasicItemData.class),
	EMERALD_BLOCK(133),
	SPRUCE_WOOD_STAIRS(134, BasicItemData.class),
	BIRCH_WOOD_STAIRS(135, BasicItemData.class),
	JUNGLE_WOOD_STAIRS(136, BasicItemData.class),
	COMMAND(137),
	BEACON(138),
	COBBLE_WALL(139),
	FLOWER_POT(140, BasicItemData.class),
	CARROT(141),
	POTATO(142),
	WOOD_BUTTON(143, BasicItemData.class),
	SKULL(144, MobHeadItemData.class),
	ANVIL(145, BasicItemData.class);

	/**
	 * Block id
	 */
	private final int id;

	/**
	 * Custom item data class
	 */
	private final Class<? extends ItemData> dataClass;

	/**
	 * Mapping of ids to blocks for quick access
	 */
	private static final Map<Integer, Block> idMap = new HashMap<Integer, Block>(values().length);

	private Block(int id) {
		this(id, null);
	}

	private Block(int id, Class<? extends ItemData> dataClass) {
		this.id = id;
		this.dataClass = dataClass;
	}

	/**
	 * Gets the item id of this block
	 *
	 * @return This block's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets whether or not this block stores custom item data
	 *
	 * @return True if block has custom data to store, false if not
	 */
	public boolean hasItemData() {
		return dataClass != null;
	}

	/**
	 * Gets a block by its id
	 *
	 * @param id Id of block to get
	 * @return Block with the specified id or null if it doesn't exist
	 */
	public static Block getById(int id) {
		return idMap.containsKey(id) ? idMap.get(id) : null;
	}

	static {
		for (Block block : values()) {
			idMap.put(block.id, block);
		}
	}

}
