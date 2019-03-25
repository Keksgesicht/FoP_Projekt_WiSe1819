package dice3d.main;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import dice3d.math.Vector;
import dice3d.models.cuboids.Cuboid;
import dice3d.models.cuboids.Dice;

public class World {

	public static final double projectionDistance = 1000;
	public Vector gravity;

	public ArrayList<Cuboid> cuboids;
	public Cuboid floor;
	
	int val[] = new int[6];

	public World() {
		World w = this;
		gravity = new Vector(0, .3, 0);
		
		cuboids = new ArrayList<Cuboid>();
		
		floor =  new Cuboid(-1000, 400, -1000, 20000, 20000, 50);
		cuboids.add(floor);

		cuboids.add(new Dice(50, 310, 800, 60));
//		cuboids.add(new Dice(150, 310, 800, 60));
//		cuboids.add(new Dice(250, 310, 800, 60));

		long delayInMS = 500;  // start updating after 500ms
		long intervalInMS = 15; // update every 15ms
		
		for(Cuboid c : cuboids) c.reset();

		
		new Timer().scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				for ( Cuboid c : cuboids ) {
					if (  c == floor  ) continue;
					if (!c.notMoving()) c.update(w);
//					else {
//						val[((Dice) c).getNumberRolled()-1]++;
//						c.reset();
//						System.out.println("1: " + val[0]+ " , 2: " + val[1]+ " , 3: " + val[2]+ " , 4: " + val[3]+ " , 5: " + val[4]+ " , 6: " + val[5]);
//					}
					MainWindow.out.write(" position=" + c.vertices.get(3).position.toString() + " velocity=" + c.vertices.get(3).a + " number_rolled=" + ((Dice)c).getNumberRolled() + "\n");
					
				}
			}
		}, delayInMS, intervalInMS);
	}
}
