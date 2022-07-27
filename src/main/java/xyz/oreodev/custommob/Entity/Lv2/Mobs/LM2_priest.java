package xyz.oreodev.custommob.Entity.Lv2.Mobs;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.item.ItemStack;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.attribute.CraftAttributeMap;
import org.bukkit.entity.Entity;

import java.lang.reflect.Field;
import java.util.Map;

import static net.minecraft.world.item.Items.ENCHANTED_BOOK;

public class LM2_priest extends Drowned {

    public LM2_priest(Location loc) {
        super(EntityType.DROWNED, ((CraftWorld) loc.getWorld()).getHandle());
        this.setPos(loc.getX(), loc.getY(), loc.getZ());
        this.setAggressive(true);
        this.setCustomName(Component.literal(ChatColor.BLUE + "PRIEST"));
        this.setCustomNameVisible(true);
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(300);
        this.setHealth(300F);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(200);
        this.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ENCHANTED_BOOK));
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
        AttributeMap attributeMap = ((org.bukkit.craftbukkit.v1_19_R1.entity.CraftLivingEntity) entity).getHandle().getAttributes();
        Map<Attribute, AttributeInstance> map = (Map<Attribute, AttributeInstance>) attributeField.get(attributeMap);
        Attribute attribute1 = CraftAttributeMap.toMinecraft(attribute);
        AttributeInstance attributeModifier = new AttributeInstance(attribute1, AttributeInstance::getAttribute);
        map.put(attribute1, attributeModifier);
    }

    /*Skill*/
}