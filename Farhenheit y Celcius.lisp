(DEFUN farhenheitCelcius (c) (* (- c 32) 0.5556))


(DEFUN FIBONACCI (N)
(COND ((= N 0) 1) ; n = 0?
((= N 1) 1) ; n = 1?
(T (+ (FIBONACCI (- N 1)) ; recursivo
(FIBONACCI (- N 2))))))


(print "Farhenheit a Celcius 15")
(write(farhenheitCelcius 15))

(print "")

(print "Fibonacci 5")
(write(FIBONACCI 5))






