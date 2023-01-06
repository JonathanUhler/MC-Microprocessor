	// Test/debugging program to do the following:
	//
	// int a = 1;
	// int b = 2;
	// print(a + b);


	nop

	// Register allocation:
	//
	// r0: send result to GPU instead of writing
	// r1: a
	// r2: b

	li r1, 1	// a = 1
	nop
	nop
	li r2, 2	// b = 2
	nop
	nop

	add r0, r1, r2	// print(a + b)
	nop
	nop
	halt 0
	
