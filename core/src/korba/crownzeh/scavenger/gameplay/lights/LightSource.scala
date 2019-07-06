package korba.crownzeh.scavenger.gameplay.lights

import box2dLight.RayHandler
import com.badlogic.gdx.physics.box2d.Body

trait LightSource {

  def setLightUp(body: Body, rayHandler: RayHandler)

}
