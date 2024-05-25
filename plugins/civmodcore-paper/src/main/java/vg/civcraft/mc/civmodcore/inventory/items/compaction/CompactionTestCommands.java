package vg.civcraft.mc.civmodcore.inventory.items.compaction;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.jetbrains.annotations.NotNull;

@CommandAlias("compaction")
@CommandPermission("cmc.debug")
public final class CompactionTestCommands extends BaseCommand {
	private static final ItemStack TEMPLATE_ITEM = new ItemStack(Material.DIAMOND);

	public enum CompactedItemType { NEW, LEGACY }

	@Subcommand("give")
	public void giveNewItem(
		final @NotNull Player sender,
		final @NotNull CompactedItemType type
	) {
		final ItemStack item = TEMPLATE_ITEM.clone();
		switch (type) {
			case NEW -> item.editMeta(Compaction::markAsCompacted);
			case LEGACY -> item.editMeta((meta) -> {
				meta.setLore(List.of(Compaction.COMPACTED_ITEM_LORE));
			});
		}
		sender.getInventory().addItem(item);
		sender.sendMessage(Component.text("You've been given a compacted item!", NamedTextColor.GREEN));
	}

	@Subcommand("upgrade")
	public void upgradeHeldLegacy(
		final @NotNull Player sender
	) {
		final ItemStack item = sender.getInventory().getItemInMainHand();
		switch (Compaction.attemptUpgrade(item)) {
			case SUCCESS -> sender.sendMessage(Component.text(
				"Successfully upgraded legacy compacted item!",
				NamedTextColor.GREEN
			));
			case EMPTY_ITEM -> sender.sendMessage(Component.text(
				"You are not holding an item to upgrade!",
				NamedTextColor.YELLOW
			));
			case ALREADY_COMPACTED -> sender.sendMessage(Component.text(
				"That item is already compacted!",
				NamedTextColor.YELLOW
			));
			case NOT_COMPACTED -> sender.sendMessage(Component.text(
				"That item is not a legacy compacted item!",
				NamedTextColor.YELLOW
			));
		}
	}

	@Subcommand("merchant")
	public void viewMerchantTest(
		final @NotNull Player sender
	) {
		final Merchant merchant = Bukkit.createMerchant(Component.text("Test Merchant"));

		final var recipes = new ArrayList<MerchantRecipe>();
		{ // Compacted result recipe
			final ItemStack result = TEMPLATE_ITEM.clone();
			result.editMeta(Compaction::markAsCompacted);
			final var recipe = new MerchantRecipe(result, Short.MAX_VALUE);
			recipe.addIngredient(new ItemStack(Material.STICK));
			recipes.add(recipe);
		}
		{ // Compacted ingredient recipe
			final var recipe = new MerchantRecipe(TEMPLATE_ITEM.clone(), Short.MAX_VALUE);
			final ItemStack ingredient = new ItemStack(Material.STICK);
			ingredient.editMeta(Compaction::markAsCompacted);
			recipe.addIngredient(ingredient);
			recipes.add(recipe);
		}
		merchant.setRecipes(recipes);

		sender.openMerchant(merchant, true);
	}
}