package UAW.type.units;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.geom.Vec2;
import arc.struct.Seq;
import arc.util.Tmp;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.UnitType;

public class JetUnitType extends UnitType {
	public static float trailX = 5f;
	public static float trailY = 0f;
	public static int trailLength = 6;
	public static float trailWidth = 4f;
	public static Seq<Vec2> trailPos = Seq.with(new Vec2(trailX, -trailY), new Vec2(-trailX, -trailY));
	public Color trailColor = Pal.bulletYellowBack;
	public Trail trailLeft = new Trail(trailLength);
	public Trail trailRight = new Trail(trailLength);
	public JetUnitType(String name) {
		super(name);
		engineSize = 0f;
		flying = true;
		omniMovement = false;
		lowAltitude = false;
		constructor = UnitEntity::create;
	}

	@Override
	public void update(Unit unit) {
		super.update(unit);
		// Code from Sh1penfire Stingray
		Tmp.v1.set(trailPos.get(0)).rotate(unit.rotation - 90);
		trailLeft.update(unit.x + Tmp.v1.x, unit.y + Tmp.v1.y);
		Tmp.v1.set(trailPos.get(1)).rotate(unit.rotation - 90);
		trailRight.update(unit.x + Tmp.v1.x, unit.y + Tmp.v1.y);
	}

	@Override
	public void draw(Unit unit) {
		super.draw(unit);
		Draw.z(Layer.effect);
		trailLeft.draw(trailColor, trailWidth);
		trailRight.draw(trailColor, trailWidth);
	}
}
