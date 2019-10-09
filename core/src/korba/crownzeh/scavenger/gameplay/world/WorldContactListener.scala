package korba.crownzeh.scavenger.gameplay.world

import com.badlogic.gdx.physics.box2d._
import korba.crownzeh.scavenger.config.Properties
import korba.crownzeh.scavenger.gameplay.enemy.Enemy
import korba.crownzeh.scavenger.gameplay.player.PlayerAspect

class WorldContactListener(playerAspect: PlayerAspect) extends ContactListener {


  override def beginContact(contact: Contact): Unit = {
    val fixtureA = contact.getFixtureA
    val fixtureB = contact.getFixtureB
    val collisionBits: Short = {fixtureA.getFilterData.categoryBits | fixtureB.getFilterData.categoryBits}.toShort
    val enemyWithInvisibleWall = {Properties.ENEMY_BIT | Properties.INVISIBLE_WALL_BIT}.toShort

    collisionBits match {
      case `enemyWithInvisibleWall` =>
        if (fixtureA.getFilterData.categoryBits == Properties.ENEMY_BIT) {
          fixtureA.getUserData.asInstanceOf[Enemy].flipEnemy()
        } else {
          fixtureB.getUserData.asInstanceOf[Enemy].flipEnemy()
        }
      case _ =>
    }
  }

  override def endContact(contact: Contact): Unit = {}

  override def preSolve(contact: Contact, oldManifold: Manifold): Unit = {}

  override def postSolve(contact: Contact, impulse: ContactImpulse): Unit = {}
}
