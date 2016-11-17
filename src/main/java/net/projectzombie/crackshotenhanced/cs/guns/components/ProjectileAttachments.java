/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.projectzombie.crackshotenhanced.cs.guns.components;

import net.projectzombie.crackshotenhanced.cs.guns.components.ProjectileAttachments.ProjectileAttachment;
import net.projectzombie.crackshotenhanced.cs.guns.attributes.modifier.*;
import net.projectzombie.crackshotenhanced.main.Main;
import net.projectzombie.crackshotenhanced.yaml.ModifierConfig;
import net.projectzombie.crackshotenhanced.yaml.ModifierMap;

/**
 *
 * @author jbannon
 */
public class ProjectileAttachments extends ModifierConfig<ProjectileAttachment>
{
    static private ProjectileAttachments slotOneSingleton = null;
            
    static public ProjectileAttachments getInstance()
    {
        if (slotOneSingleton == null)
            slotOneSingleton = new ProjectileAttachments();
        return slotOneSingleton;
    }


    static private final ModifierMap buildDefaultValues() {
        final ModifierMap defaultValues = new ModifierMap(MODULE_NAME);
        defaultValues.put("Material", 4);
        defaultValues.put("Material Data", 0);
        defaultValues.put("Price", 0);
        defaultValues.put("Color", "GREEN");
        defaultValues.put("Bulletspread Multiplier", 0.0);
        defaultValues.put("Damage ModifierAttributes", 0.0);
        defaultValues.put("Damage Multiplier", 0.0);
        defaultValues.put("Headshot Damage ModifierAttributes", 0.0);
        defaultValues.put("Headshot Damage Multiplier", 0.0);
        defaultValues.put("Crit Chance ModifierAttributes", 0.0);
        defaultValues.put("Crit Strike Multiplier", 0.0);
        defaultValues.put("Bleedout Duration Seconds", 0.0);
        defaultValues.put("Bleedout Duration Multiplier", 0.0);
        defaultValues.put("Bleedout Damage", 0.0);
        defaultValues.put("Bleedout Damage Multiplier", 0.0);
        defaultValues.put("Bleedout Damage Multiplier from Base Damage", 0.0);
        defaultValues.put("Bleedout Damage Multiplier from Shrapnel", 0.0);
        defaultValues.put("Fire Damage ModifierAttributes", 0.0);
        defaultValues.put("Fire Damage Multiplier", 0.0);
        defaultValues.put("Ignite Chance", 0.0);
        defaultValues.put("Ignite Duration", 0.0);
        defaultValues.put("Ignite Damage Multiplier From Fire Damage", 0.0);
        defaultValues.put("Ignite Damage Multiplier From Base Damage", 0.0);
        defaultValues.put("Shrapnel Damage ModifierAttributes", 0.0);
        defaultValues.put("Shrapnel Damage Multiplier", 0.0);
        defaultValues.put("Stun Chance", 0.0);
        defaultValues.put("Stun Duration", 0.0);
        return defaultValues;
    }

    static private final String YML_NAME = "Attachments.yml";
    static private final String MODULE_NAME = "Attachments";
    static private final String[] NECESSARY_VALUES = new String[] { "Display Name" };
    static private final ModifierMap DEFAULT_VALUES = buildDefaultValues();

    private ProjectileAttachments() { super(YML_NAME, MODULE_NAME, NECESSARY_VALUES, DEFAULT_VALUES); }

