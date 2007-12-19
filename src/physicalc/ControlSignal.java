package physicalc;

/** ControlSignal is an abstract base class for "exceptions" that are
 * used to signal changes in the control flow of a Physicalc program:
 * "break", "next", and "return" statements.
 *
 * This is an abuse of the Java exception mechanism, but it's the
 * easiest way to unwind the stack, since Java does not provide a
 * general-purpose condition system like Common Lisp.
 *
 * @author Stuart Sierra, ss2806@columbia.edu
 */
public abstract class ControlSignal extends RuntimeException {
}
