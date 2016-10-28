package isaac.bastion;

import java.util.LinkedHashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;

public class BastionType {
	
	private static LinkedHashMap<String, BastionType> types = new LinkedHashMap<String, BastionType>();
	private static String defaultType;
	private static int maxRadius = 0;

	private String name;
	private MaterialData material;
	private List<String> lore;
	private boolean square;
	private int effectRadius;
	private int radiusSquared;
	private boolean includeY;
	private int startScaleFactor;
	private double finalScaleFactor;
	private long warmupTime;
	private int erosionTime;
	private long placementCooldown;
	private boolean destroyOnRemove;
	private boolean blockPearls;
	private boolean blockMidair;
	private int pearlScale;
	private boolean pearlRequireMature;
	private boolean consumeOnBlock;
	private int blocksToErode;
	private boolean blockElytra;
	private boolean destroyElytra;
	private boolean damageElytra;
	private int elytraScale;
	private boolean elytraRequireMature;
	private boolean explodeOnBlock;
	private double explodeOnBlockStrength;
	private boolean damageFirstBastion;
	private int regenTime;
	
	public BastionType(String name, MaterialData material, List<String> lore, boolean square, int effectRadius,
			boolean includeY, int startScaleFactor, double finalScaleFactor, long warmupTime,
			int erosionTime, long placementCooldown, boolean destroyOnRemove, boolean blockPearls,
			boolean blockMidair, int pearlScale, boolean pearlRequireMature, boolean consumeOnBlock, int blocksToErode,
			boolean blockElytra, boolean destroyOnBlockElytra, boolean damageElytra, int elytraScale, boolean elytraRequireMature,
			boolean explodeOnBlock, double explodeOnBlockStrength, boolean damageFirstBastion, int regenTime) {
		this.name = name;
		this.material = material;
		this.lore = lore;
		this.square = square;
		this.effectRadius = effectRadius;
		this.radiusSquared = effectRadius*effectRadius;
		this.includeY = includeY;
		this.startScaleFactor = startScaleFactor;
		this.finalScaleFactor = finalScaleFactor;
		this.warmupTime = warmupTime;
		this.erosionTime = erosionTime;
		this.placementCooldown = placementCooldown;
		this.destroyOnRemove = destroyOnRemove;
		this.blockPearls = blockPearls;
		this.blockMidair = blockMidair;
		this.pearlScale = pearlScale;
		this.pearlRequireMature = pearlRequireMature;
		this.consumeOnBlock = consumeOnBlock;
		this.blocksToErode = blocksToErode;
		this.blockElytra = blockElytra;
		this.destroyElytra = destroyOnBlockElytra;
		this.damageElytra = damageElytra;
		this.elytraScale = elytraScale;
		this.elytraRequireMature = elytraRequireMature;
		this.explodeOnBlock = explodeOnBlock;
		this.explodeOnBlockStrength = explodeOnBlockStrength;
		this.damageFirstBastion = damageFirstBastion;
		maxRadius = effectRadius > maxRadius ? effectRadius : maxRadius;
	}

	/**
	 * @return The material for this bastion type
	 */
	public MaterialData getMaterial() {
		return material;
	}

	/**
	 * @return The lore for this bastion type
	 */
	public List<String> getLore() {
		return lore;
	}

	/**
	 * @return true if bastion is square
	 */
	public boolean isSquare() {
		return square;
	}

	/**
	 * @return the radius of the bastion
	 */
	public int getEffectRadius() {
		return effectRadius;
	}

	/**
	 * 
	 * @return the radius of the bastion squared
	 */
	public int getRadiusSquared() {
		return radiusSquared;
	}
	
	/**
	 * 
	 * @return true if the bastion blocks on it's own y level
	 */
	public boolean isIncludeY() {
		return includeY;
	}

	/**
	 * 
	 * @return The scale for amplifying damage when a bastion is first placed
	 */
	public int getStartScaleFactor() {
		return startScaleFactor;
	}

	/**
	 * 
	 * @return The scale for amplifying damage when a bastion is mature
	 */
	public double getFinalScaleFactor() {
		return finalScaleFactor;
	}

	/**
	 * 
	 * @return The time in ms it takes for a bastion to mature
	 */
	public long getWarmupTime() {
		return warmupTime;
	}
	
