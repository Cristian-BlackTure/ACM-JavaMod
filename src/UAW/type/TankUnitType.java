
package UAW.type;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.struct.ObjectSet;
import arc.util.Tmp;
import mindustry.content.StatusEffects;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.UnitType;

public class TankUnitType extends UnitType {
    public Color trackColor= Pal.darkMetal;
    public TankUnitType(String name) {
        super(name);
        immunities = ObjectSet.with(StatusEffects.disarmed, StatusEffects.slow, StatusEffects.freezing);
        flying = false;
        constructor = MechUnit::create;
        mechFrontSway = mechSideSway = 0f;
        mechStepShake = 0.3f;
        mechStepParticles = true;
        mechStride = -1f;
        canDrown = true;
    }

    @Override
    public void draw(Unit unit) {
        super.draw(unit);
        if(unit instanceof Mechc){
            drawTank(unit);
        }
    }
    public void drawTank(Unit unit){
        applyColor(unit);
        Draw.mixcol(Tmp.c1.set(trackColor).lerp(Color.white, Mathf.clamp(unit.hitTime)), Math.max(Math.max(0, 4 * mechStride), unit.hitTime));
        Draw.z(Layer.groundUnit - 0.1f);
        Draw.rect(region, unit.x, unit.y, unit.rotation - 90);
        Draw.reset();
    }

}

