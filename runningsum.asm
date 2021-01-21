	// Small program to do the following:
	//
	// var sum = 0
	// for i in 1..20 {
	//    sum += i
	// }
	// if sum == 210 {
	//    halt 0
	//  } else {
	//    halt 1
	// }

	// There is a bug in the simulator that causes the first instruction to be stepped
	// on, so make it a nop

        nop

	// Register allocation:
	//
	// r1: sum
	// r2: i
	// r3, r4: working registers

	// .pragma imm dec
	li r1, 0		// var sum = 0
        li r2, 1		// var i = 1

loop:   add r1, r1, r2		// sum += i
        cmpi r3, r2, 20	// Is i == 20?
        brz r3, loop		// Branch back to the start of the loop if not
        addi r2, r2, 1		// Increment i in the delay slot

	// Here at the end of the loop. Check for expected value
        nop

        cmpi r3, r1, 210	// Is sum == 1+2+...+20 = 210?
        brz r3, bad		// Branch if not
        nop
        halt 0			// Halt 0 is the success code
        nop

	.loc 40
bad:    halt 1			// Halt 1 is the failure code
