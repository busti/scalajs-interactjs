package org.scalajs.interactjs

import org.scalajs.dom._

import scala.scalajs.js
import js.|
import scala.collection.mutable

/**
  * The InteractEvent types are:
  * Draggable: dragstart, dragmove, draginertiastart, dragend
  * Resizable: resizestart, resizemove, resizeinertiastart, resizeend
  * Gesturable: gesturestart, gesturemove, gestureend
  *
  * To respond to InteractEvents, you must add listeners for the event types
  * either directly on an interactable or globally for all events of those types on the interact object.
  *
  * The event object that was created is passed to these functions as the first parameter.
  *
  * InteractEvent properties include the usual properties of mouse/touch events
  * such as pageX/Y, clientX/Y, modifier keys etc.
  * But also some properties providing information about the change in coordinates and event specific data.
  * The table below displays all of these events.
  */
@js.native
trait InteractEvent extends js.Object {

  /**
    * The element that is being interacted with
    */
  var target: Element = js.native

  /**
    * The Interactable that is being interacted with
    */
  var interactable: Element = js.native

  /**
    * The Interaction that the event belongs to
    */
  var interaction: js.Any = js.native

  /**
    * Page x coordinate of the starting event
    */
  var x0: Double = js.native

  /**
    * Page y coordinate of the starting event
    */
  var y0: Double = js.native

  /**
    * Client x coordinate of the starting event
    */
  var clientX0: Double = js.native

  /**
    * Client y coordinate of the starting event
    */
  var clientY0: Double = js.native

  /**
    * Change in coordinates of the mouse/touch
    */
  var dx: Double = js.native

  /**
    * Change in coordinates of the mouse/touch
    */
  var dy: Double = js.native

  /**
    * The Velocity of the pointer
    */
  var velocityX: Double = js.native

  /**
    * The Velocity of the pointer
    */
  var velocityY: Double = js.native

  /**
    * The speed of the pointer
    */
  var speed: Double = js.native

  /**
    * The time of creation of the event object
    */
  var timeStamp: Double = js.native
}

@js.native
trait PointerEvent extends js.Object {
  var pointerId: Double = js.native
  var pointerType: String = js.native
}

@js.native
trait DragEvent extends InteractEvent {

  /**
    * The dropzone this Interactable was dragged over
    */
  var dragEnter: js.Any = js.native

  /**
    * The Dropzone this Interactable was dragged out of
    */
  var dragLeave: Interactable = js.native
}

@js.native
trait ResizeEvent extends InteractEvent {

  /**
    * The axes the resizing is constrained to (x/y/xy)
    */
  var axes: String = js.native
}

@js.native
trait GestureEvent extends InteractEvent {

  /**
    * The distance between the event’s first two touches
    */
  var distance: Double = js.native

  /**
    * The angle of the line made by the two touches
    */
  var angle: Double = js.native

  /**
    * The change in angle since previous event
    */
  var da: Double = js.native

  /**
    * The ratio of the distance of the start event to the distance of the current event
    */
  var scale: Double = js.native

  /**
    * The change in scale since the previous event
    */
  var ds: Double = js.native

  /**
    * A box enclosing all touch points
    */
  var box: js.Any = js.native
}

/**
  * Dropzones can receive the following events: dropactivate, dropdeactivate, dragenter, dragleave, dropmove, drop.
  *
  * The dropzone events are plain objects with the following properties:
  */
@js.native
trait DropEvent extends js.Object {

  /**
    * The dropzone element
    */
  var target: Element = js.native

  /**
    * The dropzone Interactable
    */
  var dropzone: Interactable = js.native

  /**
    * The element that is being dragged
    */
  var relatedTarget: Element = js.native

  /**
    * The Interactable that is being dragged
    */
  var draggable: Interactable = js.native

  /**
    * The related drag event – drag{start,move,end}
    */
  var dragEvent: DragEvent = js.native

  /**
    * Time of the Event
    */
  var timeStamp: Double = js.native

  /**
    * The event type
    */
  var `type`: String = js.native
}

@js.native
trait Point extends js.Object {
  var x: Double = js.native
  var y: Double = js.native
}

@js.native
trait SnapTarget extends Point {
  var range: Double = js.native
}

@js.native
sealed trait SnapTargetGrid extends js.Any

@js.native
trait SnapGridOptions extends SnapTarget {
  var offset: Point = js.native
}

