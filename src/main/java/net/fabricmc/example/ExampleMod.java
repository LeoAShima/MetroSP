package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleMod implements ModInitializer {
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");
		Registry.register(Registry.BLOCK, new Identifier("metrosp", "map_block"), MAP_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("metrosp", "map_item"), MAP_ITEM);
	}

		// an instance of our new block
		public static final BlockMap MAP_BLOCK = new BlockMap(FabricBlockSettings.of(Material.METAL).strength(5.0f, 5.0f).build());
		public static final Item MAP_ITEM = new ItemMap(new Item.Settings().group(ItemGroup.MISC), MAP_BLOCK);
}
