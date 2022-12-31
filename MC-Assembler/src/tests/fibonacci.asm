	// Fibonacci sequence to the 8-bit limit (first 13 terms, including 0)
	//
	// int disp = 0;
	// int a = 0;
	// int b = 0;
	// do {
	//     disp = a + b; // Temp for sum of previous two, and also display register
	//     a = b; // Shift forward 1 value in the sequence
	//     b = disp; // Sum of previous two
	// }
	// while (disp != 233);
	

	// Register allocation:
	//
	// r1: display value (sum of previous two values)
	// r2: a, first value
	// r3: b, second value
	// r4: working register for compare


	nop

	li r1, 0	// disp = 0
	li r2, 0	// a = 0
	li r3, 1	// b = 1

loop:
	add r1, r2, r3		// disp = a + b
	ori r2, r3, 0		// a = b
	ori r3, r1, 0		// b = disp (a_prev + b_prev)

	cmpi r4, r1, 233	// Is disp == 233?
	brz r4, loop		// Continue loop if disp != 233

	// Loop has ended at this point
	nop
	halt 0	// End the program
	nop