/**
 * Using the snap option while dragging,
 * The coordinates of the pointer that the drag event listeners receive
 * will be modified to meet the coordinates of the snap targets.
 * This option can also be used with resizable targets,
 * but may not yield intuitive results.
 * The snapSize option is specifically for snapping the dimensions of targets when resizing.
 * With both snap and snapSize, the snapping targets is an array of objects and functions.
 * There are more options listed below.
 */
@js.native
trait SnapOptions extends js.Object {
  /**
   * The elements of this array can be any combination of points and functions
   * which return points to snap to.
   * If there are multiple snap targets,
   * then the nearest snap target that is in range is used.
   *
   * POINT TARGETS
   * A snap target can be an object with x, y and range properties.
   * snapSize targets may have either width and height, or x and y.
   * If a target specifies a coordinate in only one axis then snapping will be on a line.
   *
   * If a target is defined as { y: 100, range Infinity } then the snapped movement will be horizontal at (100, pageX).
   *
   * FUNCTION TARGETS
   * If a snap target is a function, then it called and given the pageX and pageY coordinates of the event.
   * If the function returns an object, that function is used like a point target.
   *
   * SNAP GRIDS
   * Use the interact.createSnapGrid method to create a target that snaps to a grid.
   * The method takes an object describing a grid and returns a function
   * that snaps to the corners of that grid.
   *
   * The properties of the grid are:
   * x, y – the spacing between the horizontal and vertical grid lines.
   * range – (optional) the distance from the grid corners within which the pointer coords will be snapped.
   * offset – (optional) an object with x and y props to offset the grid lines
   */
  var targets: mutable.Seq[
    SnapTarget | js.Function2[Double, Double, SnapTarget] | SnapTargetGrid
  ] =
    js.native

  /**
   * The range of a snap target is the distance
   * the pointer must be from the target’s coordinates for a snap to be possible.
   *
   * i.e. inRange = distance <= range.
   *
   * If a snap target doesn’t have it’s own range, the default range is used.
   */
  var range: Double                         = js.native

  /**
   * If you want to specify for snap (not snapSize) the points on the element which snapping should be relative to,
   * then use an array of relativePoints.
   * Each item in the array should be an object with x and y properties
   * which are scalars specifying the position on the element to which snapping should be relative.
   * If no relativePoints array is specified or the array is empty
   * then snapping is relative to the pointer coordinates (default).
   *
   * There are effectively targets.length * max( relativePoints.length, 1 ) snap targets
   * while snap calculations are done.
   * Snap functions are called multiple times with the coordinates of each relativePoint.
   */
  var relatvePoints: mutable.Seq[Point] = js.native

  /**
   * A snap offsets lets you shift the coordinates of all snap targets
   * by the x and y properties of a given object or if the string 'startCoords' is given,
   * then pageX and pageY at the start of the action are used.
   */
  var offset: Point                     = js.native

  /**
   * The endOnly option is used to snap just before the end of a drag or resize.
   * Before the end event is fired, an extra <action>move event is snapped and fired.
   *
   * If inertia is enabled and endOnly is set to true
   * then the pointer will follow a curve to the snap target.
   */
  var endOnly: Boolean                      = js.native
}

@js.native
trait RelativeRect extends js.Object {
  var top: Double    = js.native
  var left: Double   = js.native
  var bottom: Double = js.native
  var right: Double  = js.native
}

@js.native
trait AbsoluteRect extends js.Object {
  var x: Double      = js.native
  var y: Double      = js.native
  var width: Double  = js.native
  var height: Double = js.native
}

/**
 * Drags, resizes and gestures can be restricted to a certain areas
 * using one of 3 variations of action restriction:
 * - pointer coordinate-based restrict
 * - element size-based restrictSize (resize only)
 * - and element edge-based restrictEdges (resize only)
 */
@js.native
trait RestrictOptions extends js.Object {
  /**
   * The restriction value specifies the area that the action will be confined to. The value can be:
   * - a rect object with top, left, bottom and right or x, y, width and height,
   * - an element whose dimensions will be used as the restriction area,
   * - a function which takes (x, y, element) and returns a rect or an element
   * one of these strings:
   *   'self' – restrict to the target element’s rect
   *   'parent' – restrict to the rect of the element’s parentNode or
   * a CSS selector string – if one of the parents of the target element matches this selector,
   * it’s rect will be used as the restriction area.
   */
  var restriction: RelativeRect | AbsoluteRect | js.Function3[
    Double,
    Double,
    Element,
    RelativeRect | AbsoluteRect | Element
  ] | String                    = js.native