    public ProjectileAttachment buildModule(final int uniqueID, final ModifierMap values) {
        try {
            return new ProjectileAttachment(
                    uniqueID,
                    values.getString("Display Name"),
                    values.getString("Material"),
                    values.getInt("Material Data"),
                    values.getInt("Price"),
                    values.getString("Color"),
                    values.getDouble("Bulletspread Multiplier"),
                    values.getDouble("Damage ModifierAttributes"),
                    values.getDouble("Damage Multiplier"),
                    values.getDouble("Headshot Damage ModifierAttributes"),
                    values.getDouble("Headshot Damage Multiplier"),
                    values.getDouble("Crit Chance ModifierAttributes"),
                    values.getDouble("Crit Strike Multiplier"),
                    values.getDouble("Bleedout Duration Seconds"),
                    values.getDouble("Bleedout Duration Multiplier"),
                    values.getDouble("Bleedout Damage"),
                    values.getDouble("Bleedout Damage Multiplier"),
                    values.getDouble("Bleedout Damage Multiplier from Base Damage"),
                    values.getDouble("Bleedout Damage Multiplier from Shrapnel"),
                    values.getDouble("Fire Damage ModifierAttributes"),
                    values.getDouble("Fire Damage Multiplier"),
                    values.getDouble("Ignite Chance"),
                    values.getDouble("Ignite Duration"),
                    values.getDouble("Ignite Damage Multiplier From Fire Damage"),
                    values.getDouble("Ignite Damage Multiplier From Base Damage"),
                    values.getDouble("Shrapnel Damage ModifierAttributes"),
                    values.getDouble("Shrapnel Damage Multiplier"),
                    values.getDouble("Stun Chance"),
                    values.getDouble("Stun Duration")
            );
        } catch (Exception e) {
            Main.getPlugin().getLogger().warning("Cannot add projectile attachment " + values.getString("Display Name"));
            return null;
        }
    }
    
    @Override
    public ProjectileAttachment getNullValue()
    {
        return new ProjectileAttachment();
    }


