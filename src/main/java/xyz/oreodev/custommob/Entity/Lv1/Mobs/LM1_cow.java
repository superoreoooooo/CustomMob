package xyz.oreodev.custommob.Entity.Lv1.Mobs;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_18_R2.attribute.CraftAttributeMap;
import org.bukkit.entity.Entity;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Map;

public class LM1_cow extends Cow {

    public LM1_cow(Location loc) {
        super(EntityType.COW, ((CraftWorld) loc.getWorld()).getHandle());
        this.setPos(loc.getX(), loc.getY(), loc.getZ());
        this.setAggressive(true);
        this.setCustomName(new TextComponent(ChatColor.RED + "COW"));
        this.setCustomNameVisible(true);
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(100);
        this.setHealth(100F);
        try {
            registerGenericAttribute(this.getBukkitEntity(), org.bukkit.attribute.Attribute.GENERIC_ATTACK_DAMAGE);
            registerGenericAttribute(this.getBukkitEntity(), org.bukkit.attribute.Attribute.GENERIC_FOLLOW_RANGE);
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(15.0);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerGoals() {
        this.getAttributes().getDirtyAttributes().add(new AttributeInstance(Attributes.ATTACK_DAMAGE, attributeInstance -> attributeInstance.setBaseValue(1.0)));
        this.getAttributes().getDirtyAttributes().add(new AttributeInstance(Attributes.FOLLOW_RANGE, attributeInstance -> attributeInstance.setBaseValue(1.0)));
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.4D, false));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, ServerPlayer.class, true));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, ServerPlayer.class, 11.0F));
        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.0F));
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
        AttributeMap attributeMap = ((org.bukkit.craftbukkit.v1_18_R2.entity.CraftLivingEntity) entity).getHandle().getAttributes();
        Map<Attribute, AttributeInstance> map = (Map<Attribute, AttributeInstance>) attributeField.get(attributeMap);
        Attribute attribute1 = CraftAttributeMap.toMinecraft(attribute);
        AttributeInstance attributeModifier = new AttributeInstance(attribute1, AttributeInstance::getAttribute);
        map.put(attribute1, attributeModifier);
    }
}