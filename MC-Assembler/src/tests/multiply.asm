	// Multiplication of pre-filled values in r1 and r2. This program expects pre-set user input, which
	// should be entered into the required registers using the user platform before the clock is enabled.
	//
	// Because multiplication is not natively supported by the ALU, a loop must be used. This program
	// does the following:
	//
	// int disp = 0;
	// int i = 0;
	// int a = input(r1);
	// int b = input(r2);
	// while (i != a) {
	//     disp = disp + b;
	//     i = i + 1;
	// }

	// Register allocation:
	//
	// r1: a input
	// r2: b input
	// r3: i
	// r4: displayed value (running sum)
	// r5: working register for compare


	nop

	// DEBUG, setting values for the simulator
	li r1, 10
	li r2, 13
	// end: DEBUG

	li r3, 0	// i = 0
	li r4, 0	// disp = 0

loop:
	// while (i != a)
	cmp r5, r3, r1
	bro r5, end
	nop

	// Loop contents
	add r4, r4, r2	// disp += b
	addi r3, r3, 1	// i += 1

	// Continue loop, check done at start of loop before the contents
	br loop
	nop
	

end:
	halt 0	// End the program
