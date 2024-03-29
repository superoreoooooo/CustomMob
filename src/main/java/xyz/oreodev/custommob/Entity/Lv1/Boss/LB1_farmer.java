package xyz.oreodev.custommob.Entity.Lv1.Boss;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.attribute.CraftAttributeMap;
import org.bukkit.entity.Entity;

import java.lang.reflect.Field;
import java.util.Map;

public class LB1_farmer extends Villager {

    public LB1_farmer(Location loc) {
        super(EntityType.VILLAGER, ((CraftWorld) loc.getWorld()).getHandle());
        this.setPos(loc.getX(), loc.getY(), loc.getZ());
        this.setAggressive(true);
        this.setCustomName(Component.literal(ChatColor.RED + "FARMER"));
        this.setCustomNameVisible(true);
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(500);
        this.setHealth(500F);
        try {
            registerGenericAttribute(this.getBukkitEntity(), org.bukkit.attribute.Attribute.GENERIC_ATTACK_DAMAGE);
            registerGenericAttribute(this.getBukkitEntity(), org.bukkit.attribute.Attribute.GENERIC_FOLLOW_RANGE);
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(50);
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.3);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerGoals() {
        this.getAttributes().getDirtyAttributes().add(new AttributeInstance(Attributes.ATTACK_DAMAGE, attributeInstance -> attributeInstance.setBaseValue(1.0)));
        this.getAttributes().getDirtyAttributes().add(new AttributeInstance(Attributes.FOLLOW_RANGE, attributeInstance -> attributeInstance.setBaseValue(1.0)));

        this.goalSelector.removeAllGoals();
        this.targetSelector.removeAllGoals();

        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, ServerPlayer.class, true));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, ServerPlayer.class, 11.0F));
        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.0F));
        this.setVillagerData(new VillagerData(VillagerType.PLAINS, VillagerProfession.FARMER, 1));
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
}
