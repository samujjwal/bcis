This page is based on a physics problem here: http://zebu.uoregon.edu/~probs/mech/grav/Gravity2/Gravity2.html

Code (including standard library files, each file starts with a comment line):

(This is incomplete.)

```
# -- si/time.bcis --
def measure time
def unit second for time
def unit millisecond = 0.001 second
def unit minute = 60 second
def unit hour = 60 minute
def unit day = 24 hour
def unit year = 365 day


# -- si/mass.bcis -- 
def measure mass
def unit kilogram for mass
def unit gram = 0.001 kilogram


# -- si/distance.bcis --
def measure distance
alias length for distance
def unit meter for distance
def unit kilometer = 1000 meter
def unit centimeter = 0.01 meter
def unit millimeter = 0.001 millimeter


# -- si.bcis --
use si/mass
use si/time


# -- physics/gravity.bcis --
def measure gravitational_force like force

def constant universal_gravitation_constant = 6.67e-11 N * m^2 / kg^2

def equation Fg = G * m1 * m2 / r^2 where
    Fg = gravitational_force
    G = universal_gravitation_constant
    m1 = mass
    m2 = mass
    r = distance
done


# -- math.bcis --
def constant PI = 3.14159265


# -- sun_mass.bcis --
use "math"
use "si"
use "physics/gravity"


def measure angular_velocity

def measure rotations

def conversion 1 rotation = (2 * PI) radians

def equation w = r / t where
    w is angular_velocity
    r is rotations
    t is time
done
```