  /**
   * With the restrict variant, restricting is by default relative to the pointer coordinates
   * so that the action coordinates, not the element’s dimensions,
   * will be kept within the restriction area.
   * The elementRect option changes this so that the element’s edges
   * are considered while dragging.
   *
   * For the left and right properties,
   * 0 means the left edge of the element and
   * 1 means the right edge. For top and bottom,
   * 0 means the top edge of the element and
   * 1 means the bottom.
   *
   * { top: 0.25, left: 0.25, bottom: 0.75, right: 0.75 }
   * would result in a quarter of the element being allowed
   * to hang over the restriction edges.
   */
  var elementRect: RelativeRect = js.native

  /**
   * The endOnly option is used to restrict just before the end of a drag or resize.
   * Before the end event is fired, an extra <action>move event is restricted and fired.
   *
   * If inertia is enabled and endOnly is set to true
   * then the pointer will follow a curve to the restricted coordinates.
   */
  var endOnly: Boolean          = js.native
}

/**
 * Drags, resizes and gestures can be restricted to a certain areas using one of 3
 * variations of action restriction:
 * - pointer coordinate-based restrict
 * - element size-based restrictSize (resize only)
 * - and element edge-based restrictEdges (resize only)
 */
@js.native
trait RestrictAble extends js.Object {
  var restrict: RestrictOptions      = js.native
  var restrictSize: RestrictOptions  = js.native
  var restrictEdges: RestrictOptions = js.native
}

/**
 * Inertia allows drag and resize actions to continue after the user releases the pointer
 * at a fast enough speed.
 * The required launch speed,
 * end speed and resistance can optionally be configured with the settings below.
 *
 * If an action ends without inertia but is snapped or restricted with the endOnly option,
 * then the the coordinates are interpolated from the end coords to the snapped/restricted coords.
 */
@js.native
trait InertiaOptions extends js.Object {

  /**
   * The resistance is a number greater than zero which sets the rate at which the action slows down.
   * Higher values slow it down more quickly.
   */
  var resistance: Double         = js.native

  /**
   * The minSpeed is a number which is the minimum launch speed (pixels per second) that can start inertia.
   */
  var minSpeed: Double           = js.native

  /**
   * endSpeed is the speed (pixels per second) at which the action is considered to have stopped.
   */
  var endSpeed: Double           = js.native

  /**
   * allowResume is a boolean value which indicates whether the user
   * should be allowed to resume an action while it is in the inertia phase.
   */
  var allowResume: Boolean       = js.native

  /**
   * zeroResumeDelta is a boolean which can allow the jump when resuming from inertia
   * to be ignored in the next move event’s dx/dy.
   * This should be set to true (default) when resizing with inertia or
   * dragging or panning elements around.
   * For things like drawing on a canvas it should generally be changed to false.
   */
  var zeroResumeDelta: Boolean   = js.native

  /**
   * smoothEndDuration is the duration (milliseconds) of the interpolated movement
   * from the actual end coords to the position that is snapped or restricted with endOnly.
   * Set the value to 0 to disable end transitions with endOnly snap/restrict.
   */
  var smoothEndDuration: Boolean = js.native
}

/**
 * Inertia allows drag and resize actions to continue after the user releases the pointer
 * at a fast enough speed.
 * The required launch speed,
 * end speed and resistance can optionally be configured with the settings below.
 *
 * If an action ends without inertia but is snapped or restricted with the endOnly option,
 * then the the coordinates are interpolated from the end coords to the snapped/restricted coords.
 */
@js.native
trait InertiaAble extends js.Object {
  var inertia: Boolean | InertiaOptions = js.native
}

/**
 * Scroll a container (window or an HTMLElement) when a drag or resize move happens at the edge of the container.
 */
@js.native
trait AutoScrollOptions extends js.Object {
  var container: Element = js.native
  var margin: Double     = js.native
  var distance: Double   = js.native
  var interval: Double   = js.native
}

/**
 * Scroll a container (window or an HTMLElement) when a drag or resize move happens at the edge of the container.
 */
@js.native
trait AutoScrollAble extends js.Object {
  var autoScroll: Boolean | AutoScrollOptions = js.native
}

