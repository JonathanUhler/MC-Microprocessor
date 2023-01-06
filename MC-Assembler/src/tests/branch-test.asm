	// Test/debugging program to test branching by doing the following:
	//
	// int a = 1;
	// int b = 2;
	// plus:
	//     a = a + b;
	// if (a == 3)
	//     goto add;
	//
	// if (a == 5)
	//     halt 0;
	// else
	//     halt 1;


	nop

	// Register allocation:
	//
	// r1: a
	// r2: b
	// r3: working register for compares
	
	li r1, 1	// a = 1
	nop
	nop
	li r2, 2	// b = 2
	nop
	nop

plus:
	add r1, r1, r2	// a = a + b
	nop
	nop

	cmpi r3, r1, 3	// r3 = (a == 3)
	nop
	nop
	bro r3, plus	// if (r3), then goto plus
	nop
	nop

	cmpi r3, r1, 5	// r3 = (a == 5)
	nop
	nop
	bro r3, good	// if (r3), then halt 0
	nop
	nop
	brz r3, bad		// if (!r3), then halt 1
	nop
	nop

good:
	halt 0
bad:
	halt 1


	// Expected ending:
	//
	// HALT == 0b00000000
	// RF[1] == 0b0000101
	// RF[2] == 0b0000010
	// RF[3] == 0b0000001
