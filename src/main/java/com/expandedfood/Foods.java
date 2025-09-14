package com.expandedfood;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import java.util.function.Function;

public class Foods {

    // Furnace & Smoker
    public static final Item CARAMEL = register("caramel", Item::new, new Item.Settings().maxCount(64));
    public static final Item FRIED_EGG = register("fried_egg", Item::new, new Item.Settings().food(new FoodComponent.Builder().nutrition(5).saturationModifier(0.6f).build()));

    // Crafting Table
    public static final Item HONEY_CHICKEN = register("honey_chicken", Item::new,new Item.Settings().food(new FoodComponent.Builder().nutrition(8).saturationModifier(0.5f).build()));
    public static final Item MEAT_PIE = register("meat_pie", Item::new,new Item.Settings().food(new FoodComponent.Builder().nutrition(7).saturationModifier(0.5f).build()));
    public static final Item CHOCOLATE = register("chocolate", Item::new,new Item.Settings().food(new FoodComponent.Builder().nutrition(5).saturationModifier(0.3f).build()));
    public static final Item SUSHI = register("sushi", Item::new,new Item.Settings().food(new FoodComponent.Builder().nutrition(5).saturationModifier(0.3f).build()));
    public static final Item STUFFED_RABBIT = register("stuffed_rabbit", Item::new,new Item.Settings().food(new FoodComponent.Builder().nutrition(7).saturationModifier(0.7f).build()));
    public static final Item BURGER = register("burger", Item::new,new Item.Settings().food(new FoodComponent.Builder().nutrition(8).saturationModifier(0.7f).build()));
    public static final Item CARAMEL_APPLE = register("caramel_apple", Item::new,new Item.Settings().food(new FoodComponent.Builder().nutrition(5).saturationModifier(0.4f).build()));

    // Bowl
    public static final Item VEGIES_SALAD = register("vegies_salad", Item::new,new Item.Settings()
            .food(new FoodComponent.Builder().nutrition(5).saturationModifier(0.5f).build())
            .maxCount(16)
            .useRemainder(Items.BOWL));
    public static final Item MASHED_POTATO = register("mashed_potato", Item::new,new Item.Settings()
            .food(new FoodComponent.Builder().nutrition(7).saturationModifier(0.5f).build())
            .maxCount(16)
            .useRemainder(Items.BOWL));
    public static final Item CARROT_SOUP = register("carrot_soup", Item::new,new Item.Settings()
            .food(new FoodComponent.Builder().nutrition(7).saturationModifier(0.4f).build())
            .maxCount(16)
            .useRemainder(Items.BOWL));
    public static final Item PORRIDGE = register("porridge", Item::new,new Item.Settings()
            .food(new FoodComponent.Builder().nutrition(7).saturationModifier(0.6f).build())
            .maxCount(16)
            .useRemainder(Items.BOWL));

    // Bottle

    public static final Item JAM = register("jam", Item::new,new Item.Settings()
            .food(new FoodComponent.Builder().nutrition(5).saturationModifier(0.2f).build())
            .maxCount(16)
            .useRemainder(Items.GLASS_BOTTLE));


    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
    // Create the item key.
    RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ExpandedFood.MOD_ID, name));

    // Create the item instance.
    Item item = itemFactory.apply(settings.registryKey(itemKey));

    // Register the item.
    Registry.register(Registries.ITEM, itemKey, item);

    return item;
    }


    public static void initialize() {
        // Get the event for modifying entries in the INGREDIENTS group.
        // And register an event handler that adds our CARAMEL item to the INGREDIENTS group.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register((itemGroup) -> itemGroup.add(Foods.CARAMEL));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((itemGroup) -> itemGroup.add(Foods.FRIED_EGG));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((itemGroup) -> itemGroup.add(Foods.BURGER));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((itemGroup) -> itemGroup.add(Foods.CARAMEL_APPLE));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((itemGroup) -> itemGroup.add(Foods.CARROT_SOUP));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((itemGroup) -> itemGroup.add(Foods.CHOCOLATE));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((itemGroup) -> itemGroup.add(Foods.HONEY_CHICKEN));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((itemGroup) -> itemGroup.add(Foods.JAM));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((itemGroup) -> itemGroup.add(Foods.MASHED_POTATO));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((itemGroup) -> itemGroup.add(Foods.MEAT_PIE));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((itemGroup) -> itemGroup.add(Foods.PORRIDGE));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((itemGroup) -> itemGroup.add(Foods.STUFFED_RABBIT));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((itemGroup) -> itemGroup.add(Foods.SUSHI));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((itemGroup) -> itemGroup.add(Foods.VEGIES_SALAD));

        CompostingChanceRegistry.INSTANCE.add(Foods.CARAMEL, 0.3f);
        // Add the CARAMEL to the composting registry with a 30% chance of increasing the composter's level.
    }

}