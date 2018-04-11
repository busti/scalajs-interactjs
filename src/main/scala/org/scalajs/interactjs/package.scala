package org.scalajs

import scala.scalajs.js

package object interactjs {
  val interact: InteractJSStatic = js.Dynamic.global.jQuery.asInstanceOf[InteractJSStatic]
}
