package korba.crownzeh.scavenger.gameplay.world

import com.badlogic.gdx.physics.box2d._
import korba.crownzeh.scavenger.gameplay.player.PlayerAspect

class WorldContactListener(playerAspect: PlayerAspect) extends ContactListener{

  override def beginContact(contact: Contact): Unit = {}

  override def endContact(contact: Contact): Unit = {}

  override def preSolve(contact: Contact, oldManifold: Manifold): Unit = {}

  override def postSolve(contact: Contact, impulse: ContactImpulse): Unit = {}
}
