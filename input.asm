         nop 
         li r1, 0			// r1 is sum
         li r2, 1			// r2 is loop count
loop:    nop 
         nop 
         add r1, r1, r2
         nop 
         nop 
         cmpi r3, r2, 10		// is i == 10
         nop 
         nop 
         brz r3, loop		// branch if not 10
         addi r2, r2, 1			// and increment i in delay slot
         nop 
         nop 
         cmpi r3, r1, 55
         nop 
         nop 
         brz r3, bad
         nop 
         nop 
         nop 
         halt 1
         nop 
         nop 
         nop 
bad:     halt 2