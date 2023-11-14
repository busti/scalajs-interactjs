# scalajs-interactjs
A scala.js facade against the interact.js library.

---

## Usage

Add the library to your project:
```scala
libraryDependencies += "com.github.busti" %%% "scalajs-interactjs" % "1.1.4"
```

Add interact.js to your javascript dependencies:
```scala
jsDependencies ++= Seq(
  "org.webjars.npm" % "interactjs" % "1.3.3" / "1.3.3/dist/interact.js"
)
```

Import the library in your code:
```scala
import org.scalajs.interactjs._
```

Initialize an interactable using an element, or selector string:  
```scala
interact("#myElement").draggable(???)

interact(element).resizeable(???)
```

All methods are fully documented using the official documentation.
