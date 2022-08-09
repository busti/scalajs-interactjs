package org.scalajs

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

package object interactjs {
  lazy val interact: InteractJSStatic = js.Dynamic.global.interact.asInstanceOf[InteractJSStatic]

  @js.native
  @JSImport("interactjs", JSImport.Namespace)
  object InteractJsModule extends InteractJSStatic
}