@js.native
trait ActionOptions extends RestrictAble with InertiaAble {

  /**
    * Enable the action for the Interactable.
    * If the options object has no enabled property or the property value is true then the action is enabled.
    * If enabled is false, the action is disabled.
    */
  var enabled: Boolean = js.native

  /**
    * max is used to limit the number of concurrent interactions that can target an interactable.
    * By default, any number of interactions can target an interactable.
    */
  var max: Double = js.native

  /**
    * By default only 1 interaction can target the same interactable+element combination.
    * If you want to allow multiple interactions on the same target element,
    * set the maxPerElement property of your object to a value >= 2.
    */
  var maxPerElement: Double = js.native

  /**
    * If this is changed to true then drag,
    * resize and gesture actions will have to be started with a call to
    * Interaction#start as the usual down,move, <action>start… sequence will not start an action.
    */
  var manualStart: Boolean = js.native

  /**
    * The action will start after the pointer is held down for the given number of milliseconds.
    */
  var hold: Double = js.native

  /**
    * Change snapping settings for drag and resize. See docs/snapping.
    */
  var snap: Boolean | SnapOptions = js.native
}

@js.native
trait DraggableOptions
    extends ActionOptions
    with RestrictAble
    with InertiaAble
    with AutoScrollAble {

  /**
    * The axis in which the first movement must be in for the drag sequence to start.
    * After the movement in that axis, the the action can move in both the x and y axes.
    */
  var axis: String = js.native
}

@js.native
trait ResizeableOptions
    extends ActionOptions
    with RestrictAble
    with InertiaAble
    with AutoScrollAble {

  /**
    * If resize edges are used, resize events will have rect and deltaRect properties.
    * In resizestart, rect will be identical to the rect returned by
    * interactable.getRect(element) and deltaRect will have all-zero properties.
    * rect is updated on each resizemove and the values in deltaRect reflect the changes.
    *
    * If you’d like an element to behave as a resize corner, let it match the selectors of two adjacent edges.
    *
    * Resize handle elements must be children of the resizable element.
    * If you need the handles to be outside the target element, then you will need to use Interaction#start.
    */
  var edges: RelativeRect = js.native

  /**
    * Choose what should happen if the target would be resized to dimensions less than 0x0. The possible values are:
    * 'none' will limit the resize rect to a minimum of 0x0
    * 'negate' will allow the rect to have negative width/height
    * 'reposition' will keep the width/height positive by swapping the top and bottom edges and/or swapping the left and right edges
    */
  var invert: String = js.native

  /**
    * When resizing, change the width and height by the same amount.
    * This doesn’t necessarily maintain the aspect ratio of the object.
    */
  var squareResize: Boolean = js.native
}

@js.native
trait GesturableOptions extends ActionOptions with RestrictAble

@js.native
trait DropzoneOptions extends ActionOptions {

  /**
    * The CSS selector or element which must match the dragged element in order for drop events to be fired.
    */
  var accept: String = js.native

  /**
    * Set how drops are checked for. The allowed values are:
    * 'pointer' – the pointer must be over the dropzone (default)
    * 'center' – the draggable element’s center must be over the dropzone
    *
    *  a number from 0-1 which is the (intersection area) / (draggable area). e.g. 0.5
    *  for drop to happen when half of the area of the draggable is over the dropzone
    */
  var overlap: String | Double = js.native
}

@js.native
trait PointerEventOptions extends js.Object {
  var holdDuration: Double = js.native
  var ignoreFrom: String = js.native
  var allowFrom: String = js.native
  var origin: String = js.native
}

@js.native
trait InteractJSOptions extends js.Object {
  var context: Element = js.native
}

@js.native
trait Interactable extends js.Object {
  def draggable(options: DraggableOptions): Interactable
  def resizeable(options: ResizeableOptions): Interactable
  def gesturable(options: GesturableOptions): Interactable
  def pointerEvents(options: PointerEventOptions): Interactable

  def on(listener: js.Function1[PointerEvent, Unit])

  def createSnapGrid(options: SnapGridOptions): SnapTargetGrid
}

@js.native
trait InteractJSStatic extends js.Object {
  def apply(element: String): Interactable = js.native

  def apply(element: Element): Interactable = js.native

  def apply(element: String, options: InteractJSOptions): Interactable =
    js.native

  def apply(element: Element, options: InteractJSOptions): Interactable =
    js.native
}
