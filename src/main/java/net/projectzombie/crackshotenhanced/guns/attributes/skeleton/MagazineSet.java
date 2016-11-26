/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.projectzombie.crackshotenhanced.guns.attributes.skeleton;

import java.util.ArrayList;

import net.projectzombie.crackshotenhanced.guns.components.modifier.GunModifier;
import net.projectzombie.crackshotenhanced.guns.components.modifier.ModifierLoreBuilder;
import net.projectzombie.crackshotenhanced.guns.attributes.AttributeSet;

import static net.projectzombie.crackshotenhanced.guns.utilities.Constants.TPS;

/**
 *
 * @author jb
 */
public class MagazineSet extends AttributeSet<MagazineSet.MagazineAttributes>
{
    public interface MagazineAttributes extends SkeletonAttributes
    {
        int getMagazineSizeModifier();
        double getMagazineSizeMultiplier();

        double getReloadSpeedMultiplier();
    }

    static private final double MIN_RELOAD_SPEED = 1.0;
    static private final int MIN_MAG_SIZE = 1;
    
    private final int magSizeModifier;
    private final double magSizeMultiplier;
    private final int totalMagSize;
    private final double reloadSpeedMultiplier;
    private final double totalReloadDurationInTicks;
    
    public MagazineSet(final GunModifier[] gunMods,
                       final int skeletonMagSize,
                       final double skeletonReloadDurationInTicks)
    {
        super("Magazine", gunMods, MagazineAttributes.class);
        this.magSizeModifier = super.getIntSum(MagazineAttributes::getMagazineSizeModifier);
        this.magSizeMultiplier = super.getMultiplierSum(MagazineAttributes::getMagazineSizeMultiplier);
        this.reloadSpeedMultiplier = super.getMultiplierSum(MagazineAttributes::getReloadSpeedMultiplier);
        this.totalMagSize = Math.max(MIN_MAG_SIZE, (int)((skeletonMagSize + magSizeModifier) * magSizeMultiplier));
        this.totalReloadDurationInTicks = Math.max(MIN_RELOAD_SPEED, skeletonReloadDurationInTicks * reloadSpeedMultiplier);
    }
    
    public MagazineSet(GunModifier mod)
    {
        this(new GunModifier[] { mod }, 0, 0);
    }
    
    public int getTotalMagazineSize()   { return totalMagSize; }
    public double getTotalReloadDurationInTicks() { return totalReloadDurationInTicks;  }
    
    @Override
    public ArrayList<String> getGunStats()
    {
        final ArrayList<String> stats = new ArrayList<>();
        stats.add(ModifierLoreBuilder.getValueStat(totalMagSize, "magazine size"));
        stats.add(ModifierLoreBuilder.getValueStat(totalReloadDurationInTicks / TPS, "reload duration"));
        return stats;
    }
    
    @Override
    public ArrayList<String> getIndividualStats()
    {
        final ArrayList<String> stats = new ArrayList<>();
        stats.add(ModifierLoreBuilder.getValueStat(magSizeModifier, "magazine size"));
        stats.add(ModifierLoreBuilder.getMultiplierStat(magSizeMultiplier, "magazine size"));
        stats.add(ModifierLoreBuilder.getMultiplierStat(reloadSpeedMultiplier, "reload duration"));
        return stats;
    }

    @Override
    public boolean hasStats()
    {
        return magSizeModifier > 0 || magSizeMultiplier > 0 || reloadSpeedMultiplier > 0;
    }
}
