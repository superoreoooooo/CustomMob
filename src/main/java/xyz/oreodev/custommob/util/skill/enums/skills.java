package xyz.oreodev.custommob.util.skill.enums;

import org.bukkit.ChatColor;
import org.bukkit.Particle;

public enum skills {
    SPIRIT_BIND(Particle.SCULK_SOUL, 100, 0, 0, 0, ChatColor.BLUE + "속박되었습니다!");

    private final Particle particle;
    private final int count;
    private final int offX;
    private final int offY;
    private final int offZ;
    private final String msg;


    skills(Particle particle, int count, int offX, int offY, int offZ, String msg) {
        this.particle = particle;
        this.count = count;
        this.offX = offX;
        this.offY = offY;
        this.offZ = offZ;
        this.msg = msg;
    }

    public Particle getParticle() {
        return particle;
    }

    public int getCount() {
        return count;
    }

    public int getOffX() {
        return offX;
    }

    public int getOffY() {
        return offY;
    }

    public int getOffZ() {
        return offZ;
    }

    public String getMsg() {
        return msg;
    }
}
