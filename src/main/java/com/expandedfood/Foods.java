package com.expandedfood;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

public class Foods {
    // Jam Components
    private static final FoodComponent JAM_FOOD = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(0.4f)
            .build();
    private static final ConsumableComponent JAM_CONSUME = ConsumableComponents.drink()
            .consumeSeconds(2.0f)
            .sound(SoundEvents.ITEM_HONEY_BOTTLE_DRINK)
            .build();

    // Items
    public static final Item CARAMEL = register("caramel", new Item.Settings());
    public static final Item FRIED_EGG = register("fried_egg", new Item.Settings().food(food(5, 0.6f)));
    public static final Item HONEY_CHICKEN = register("honey_chicken", new Item.Settings().food(food(8, 0.5f)));
    public static final Item MEAT_PIE = register("meat_pie", new Item.Settings().food(food(7, 0.5f)));
    public static final Item CHOCOLATE = register("chocolate", new Item.Settings().food(food(5, 0.3f)));
    public static final Item SUSHI = register("sushi", new Item.Settings().food(food(5, 0.3f)));
    public static final Item STUFFED_RABBIT = register("stuffed_rabbit", new Item.Settings().food(food(7, 0.7f)));
    public static final Item BURGER = register("burger", new Item.Settings().food(food(8, 0.7f)));
    public static final Item CARAMEL_APPLE = register("caramel_apple", new Item.Settings().food(food(5, 0.4f)));
    public static final Item VEGGIE_SALAD = register("veggie_salad", new Item.Settings()
            .food(food(5, 0.5f))
            .useRemainder(Items.BOWL)
            .maxCount(1));
    public static final Item MASHED_POTATO = register("mashed_potato", new Item.Settings()
            .food(food(7, 0.5f))
            .useRemainder(Items.BOWL)
            .maxCount(1));
    public static final Item CARROT_SOUP = register("carrot_soup", new Item.Settings()
            .food(food(7, 0.6f))
            .useRemainder(Items.BOWL)
            .maxCount(1));
    public static final Item PORRIDGE = register("porridge", new Item.Settings()
            .food(food(6, 0.6f))
            .useRemainder(Items.BOWL)
            .maxCount(1));
    public static final Item JAM = register("jam", new Item.Settings()
            .food(JAM_FOOD, JAM_CONSUME)
            .useRemainder(Items.GLASS_BOTTLE)
            .maxCount(16));

    private static final List<Item> FOOD_ITEMS = List.of(
            FRIED_EGG, BURGER, CARAMEL_APPLE, CARROT_SOUP, CHOCOLATE,
            HONEY_CHICKEN, JAM, MASHED_POTATO, MEAT_PIE, PORRIDGE,
            STUFFED_RABBIT, SUSHI, VEGGIE_SALAD
    );

    // Compostable Items
    private static final Map<Item, Float> COMPOSTABLE_ITEMS = Map.of(
            CARAMEL, 0.3f,
            CARAMEL_APPLE, 0.8f
    );

    private static FoodComponent food(int nutrition, float saturation) {
        return new FoodComponent.Builder()
                .nutrition(nutrition)
                .saturationModifier(saturation)
                .build();
    }

    private static Item register(String name, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ExpandedFood.MOD_ID, name));
        Item item = new Item(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register(itemGroup -> itemGroup.add(CARAMEL));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register(itemGroup -> FOOD_ITEMS.forEach(itemGroup::add));

        COMPOSTABLE_ITEMS.forEach(CompostingChanceRegistry.INSTANCE::add);
    }
}