Zachary Aman
Project 1 - Calculator

Online sources:
stack overflow
android dev site
general googling - I didn't pull any wholesale chunks of code from anywhere, just looking up specifications
except for adapting the Toast code from here: https://developer.android.com/guide/topics/ui/notifiers/toasts.html

Bells and whistles:
- multiple operators in statement - the way I handle display is not to display the full statement at a time (e.g. 12+3+5)
but to instead only show the numbers, e.g. 12 + 3 + 5 = displays 12, then 3, then 15 (automatically calculated on the second +)
and then 5 and then 20.
- screen supports select and copy functionality

