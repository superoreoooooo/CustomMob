package xyz.oreodev.custommob.Entity.Lv2.Boss;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.warden.Warden;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.attribute.CraftAttributeMap;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitScheduler;
import xyz.oreodev.custommob.CustomMobMain;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LB2_spirit extends Warden {

    private final CustomMobMain plugin;

    BukkitScheduler scheduler = Bukkit.getScheduler();
    List<LB2_spirit> coolDown = new ArrayList<>();

    public LB2_spirit(Location loc, CustomMobMain plugin) {
        super(EntityType.WARDEN, ((CraftWorld) loc.getWorld()).getHandle());
        this.plugin = plugin;
        this.setPos(loc.getX(), loc.getY(), loc.getZ());
        this.setAggressive(true);
        this.setCustomName(Component.literal(ChatColor.GREEN + "TEST"));
        this.setCustomNameVisible(true);
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(500);
        this.setHealth(500F);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(150);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.removeAllGoals();
        this.targetSelector.removeAllGoals();
    }

    private static Field attributeField;

    static {
        try {
            attributeField = AttributeMap.class.getDeclaredField("b");
            attributeField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void registerGenericAttribute(Entity entity, org.bukkit.attribute.Attribute attribute) throws IllegalAccessException {
        AttributeMap attributeMap = ((org.bukkit.craftbukkit.v1_19_R1.entity.CraftLivingEntity) entity).getHandle().getAttributes();
        Map<Attribute, AttributeInstance> map = (Map<Attribute, AttributeInstance>) attributeField.get(attributeMap);
        Attribute attribute1 = CraftAttributeMap.toMinecraft(attribute);
        AttributeInstance attributeModifier = new AttributeInstance(attribute1, AttributeInstance::getAttribute);
        map.put(attribute1, attributeModifier);
    }

    public void skillDelay(LB2_spirit spirit) {
        scheduler.runTaskLaterAsynchronously(plugin, () -> coolDown.remove(spirit), 20);
    }
}
