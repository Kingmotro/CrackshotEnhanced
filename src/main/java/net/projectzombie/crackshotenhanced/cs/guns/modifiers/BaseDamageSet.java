/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.projectzombie.crackshotenhanced.cs.guns.modifiers;

import java.util.ArrayList;

import net.projectzombie.crackshotenhanced.cs.guns.components.ModifierLoreBuilder;
import net.projectzombie.crackshotenhanced.cs.guns.components.GunModifier;

/**
 *
 * @author jb
 */
public class BaseDamageSet extends DamageOnHit<BaseDamageAttributes>
{
    private final double skeleBaseDamage;
    
    public BaseDamageSet(GunModifier[] modifiers,
                      final double skeletonBaseDamage)
    {
        super("Base Damage",
              baseDamageValueSum(modifiers, skeletonBaseDamage),
              baseDamageMultiplierSum(modifiers),
                BaseDamageAttributes.class);
        this.skeleBaseDamage = skeletonBaseDamage;
    }
    
    public BaseDamageSet(GunModifier mod)
    {
        this(new GunModifier[] { mod }, 0.0);
    }
    
    @Override
    public ArrayList<String> getStats()
    {
        final ArrayList<String> stats = new ArrayList<>();
        stats.add(ModifierLoreBuilder.getValueStat(super.getTotal(), "total base damage"));
        stats.add(ModifierLoreBuilder.STAT_SEPERATOR);
        stats.add(ModifierLoreBuilder.getValueStat(skeleBaseDamage, "stock base damage"));
        stats.addAll(getStat());
        return stats;
    }
    
    @Override
    public ArrayList<String> getStat()
    {
        final ArrayList<String> stats = new ArrayList<>();
        stats.add(ModifierLoreBuilder.getValueStat(super.getValue(), "base damage"));
        stats.add(ModifierLoreBuilder.getMultiplierStat(super.getMultiplier(), "base damage"));
        return stats;
    }
    
    static private double baseDamageValueSum(final GunModifier[] modSet,
                                            final double skeletonBaseDamage)
    {
        double baseDamageValueSum = skeletonBaseDamage;

        for (BaseDamageAttributes mod : getDamageModifiers(modSet))
        {
            baseDamageValueSum += mod.getDamageValue();
        }
        return baseDamageValueSum;
    }
    
    static private double baseDamageMultiplierSum(final GunModifier[] modSet)
    {
        double baseDamageMultiplierSum = 1.0;

        for (BaseDamageAttributes mod : getDamageModifiers(modSet))
        {
            baseDamageMultiplierSum += mod.getDamageValue();
        }
        return baseDamageMultiplierSum;
    }
    
    /**
     * @param modSet
     * @return Returns all DamageModifiers on the gun.
     */
    static private ArrayList<BaseDamageAttributes> getDamageModifiers(final GunModifier[] modSet)
    {
        final ArrayList<BaseDamageAttributes> mods = new ArrayList<>();
        for (GunModifier mod : modSet)
        {
            if (mod instanceof BaseDamageAttributes)
                mods.add((BaseDamageAttributes)mod);
        }
        return mods;
    }
}
