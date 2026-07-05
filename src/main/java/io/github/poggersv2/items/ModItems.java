package io.github.poggersv2.items;

import io.github.poggersv2.PassiveAggression;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpyglassItem;

import java.util.function.Function;

public class ModItems {
    // 1. Registration function
    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings){
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(PassiveAggression.MOD_ID, name));

        // Create the item instance.
        T item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    // 2. Register items

    // Declare item sussy substance
    public static final Item SUSPICIOUS_SUBSTANCE = register("suspicious_substance", Item::new, new Item.Properties());

    // Declare phone item
    public static final Item PHONE = register("phone", SpyglassItem::new, new Item.Properties().stacksTo(1));

    // 3. Dummy init function
    public static void initialize() {}
}