package xyz.oreodev.custommob.Entity.Lv2.Boss;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Ravager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.attribute.CraftAttributeMap;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import xyz.oreodev.custommob.CustomMobMain;
import xyz.oreodev.custommob.listener.boss.L_LB2_spirit;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LB2_spirit extends Ravager {

    private final CustomMobMain plugin;

    public static List<LB2_spirit> coolDown = new ArrayList<>();

    public static List<Player> restraint = new ArrayList<>();

    public static int schedule;


    public LB2_spirit(Location loc, CustomMobMain plugin) {
        super(EntityType.RAVAGER, ((CraftWorld) loc.getWorld()).getHandle());
        this.plugin = plugin;
        this.setPos(loc.getX(), loc.getY(), loc.getZ());
        this.setAggressive(true);
        this.setCustomName(Component.literal(ChatColor.BLUE + "SPIRIT"));
        this.setCustomNameVisible(true);
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(1000);
        this.setHealth(1000F);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(150);
        this.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(50);
        registerGoals();
        initialize(this);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.removeAllGoals();
        this.targetSelector.removeAllGoals();

        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 0.5D, false));
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

    public void initialize(LB2_spirit spirit) {
        schedule = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                castSkill(spirit);
            }
        }, 0, 10);
    }

    BukkitScheduler coolDownSchedule = Bukkit.getScheduler();

    public boolean checkSkillDelay(LB2_spirit spirit) {
        if (coolDown.contains(spirit)) return true;
        else {
            coolDownSchedule.runTaskLaterAsynchronously(JavaPlugin.getPlugin(CustomMobMain.class), () -> coolDown.remove(spirit), 200);
            return false;
        }
    }

    public void castSkill(LB2_spirit spirit) {
        if (checkSkillDelay(spirit)) return;
        if (!spirit.isAlive()) return;
        for (Player player : spirit.getBukkitEntity().getWorld().getEntitiesByClass(Player.class)) {
            if (player.getGameMode() != GameMode.SURVIVAL) return;
            if (player.getLocation().distance(spirit.getBukkitEntity().getLocation()) < 10) {
                Location targetLoc = player.getLocation();
                targetLoc.getWorld().spawnParticle(Particle.SCULK_SOUL, targetLoc, 100, 0, 0, 0);
                player.sendTitle(ChatColor.BLUE + "속박되었습니다!", "");
                if (restraint.contains(player)) return;
                restraint.add(player);
                Bukkit.getConsoleSender().sendMessage(spirit.getUUID() + "casted SKILL to " + player.getName());
            }
        }
    }
}
