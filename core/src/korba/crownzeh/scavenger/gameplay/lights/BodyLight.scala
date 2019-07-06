package korba.crownzeh.scavenger.gameplay.lights

import box2dLight.{PointLight, RayHandler}
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.physics.box2d.Body

class BodyLight(rayHandler: RayHandler, numberOfRays: Int, color: Color, strength: Float, distance: Int) {

  def attachToBody(body: Body): Body = {
    val pointLight = new PointLight(rayHandler, numberOfRays)
    pointLight.attachToBody(body)
    val lightColor: Color = color
    color.a = strength
    pointLight.setColor(lightColor)
    pointLight.setDistance(distance)
    pointLight.setContactFilter(0.toShort, 4.toShort, 4.toShort) //random values
    body
  }

}
