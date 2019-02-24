package org.scalajs

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

package object interactjs {
  lazy val interact: InteractStatic = js.Dynamic.global.interact.asInstanceOf[InteractStatic]

  @js.native
  @JSImport("interact", JSImport.Namespace)
  object InteractJsModule extends InteractStatic
}
