package org.scalajs

import scala.scalajs.js
import scala.language.implicitConversions
import scala.scalajs.js.UndefOr

package object interactjs {
  val interact: InteractJSStatic = js.Dynamic.global.interact.asInstanceOf[InteractJSStatic]
}