	/**
	 * 
	 * @return The amount of time between erosions
	 */
	public int getErosionTime() {
		return erosionTime;
	}

	/**
	 * 
	 * @return true if the physical block should be destroyed when a bastion dies
	 */
	public boolean isDestroyOnRemove() {
		return destroyOnRemove;
	}

	/**
	 * 
	 * @return true if the bastion blocks pearls
	 */
	public boolean isBlockPearls() {
		return blockPearls;
	}

	/**
	 * 
	 * @return true if the bastion blocks midair rather than when the pearl lands
	 */
	public boolean isBlockMidair() {
		return blockMidair;
	}

	/**
	 * 
	 * @return The scale for increased bastion damage from pearls
	 */
	public int getPearlScaleFactor() {
		return pearlScale;
	}

	/**
	 * 
	 * @return true if the bastion must be mature to block pearls
	 */
	public boolean isRequireMaturity() {
		return pearlRequireMature;
	}

	/**
	 * 
	 * @return true if pearls are consumed when blocked by bastion
	 */
	public boolean isConsumeOnBlock() {
		return consumeOnBlock;
	}
	
	/**
	 * 
	 * @return The number of bastions that should be eroded from a block place
	 */
	public int getBlocksToErode() {
		return blocksToErode;
	}
	
	/**
	 * 
	 * @return The cooldown for damaging a bastion
	 */
	public long getPlacementCooldown() {
		return placementCooldown;
	}
	
	/**
	 * 
	 * @return true if the bastion blocks elytra
	 */
	public boolean isBlockElytra() {
		return blockElytra;
	}

	/**
	 * 
	 * @return true if the bastion destroys elytra
	 */
	public boolean isDestroyElytra() {
		return destroyElytra;
	}

	/**
	 * 
	 * @return true if the bastion just damages elytra
	 */
	public boolean isDamageElytra() {
		return damageElytra;
	}

	/**
	 * 
	 * @return The scale for increased damage from elytra collision
	 */
	public int getElytraScale() {
		return elytraScale;
	}

	/**
	 * 
	 * @return true if bastion must be mature to block elytra
	 */
	public boolean isElytraRequireMature() {
		return elytraRequireMature;
	}
	
	/**
	 * 
	 * @return true if there should be an explosion on elytra colision
	 */
	public boolean isExplodeOnBlock() {
		return explodeOnBlock;
	}
	
	/**
	 * 
	 * @return the strength of the explosion on elytra collision
	 */
	public double getExplosionStrength() {
		return explodeOnBlockStrength;
	}
	
	/**
	 * 
	 * @return true if only the first bastion hit should be damaged by pearls
	 */
	public boolean damageFirstBastion() {
		return damageFirstBastion;
	}
	
	/**
	 * 
	 * @return The amount of time between regenerations
	 */
	public int getRegenTime() {
		return regenTime;
	}
	