    static public class ProjectileAttachment extends Attachments.Attachment implements
            BulletSpreadSet.BulletSpreadAttributes,
            BaseDamageSet.BaseDamageAttributes,
            HeadshotDamageSet.HeadshotAttributes,
            CritSet.CritAttributes,
            BleedoutSet.BleedoutAttributes,
            FireDamageSet.FireDamageAttributes,
            IgniteSet.IgniteAttributes,
            ShrapnelDamageSet.ShrapnelDamageAttributes,
            StunSet.StunAttributes
    {
        private final double bulletSpreadMultiplier;
        private final double damageModifier;
        private final double damageMultiplier;
        private final double headshotDamageModifier;
        private final double headshotDamageMultiplier;
        private final double critChanceBoost;
        private final double critStrikeMultiplier;
        private final double bleedoutDurationSeconds;
        private final double bleedoutDurationMultiplier;
        private final double bleedoutDamageBoost;
        private final double bleedoutDamageMultiplier;
        private final double bleedoutDamageMultiplierFromBase;
        private final double bleedoutDamageMultiplierFromShrap;
        private final double fireDamageModifier;
        private final double fireDamageMultiplier;
        private final double igniteChance;
        private final double igniteDuration;
        private final double igniteDamageMultiplierFromFire;
        private final double igniteDamageMultiplierFromBase;
        private final double shrapnelDamageModifier;
        private final double shrapnelDamageMultiplier;
        private final double stunChance;
        private final double stunDuration;

        private ProjectileAttachment(final int uniqueID,
                            final String displayname,
                            final String materialName,
                            final int materialByte,
                            final int price,
                            final String color,
                            final double bulletSpreadMultiplier,
                            final double damageModifier,
                            final double damageMultiplier,
                            final double headshotDamageModifier,
                            final double headshotDamageMultiplier,
                            final double critChanceBoost,
                            final double critStrikeMultiplier,
                            final double bleedoutDurationSeconds,
                            final double bleedoutDurationMultiplier,
                            final double bleedoutDamageBoost,
                            final double bleedoutDamageMultiplier,
                            final double bleedoutDamageMultiplierFromBase,
                            final double bleedoutDamageMultiplierFromShrap,
                            final double fireDamageModifier,
                            final double fireDamageMultiplier,
                            final double igniteChance,
                            final double igniteDuration,
                            final double igniteDamageMultiplierFromFire,
                            final double igniteDamageMultiplierFromBase,
                            final double shrapnelDamageModifier,
                            final double shrapnelDamageMultiplier,
                            final double stunChance,
                            final double stunDuration)
        {        
            super(uniqueID, displayname, materialName, materialByte, price, color);
            this.bulletSpreadMultiplier = bulletSpreadMultiplier;
            this.damageModifier = damageModifier;
            this.damageMultiplier = damageMultiplier;
            this.headshotDamageModifier = headshotDamageModifier;
            this.headshotDamageMultiplier = headshotDamageMultiplier;
            this.critChanceBoost = critChanceBoost;
            this.critStrikeMultiplier = critStrikeMultiplier;
            this.bleedoutDurationSeconds = bleedoutDurationSeconds;
            this.bleedoutDurationMultiplier = bleedoutDurationMultiplier;
            this.bleedoutDamageBoost = bleedoutDamageBoost;
            this.bleedoutDamageMultiplier = bleedoutDamageMultiplier;
            this.bleedoutDamageMultiplierFromBase = bleedoutDamageMultiplierFromBase;
            this.bleedoutDamageMultiplierFromShrap = bleedoutDamageMultiplierFromShrap;
            this.fireDamageModifier = fireDamageModifier;
            this.fireDamageMultiplier = fireDamageMultiplier;
            this.igniteChance = igniteChance;
            this.igniteDuration = igniteDuration;
            this.igniteDamageMultiplierFromFire = igniteDamageMultiplierFromFire;
            this.igniteDamageMultiplierFromBase = igniteDamageMultiplierFromBase;
            this.shrapnelDamageModifier = shrapnelDamageModifier;
            this.shrapnelDamageMultiplier = shrapnelDamageMultiplier;
            this.stunChance = stunChance;
            this.stunDuration = stunDuration;
        }

        /**
         * Constructs the null Attatchment.
         */
        private ProjectileAttachment()
        {
            this(0, null, null, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        @Override public double getDamageValue()                         { return damageModifier; }
        @Override public double getDamageMultiplier()                    { return damageMultiplier; }
        @Override public double getBulletSpreadMultiplier()              { return bulletSpreadMultiplier; }
        @Override public double getCritChance()                          { return critChanceBoost; }
        @Override public double getCritStrike()                          { return critStrikeMultiplier; }
        @Override public double getBleedoutDurationValue()               { return bleedoutDurationSeconds; }
        @Override public double getBleedoutDamageValuePerSecond()        { return bleedoutDamageBoost;  }
        @Override public double getBleedoutDamageValuePerSecondMultiplier() { return bleedoutDamageMultiplier; }
        @Override public double getHeadshotDamageModifier()              { return headshotDamageModifier; }
        @Override public double getHeadshotDamageMultiplier()            { return headshotDamageMultiplier; }
        @Override public double getBleedoutDurationMultiplier()          { return bleedoutDurationMultiplier; }
        @Override public double getBleedoutDamageMultiplerFromShrapnel() { return bleedoutDamageMultiplierFromShrap; }
        @Override public double getFireDamageValue()                     { return fireDamageModifier; }
        @Override public double getFireDamageMultiplier()                { return fireDamageMultiplier; }
        @Override public double getIgniteChance()                        { return igniteChance; }
        @Override public double getIgniteDuration()                      { return igniteDuration; }
        @Override public double getIgniteDamageMultiplierFromFireDamage() { return igniteDamageMultiplierFromFire; }
        @Override public double getShrapnelDamageValue()                 { return shrapnelDamageModifier; }
        @Override public double getShrapnelDamageMultiplier()            { return shrapnelDamageMultiplier; }
        @Override public double getStunChance()                          { return stunChance; }
        @Override public double getStunDuration()                        { return stunDuration; }
        @Override public boolean isAOE()                                 { return false;        }
        @Override public ProjectileAttachment getNullModifier()          { return slotOneSingleton.getNullValue(); }
    }
}
