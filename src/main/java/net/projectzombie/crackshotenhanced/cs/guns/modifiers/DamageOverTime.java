/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.projectzombie.crackshotenhanced.cs.guns.modifiers;

import net.projectzombie.crackshotenhanced.cs.guns.components.GunModifierSet;
import net.projectzombie.crackshotenhanced.cs.guns.components.Modifier;
import static net.projectzombie.crackshotenhanced.cs.guns.utilities.Constants.TPS;

/**
 *
 * @author jb
 * @param <T>
 */
public abstract class DamageOverTime<T extends Modifier> extends GunModifierSet<T>
{
    
    final double totalDPS;
    final double totalDuration;
    final double durationValue;
    final double durationMultiplier;
    
    public DamageOverTime(final String name,
                          final double totalDPS,
                          final double durationValue,
                          final double durationMultiplier,
                          final Class<T> t)
    {
        super(name, t);
        this.totalDPS = totalDPS;
        this.totalDuration = Math.max(0, durationValue * durationMultiplier);
        
        this.durationValue = durationValue;
        this.durationMultiplier = durationMultiplier;
    }
    
    @Override
    public boolean hasStats()
    {
        return totalDPS > 0 && totalDuration > 0;
    }
    
    public double getTotalDPS() { return totalDPS;       }
    public double getTotalDPT() { return totalDPS / TPS; }
    
    public double getTotalDurationInSeconds() { return totalDuration;       }
    public double getTotalDurationInTicks()   { return totalDuration / TPS; }
    public double getDurationValue()          { return durationValue;       }
    public double getDurationMultiplier()     { return durationMultiplier;  }
    
}