	/**
	 * Gets the name of the bastion type
	 * This is used to reduce space in maps elsewhere
	 * @return The name of this bastion type
	 */
	public String getName() {
		return name;
	}

	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof BastionType)) return false;
		BastionType other = (BastionType) obj;
		return other.getName().equals(name);
	}
	
	@SuppressWarnings("deprecation")
	/**
	 * Creates an item representation of the bastion type
	 * @return The bastion item
	 */
	public ItemStack getItemRepresentation() {
		ItemStack is = new ItemStack(material.getItemType(), 1, material.getData());
		if(lore != null) {
			if(!is.hasItemMeta()) {
				is.setItemMeta(Bukkit.getItemFactory().getItemMeta(material.getItemType()));
				if(!is.hasItemMeta()) {
					return is;
				}
			}
			is.getItemMeta().setLore(lore);
		}
		return is;
	}

	public static void loadBastionTypes(ConfigurationSection config) {
		for(String key : config.getKeys(false)) {
			if(defaultType == null) defaultType = key;
			BastionType type = getBastionType(config.getConfigurationSection(key));
			if(type != null) {
				types.put(key, type);
			}
		}
	}
	
	public static void startRegenAndErosionTasks() {
		for(BastionType type : types.values()) {
			if(type.erosionTime > 0) {
				new BukkitRunnable() {
					public void run() {
						for(BastionBlock bastion : Bastion.getBastionStorage().getBastionsForType(type)) {
							bastion.erode(1);
						}
					}
				}.runTaskTimerAsynchronously(Bastion.getPlugin(), type.erosionTime, type.erosionTime);
			}
			if(type.regenTime > 0) {
				new BukkitRunnable() {
					public void run() {
						for(BastionBlock bastion : Bastion.getBastionStorage().getBastionsForType(type)) {
							bastion.regen();
						}
					}
				}.runTaskTimerAsynchronously(Bastion.getPlugin(), type.regenTime, type.regenTime);
			}
		}
	}
	
	public static BastionType getBastionType(String name) {
		return types.get(name);
	}
	
	public static BastionType getBastionType(MaterialData mat, List<String> lore) {
		for(BastionType type : types.values()) {
			if(type.material.equals(mat) && ((lore == null && type.lore == null) || type.lore.equals(lore))) return type;
		}
		return null;
	}
	
	/**
	 * 
	 * @return The radius of the largest bastion type
	 */
	public static int getMaxRadius() {
		return maxRadius;
	}
	
	/**
	 * 
	 * @return The default bastion type
	 */
	public static String getDefaultType() {
		return defaultType;
	}
	
	@SuppressWarnings("deprecation")
	public static BastionType getBastionType(ConfigurationSection config) {
		String name = config.getName();
		Material mat = Material.getMaterial(config.getString("block.material"));
		if(!mat.isBlock()) return null;
		byte data = config.contains("block.durability") ? (byte)config.getInt("block.durability") : 0;
		MaterialData material = new MaterialData(mat, data);
		List<String> lore = config.getStringList("block.lore");
		boolean square = config.getBoolean("squarefield");
		int effectRadius = config.getInt("effectRadius");
		boolean includeY = config.getBoolean("includeY");
		int startScaleFactor = config.getInt("startScaleFactor");
		double finalScaleFactor = config.getDouble("finalScaleFactor");
		long warmupTime = config.getLong("warmupTime");
		int erosionTime = config.getInt("erosionPerDay");
		if(erosionTime > 0) {
			erosionTime = 1728000 / erosionTime;
		} else if(erosionTime < 0) {
			erosionTime = 0;
		}
		long placementCooldown = config.getLong("placementCooldown");
		boolean destroyOnRemove = config.getBoolean("destroyOnRemove");
		boolean blockPearls = config.getBoolean("pearls.block");
		boolean blockMidair = config.getBoolean("pearls.blockMidair");
		int scaleFactor = config.getInt("pearls.scaleFactor");
		boolean requireMaturity = config.getBoolean("pearls.requireMaturity");
		boolean consumeOnBlock = config.getBoolean("pearls.consumeOnBlock");
		boolean damageFirstBastion = config.getBoolean("pearls.damageFirstBastion");
		int blocksToErode = config.getInt("blocksToErode");
		boolean blockElytra = config.getBoolean("elytra.block");
		boolean destroyElytra = config.getBoolean("elytra.destroyOnBlock");
		boolean damageElytra = config.getBoolean("elytra.damageOnBlock");
		int elytraScale = config.getInt("elytra.scaleFactor");
		boolean elytraRequireMature = config.getBoolean("elytra.requireMaturity");
		int regenTime = config.getInt("regenPerDay");
		if(regenTime > 0) {
			regenTime = 1728000 / regenTime;
		} else if(regenTime < 0) {
			regenTime = 0;
		}
		boolean explodeOnBlock = config.getBoolean("elytra.explodeOnBlock");
		double explodeOnBlockStrength = config.getDouble("elytra.explodeOnBlockStrength");
		return new BastionType(name, material, lore, square, effectRadius, includeY, startScaleFactor, finalScaleFactor, warmupTime,
				erosionTime, placementCooldown, destroyOnRemove, blockPearls, blockMidair, scaleFactor, requireMaturity, consumeOnBlock, 
				blocksToErode, blockElytra, destroyElytra, damageElytra, elytraScale, elytraRequireMature, explodeOnBlock, 
				explodeOnBlockStrength, damageFirstBastion, regenTime);
	}
}